/**
 * JUnit tests for CircularQueue. Note that I started with
 * SimpleLinkedQueueTests from Lab 2 and modified to test
 * the circular behavior at interesting edge cases and to
 * verify capacity behavior.
 *
 * @author Hatalsky/Jones
 * @version 10/9/2016 Developed for CPE 103 Program 2
 */
import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import java.util.NoSuchElementException;
import org.junit.runners.MethodSorters;
import org.junit.rules.*;
import org.junit.runner.Description;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.lang.annotation.*;
import java.util.Date;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CircularQueueAcceptanceTests { 
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
      assertEquals(Object.class, CircularQueue.class.getSuperclass());
   }

   @Test
   public void test02_verifyInterfaces() {
      Class[] faces = CircularQueue.class.getInterfaces();

      assertEquals(1, faces.length);
      assertEquals(SimpleQueue.class, faces[0]);
   }

   @Test
   public void test03_verifyFields() {
      int arrayCount = 0;
      int intCount = 0;
      int constCount = 0;
      int otherCount = 0;

      Field[] fields = CircularQueue.class.getDeclaredFields();

      // 1 constant, 1 array, and 2 or 3 ints (front, back, and optional size).
      assertTrue(fields.length == 4 || fields.length == 5);

      for (Field f : fields) {
         if (f.getType() == Object[].class) {
            assertTrue(Modifier.isPrivate(f.getModifiers()));
            arrayCount++;
         } else if (f.getType() == int.class) {
            int mod = f.getModifiers();
            if (Modifier.isPublic(mod) && Modifier.isFinal(mod)
                  && Modifier.isStatic(mod)) {
               constCount++;
            } else if (Modifier.isPrivate(f.getModifiers())) {
               intCount++;
            }
         } else {
            otherCount++;
         }
      }

      assertEquals(1, arrayCount);
      assertTrue(intCount == 2 || intCount == 3);
      assertEquals(1, constCount);
      assertEquals(0, otherCount);
   }

   @Test
   public void test04_verifyConstructors() {
      int noParams = 0;
      int oneParam = 0;
      Constructor[] cons = CircularQueue.class.getDeclaredConstructors();

      assertEquals(2, cons.length);

      for (Constructor c : cons) {
         assertTrue(Modifier.isPublic(c.getModifiers()));
         Class[] params = c.getParameterTypes();
         if (params.length == 0) {
            noParams++;
         } else if (params.length == 1) {
            assertEquals(int.class, params[0]);
            oneParam++;
         }
      }

      assertEquals(1, noParams);
      assertEquals(1, oneParam);
   }

   @Test
   public void test05_verifyMethods() {
      int countPublic = 0;
      int countProtected = 0;
      int countPackage = 0;
      int countPrivate = 0;

      Method[] meths = CircularQueue.class.getDeclaredMethods();

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

      assertEquals(5, countPublic);
      assertEquals(0, countProtected);
      assertEquals(0, countPackage);
   }

   @Test
   public void test06_verifyInnerClass() {
      Class[] classes = CircularQueue.class.getDeclaredClasses();

      assertEquals(1, classes.length);

      Class exception = classes[0];

      assertEquals(CircularQueue.MyException.class, exception);
      assertTrue(Modifier.isPublic(exception.getModifiers()));
      assertTrue(Modifier.isStatic(exception.getModifiers()));
      assertEquals(RuntimeException.class, exception.getSuperclass());

      Field[] fields = exception.getDeclaredFields();
      for (Field f : fields) {
         int mod = f.getModifiers();
         assertTrue(Modifier.isPrivate(mod));
         assertTrue(Modifier.isStatic(mod));
         assertTrue(Modifier.isFinal(mod));
      }

      Constructor[] cons = exception.getDeclaredConstructors();
      int noParams = 0, oneParam = 0;
      assertEquals(2, cons.length);
      for (Constructor c : cons) {
         Class[] params = c.getParameterTypes();
         if (params.length == 0) {
            noParams++;
         } else if (params.length == 1) {
            assertEquals(String.class, params[0]);
            oneParam++;
         }
      }
      assertEquals(1, noParams);
      assertEquals(1, oneParam);
   }

   @Test(timeout=10000)
   public void test07_sizeAtConstruction() {
      CircularQueue<Integer> q = new CircularQueue<Integer>();
      assertEquals(0, q.size());
   }

   @Test(expected=CircularQueue.MyException.class, timeout=10000)
   public void test08_constructor2NegativeCap() {
      CircularQueue<Integer> q = new CircularQueue<Integer>(-5);
   }

   @Test(expected=CircularQueue.MyException.class, timeout=10000)
   public void test09_constructor2ZeroCap() {
      CircularQueue<Integer> q = new CircularQueue<Integer>(0);
   }

   @Test(expected=CircularQueue.MyException.class, timeout=10000)
   public void test10_myExceptionCons1() {
      CircularQueue.MyException e = new CircularQueue.MyException();
      throw e;
   }

   @Test(expected=CircularQueue.MyException.class, timeout=10000)
   public void test11_myExceptionCons2() {
      CircularQueue.MyException e = new CircularQueue.MyException("My Message");
      try {
         throw e;
      } catch (CircularQueue.MyException CQME) {
         assertEquals("My Message", CQME.getMessage());
         throw CQME;
      }
   }

   @Test(expected=NoSuchElementException.class, timeout=10000)
   public void test12_peekAtConstruction() {
      CircularQueue<Integer> q = new CircularQueue<Integer>();
      q.peek();
   }

   @Test(expected=NoSuchElementException.class, timeout=10000)
   public void test13_dequeueAtConstruction() {
      CircularQueue<Integer> q = new CircularQueue<Integer>();
      q.dequeue();
   }

   @Test(timeout=10000)
   public void test14_enqueuePeekSize() {
      CircularQueue<Integer> q = new CircularQueue<Integer>();
      
      q.enqueue(39);
      assertEquals(1, q.size());
      assertEquals((Integer)39, q.peek());

      q.enqueue(-55);
      assertEquals(2, q.size());
      assertEquals((Integer)39, q.peek());

      q.enqueue(7);
      assertEquals(3, q.size());
      assertEquals((Integer)39, q.peek());
   }

   @Test(timeout=10000)
   public void test15_dequeuePeekSize() {
      CircularQueue<Integer> q = new CircularQueue<Integer>();
      
      q.enqueue(39);
      q.enqueue(-55);
      q.enqueue(7);

      assertEquals(3, q.size());
      assertEquals((Integer)39, q.peek());

      assertEquals((Integer)39, q.dequeue());
      assertEquals(2, q.size());
      assertEquals((Integer)(-55), q.peek());

      assertEquals((Integer)(-55), q.dequeue());
      assertEquals(1, q.size());
      assertEquals((Integer)7, q.peek());

      assertEquals((Integer)7, q.dequeue());
      assertEquals(0, q.size());
   }

   @Test(expected=NoSuchElementException.class, timeout=10000)
   public void test16_peekExceptionAfterDequeue() {
      CircularQueue<Integer> q = new CircularQueue<Integer>();
      
      q.enqueue(39);
      q.enqueue(-55);
      q.enqueue(7);
      q.dequeue();
      q.dequeue();
      q.dequeue();

      q.peek();
   }

   @Test(expected=NoSuchElementException.class, timeout=10000)
   public void test17_dequeueExceptionAfterDequeue() {
      CircularQueue<Integer> q = new CircularQueue<Integer>();
      
      q.enqueue(39);
      q.enqueue(-55);
      q.enqueue(7);
      q.dequeue();
      q.dequeue();
      q.dequeue();

      q.dequeue();
   }
      
   @Test(timeout=10000)
   public void test18_mixedEnqueueDequeue() {
      CircularQueue<Integer> q = new CircularQueue<Integer>();

      q.enqueue(37);
      assertEquals(1, q.size());
      assertEquals((Integer)37, q.peek());

      assertEquals((Integer)37, q.dequeue());
      assertEquals(0, q.size());

      q.enqueue(37);
      assertEquals(1, q.size());
      assertEquals((Integer)37, q.peek());

      q.enqueue(-48);
      assertEquals(2, q.size());
      assertEquals((Integer)37, q.peek());

      assertEquals((Integer)37, q.dequeue());
      assertEquals(1, q.size());
      assertEquals((Integer)(-48), q.peek());

      q.enqueue(99);
      q.enqueue(23);
      assertEquals(3, q.size());
      assertEquals((Integer)(-48), q.peek());

      assertEquals((Integer)(-48), q.dequeue());
      assertEquals(2, q.size());
      assertEquals((Integer)99, q.peek());
      
      assertEquals((Integer)99, q.dequeue());
      assertEquals(1, q.size());
      assertEquals((Integer)23, q.peek());

      assertEquals((Integer)23, q.dequeue());
      assertEquals(0, q.size());
   }

   // Tests above this line are from Lab 2.
   // Tests below this line are CircularQueue specific.
   @Test(timeout=10000)
   public void test19_verifyConstant() {
      assertEquals(10, CircularQueue.INITIAL_LENGTH);
   }

   @Test(timeout=10000)
   public void test20_capacityAtConstruction() {
      CircularQueue<Integer> q = new CircularQueue<Integer>();

      Object[] a = q.unusualMethodForTestingPurposesOnly();
      assertEquals(CircularQueue.INITIAL_LENGTH, a.length);
   }

   @Test(timeout=10000)
   public void test21_capacityAtConstruction2() {
      CircularQueue<Integer> q = new CircularQueue<Integer>(1000);

      Object[] a = q.unusualMethodForTestingPurposesOnly();
      assertTrue(a.length == 1000 || a.length == 1001); //For spec ambiguity
   }

   @Test(timeout=10000)
   public void test22_wrapNoGrow() {
      //Todo - change this so that it is different, depending on if they keep track of size
      CircularQueue<Integer> q = new CircularQueue<Integer>();

      q.enqueue(37);
      q.enqueue(38);
      q.enqueue(39);
      q.enqueue(40);
      q.enqueue(41);
      q.enqueue(42);
      q.enqueue(43);
      q.enqueue(44);
      q.enqueue(45);
      assertEquals(9, q.size());
      assertEquals((Integer)37, q.dequeue());
      assertEquals((Integer)38, q.dequeue());
      assertEquals((Integer)39, q.dequeue());
      assertEquals((Integer)40, q.dequeue());
      assertEquals((Integer)41, q.dequeue());
      assertEquals((Integer)42, q.dequeue());
      assertEquals((Integer)43, q.dequeue());
      assertEquals((Integer)44, q.dequeue());
      assertEquals(1, q.size());
      q.enqueue(46);
      q.enqueue(47);
      q.enqueue(48);
      q.enqueue(49);
      q.enqueue(50);
      assertEquals(6, q.size());
      q.enqueue(51);
      q.enqueue(52);
      q.enqueue(53);
      q.enqueue(54);
      assertEquals(10, q.size());
      assertEquals((Integer)45, q.dequeue());
      assertEquals((Integer)46, q.dequeue());
      assertEquals((Integer)47, q.dequeue());
      assertEquals((Integer)48, q.dequeue());
      assertEquals((Integer)49, q.dequeue());
      assertEquals((Integer)50, q.dequeue());
      assertEquals((Integer)51, q.dequeue());
      assertEquals((Integer)52, q.dequeue());
      assertEquals((Integer)53, q.dequeue());
      assertEquals((Integer)54, q.dequeue());
      assertEquals(0, q.size());
   }

   @Test(timeout=10000)
   public void test23_growthNoWrap1() {
      CircularQueue<Integer> q = new CircularQueue<Integer>(13);
      // If they are using 5 fields they are keeping track of size and should
      // be able to use all of the array, otherwise they have to calculate
      // size and can only use length-1 array elements.
      boolean usingSize = CircularQueue.class.getDeclaredFields().length == 5;      
      int oldSize, newSize, qSize;

      for(int i = 0; i < 15; i++) {
         oldSize = q.unusualMethodForTestingPurposesOnly().length;
         qSize = q.size();
         q.enqueue(i);
         newSize = q.unusualMethodForTestingPurposesOnly().length;
         
         if (usingSize && qSize == oldSize) // Size should have doubled
         {
            assertEquals(newSize, oldSize * 2);
         }
         else if (!usingSize && qSize == oldSize - 1) // Size should double or double - 1
         {
            assertTrue(newSize == oldSize * 2 || newSize == oldSize * 2 - 1);
         }
         else 
            assertEquals(newSize, oldSize);
      }
      for(int i = 0; i < 15; i++) 
         assertEquals((Integer)i, q.dequeue());
   }

   @Test(timeout=10000)
   public void test24_growthWithWrapping() {
      CircularQueue<Integer> q = new CircularQueue<Integer>(13);
      // If they are using 5 fields they are keeping track of size and should
      // be able to use all of the array, otherwise they have to calculate
      // size and can only use length-1 array elements.
      boolean usingSize = CircularQueue.class.getDeclaredFields().length == 5;      
      int oldSize, newSize, qSize;

      for(int i = 0; i < 6; i++) {
         q.enqueue(i);
      }
      for(int i = 0; i < 5; i++) {
         q.dequeue();
      }
      
      for(int i = 0; i < 15; i++) {
         oldSize = q.unusualMethodForTestingPurposesOnly().length;
         qSize = q.size();
         q.enqueue(i);
         newSize = q.unusualMethodForTestingPurposesOnly().length;
         
         if (usingSize && qSize == oldSize) // Size should have doubled
         {
            assertEquals(newSize, oldSize * 2);
         }
         else if (!usingSize && qSize == oldSize - 1) // Size should double or double - 1
         {
            assertTrue(newSize == oldSize * 2 || newSize == oldSize * 2 - 1);
         }
         else 
            assertEquals(newSize, oldSize);
      }
      q.dequeue();
      for(int i = 0; i < 15; i++) 
         assertEquals((Integer)i, q.dequeue());
   }

   @Test(timeout=1600)
   public void test25_bigOhTest() {
      CircularQueue<Integer> q = new CircularQueue<Integer>();

      for (int i = 0; i < 1000000; i++) {
         q.enqueue(i);
         q.peek();
      }

      for (int i = 0; i < 1000000; i++) {
         q.dequeue();
      }
   }
   @Test
   public void test26_wrapNoGrow2() {
      CircularQueue<Integer> q = new CircularQueue<Integer>();

      q.enqueue(41);
      q.enqueue(42);
      q.enqueue(43);
      q.enqueue(44);
      q.enqueue(45);
      assertEquals(5, q.size());
      assertEquals((Integer)41, q.dequeue());
      assertEquals((Integer)42, q.dequeue());
      assertEquals(3, q.size());
      q.enqueue(46);
      q.enqueue(47);
      q.enqueue(48);
      q.enqueue(49);
      q.enqueue(50);
      q.enqueue(51);
      q.enqueue(52);
      q.enqueue(53);
      q.enqueue(54);
      assertEquals(12, q.size());
      assertEquals((Integer)43, q.dequeue());
      assertEquals((Integer)44, q.dequeue());
      assertEquals((Integer)45, q.dequeue());
      assertEquals((Integer)46, q.dequeue());
      assertEquals((Integer)47, q.dequeue());
      assertEquals((Integer)48, q.dequeue());
      assertEquals((Integer)49, q.dequeue());
      assertEquals((Integer)50, q.dequeue());
      assertEquals((Integer)51, q.dequeue());
      assertEquals((Integer)52, q.dequeue());
      assertEquals((Integer)53, q.dequeue());
      assertEquals((Integer)54, q.dequeue());
   }
   @Test(timeout=10000)
   public void test27_multiGrowth1() {
      CircularQueue<Integer> q = new CircularQueue<Integer>(13);
      boolean usingSize = CircularQueue.class.getDeclaredFields().length == 5;      
      int oldSize, newSize, qSize;

      for(int i = 0; i < 3; i++) {
         q.enqueue(i);
      }
      for(int i = 0; i < 2; i++) {
         q.dequeue();
      }
      
      for(int i = 0; i < 100; i++) {
         oldSize = q.unusualMethodForTestingPurposesOnly().length;
         qSize = q.size();
         q.enqueue(i);
         newSize = q.unusualMethodForTestingPurposesOnly().length;
         
         if (usingSize && qSize == oldSize) // Size should have doubled
         {
            assertEquals(newSize, oldSize * 2);
         }
         else if (!usingSize && qSize == oldSize - 1) // Size should double or double - 1
         {
            assertTrue(newSize == oldSize * 2 || newSize == oldSize * 2 - 1);
         }
         else 
            assertEquals(newSize, oldSize);
      }
      q.dequeue();
      for(int i = 0; i < 100; i++) 
         assertEquals((Integer)i, q.dequeue());
   }
   @Test(timeout=10000)
   public void test28_multiGrowth2() {
      CircularQueue<Integer> q = new CircularQueue<Integer>();
      boolean usingSize = CircularQueue.class.getDeclaredFields().length == 5;      
      int oldSize, newSize, qSize;

      for(int i = 0; i < 3; i++) {
         q.enqueue(i);
      }
      for(int i = 0; i < 2; i++) {
         q.dequeue();
      }
      
      for(int i = 0; i < 100; i++) {
         oldSize = q.unusualMethodForTestingPurposesOnly().length;
         qSize = q.size();
         q.enqueue(i);
         newSize = q.unusualMethodForTestingPurposesOnly().length;
         
         if (usingSize && qSize == oldSize) // Size should have doubled
         {
            assertEquals(newSize, oldSize * 2);
         }
         else if (!usingSize && qSize == oldSize - 1) // Size should double or double - 1
         {
            assertTrue(newSize == oldSize * 2 || newSize == oldSize * 2 - 1);
         }
         else 
            assertEquals(newSize, oldSize);
      }
      q.dequeue();
      for(int i = 0; i < 100; i++) 
         assertEquals((Integer)i, q.dequeue());
   }
}
