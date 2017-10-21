import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Zeph Nord
 * @version Project04
 * @version Date
 * @version Winter 2017
 */
public class TextToMorseTests {

	private static final long t = 1492580862000L;
	private static PrintWriter testSummaryFile;
	private static int globalSize = 1000000;
	private static int[] globalInts = new int[globalSize];
	private String t1 = getClass().getName() + ".java";
	private String t2 = getClass().getName() + ".class";
	private static String globalText, globalMorse;

	@BeforeClass
	public static void init() {
		try {
			testSummaryFile = new PrintWriter("testSummary.txt");
		} catch (Exception e) {
		}
		Random r = new Random(5678);
		for (int i = 0; i < globalSize; i++) {
			globalInts[i] = r.nextInt();
		}
		globalText = makeText(globalInts);
		globalMorse = makeMorse(globalInts);
	}

	@Test
	public void test25_verifyTextToMorseBasic() {
		TextToMorse translator = new TextToMorse();

		for (int i = 0; i < MorseCode.size(); i++) {
			String text = "" + MorseCode.get(i).getCharacter();
			String morse = translator.translate(text);
			assertEquals(MorseCode.get(i).getCode(), morse);
		}
	}

	////////////////////////////////////////////////////////////////////////////
	// TextToMorse tests...
	@Test
	public void test23_verifyTextToMorseSuperClass() {
		assertTrue(TextToMorse.class.getSuperclass() == Object.class);
	}

	@Test
	public void test24_verifyTextToMorseInterfaces() {
		Class[] faces = TextToMorse.class.getInterfaces();
		assertTrue(faces.length == 1);
		assertTrue(faces[0] == BSTTranslator.class);
	}


	@Test(timeout = 20)
	public void test26_verifyTextToMorseTreeHeight() {
		TextToMorse translator = new TextToMorse();
		BST<CharacterOrder> bst = translator.getBST();
		for (int i = 0; i < 1000000; i++)
			bst = translator.getBST();
		assertEquals(6, bst.treeHeight());
		assertEquals(366, bst.internalPathLength());
	}

	@Test
	public void test27_verifyTextToMorseBasic() {
		TextToMorse translator = new TextToMorse();

		for (int i = 0; i < MorseCode.size(); i++) {
			String text = "" + MorseCode.get(i).getCharacter();
			String morse = translator.translate(text);
			assertTrue(morse.equals(MorseCode.get(i).getCode()));
		}
	}

	@Test
	public void test28_verifyTextToMorseAdvanced() {
		TextToMorse translator = new TextToMorse();
		String in = "now is the time for all good men to come to the aid of their country";
		String expected = "-. --- .-- ---- .. ... ---- - .... . ---- - .. -- . ---- ..-. --- .-. ---- .- .-.. .-.. ---- --. --- --- -.. ---- -- . -. ---- - --- ---- -.-. --- -- . ---- - --- ---- - .... . ---- .- .. -.. ---- --- ..-. ---- - .... . .. .-. ---- -.-. --- ..- -. - .-. -.--";

		String out = translator.translate(in);
		assertTrue(out.equals(expected));
	}

	@Test
	public void test29_verifyTextToMorseRandomBig() {
		int[] array = makeRandomArray(100000, 1234);
		String input = makeText(array);
		String expect = makeMorse(array);
		TextToMorse translator = new TextToMorse();
		String actual = translator.translate(input);

		assertEquals(actual, expect);
	}

	@Test(timeout = 5000)
	public void test30_verifyTextToMorseBigOh() {
		TextToMorse translator = new TextToMorse();

		for (int i = 0; i < 10; i++) {
			translator.translate(globalText);
		}
	}

	@Test
	public void test31_MorseToTextSpacing1() {
		String s = ".- ---- -... ---- ---- ---- ---- ---- ---- ---- ---- ---- -.-.";
		String expected = "a b         c";
		MorseToText t = new MorseToText();
		assertEquals(expected, t.translate(s));
	}

	@Test
	public void test32_MorseToTextSpacing2() {
		String s = "---- ---- .- ---- -... ---- ---- ---- ---- ---- ---- ---- ---- ---- -.-. ---- ----";
		String expected = "a b         c";
		MorseToText t = new MorseToText();
		assertEquals(expected, t.translate(s));
	}

	@Test
	public void test33_TextToMorseIgnoreUntranslatable() {
		TextToMorse translator = new TextToMorse();
		String in = "String with some untranslatable characters: !@#$%^&*()_+-=[]\\{}|;':\",./<>?ABCabc";
		String expected = "....... - .-. .. -. --. ---- .-- .. - .... ---- ... --- -- . ---- ..- -. - .-. .- -. ... .-.. .- - .- -... .-.. . ---- -.-. .... .- .-. .- -.-. - . .-. ... ....---... ---- ....-.-.-- ....-..-.- .......-..- .....-... ....-.--. ....-.--.- ......--.- .....-.-. ....-....- ....-...- ....-.-.-. .....----. ....---... .....-..-. ....--..-- .....-.-.- ....-..-. ......--.. .....- ....-... ....-.-. .- -... -.-.";

		String out = translator.translate(in);

		assertEquals(expected, out);
	}

	@Test
	public void test34_MorseToTextIgnoreUntranslatable() {
		MorseToText t = new MorseToText();
		String in = ".....- ....-... .......... ---------- .-.-.-.-.- ....-.-. .- -... -.-.";
		String expected = "ABCabc";

		String out = t.translate(in);

		assertEquals(expected, out);
	}
	
	@Test
	public void test_BuggyNoPeriods() {
		TextToMorse translator = new TextToMorse();
		String string = "Hello. This is a test.";
		String actual = translator.translate(string);
		
		assertEquals("........ . .-.. .-.. --- .....-.-.- ---- ....- .... .. ... ---- .. ... ---- .- ---- - . ... - .....-.-.-", (actual));
		assertFalse("........ . .-.. .-.. --- ---- ....- .... .. ... ---- .. ... ---- .- ---- - . ... -".equals(actual));
		
	}
	
	   private int[] makeRandomArray(int size, int seed)
	   {
	      int[] array = new int[size];
	      Random rand = new Random(seed);

	      for (int i = 0; i < size; i++)
	      {
	         array[i] = rand.nextInt();
	      }

	      return array;
	   }
	   
	   private static String makeText(int[] indexes)
	   {
	      StringBuilder sb = new StringBuilder(indexes.length);

	      for (int i = 0; i < indexes.length; i++)
	      {
	         sb.append(MorseCode.get(Math.abs(indexes[i]) % MorseCode.size()).getCharacter());
	      }

	      return sb.toString().trim();
	   }

	   private static String makeMorse(int[] indexes)
	   {
	      StringBuilder sb = new StringBuilder(indexes.length);

	      for (int i = 0; i < indexes.length; i++)
	      {
	         sb.append(MorseCode.get(Math.abs(indexes[i]) % MorseCode.size()).getCode());
	         sb.append(" ");
	      }

	      return sb.toString().trim();
	   }

}
