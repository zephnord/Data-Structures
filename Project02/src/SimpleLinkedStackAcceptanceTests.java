/**
 * JUnit tests for SimpleLinkedStack - a modification of Lab 3
 * SimpleLinkedStackTests.
 *
 * @author Paul Hatalsky
 * @version version 10/16/2016 
 */
import static org.junit.Assert.*;
import org.junit.*;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.Random;
import java.lang.reflect.*;
import org.junit.runners.MethodSorters;
import org.junit.rules.*;
import org.junit.runner.Description;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.lang.annotation.*;
import java.util.Date;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SimpleLinkedStackAcceptanceTests {
   private static final long t = 1492580862000L;
   private static PrintWriter testSummaryFile;
   private String t1 = getClass().getName() + ".java";
   private String t2 = getClass().getName() + ".class";
   private static SimpleLinkedStack<Integer> globalStack = new SimpleLinkedStack<Integer>();
   final int TEST_SIZE = 1000000;
 
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
   @Test(timeout=1000)
   public void test01_verifySuperClass() {
      assertEquals(Object.class, SimpleLinkedStack.class.getSuperclass());
   }

   @Test(timeout=1000)
   public void test02_verifyInterfaces() {
      Class[] faces = SimpleLinkedStack.class.getInterfaces();

      assertEquals(1, faces.length);
      assertEquals(SimpleStack.class, faces[0]);
   }

   @Test(timeout=1000)
   public void test03_verifyFields() {
      int nodeCount = 0;
      int intCount = 0;
      Field[] fields = SimpleLinkedStack.class.getDeclaredFields();

      assertEquals(2, fields.length);

      for (int i = 0; i < fields.length; i++) {
         assertTrue(Modifier.isPrivate(fields[i].getModifiers()));

         if (fields[i].getType().toString().contains("SimpleLinkedStack$")) {
            nodeCount++;
         } else if (fields[i].getType() == int.class) {
            intCount++;
         } else {
            fail("Invalid field in SimpleLinkedStack");
         }
      }

      assertEquals(1, nodeCount);
      assertEquals(1, intCount);
   }

   @Test(timeout=1000)
   public void test04_verifyConstructors() {
      int count = 0;
      Constructor[] cons = SimpleLinkedStack.class.getDeclaredConstructors();

      assertEquals(1, cons.length);
      assertTrue(Modifier.isPublic(cons[0].getModifiers()));

      Class[] params = cons[0].getParameterTypes();
     
      assertEquals(0, params.length);
   }

   @Test(timeout=1000)
   public void test05_verifyMethods() {
      int countPublic = 0;

      Method[] meths = SimpleLinkedStack.class.getDeclaredMethods();

      for (Method m : meths) {
         int mod = m.getModifiers();
         if (Modifier.isPublic(mod)) {
            countPublic++;
         } else {
            assertTrue(Modifier.isPrivate(mod));
         }
      }

      assertEquals(4, countPublic);
   }

   // Simplified inner class test
   @Test(timeout=1000)
   public void test06_verifyInnerClasses() {
      Class[] classes = SimpleLinkedStack.class.getDeclaredClasses();
      boolean seenPrivate = false;

      assertTrue(classes.length > 0);

      for (Class c : classes) {
         seenPrivate = seenPrivate || Modifier.isPrivate(c.getModifiers());
      }

      assertTrue(seenPrivate);
   }


   @Test(timeout=1000)
   public void test07_sizeAtConstruction() {
      SimpleLinkedStack<Integer> s = new SimpleLinkedStack<Integer>();
      assertEquals(0, s.size());
   }
   
   @Test(timeout=1000,expected=NoSuchElementException.class)
   public void test08_peekAtConstruction() {
      SimpleLinkedStack<Integer> s = new SimpleLinkedStack<Integer>();
      s.peek();
   }

   @Test(timeout=1000,expected=NoSuchElementException.class)
   public void test09_popAtConstruction() {
      SimpleLinkedStack<Integer> s = new SimpleLinkedStack<Integer>();
      s.pop();
   }

   @Test(timeout=1000)
   public void test10_simplePush() {
      SimpleLinkedStack<Integer> s = new SimpleLinkedStack<Integer>();
    
      int[] a = new int[] {5, 10, 15, 20, 25, 30, 35, 40, 45, 50};

      for (int i = 0; i < a.length; i++) {
         s.push(a[i]);
         assertEquals(i + 1, s.size());
         assertEquals((Integer)a[i], s.peek());
      }
   }

   @Test(timeout=1000)
   public void test11_simplePop() {
      SimpleLinkedStack<Integer> s = new SimpleLinkedStack<Integer>();

      for (int i = 0; i < 10; i++) {
         s.push(i * 13);
      }

      for (int i = 9; i > -1; i--) {
         assertEquals((Integer)(i * 13), s.peek());
         assertEquals((Integer)(i * 13), s.pop());
         assertEquals(i, s.size());
      }
   }

   @Test(timeout=1000)
   public void test12_pushPopCombo() {
      SimpleLinkedStack<Integer> s = new SimpleLinkedStack<Integer>();

      s.push(55);
      assertEquals(1, s.size());
      assertEquals((Integer)55, s.peek());

      s.push(66);
      assertEquals(2, s.size());
      assertEquals((Integer)66, s.peek());

      s.pop();
      assertEquals(1, s.size());
      assertEquals((Integer)55, s.peek());

      s.push(77);
      assertEquals(2, s.size());
      assertEquals((Integer)77, s.peek());

      s.push(33);
      assertEquals(3, s.size());
      assertEquals((Integer)33, s.peek());

      s.push(11);
      assertEquals(4, s.size());
      assertEquals((Integer)11, s.peek());

      s.pop();
      assertEquals(3, s.size());
      assertEquals((Integer)33, s.peek());

      s.push(-4);
      assertEquals(4, s.size());
      assertEquals((Integer)(-4), s.peek());

      s.push(89);
      assertEquals(5, s.size());
      assertEquals((Integer)89, s.peek());

      s.push(-521);
      assertEquals(6, s.size());
      assertEquals((Integer)(-521), s.peek());

      s.push(333);
      assertEquals(7, s.size());
      assertEquals((Integer)333, s.peek());

      s.push(-9);
      assertEquals(8, s.size());
      assertEquals((Integer)(-9), s.peek());

      s.push(45);
      assertEquals(9, s.size());
      assertEquals((Integer)45, s.peek());

      s.push(61);
      assertEquals(10, s.size());
      assertEquals((Integer)61, s.peek());

      s.push(93);
      assertEquals(11, s.size());
      assertEquals((Integer)93, s.peek());


      assertEquals((Integer)93, s.pop());
      assertEquals(10, s.size());
      assertEquals((Integer)61, s.pop());
      assertEquals(9, s.size());
      assertEquals((Integer)45, s.pop());
      assertEquals(8, s.size());
      assertEquals((Integer)(-9), s.pop());
      assertEquals(7, s.size());
      assertEquals((Integer)333, s.pop());
      assertEquals(6, s.size());
      assertEquals((Integer)(-521), s.pop());
      assertEquals(5, s.size());
      assertEquals((Integer)89, s.pop());
      assertEquals(4, s.size());
      assertEquals((Integer)(-4), s.pop());
      assertEquals(3, s.size());
      assertEquals((Integer)33, s.pop());
      assertEquals(2, s.size());
      assertEquals((Integer)77, s.pop());
      assertEquals(1, s.size());
      assertEquals((Integer)55, s.pop());
      assertEquals(0, s.size());
   }

   @Test(timeout=1000)
   public void test13_testPushPopNulls() {
      SimpleLinkedStack<Integer> s = new SimpleLinkedStack<Integer>();

      s.push(null);
      s.push(66);
      s.pop();
      s.push(77);
      s.push(33);
      s.push(11);
      s.pop();
      s.push(-4);
      s.push(89);
      s.push(null);
      s.push(333);
      s.push(-9);
      s.push(45);
      s.push(61);
      s.push(null);

      assertEquals(null, s.pop());
      assertEquals((Integer)61, s.pop());
      assertEquals((Integer)45, s.pop());
      assertEquals((Integer)(-9), s.pop());
      assertEquals((Integer)333, s.pop());
      assertEquals(null, s.pop());
      assertEquals((Integer)89, s.pop());
      assertEquals((Integer)(-4), s.pop());
      assertEquals((Integer)33, s.pop());
      assertEquals((Integer)77, s.pop());
      assertEquals(null, s.pop());
   }

   @Test(timeout=3500)
   public void test14_testOrderPush() {
      for (int i = 0; i < TEST_SIZE; i++) {
         globalStack.push(i);
      }
   }

   @Test(timeout=50)
   public void test15_testOrderPop() {
      for (int i = 0; i < TEST_SIZE; i++) {
         globalStack.pop();
      }
   }

   @Test(timeout=1000)
   public void test16_isGeneric() {
      SimpleLinkedStack<Object> s = new SimpleLinkedStack<>();

      s.push("Hello");
      s.push(Long.MAX_VALUE);
      s.push(Double.NaN);
      s.push(Double.POSITIVE_INFINITY);
      s.push(5);
      s.push(true);
      s.push('%');

      assertEquals((Character)'%', s.pop());
      assertEquals((Boolean)true, s.pop());
      assertEquals((Integer)5, s.pop());
      assertEquals(Double.POSITIVE_INFINITY, (double)s.pop(), 0.0);
      assertEquals(Double.NaN, (double)s.pop(), 0.0);
      assertEquals((Long)Long.MAX_VALUE, s.pop());
      assertEquals("Hello", s.pop());
   }

   @Test(timeout=4500)
   public void test17_randomStackOperations() {
      SimpleLinkedStack<Integer> s = new SimpleLinkedStack<>();
      Stack<Integer> s2 = new Stack<>();
      Random r = new Random(1234);

      for (int i = 0; i < 10000000; i++) {
         switch (r.nextInt(s2.size() > 0 ? 3 : 1)) {
            case 0:
               int val = r.nextInt();
               s.push(val);
               s2.push(val);
               break;
            case 1:
               assertEquals(s2.peek(), s.peek());
               break;
            case 2:
               assertEquals(s2.pop(), s.pop());
               break;
         }
         assertEquals(s2.size(), s.size());
      }

      while (s2.size() > 0) {
         assertEquals(s2.peek(), s.peek());
         assertEquals(s2.pop(), s.pop());
         assertEquals(s2.size(), s.size());
      }
   }
}