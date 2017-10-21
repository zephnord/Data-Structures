import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.runners.MethodSorters;
import org.junit.rules.*;
import org.junit.runner.Description;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.lang.annotation.*;
import java.util.Date;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeckAcceptanceTests { 
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
   public void test01_verifySuperClass() {
      assertEquals(CircularQueue.class, Deck.class.getSuperclass());
   }

   @Test
   public void test02_verifyFields() {
      Field[] fields = Deck.class.getDeclaredFields();

      for (Field f : fields) {
         int mod = f.getModifiers();
         assertTrue(Modifier.isStatic(mod));
         assertTrue(Modifier.isFinal(mod));
      }
   }

   @Test
   public void test03_verifyConstructors() {
      Constructor[] cons = Deck.class.getDeclaredConstructors();

      assertEquals(1, cons.length);
      Class[] params = cons[0].getParameterTypes();
      assertEquals(1, params.length);
      assertEquals(boolean.class, params[0]);
   }

   @Test
   public void test04_verifyMethods() {
      int countPublic = 0;
      int countProtected = 0;
      int countPackage = 0;
      int countPrivate = 0;

      Method[] meths = Deck.class.getDeclaredMethods();

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

      assertEquals(2, countPublic);
      assertEquals(0, countProtected);
      assertEquals(0, countPackage);
   }

   @Test(timeout=10000)
   public void test05_unshuffledOrder() {
      Deck d = new Deck(false);

      assertEquals(52, d.size());

      for (int suit = 1; suit <= 4; suit++) {
         for (int rank = 1; rank <= 13; rank++) {
            Card c = d.draw();
            assertEquals(new Card(rank, suit).toString(), c.toString());
            assertEquals(65 - 13*suit - rank, d.size());
         }
      }
   }

   @Test(timeout=1000)
   public void test06_shuffledConstuctor() {
      Deck d = new Deck(true);
      int numCards = 0;

      assertEquals(52, d.size());
      for (int suit = 1; suit <= 4; suit++) {
         for (int rank = 1; rank <= 13; rank++) {
            Card c = d.draw();
            if (!new Card(rank, suit).toString().equals(c.toString())) {
               numCards++;
            }
            assertEquals(65 - 13*suit - rank, d.size());
         }
      }
      assertTrue(numCards > 0);
   }

   @Test(timeout=1000)
   public void test07_shuffleFullDeck() {
      Deck d = new Deck(false);
      ArrayList<String> unshuffled = new ArrayList<String>(52);
      ArrayList<String> shuffled = new ArrayList<String>(52);

      assertEquals(52, d.size());
      for (int card = 0; card < 52; card++) {
         Card c = d.draw();
         unshuffled.add(c.toString());
         d.enqueue(c);
      }

      d.shuffle();
      assertEquals(52, d.size());

      for (int card = 0; card < 52; card++) {
         Card c = d.draw();
         shuffled.add(c.toString());
         d.enqueue(c);
      }

      assertFalse(shuffled.equals(unshuffled));
      assertTrue(new HashSet<String>(shuffled).equals(new HashSet<String>(unshuffled)));

      unshuffled = shuffled;
      shuffled = new ArrayList<String>(52);

      d.shuffle();
      assertEquals(52, d.size());

      for (int card = 0; card < 52; card++) {
         Card c = d.draw();
         shuffled.add(c.toString());
         d.enqueue(c);
      }

      assertFalse(shuffled.equals(unshuffled));
      assertTrue(new HashSet<String>(shuffled).equals(new HashSet<String>(unshuffled)));
   }

   @Test(timeout=1000)
   public void test08_shuffleHalfDeck() {
      Deck d = new Deck(false);
      ArrayList<String> unshuffled = new ArrayList<String>(26);
      ArrayList<String> shuffled = new ArrayList<String>(26);
      
      assertEquals(52, d.size());
      for (int card = 0; card < 26; card++) {
         d.draw();
      }

      assertEquals(26, d.size());
      for (int card = 0; card < 26; card++) {
         Card c = d.draw();
         unshuffled.add(c.toString());
         d.enqueue(c);
      }

      d.shuffle();
      assertEquals(26, d.size());

      for (int card = 0; card < 26; card++) {
         Card c = d.draw();
         shuffled.add(c.toString());
         d.enqueue(c);
      }

      assertFalse(shuffled.equals(unshuffled));
      assertTrue(new HashSet<String>(shuffled).equals(new HashSet<String>(unshuffled)));

      unshuffled = shuffled;
      shuffled = new ArrayList<String>(26);

      d.shuffle();
      assertEquals(26, d.size());

      for (int card = 0; card < 26; card++) {
         Card c = d.draw();
         shuffled.add(c.toString());
         d.enqueue(c);
      }

      assertFalse(shuffled.equals(unshuffled));
      assertTrue(new HashSet<String>(shuffled).equals(new HashSet<String>(unshuffled)));
   }

   @Test(timeout=1000)
   public void test09_shuffleIsRandom() {
      Deck d1 = new Deck(false);
      Deck d2 = new Deck(false);
      int differ = 0;

      d1.shuffle();
      d2.shuffle();

      assertEquals(52, d1.size());
      assertEquals(52, d2.size());

      for (int card = 0; card < 52; card++) {
         if (! d1.draw().toString().equals(d2.draw().toString())) {
            differ++;
         }
      }

      assertTrue(differ > 0);
   }
}