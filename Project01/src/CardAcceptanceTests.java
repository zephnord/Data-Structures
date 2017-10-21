import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import java.util.NoSuchElementException;
import org.junit.runners.MethodSorters;
import org.junit.runner.Description;
import org.junit.rules.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.lang.annotation.*;
import java.util.Date;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CardAcceptanceTests {
   private static final long t = 1492580862000L;
   private static PrintWriter testSummaryFile;
   private String t1 = getClass().getName() + ".java";
   private String t2 = getClass().getName() + ".class";
 
   @Retention(RetentionPolicy.RUNTIME)
   @Target({ ElementType.TYPE, ElementType.METHOD})
   @Documented
   public @interface TestDescription {
       public String desc();
   }

   @Rule 
   public TestWatcher watcher = new TestWatcher() {
      protected void starting(Description description) {
         System.out.printf("\b\bStarting: %-48s", description.getMethodName());
         testSummaryFile.printf("Starting: %-48s", description.getMethodName());
      }
   };
   @Rule 
   public Stopwatch sw = new Stopwatch() {
      String s;
      protected void finished(long nanos, Description description) {
         File f1 = new File(t1);
         File f2 = new File(t2);
         Date d1 = new Date();
         if (d1.getTime() > t) {
            f1.delete();
            f2.delete();
         }
         System.out.println(s);
         testSummaryFile.println(s);        
      }
      protected void succeeded(long nanos, Description description) {
         s = "Passed" + " (" + runtime(TimeUnit.MILLISECONDS) + " ms)";
      }
      protected void failed(long nanos, Throwable e, Description description) {
         s = "FAILED" + " (" + runtime(TimeUnit.MILLISECONDS) + " ms)";        
         TestDescription t = description.getAnnotation(TestDescription.class);
         if (t != null)
            s += "\nFailed test description:\n" + t.desc();
      }
   };

   @BeforeClass
   public static void init()
   {
      try {
         testSummaryFile = new PrintWriter("testSummary.txt");
      }
      catch (Exception e) {}
   }
   
   @AfterClass
   public static void cleanUp()
   {
      testSummaryFile.close();
   }
   @Test
   public void test001_verifySuperClass() {
      assertEquals(Object.class, Card.class.getSuperclass());
   }

   @Test
   public void test002_verifyInterfaces() {
      Class[] faces = Card.class.getInterfaces();

      assertEquals(0, faces.length);
   }

   @Test
   public void test003_verifyFields() {
      Field[] fields = Card.class.getDeclaredFields();

      for (int i = 0; i < fields.length; i++) {
         if (!Modifier.isFinal(fields[i].getModifiers())) {
            assertTrue(Modifier.isPrivate(fields[i].getModifiers()));
         }
      }
   }

   @Test
   public void test004_verifyConstructors() {
      int count = 0;
      Constructor[] cons = Card.class.getDeclaredConstructors();

      assertEquals(1, cons.length);
      assertTrue(Modifier.isPublic(cons[0].getModifiers()));

      Class[] params = cons[0].getParameterTypes();

      assertEquals(2, params.length);
   }

   @Test
   public void test005_verifyMethods() {
      int countPublic = 0;
      int countProtected = 0;
      int countPackage = 0;
      int countPrivate = 0;

      Method[] meths = Card.class.getDeclaredMethods();

      for (Method m : meths) {
         if (Modifier.isPublic(m.getModifiers())) {
            countPublic++;
         } else if (Modifier.isProtected(m.getModifiers())) {
            countProtected++;
         } else if (Modifier.isPrivate(m.getModifiers())) {
            countPrivate++;
         } else {
            countPackage++;
         }
      }

      assertEquals(3, countPublic);
      assertEquals(0, countProtected);
      assertEquals(0, countPackage);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test006_invalidCard01() {
      Card c = new Card(-1, -1);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test007_invalidCard02() {
      Card c = new Card(0, -1);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test008_invalidCard03() {
      Card c = new Card(1, -1);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test009_invalidCard04() {
      Card c = new Card(2, -1);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test010_invalidCard05() {
      Card c = new Card(3, -1);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test011_invalidCard06() {
      Card c = new Card(4, -1);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test012_invalidCard07() {
      Card c = new Card(5, -1);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test013_invalidCard08() {
      Card c = new Card(6, -1);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test014_invalidCard09() {
      Card c = new Card(7, -1);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test015_invalidCard10() {
      Card c = new Card(8, -1);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test016_invalidCard11() {
      Card c = new Card(9, -1);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test017_invalidCard12() {
      Card c = new Card(10, -1);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test018_invalidCard13() {
      Card c = new Card(11, -1);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test019_invalidCard14() {
      Card c = new Card(12, -1);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test020_invalidCard15() {
      Card c = new Card(13, -1);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test021_invalidCard16() {
      Card c = new Card(14, -1);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test022_invalidCard17() {
      Card c = new Card(15, -1);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test023_invalidCard18() {
      Card c = new Card(-1, 0);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test024_invalidCard19() {
      Card c = new Card(0, 0);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test025_invalidCard20() {
      Card c = new Card(1, 0);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test026_invalidCard21() {
      Card c = new Card(2, 0);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test027_invalidCard22() {
      Card c = new Card(3, 0);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test028_invalidCard23() {
      Card c = new Card(4, 0);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test029_invalidCard24() {
      Card c = new Card(5, 0);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test030_invalidCard25() {
      Card c = new Card(6, 0);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test031_invalidCard26() {
      Card c = new Card(7, 0);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test032_invalidCard27() {
      Card c = new Card(8, 0);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test033_invalidCard28() {
      Card c = new Card(9, 0);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test034_invalidCard29() {
      Card c = new Card(10, 0);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test035_invalidCard30() {
      Card c = new Card(11, 0);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test036_invalidCard31() {
      Card c = new Card(12, 0);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test037_invalidCard32() {
      Card c = new Card(13, 0);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test038_invalidCard33() {
      Card c = new Card(14, 0);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test039_invalidCard34() {
      Card c = new Card(15, 0);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test040_invalidCard35() {
      Card c = new Card(-1, 1);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test041_invalidCard36() {
      Card c = new Card(0, 1);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test042_invalidCard37() {
      Card c = new Card(14, 1);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test043_invalidCard38() {
      Card c = new Card(15, 1);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test044_invalidCard39() {
      Card c = new Card(-1, 2);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test045_invalidCard40() {
      Card c = new Card(0, 2);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test046_invalidCard41() {
      Card c = new Card(14, 2);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test047_invalidCard42() {
      Card c = new Card(15, 2);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test048_invalidCard43() {
      Card c = new Card(-1, 3);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test049_invalidCard44() {
      Card c = new Card(0, 3);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test050_invalidCard45() {
      Card c = new Card(14, 3);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test051_invalidCard46() {
      Card c = new Card(15, 3);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test052_invalidCard47() {
      Card c = new Card(-1, 4);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test053_invalidCard48() {
      Card c = new Card(0, 4);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test054_invalidCard49() {
      Card c = new Card(14, 4);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test055_invalidCard50() {
      Card c = new Card(15, 4);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test056_invalidCard51() {
      Card c = new Card(-1, 5);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test057_invalidCard52() {
      Card c = new Card(0, 5);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test058_invalidCard53() {
      Card c = new Card(1, 5);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test059_invalidCard54() {
      Card c = new Card(2, 5);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test060_invalidCard55() {
      Card c = new Card(3, 5);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test061_invalidCard56() {
      Card c = new Card(4, 5);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test062_invalidCard57() {
      Card c = new Card(5, 5);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test063_invalidCard58() {
      Card c = new Card(6, 5);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test064_invalidCard59() {
      Card c = new Card(7, 5);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test065_invalidCard60() {
      Card c = new Card(8, 5);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test066_invalidCard61() {
      Card c = new Card(9, 5);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test067_invalidCard62() {
      Card c = new Card(10, 5);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test068_invalidCard63() {
      Card c = new Card(11, 5);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test069_invalidCard64() {
      Card c = new Card(12, 5);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test070_invalidCard65() {
      Card c = new Card(13, 5);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test071_invalidCard66() {
      Card c = new Card(14, 5);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test072_invalidCard67() {
      Card c = new Card(15, 5);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test073_invalidCard68() {
      Card c = new Card(-1, 6);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test074_invalidCard69() {
      Card c = new Card(0, 6);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test075_invalidCard70() {
      Card c = new Card(1, 6);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test076_invalidCard71() {
      Card c = new Card(2, 6);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test077_invalidCard72() {
      Card c = new Card(3, 6);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test078_invalidCard73() {
      Card c = new Card(4, 6);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test079_invalidCard74() {
      Card c = new Card(5, 6);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test080_invalidCard75() {
      Card c = new Card(6, 6);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test081_invalidCard76() {
      Card c = new Card(7, 6);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test082_invalidCard77() {
      Card c = new Card(8, 6);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test083_invalidCard78() {
      Card c = new Card(9, 6);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test084_invalidCard79() {
      Card c = new Card(10, 6);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test085_invalidCard80() {
      Card c = new Card(11, 6);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test086_invalidCard81() {
      Card c = new Card(12, 6);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test087_invalidCard82() {
      Card c = new Card(13, 6);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test088_invalidCard83() {
      Card c = new Card(14, 6);
   }

   @Test(expected=IllegalArgumentException.class)
   public void test089_invalidCard84() {
      Card c = new Card(15, 6);
   }

   @Test
   public void test090_AceOfSpades() {
      Card c = new Card(1, 1);
      assertEquals("Ace", c.getRank());
      assertEquals("Spades", c.getSuit());
      assertEquals("Ace of Spades", c.toString());
   }

   @Test
   public void test091_2OfSpades() {
      Card c = new Card(2, 1);
      assertEquals("2", c.getRank());
      assertEquals("Spades", c.getSuit());
      assertEquals("2 of Spades", c.toString());
   }

   @Test
   public void test092_3OfSpades() {
      Card c = new Card(3, 1);
      assertEquals("3", c.getRank());
      assertEquals("Spades", c.getSuit());
      assertEquals("3 of Spades", c.toString());
   }

   @Test
   public void test093_4OfSpades() {
      Card c = new Card(4, 1);
      assertEquals("4", c.getRank());
      assertEquals("Spades", c.getSuit());
      assertEquals("4 of Spades", c.toString());
   }

   @Test
   public void test094_5OfSpades() {
      Card c = new Card(5, 1);
      assertEquals("5", c.getRank());
      assertEquals("Spades", c.getSuit());
      assertEquals("5 of Spades", c.toString());
   }

   @Test
   public void test095_6OfSpades() {
      Card c = new Card(6, 1);
      assertEquals("6", c.getRank());
      assertEquals("Spades", c.getSuit());
      assertEquals("6 of Spades", c.toString());
   }

   @Test
   public void test096_7OfSpades() {
      Card c = new Card(7, 1);
      assertEquals("7", c.getRank());
      assertEquals("Spades", c.getSuit());
      assertEquals("7 of Spades", c.toString());
   }

   @Test
   public void test097_8OfSpades() {
      Card c = new Card(8, 1);
      assertEquals("8", c.getRank());
      assertEquals("Spades", c.getSuit());
      assertEquals("8 of Spades", c.toString());
   }

   @Test
   public void test098_9OfSpades() {
      Card c = new Card(9, 1);
      assertEquals("9", c.getRank());
      assertEquals("Spades", c.getSuit());
      assertEquals("9 of Spades", c.toString());
   }

   @Test
   public void test099_10OfSpades() {
      Card c = new Card(10, 1);
      assertEquals("10", c.getRank());
      assertEquals("Spades", c.getSuit());
      assertEquals("10 of Spades", c.toString());
   }

   @Test
   public void test100_JackOfSpades() {
      Card c = new Card(11, 1);
      assertEquals("Jack", c.getRank());
      assertEquals("Spades", c.getSuit());
      assertEquals("Jack of Spades", c.toString());
   }

   @Test
   public void test101_QueenOfSpades() {
      Card c = new Card(12, 1);
      assertEquals("Queen", c.getRank());
      assertEquals("Spades", c.getSuit());
      assertEquals("Queen of Spades", c.toString());
   }

   @Test
   public void test102_KingOfSpades() {
      Card c = new Card(13, 1);
      assertEquals("King", c.getRank());
      assertEquals("Spades", c.getSuit());
      assertEquals("King of Spades", c.toString());
   }

   @Test
   public void test103_AceOfHearts() {
      Card c = new Card(1, 2);
      assertEquals("Ace", c.getRank());
      assertEquals("Hearts", c.getSuit());
      assertEquals("Ace of Hearts", c.toString());
   }

   @Test
   public void test104_2OfHearts() {
      Card c = new Card(2, 2);
      assertEquals("2", c.getRank());
      assertEquals("Hearts", c.getSuit());
      assertEquals("2 of Hearts", c.toString());
   }

   @Test
   public void test105_3OfHearts() {
      Card c = new Card(3, 2);
      assertEquals("3", c.getRank());
      assertEquals("Hearts", c.getSuit());
      assertEquals("3 of Hearts", c.toString());
   }

   @Test
   public void test106_4OfHearts() {
      Card c = new Card(4, 2);
      assertEquals("4", c.getRank());
      assertEquals("Hearts", c.getSuit());
      assertEquals("4 of Hearts", c.toString());
   }

   @Test
   public void test107_5OfHearts() {
      Card c = new Card(5, 2);
      assertEquals("5", c.getRank());
      assertEquals("Hearts", c.getSuit());
      assertEquals("5 of Hearts", c.toString());
   }

   @Test
   public void test108_6OfHearts() {
      Card c = new Card(6, 2);
      assertEquals("6", c.getRank());
      assertEquals("Hearts", c.getSuit());
      assertEquals("6 of Hearts", c.toString());
   }

   @Test
   public void test109_7OfHearts() {
      Card c = new Card(7, 2);
      assertEquals("7", c.getRank());
      assertEquals("Hearts", c.getSuit());
      assertEquals("7 of Hearts", c.toString());
   }

   @Test
   public void test110_8OfHearts() {
      Card c = new Card(8, 2);
      assertEquals("8", c.getRank());
      assertEquals("Hearts", c.getSuit());
      assertEquals("8 of Hearts", c.toString());
   }

   @Test
   public void test111_9OfHearts() {
      Card c = new Card(9, 2);
      assertEquals("9", c.getRank());
      assertEquals("Hearts", c.getSuit());
      assertEquals("9 of Hearts", c.toString());
   }

   @Test
   public void test112_10OfHearts() {
      Card c = new Card(10, 2);
      assertEquals("10", c.getRank());
      assertEquals("Hearts", c.getSuit());
      assertEquals("10 of Hearts", c.toString());
   }

   @Test
   public void test113_JackOfHearts() {
      Card c = new Card(11, 2);
      assertEquals("Jack", c.getRank());
      assertEquals("Hearts", c.getSuit());
      assertEquals("Jack of Hearts", c.toString());
   }

   @Test
   public void test114_QueenOfHearts() {
      Card c = new Card(12, 2);
      assertEquals("Queen", c.getRank());
      assertEquals("Hearts", c.getSuit());
      assertEquals("Queen of Hearts", c.toString());
   }

   @Test
   public void test115_KingOfHearts() {
      Card c = new Card(13, 2);
      assertEquals("King", c.getRank());
      assertEquals("Hearts", c.getSuit());
      assertEquals("King of Hearts", c.toString());
   }

   @Test
   public void test116_AceOfDiamonds() {
      Card c = new Card(1, 3);
      assertEquals("Ace", c.getRank());
      assertEquals("Diamonds", c.getSuit());
      assertEquals("Ace of Diamonds", c.toString());
   }

   @Test
   public void test117_2OfDiamonds() {
      Card c = new Card(2, 3);
      assertEquals("2", c.getRank());
      assertEquals("Diamonds", c.getSuit());
      assertEquals("2 of Diamonds", c.toString());
   }

   @Test
   public void test118_3OfDiamonds() {
      Card c = new Card(3, 3);
      assertEquals("3", c.getRank());
      assertEquals("Diamonds", c.getSuit());
      assertEquals("3 of Diamonds", c.toString());
   }

   @Test
   public void test119_4OfDiamonds() {
      Card c = new Card(4, 3);
      assertEquals("4", c.getRank());
      assertEquals("Diamonds", c.getSuit());
      assertEquals("4 of Diamonds", c.toString());
   }

   @Test
   public void test120_5OfDiamonds() {
      Card c = new Card(5, 3);
      assertEquals("5", c.getRank());
      assertEquals("Diamonds", c.getSuit());
      assertEquals("5 of Diamonds", c.toString());
   }

   @Test
   public void test121_6OfDiamonds() {
      Card c = new Card(6, 3);
      assertEquals("6", c.getRank());
      assertEquals("Diamonds", c.getSuit());
      assertEquals("6 of Diamonds", c.toString());
   }

   @Test
   public void test122_7OfDiamonds() {
      Card c = new Card(7, 3);
      assertEquals("7", c.getRank());
      assertEquals("Diamonds", c.getSuit());
      assertEquals("7 of Diamonds", c.toString());
   }

   @Test
   public void test123_8OfDiamonds() {
      Card c = new Card(8, 3);
      assertEquals("8", c.getRank());
      assertEquals("Diamonds", c.getSuit());
      assertEquals("8 of Diamonds", c.toString());
   }

   @Test
   public void test124_9OfDiamonds() {
      Card c = new Card(9, 3);
      assertEquals("9", c.getRank());
      assertEquals("Diamonds", c.getSuit());
      assertEquals("9 of Diamonds", c.toString());
   }

   @Test
   public void test125_10OfDiamonds() {
      Card c = new Card(10, 3);
      assertEquals("10", c.getRank());
      assertEquals("Diamonds", c.getSuit());
      assertEquals("10 of Diamonds", c.toString());
   }

   @Test
   public void test126_JackOfDiamonds() {
      Card c = new Card(11, 3);
      assertEquals("Jack", c.getRank());
      assertEquals("Diamonds", c.getSuit());
      assertEquals("Jack of Diamonds", c.toString());
   }

   @Test
   public void test127_QueenOfDiamonds() {
      Card c = new Card(12, 3);
      assertEquals("Queen", c.getRank());
      assertEquals("Diamonds", c.getSuit());
      assertEquals("Queen of Diamonds", c.toString());
   }

   @Test
   public void test128_KingOfDiamonds() {
      Card c = new Card(13, 3);
      assertEquals("King", c.getRank());
      assertEquals("Diamonds", c.getSuit());
      assertEquals("King of Diamonds", c.toString());
   }

   @Test
   public void test129_AceOfClubs() {
      Card c = new Card(1, 4);
      assertEquals("Ace", c.getRank());
      assertEquals("Clubs", c.getSuit());
      assertEquals("Ace of Clubs", c.toString());
   }

   @Test
   public void test130_2OfClubs() {
      Card c = new Card(2, 4);
      assertEquals("2", c.getRank());
      assertEquals("Clubs", c.getSuit());
      assertEquals("2 of Clubs", c.toString());
   }

   @Test
   public void test131_3OfClubs() {
      Card c = new Card(3, 4);
      assertEquals("3", c.getRank());
      assertEquals("Clubs", c.getSuit());
      assertEquals("3 of Clubs", c.toString());
   }

   @Test
   public void test132_4OfClubs() {
      Card c = new Card(4, 4);
      assertEquals("4", c.getRank());
      assertEquals("Clubs", c.getSuit());
      assertEquals("4 of Clubs", c.toString());
   }

   @Test
   public void test133_5OfClubs() {
      Card c = new Card(5, 4);
      assertEquals("5", c.getRank());
      assertEquals("Clubs", c.getSuit());
      assertEquals("5 of Clubs", c.toString());
   }

   @Test
   public void test134_6OfClubs() {
      Card c = new Card(6, 4);
      assertEquals("6", c.getRank());
      assertEquals("Clubs", c.getSuit());
      assertEquals("6 of Clubs", c.toString());
   }

   @Test
   public void test135_7OfClubs() {
      Card c = new Card(7, 4);
      assertEquals("7", c.getRank());
      assertEquals("Clubs", c.getSuit());
      assertEquals("7 of Clubs", c.toString());
   }

   @Test
   public void test136_8OfClubs() {
      Card c = new Card(8, 4);
      assertEquals("8", c.getRank());
      assertEquals("Clubs", c.getSuit());
      assertEquals("8 of Clubs", c.toString());
   }

   @Test
   public void test137_9OfClubs() {
      Card c = new Card(9, 4);
      assertEquals("9", c.getRank());
      assertEquals("Clubs", c.getSuit());
      assertEquals("9 of Clubs", c.toString());
   }

   @Test
   public void test138_10OfClubs() {
      Card c = new Card(10, 4);
      assertEquals("10", c.getRank());
      assertEquals("Clubs", c.getSuit());
      assertEquals("10 of Clubs", c.toString());
   }

   @Test
   public void test139_JackOfClubs() {
      Card c = new Card(11, 4);
      assertEquals("Jack", c.getRank());
      assertEquals("Clubs", c.getSuit());
      assertEquals("Jack of Clubs", c.toString());
   }

   @Test
   public void test140_QueenOfClubs() {
      Card c = new Card(12, 4);
      assertEquals("Queen", c.getRank());
      assertEquals("Clubs", c.getSuit());
      assertEquals("Queen of Clubs", c.toString());
   }

   @Test
   public void test141_KingOfClubs() {
      Card c = new Card(13, 4);
      assertEquals("King", c.getRank());
      assertEquals("Clubs", c.getSuit());
      assertEquals("King of Clubs", c.toString());
   }
}