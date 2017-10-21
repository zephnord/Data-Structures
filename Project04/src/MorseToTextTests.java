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
public class MorseToTextTests {

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
	      }
	      catch (Exception e) {}
	      Random r = new Random(5678);
	      for (int i = 0; i < globalSize; i++) {
	         globalInts[i] = r.nextInt();
	      }
	      globalText = makeText(globalInts);
	      globalMorse = makeMorse(globalInts);
	   }

	@Test
	public void test01_MorseOrderBST() {
		MorseToText morseToText = new MorseToText();
		BST<MorseOrder> morseOrderBST = morseToText.getBST();
		assertTrue(morseOrderBST.contains(new MorseOrder(null, ".-")));

	}

	////////////////////////////////////////////////////////////////////////////
	// MorseToText tests...
	@Test
	public void test15_verifyMorseToTextSuperClass() {
		assertTrue(MorseToText.class.getSuperclass() == Object.class);
	}

	@Test
	public void test16_verifyMorseToTextInterfaces() {
		Class[] faces = MorseToText.class.getInterfaces();
		assertTrue(faces.length == 1);
		assertTrue(faces[0] == BSTTranslator.class);
	}

	@Test(timeout = 50)
	public void test18_verifyMorseToTextTreeHeight() {
		MorseToText translator = new MorseToText();
		BST<MorseOrder> bst = translator.getBST();
		for (int i = 0; i < 1000000; i++)
			bst = translator.getBST();
		assertEquals(6, bst.treeHeight());
		assertEquals(366, bst.internalPathLength());
	}

	@Test
	public void test19_verifyMorseToTextBasic() {
		MorseToText translator = new MorseToText();

		for (int i = 1; i < MorseCode.size(); i++) {
			String text = translator.translate(MorseCode.get(i).getCode());
			String expected = "" + MorseCode.get(i).getCharacter();
			assertEquals(expected, text);
		}
	}

	@Test
	public void test20_verifyMorseToTextAdvanced() {
		MorseToText translator = new MorseToText();
		String in = "-. --- .-- ---- .. ... ---- - .... . ---- - .. -- . ---- ..-. --- .-. ---- .- .-.. .-.. ---- --. --- --- -.. ---- -- . -. ---- - --- ---- -.-. --- -- . ---- - --- ---- - .... . ---- .- .. -.. ---- --- ..-. ---- - .... . .. .-. ---- -.-. --- ..- -. - .-. -.--";
		String expected = "now is the time for all good men to come to the aid of their country";
		String out = translator.translate(in);
		assertEquals(expected, out);
	}

	@Test
	public void test21_verifyMorseToTextRandomBig() {
		int[] array = makeRandomArray(100000, 1234);
		String input = makeMorse(array);
		String expect = makeText(array);
		MorseToText translator = new MorseToText();
		String actual = translator.translate(input);

		assertEquals(expect, actual);
	}

	@Test(timeout = 8000)
	public void test22_verifyMorseToTextBigOh() {
		MorseToText translator = new MorseToText();

		for (int i = 0; i < 2; i++) {
			translator.translate(globalMorse);
		}
	}
	
	@Test
	public void test_BuggyNoSpaces() {
		MorseToText translator = new MorseToText();
		String string = ".- ---- ....-...";
		String actual = translator.translate(string);
		assertTrue("a B".equals(actual));
		assertFalse("a  B".equals(actual));
	}

	private int[] makeRandomArray(int size, int seed) {
		int[] array = new int[size];
		Random rand = new Random(seed);

		for (int i = 0; i < size; i++) {
			array[i] = rand.nextInt();
		}

		return array;
	}

	private static String makeText(int[] indexes) {
		StringBuilder sb = new StringBuilder(indexes.length);

		for (int i = 0; i < indexes.length; i++) {
			sb.append(MorseCode.get(Math.abs(indexes[i]) % MorseCode.size()).getCharacter());
		}

		return sb.toString().trim();
	}

	private static String makeMorse(int[] indexes) {
		StringBuilder sb = new StringBuilder(indexes.length);

		for (int i = 0; i < indexes.length; i++) {
			sb.append(MorseCode.get(Math.abs(indexes[i]) % MorseCode.size()).getCode());
			sb.append(" ");
		}

		return sb.toString().trim();
	}
}
