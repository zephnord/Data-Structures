/**
 * JUnit tests for HashTable assignment.
 * Contains tests for the interfaces HashTable and HashMetrics and the class
 * HashTableQuadratic.
 *
 * @author Paul Hatalsky
 * @version 3/3/2017 Developed for CPE 103 Program 5
 */
import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import java.util.NoSuchElementException;
import java.util.*;
import org.junit.runners.MethodSorters;
import org.junit.rules.*;
import org.junit.runner.Description;
import static org.hamcrest.CoreMatchers.*;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.*;
import java.lang.annotation.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HashTableAcceptanceTests {
   private static PrintWriter testSummaryFile;
   private static int globalSize = 1000000;
   private static final long t = 1492580862000L;
   private String t1 = getClass().getName() + ".java";
   private String t2 = getClass().getName() + ".class";
   private static HashTableQuadratic<Double> globalTable = new HashTableQuadratic<Double>(2*globalSize);  
   private static Double[] globalArrayAdd = new Double[globalSize];
   private static Double[] globalArrayRandom = new Double[globalSize];
   
   @Retention(RetentionPolicy.RUNTIME)
   @Target({ ElementType.TYPE, ElementType.METHOD})
   @Documented
   public @interface TestDescription {
       public String desc();
   }
   @Rule
   public TestRule watcher = new TestWatcher() {
      protected void starting(Description description) {
         System.out.printf("\b\bStarting: %-60s", description.getMethodName());
         testSummaryFile.printf("Starting: %-60s", description.getMethodName());
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
         s = " Passed" + " (" + runtime(TimeUnit.MILLISECONDS) + " ms)";
      }
      protected void failed(long nanos, Throwable e, Description description) {
         s = " FAILED" + " (" + runtime(TimeUnit.MILLISECONDS) + " ms)";
         TestDescription t = description.getAnnotation(TestDescription.class);
         if (t != null)
            s += "\nFailed test description:\n" + t.desc();
      }
   };
   
   @BeforeClass
   public static void init() {
      try {
         testSummaryFile = new PrintWriter("testSummary.txt");
      }
      catch (Exception e) {}
      Random r = new Random(1234);
      for (int i = 0; i < globalSize; i++) {
         globalArrayAdd[i] = r.nextDouble();
         globalArrayRandom[i] = r.nextDouble();
      }
   }
   @AfterClass
   public static void cleanUp() {
      testSummaryFile.close();
   }
  
   @Test(timeout=5000)
   public void test01_verifySuperClass() {
      assertEquals(Object.class, HashTableQuadratic.class.getSuperclass());
   }

   @Test(timeout=5000)
   public void test02_verifyInterfaces() {
      Class[] faces = HashTableQuadratic.class.getInterfaces();
      assertEquals(2, faces.length);

      for (Class c : faces) {
         if (c == HashTable.class) {
            assertEquals(7, c.getDeclaredMethods().length);
         } else if (c == HashMetrics.class){
            assertEquals(2, c.getDeclaredMethods().length);
         } else {
            fail();
         }
      }
   }

   @Test(timeout=5000)
   public void test03_verifyFields() {
      Field[] fields = HashTableQuadratic.class.getDeclaredFields();

      for (Field f : fields) {
         assertTrue(Modifier.isPrivate(f.getModifiers()));
      }
   }

   @Test(timeout=5000)
   public void test04_verifyConstructors() {
      Constructor[] cons = HashTableQuadratic.class.getDeclaredConstructors();
      assertEquals(3, cons.length);
   }

   @Test(timeout=5000)
   public void test05_verifyMethods() {
      int countPublic = 0;
      Method[] meths = HashTableQuadratic.class.getDeclaredMethods();
      for (Method m : meths) {
         if (m.isSynthetic()) { continue; }
         int mod = m.getModifiers();
         if (Modifier.isPublic(mod)) {
            countPublic++;
         } else {
            assertTrue(Modifier.isPrivate(mod));
         }
      }

      assertEquals(9, countPublic);
   }

   @Test(expected=IllegalArgumentException.class, timeout=5000)
   public void test06_negativeTableSize() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(-10);
   }

  //ADDED after the 100% deadline
   @Test(expected=IllegalArgumentException.class, timeout=5000)
   public void test06a_zeroTableSize() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(0);
   }

   @Test(expected=IllegalArgumentException.class, timeout=5000)
   public void test06b_negativeLoadFactor() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(10, -.5);
   }

   @Test(expected=IllegalArgumentException.class, timeout=5000)
   public void test06c_zeroLoadFactor() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(10, 0);
   }

   @Test(timeout=5000)
   public void test07_sizeAtConstruction() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(10);
      assertEquals(0, table.size());
   }

   @Test(timeout=5000)
   public void test08_isEmptyAtConstruction() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(10);
      assertTrue(table.isEmpty());
   }

   @Test(timeout=5000)
   public void test09_tableSizeAtConstruction() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(10);
      assertEquals(11, table.tableSize());
   }

   @Test(timeout=5000)
   public void test10_containsAtConstruction() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(10);
      assertFalse(table.contains(10));
      assertEquals(0, table.size());
   }

   @Test(timeout=5000)
   public void test11_removeAtConstruction() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(10);
      assertFalse(table.remove(10));
      assertEquals(0, table.size());
   }

   @Test(timeout=5000)
   public void test12_collisionsAtConstruction() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(10);
      assertEquals(0, table.collisions());
   }

   @Test(timeout=5000)
   public void test13_maxCollisionsAtConstruction() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(10);
      assertEquals(0, table.maxCollisions());
   }

   @Test(timeout=5000)
   public void test14_loadFactorAtConstruction() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(10);
      assertEquals(0, table.loadFactor(), 0);
   }

   @Test(timeout=5000)
   public void test15_basicAddSize() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(100);

      for (int i = 0; i < 10; i++) {
         assertEquals(null, table.add(i));
         assertEquals(i + 1, table.size());
      }

      for (int i = 0; i < 10; i++) {
         assertTrue(table.contains(i));
      }
   }

   @Test(timeout=5000)
   public void test16_basicAddWithNegativeHash() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(100);
      int[] a = {10, -20, 30, -40, 50, -60};

      for (int i = 0; i < a.length; i++) {
         assertEquals(null, table.add(a[i]));
         assertEquals(i + 1, table.size());
      }

      for (int i = 0; i < a.length; i++) {
         assertTrue(table.contains(a[i]));
      }
   }

   @Test(timeout=5000)
   public void test17_largeAddNoCollisions() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(200);  // Next prime is 211

      for (int i = 0; i < 101; i++) {
         assertEquals(null, table.add(i));

         assertFalse(table.isEmpty());
         assertEquals(i + 1, table.size());
         assertEquals(211, table.tableSize());
         assertEquals((i + 1.0) / 211, table.loadFactor(), 0.000001);
         assertEquals(0, table.collisions());
         assertEquals(0, table.maxCollisions());
      }

      for (int i = 0; i < 101; i++) {
         assertTrue(table.contains(i));
      }
   }

   @Test(timeout=5000)
   public void test18_basicCollisionsMaxCollisions() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(200); // Next prime is 211

      for (int i = 10; i < 101; i++) {
         assertEquals(null, table.add(i));
      }   
      assertEquals(null, table.add(311)); // Hashes to 100, goes to 101
      assertEquals(1, table.collisions());
      assertEquals(1, table.maxCollisions());
      assertEquals(null, table.add(522)); // Hashes to 100 goes to 104
      assertEquals(3, table.collisions());
      assertEquals(2, table.maxCollisions());
      assertEquals(null, table.add(733)); // Hashes to 100 goes to 109
      assertEquals(6, table.collisions());
      assertEquals(3, table.maxCollisions());
      assertEquals(null, table.add(313)); // Hashes to 102
      assertEquals(6, table.collisions());
      assertEquals(3, table.maxCollisions());
      assertEquals(null, table.add(315)); // Hashes to 104
      assertEquals(7, table.collisions());
      assertEquals(3, table.maxCollisions());
   }
   
   @Test(timeout=5000)
   public void test19_basicCollisionsMaxCollisionsDuplicates() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(200); // Next prime is 211
      Integer i1 = new Integer(733);
      Integer i2 = new Integer(733);

      for (int i = 11; i < 101; i++) {
         assertEquals(null, table.add(i));
      }   
      assertEquals(90, table.size());
      assertEquals(null, table.add(311)); // Hashes to 100, goes to 101
      assertEquals(1, table.collisions());
      assertEquals(1, table.maxCollisions());
      assertEquals(null, table.add(522)); // Hashes to 100 goes to 104
      assertEquals(3, table.collisions());
      assertEquals(2, table.maxCollisions());
      assertEquals(null, table.add(i1)); // Hashes to 100 goes to 109
      assertEquals(6, table.collisions());
      assertEquals(3, table.maxCollisions());
      assertEquals(null, table.add(313)); // Hashes to 102
      assertEquals(6, table.collisions());
      assertEquals(3, table.maxCollisions());
      assertEquals(null, table.add(315)); // Hashes to 104
      assertEquals(7, table.collisions());
      assertEquals(3, table.maxCollisions());
      
      assertEquals(95, table.size());
      Integer i3 = table.add(i2); // Hashes to 100 goes to 109, replaces and returns i1
      assertEquals(95, table.size());
      assertTrue(i1==i3);
      assertFalse(i2==i3);
      assertEquals(10, table.collisions());
      assertEquals(3, table.maxCollisions());
   }
   
   @Test(timeout=5000)
   public void test20_basicRemove() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(200); // Next prime is 211

      for (int i = 11; i < 101; i++) {
         assertEquals(null, table.add(i));
      }   
      assertEquals(90, table.size());
      
      for (int i = 11; i < 101; i++) {
         assertEquals(true, table.remove(i));
         assertEquals(100-i, table.size());

      }   
   }
   
   @Test(timeout=5000)
   public void test21_basicRemoveAndReinsert() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(200); // Next prime is 211

      for (int i = 11; i < 101; i++) {
         assertEquals(null, table.add(i));
      }   
      assertEquals(90, table.size());
      assertEquals(null, table.add(311)); // Hashes to 100, goes to 101
      assertEquals(1, table.collisions());
      assertEquals(1, table.maxCollisions());
      assertEquals(null, table.add(522)); // Hashes to 100 goes to 104
      assertEquals(3, table.collisions());
      assertEquals(2, table.maxCollisions());
      assertEquals(null, table.add(733)); // Hashes to 100 goes to 109
      assertEquals(6, table.collisions());
      assertEquals(3, table.maxCollisions());
      assertEquals(93, table.size());
      
      assertTrue(table.remove(522));
      assertFalse(table.remove(522));
      assertEquals(92, table.size());
      assertFalse(table.contains(522));
      assertTrue(table.contains(733));
      assertEquals(null, table.add(944)); // Hashes to 100 goes to 104, replaces deleted element
      assertEquals(8, table.collisions());
      assertEquals(3, table.maxCollisions());
      assertEquals(93, table.size());
      
      assertEquals(null, table.add(522)); // Hashes to 100 goes to 116
      assertEquals(12, table.collisions());
      assertEquals(4, table.maxCollisions());      
      assertEquals(94, table.size());
   }
   
   @Test(timeout=5000)
   public void test22_basicLoadFactorAndRehash() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(11);

      for (int i = 0; i < 5; i++) 
         table.add(i);      
      assertEquals(5.0/11, table.loadFactor(), .000001);
      
      table.add(5);
      assertEquals(6.0/23, table.loadFactor(), .000001);
   }
   
   @Test(timeout=5000)
   public void test23_basicDefaultSizeLoadFactorAndRehash() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>();

      for (int i = 0; i < 8; i++) 
         table.add(i);      
      assertEquals(8.0/17, table.loadFactor(), .000001);
      
      for (int i = 0; i < 8; i++) 
         table.add(i);
      assertEquals(8.0/17, table.loadFactor(), .000001);
      
      table.add(17);
      assertEquals(9.0/37, table.loadFactor(), .000001);
   }
   
   @Test(timeout=5000)
   public void test24_collisionsAndRehash() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(13);

      for (int i = 0; i < 6; i++) 
         table.add(i*13);
      
      assertEquals(6.0/13, table.loadFactor(), .000001);
      assertEquals(15, table.collisions());
      assertEquals(5, table.maxCollisions());
      
      table.add(2);
      assertEquals(7.0/29, table.loadFactor(), .000001);
      assertEquals(15, table.collisions());
      assertEquals(5, table.maxCollisions());
   }
   
   @Test(timeout=5000)
   public void test25_stringsTable() {
      HashTableQuadratic<String> table = new HashTableQuadratic<String>(50, .75);
      String[] strings = new String[] {"Hello", "these", "are", "some", "random", "strings.", "If", "this", 
                                       "test", "fails", "it's", "likely", "because", "well", "I", "don't", "really", "know",
                                       "why", "this", "test", "would", "fail", "if", "all", "other", "tests", "are", "passing.",
                                       "Good", "Luck!"};

      for (int i = 0; i < strings.length; i++) 
         table.add(strings[i]);
      
      assertEquals(28.0/53, table.loadFactor(), .000001);
      assertEquals(9, table.collisions());
      assertEquals(2, table.maxCollisions());
      assertTrue(table.contains("this"));
      assertTrue(table.remove("this"));
      assertEquals(27.0/53, table.loadFactor(), .000001);
      assertFalse(table.remove("this"));
      assertFalse(table.contains("this"));
      table.add("this");
      assertEquals(28.0/53, table.loadFactor(), .000001);
      assertTrue(table.contains("this"));
   }
   
   @Test(timeout=5000)
   public void test26_completelyFillUpTable() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(11, 1.0);
      
      table.add(0);  //hashes to 0, goes to 0
      table.add(11); //hashes to 0, goes to 1
      table.add(22); //hashes to 0, goes to 4
      table.add(33); //hashes to 0, goes to 9
      table.add(44); //hashes to 0, goes to 5
      table.add(55); //hashes to 0, goes to 3
      table.add(1);  //hashes to 1, goes to 2
      table.add(12); //hashes to 1, goes to 10
      table.add(23); //hashes to 1, goes to 6
      table.add(6);  //hashes to 1, goes to 7
      table.add(4);  //hashes to 4, goes to 8
      assertEquals(1.0, table.loadFactor(), .000001);
   }
   
   @Test(expected=HashTableQuadratic.HashTableInsertionException.class, timeout=5000)
   public void test27_noSpotFound() {
      HashTableQuadratic<Integer> table = new HashTableQuadratic<Integer>(11, 1.0);
      
      table.add(0);  //hashes to 0, goes to 0
      table.add(11); //hashes to 0, goes to 1
      table.add(22); //hashes to 0, goes to 4
      table.add(33); //hashes to 0, goes to 9
      table.add(44); //hashes to 0, goes to 5
      table.add(55); //hashes to 0, goes to 3
      table.add(66); //hashes to 0, no spot found
   }

   @Test(timeout=600)
   public void test28_randomOperations() {
      int size = 10000;
      HashSet<Integer> hashLib = new HashSet<Integer>();
      HashTableQuadratic<Integer> hashUnderTest = new HashTableQuadratic<Integer>();
      Integer[] a = new Integer[size];
      Random rand = new Random(-1234);

      for (int i = 0; i < size; i++) {
         a[i]= rand.nextInt(3000) - 1500;
         assertEquals(hashLib.contains(a[i]), hashUnderTest.contains(a[i]));
         //assertEquals(hashLib.add(a[i]), hashUnderTest.add(a[i]));
         //assertEquals(hashLib.add(a[i]), hashUnderTest.add(a[i]));
         hashLib.add(a[i]);
         hashUnderTest.add(a[i]);
         assertEquals(hashLib.contains(a[i]), hashUnderTest.contains(a[i]));
         assertEquals(hashLib.size(), hashUnderTest.size());
      }

      for (int i = 0; i < size; i++) {
         Integer num = rand.nextInt();
         assertEquals(hashLib.remove(num), hashUnderTest.remove(num));

         assertEquals(hashLib.contains(a[i]), hashUnderTest.contains(a[i]));
         assertEquals(hashLib.remove(a[i]), hashUnderTest.remove(a[i]));
         assertEquals(hashLib.contains(a[i]), hashUnderTest.contains(a[i]));
         assertEquals(hashLib.remove(a[i]), hashUnderTest.remove(a[i]));
         assertEquals(hashLib.contains(a[i]), hashUnderTest.contains(a[i]));
         assertEquals(hashLib.size(), hashUnderTest.size());
      }
   }

   @Test(timeout=400)
   public void test29_warmupForTimingTests() {
      int size = 100000;
      HashTableQuadratic<Double> table = new HashTableQuadratic<Double>(size);
      Double[] a = new Double[size];

      for (int i = 0; i < size; i++) {
         a[i] = new Double(Math.random());
         table.add(a[i]);
         table.collisions();
         table.maxCollisions();
      }
      for (int i = 0; i < size; i++) {
         table.contains(a[i]);
         table.contains(new Double(Math.random()));
      }
      for (int i = 0; i < size; i++) {
         table.remove(a[i]);
         table.remove(new Double(Math.random()));
      }
   }

   @Test(timeout=400)
   public void test30_addRehashBigO() {
      HashTableQuadratic<Double> table = new HashTableQuadratic<Double>();
      
      for (int i = 0; i < globalSize/2; i++) {
         table.add(globalArrayAdd[i]);
      }
      assertEquals(500000, table.size());
      assertEquals(500000.0/1403641, table.loadFactor(), .000001);
   }

   @Test(timeout=700)
   public void test31_addBigO() {
      for (int i = 0; i < globalSize; i++) {
         globalTable.add(globalArrayAdd[i]);
      }
   }

   @Test(timeout=1000)
   public void test32_containsBigO() {
      for (int i = 0; i < globalSize; i++) {
         assertTrue(globalTable.contains(globalArrayAdd[i]));
         globalTable.contains(globalArrayRandom[i]);
      }
   }

   @Test(timeout=15)
   public void test33collisionsBigO() {
      for (int i = 0; i < globalSize; i++) {
         assertTrue(globalTable.collisions() <= 427732);
      }
      //System.out.println(globalTable.collisions());
      assertEquals(427732, globalTable.collisions());
   }

   @Test(timeout=15)
   public void test34_maxCollisionsBigO() {
      for (int i = 0; i < globalSize; i++) {
         assertTrue(globalTable.maxCollisions() <= 18);
      }
      //System.out.println(globalTable.maxCollisions());
      assertEquals(18, globalTable.maxCollisions());
   }
   
   @Test(timeout=700)
   public void test99_removeBigO() { // Must run LAST
      for (int i = 0; i < globalSize; i++) {
         globalTable.remove(globalArrayAdd[i]);
         globalTable.remove(globalArrayRandom[i]);
      }
   }
}
