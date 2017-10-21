/**
 * JUnit tests for BST assignment.
 *
 * @author Paul Hatalsky
 * @version 2/23/2017 Developed for CPE 103 P4 
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
public class BSTAcceptanceTests {
   private static PrintWriter testSummaryFile;
   private static int globalSize = 1000000;
   private static final long t = 1492580862000L;
   private static BST<Integer> globalBst = new BST<Integer>();
   private static TreeMap<Integer, Object> globalTreeMap = new TreeMap<Integer, Object>(); 
   private static Integer[] globalInts = new Integer[globalSize];
   private String t1 = getClass().getName() + ".java";
   private String t2 = getClass().getName() + ".class";
   private static Set<Integer> globalKeys;

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
      Random r = new Random(5678);
      for (int i = 0; i < globalSize; i++) {
         globalInts[i] = r.nextInt();
         globalTreeMap.put(globalInts[i], null);
      }
      globalKeys = globalTreeMap.keySet();
   }

   @AfterClass
   public static void cleanUp() {
      testSummaryFile.close();
   }

   @Test
   @TestDescription(desc="Verify correct fields of BST")
   public void test03_verifyFields() throws NoSuchFieldException { 
      Field[] fields = BST.class.getDeclaredFields();
      int flag = 0;

      for (Field f : fields) {
         if (f.isSynthetic()) { continue; }

         int mod = f.getModifiers();
         assertTrue(Modifier.isPrivate(mod));

         switch (f.getName()) {
            case "EMPTY_NODE":
               assertTrue((flag & 1) == 0);
               flag |= 1;
               assertTrue(Modifier.isFinal(mod));
               assertThat(f.getType().toString(), endsWith("BSTNode"));
               break;
            case "root":
               assertTrue((flag & 2) == 0);
               flag |= 2;
               assertThat(f.getType().toString(), endsWith("BSTNode"));
               break;
            case "size":
               assertTrue((flag & 4) == 0);
               flag |= 4;
               assertEquals(int.class, f.getType());
               break;
            default:
               fail("Invalid field in BST");
               break;
         }

      }
      assertTrue("Missing field in BST", 7 == flag);
   }

   @Test
   @TestDescription(desc="Verify 11 public methods in BST")
   public void test05_verifyMethods() { 
      int countPublic = 0;
      Method[] meths = BST.class.getDeclaredMethods();

      for (Method m : meths) {
         if (m.isSynthetic()) { continue; }

         if (Modifier.isPublic(m.getModifiers())) {
            countPublic++;
         } else {
            assertTrue(Modifier.isPrivate(m.getModifiers()));
         }
      }

      assertEquals(11, countPublic);
   }

   @Test
   @TestDescription(desc="Verify correct inner classes in BST")
   public void test06_verifyInnerClasses() { 
      Class[] classes = BST.class.getDeclaredClasses();
      int flag = 0;
      Class nodeFace = null;
      Class emptyNodeFace = null;
      Class nodeInterface = null;
      Class[] faces;

      for (Class c : classes) {
         if (c.isSynthetic()) { continue; }

         assertTrue(Modifier.isPrivate(c.getModifiers()));
         switch (c.getName()) {
            case "BST$Node":
               assertTrue((flag & 1) == 0);
               flag |= 1;
               assertEquals(Object.class, c.getSuperclass());
               faces = c.getInterfaces();
               assertEquals(1, faces.length);
               nodeFace = faces[0];

               int count = 0;
               for (Field f : c.getDeclaredFields()) {
                  if (f.isSynthetic()) { continue; }
                  count++;
               }
               assertEquals(3, count);
               break;
            case "BST$EmptyNode":
               assertTrue((flag & 2) == 0);
               flag |= 2;
               assertEquals(Object.class, c.getSuperclass());
               faces = c.getInterfaces();
               assertEquals(1, faces.length);
               emptyNodeFace = faces[0];

               for (Field f : c.getDeclaredFields()) {
                  if (f.isSynthetic()) { continue; }
                  fail("EmptyNode shouldn't have fields");
               }
               break;
            case "BST$BSTNode":
               assertTrue((flag & 4) == 0);
               flag |= 4;
               assertTrue(c.isInterface());
               nodeInterface = c;
               break;
            default:
               //fail("Invalid class in BST");
               faces = c.getInterfaces();
               assertEquals(1, faces.length);
               assertEquals(faces[0], Iterator.class);
               
         }

      }

      assertTrue("Missing class in BST", 7 == flag);
      assertEquals(nodeInterface, nodeFace);
      assertEquals(nodeInterface, emptyNodeFace);
   }

   @Test
   @TestDescription(desc="Call contains on empty tree - it should return false")
   public void test01_containsAtConstruction() { 
      BST<Integer> bst = new BST<Integer>();
      assertFalse(bst.contains(999));
   }

   @Test
   @TestDescription(desc="Call toSortedList on empty tree - list should remain empty")
   public void test02_toSortedListAtConstruction() {  
      BST<Integer> bst = new BST<Integer>();
      ArrayList<Integer> arr = new ArrayList<Integer>();
      bst.toSortedList(arr);
      assertEquals(0, arr.size());
   }
   
   @Test
   @TestDescription(desc="Insert values: {99, -4, 167, 139, 55, -89, 13, 78, 128, 119}.\n" + 
                         "Check contains for values in and not in the BST")
   public void test03_insertContains() {  
      BST<Integer> bst = new BST<Integer>();
      int[] values = new int[] {99, -4, 167, 139, 55, -89, 13, 78, 128, 119};

      for (int i = 0; i < values.length; i++) {
         bst.insert(values[i]);

         assertEquals(i + 1, bst.size());

         for (int j = 0; j <= i; j++) {
            assertTrue(bst.contains(values[j]));
            assertFalse(bst.contains(values[j] - 1));
            assertFalse(bst.contains(values[j] + 1));
         }
      }
   }
   
   @Test
   @TestDescription(desc="Insert values: {99, -4, 167, 139, 55, -89, 13, 78, 178, 174}.\n" + 
                         "Verify minimum")
   public void test04_minimum() { 
      BST<Integer> bst = new BST<Integer>();
      int[] values = new int[] {99, -4, 167, 139, 55, -89, 13, 78, 178, 174};
      
      for (int i = 0; i < values.length; i++) {
         bst.insert(values[i]);

         if (i < 1) {
            assertEquals((Integer)99, bst.minimum());
         } else if (i < 5) {
            assertEquals((Integer)(-4), bst.minimum());
         } else {
            assertEquals((Integer)(-89), bst.minimum());
         }
      }
   }

   @Test
   @TestDescription(desc="Insert values: {99, -4, 167, 139, 55, -89, 13, 78, 178, 174}.\n" + 
                         "Verify maximum")
   public void test05_maximum() { 
      BST<Integer> bst = new BST<Integer>();
      int[] values = new int[] {99, -4, 167, 139, 55, -89, 13, 78, 178, 174};
      
      for (int i = 0; i < values.length; i++) {
         bst.insert(values[i]);

         if (i < 2) {
            assertEquals((Integer)99, bst.maximum());
         } else if (i < 8) {
            assertEquals((Integer)167, bst.maximum());
         } else {
            assertEquals((Integer)178, bst.maximum());
         }
      }
   }

   @Test
   @TestDescription(desc="Insert values: {99, -4, 167, 139, 55, -89, 13, 78, 178, 174}.\n" + 
                         "Verify toSortedList")
   public void test06_toSortedList() { 
      BST<Integer> bst = new BST<Integer>();
      int[] values = new int[] {99, -4, 167, 139, 55, -89, 13, 78, 178, 174};
      
      for (int i = 0; i < values.length; i++) {
         bst.insert(values[i]);
      }

      ArrayList<Integer> list = new ArrayList<Integer>();
      bst.toSortedList(list);

      int[] expected = new int[] {-89, -4, 13, 55, 78, 99, 139, 167, 174, 178};

      for (int i = 0; i < values.length; i++) {
         assertEquals((Integer)expected[i], list.get(i));
      }
   }
   
   @Test
   @TestDescription(desc="Verify all methods work correctly with Strings")
   public void test07_insertContainsMinMaxSortedWithStrings() { // points = 5 
      BST<String> bst = new BST<String>();
      String[] strings = new String[] {"Hello", "these", "are", "some", "random", "strings.", "If", "this", 
                                       "test", "fails", "it's", "likely", "because", "the", "BST", "code", "being", "tested",
                                       "uses", "compareTo", "incorrectly", "by", "looking", "for", "1", "and", "-1", "instead",
                                       "of", "> 0 and", "< 0."};
      String[] sortedStrings = new String[strings.length];
      String[] otherStrings = new String[] {"things", "that", "do", "not", "exist", "in", "strings"};
      ArrayList<String> sortedList = new ArrayList<String>();
      int i;
      
      for (i = 0; i < strings.length; i++) {
         sortedStrings[i] = strings[i];
         bst.insert(strings[i]);
      }
      for (i = 0; i < strings.length; i++)
         assertTrue(bst.contains(strings[i]));
         
      for (i = 0; i < otherStrings.length; i++)
         assertFalse(bst.contains(otherStrings[i]));

      Arrays.sort(sortedStrings);
      assertEquals(sortedStrings[0], bst.minimum());
      assertEquals(sortedStrings[sortedStrings.length-1], bst.maximum());
      
      bst.toSortedList(sortedList);
      i = 0;
      for (String s : sortedList) {
         assertEquals("" + i, sortedStrings[i], s);
         i++;
      }
      int size = bst.size();
      assertEquals(strings.length, bst.size());
      for (String s : strings)
      {
         bst.remove(s);
         assertFalse(bst.contains(s));
         assertEquals(--size, bst.size());
      }
   }  
   
   @Test(timeout = 5000)
   @TestDescription(desc="Insert 1,000,000 Integers.  Verify size, maximum time allowed: 5000ms")
   public void test08_insertBigOh() { 
      for (int i = 0; i < globalSize; i++) {
         globalBst.insert(globalInts[i]);
      }
      assertEquals(globalTreeMap.size(), globalBst.size());
   }
   
   @Test(timeout = 4000)
   @TestDescription(desc="Call contains for every element in BST with 1,000,000 Integers.\n" +
                          "Verify all calls return true, maximum time allowed: 4000ms")
   public void test09_containsBigOh() { 
      for (int i = 0; i < globalSize; i++) {
         assertTrue(globalBst.contains(globalInts[i]));
      }
   }
   
   @Test(timeout = 4000)
   @TestDescription(desc="Call contains for 500,000 random values in BST with 1,000,000 Integers.\n" +
                          "Verify all calls return correct boolean, maximum time allowed: 4000ms")
   public void test10_containsBigOh2() {  
      Random r = new Random(-9876);
      int val;
      
      for (int i = 0; i < globalSize/2; i++) {
         val = r.nextInt();
         assertEquals(globalBst.contains(val), globalTreeMap.containsKey(val));
      }
   }
   @Test(timeout = 500)
   @TestDescription(desc="Call minimum and maximum 1,000,000 times on BST with 1,000,000 Integers.\n" + 
                         "Verify all calls return true, maximum time allowed: 500ms")
   public void test11_minMaxBigOh() { 
      assertEquals(globalBst.minimum(), globalTreeMap.firstKey());
      assertEquals(globalBst.maximum(), globalTreeMap.lastKey());
      for (int i = 0; i < globalSize; i++) {
         globalBst.minimum();
         globalBst.maximum();
      }
   }
   @Test(timeout = 500)
   @TestDescription(desc="Call toSortedList on BST with 1,000,000 Integers.\n" + 
                         "Verify list is correct, maximum time allowed: 500ms")
   public void test12_toSortedListBigOh() { 
      ArrayList<Integer> list = new ArrayList<Integer>(1000000);
      globalBst.toSortedList(list);
      Iterator<Integer> linkedItr = list.iterator();
      Iterator<Integer> keyItr = globalKeys.iterator();
      while (linkedItr.hasNext())
         assertEquals(linkedItr.next(), keyItr.next());      
   }
   
   /////////////////////////////////////////////////////////////////////////////
   // New tests for Lab 8
   /////////////////////////////////////////////////////////////////////////////
    
   @Test(expected = NoSuchElementException.class)
   @TestDescription(desc="Call remove on empty BST, it should throw NoSuchElementException")
   public void test04_removeAtConstruction() {
     BST<Integer> bst = new BST<Integer>();
     bst.remove(99);
   }

   @Test
   @TestDescription(desc="Call insert, then remove, insert, then remove")
   public void test15_removeBSTOfOne() {
     BST<Integer> bst = new BST<Integer>();
     bst.insert(99);
     bst.remove(99);

     assertEquals(0, bst.size());
     assertFalse(bst.contains(99));

     bst.insert(10);
     bst.remove(10);

     assertEquals(0, bst.size());
     assertFalse(bst.contains(10));
   }

   @Test
   @TestDescription(desc="Call remove on BSTs of size 2 with left child.  Remove root, then leaf, repeat with leaf then root")
   public void test16_removeBSTOfTwoLeft() {
      BST<Integer> bst = new BST<Integer>();
     
      // Root first...
      bst.insert(99);
      bst.insert(88);

      bst.remove(99);
      assertEquals(1, bst.size());
      assertTrue(bst.contains(88));
      assertFalse(bst.contains(99));

      bst.remove(88);
      assertEquals(0, bst.size());
      assertFalse(bst.contains(88));
      assertFalse(bst.contains(99));
     
       // Leaf first...
      bst.insert(99);
      bst.insert(88);
     
      bst.remove(88);
      assertEquals(1, bst.size());
      assertTrue(bst.contains(99));
      assertFalse(bst.contains(88));
   
      bst.remove(99);
      assertEquals(0, bst.size());
      assertFalse(bst.contains(88));
      assertFalse(bst.contains(99));
   }

   @Test
   @TestDescription(desc="Call remove on BSTs of size 2 with right child.  Remove root, then leaf, repeat with leaf then root")
   public void test17_removeListOfTwoRight() {
      BST<Integer> bst = new BST<Integer>();
     
      // Root first...
      bst.insert(99);
      bst.insert(108);

      bst.remove(99);
      assertEquals(1, bst.size());
      assertTrue(bst.contains(108));
      assertFalse(bst.contains(99));

      bst.remove(108);
      assertEquals(0, bst.size());
      assertFalse(bst.contains(108));
      assertFalse(bst.contains(99));
     
       // Leaf first...
      bst.insert(99);
      bst.insert(108);
     
      bst.remove(108);
      assertEquals(1, bst.size());
      assertTrue(bst.contains(99));
      assertFalse(bst.contains(108));
   
      bst.remove(99);
      assertEquals(0, bst.size());
      assertFalse(bst.contains(88));
      assertFalse(bst.contains(99));
   }

   @Test
   @TestDescription(desc="Call remove on BSTs of size 3. Try 1) root, root, root\n" +
                         "2) Left, right, root, then right, left, root")
   public void test18_removeListOfThreeCompleteTree() {
      BST<Integer> bst = new BST<Integer>();

      // Root, root, root... 
      bst.insert(88);
      bst.insert(77);
      bst.insert(99);

      bst.remove(77);
      assertEquals(2, bst.size());
      assertFalse(bst.contains(77));
      assertTrue(bst.contains(88));
      assertTrue(bst.contains(99));

      bst.remove(88);
      assertEquals(1, bst.size());
      assertFalse(bst.contains(77));
      assertFalse(bst.contains(88));
      assertTrue(bst.contains(99));
      
      bst.remove(99);
      assertEquals(0, bst.size());
      assertFalse(bst.contains(77));
      assertFalse(bst.contains(88));
      assertFalse(bst.contains(99));

      // Left, right, root...
      bst.insert(88);
      bst.insert(77);
      bst.insert(99);

      bst.remove(77);
      assertEquals(2, bst.size());
      assertFalse(bst.contains(77));
      assertTrue(bst.contains(88));
      assertTrue(bst.contains(99));

      bst.remove(99);
      assertEquals(1, bst.size());
      assertFalse(bst.contains(77));
      assertFalse(bst.contains(99));
      assertTrue(bst.contains(88));
      
      bst.remove(88);
      assertEquals(0, bst.size());
      assertFalse(bst.contains(77));
      assertFalse(bst.contains(99));
      assertFalse(bst.contains(88));

      // Right, left, root...
      bst.insert(88);
      bst.insert(77);
      bst.insert(99);

      bst.remove(99);
      assertEquals(2, bst.size());
      assertFalse(bst.contains(99));
      assertTrue(bst.contains(88));
      assertTrue(bst.contains(77));

      bst.remove(77);
      assertEquals(1, bst.size());
      assertFalse(bst.contains(77));
      assertFalse(bst.contains(99));
      assertTrue(bst.contains(88));
      
      bst.remove(88);
      assertEquals(0, bst.size());
      assertFalse(bst.contains(88));
      assertFalse(bst.contains(77));
      assertFalse(bst.contains(99));
   }

   @Test
   @TestDescription(desc="Create tree with: {34, 55, -33, 66, -87, 5, 7, 111, -49, -77, -3, 2, 8, -9, 11}\n" +
                         "Remove each element, test tree structure after each remove using size and contains")
   public void test19_removeAllLargeTree() {
      BST<Integer> bst = new BST<Integer>();
      int array[] = new int[]{34, 55, -33, 66, -87, 5, 7, 111, -49, -77, -3, 2, 8, -9, 11};

      for (int i = 0; i < array.length; i++)
      {
         bst.insert(array[i]);
      }

      for (int i = 0; i < array.length; i++)
      {
         bst.remove(array[i]);

         assertTrue(bst.size() == array.length - i - 1);
         assertFalse(bst.contains(array[i]));

         for (int j = i + 1; j < array.length; j++)
         {
            assertTrue(bst.contains(array[j]));
         }
      }
   }

   @Test
   @TestDescription(desc="Verify height of empty tree is -1")
   public void test20_treeHeightEmpty() {
      BST<Integer> bst = new BST<Integer>();
      assertTrue(bst.treeHeight() == -1);
   }

   @Test
   @TestDescription(desc="Verify height of tree with one element is 0")
   public void test21_treeHeightOne() {
      BST<Integer> bst = new BST<Integer>();
      bst.insert(12);
      assertTrue(bst.treeHeight() == 0);
   }

   @Test
   @TestDescription(desc="Verify height of trees of various sizes")
   public void test22_treeHeightVariousGreaterThanOne() {
      BST<Integer> bst = new BST<Integer>();

      // one Left (1)
      bst.insert(88);
      bst.insert(77);
      assertTrue(bst.treeHeight() == 1);
      
      // two left (2)
      bst.insert(66);
      assertTrue(bst.treeHeight() == 2);

      // two left and one right (2)
      bst.insert(95);
      assertTrue(bst.treeHeight() == 2);

      // two left and two right (2)
      bst.insert(97);
      assertTrue(bst.treeHeight() == 2);

      // complete tree of 6 three left (2)
      bst.insert(79);
      assertTrue(bst.treeHeight() == 2);

      // complete tree of 6 three right (2)
      bst.remove(79);
      bst.insert(93);
      assertTrue(bst.treeHeight() == 2);

      // treeHeight seven complete (2)
      bst.insert(79);
      assertTrue(bst.treeHeight() == 2);

      // treeHeight eight complete (right) (3)
      bst.insert(99);
      assertTrue(bst.treeHeight() == 3);

      // treeHeight eight complete (left) (3)
      bst.remove(99);
      bst.insert(55);
      assertTrue(bst.treeHeight() == 3);

      // treeHeight eight complete (left middle) (3)
      bst.remove(55);
      bst.insert(80);
      assertTrue(bst.treeHeight() == 3);

      // treeHeight eight complete (right middle) (3)
      bst.remove(80);
      bst.insert(91);
      assertTrue(bst.treeHeight() == 3);
   }
   
   @Test
   @TestDescription(desc="Verify height of random large tree")
   public void test23_treeHeightRandomBig() {
      BST<Integer> bst = makeTree(makeRandomArray(1000, -387));
      assertTrue(bst.treeHeight() == 20);
   }
    
   @Test
   @TestDescription(desc="Verify internalPathLength of empty tree is -1")
   public void test24_internalPathLengthEmpty() {
      BST<Integer> bst = new BST<Integer>();
      assertTrue(bst.internalPathLength() == -1);
   }

   @Test
   @TestDescription(desc="Verify internalPathLength of tree with one node is 0")
   public void test25_internalPathLengthOneElement() {
      BST<Integer> bst = new BST<Integer>();
      bst.insert(99);
      assertTrue(bst.internalPathLength() == 0);
   }
   
   @Test
   @TestDescription(desc="Verify internalPathLength of trees of various sizes")
   public void test26_internalPathLengthVariousGreaterThanOneElement() {
      BST<Integer> bst = new BST<Integer>();

      // one Left
      bst.insert(88);
      bst.insert(77);
      assertTrue(bst.internalPathLength() == 1);

      // two left
      bst.insert(55);
      assertTrue(bst.internalPathLength() == 3);

      // one right
      bst = new BST<Integer>();
      bst.insert(88);
      bst.insert(95);
      assertTrue(bst.internalPathLength() == 1);

      // two right
      bst.insert(97);
      assertTrue(bst.internalPathLength() == 3);

      // three complete
      bst = new BST<Integer>();
      bst.insert(88);
      bst.insert(77);
      bst.insert(95);
      assertTrue(bst.internalPathLength() == 2);

      // four complete (left)
      bst.insert(66);
      assertTrue(bst.internalPathLength() == 4);

      // five complete (left-right)
      bst.insert(97);
      assertTrue(bst.internalPathLength() == 6);

      // six complete (left-right-middle right)
      bst.insert(93);
      assertTrue(bst.internalPathLength() == 8);

      // seven complete
      bst.insert(79);
      assertTrue(bst.internalPathLength() == 10);

      // eight complete (a middle node)
      bst.insert(92);
      assertTrue(bst.internalPathLength() == 13);
   }

   @Test
   @TestDescription(desc="Verify height of random large tree")
   public void test27_internalPathLengthRandomBig() {
      BST<Integer> bst = makeTree(makeRandomArray(1000, -4873));
      assertTrue(bst.internalPathLength() == 10162);
   }

   @Test(timeout=1000)
   @TestDescription(desc="Call treeHeight 10x on tree with 1,000,000 nodes\n" + 
                         "Verify height, maximum time allowed = 1000ms")
   public void test31_treeHeightBigOh() {
      for (int i = 0; i < 10; i++)
         globalBst.treeHeight();
      assertEquals(45, globalBst.treeHeight());
   }
   
   @Test(timeout=1000)
   @TestDescription(desc="Call internalPathLength 10x on tree with 1,000,000 nodes\n" + 
                         "Verify length, maximum time allowed = 1000ms")
   public void test32_internalPathLengthBigOh() {
      for (int i = 0; i < 10; i++)
         globalBst.internalPathLength();
      assertEquals(23948902, globalBst.internalPathLength());
   }
   
   @Test(timeout=1000)
   @TestDescription(desc="Remove all nodes on tree with 1,000,000 nodes\n" + 
                         "Maximum time allowed = 1000ms")
   public void test99_removeBigOh() {// Test must run last since nodes being removed
      for (Integer value : globalKeys)
      {
         globalBst.remove(value);
      }
   }
   ////////////////////////////////////////////////////////////////////////////
   // New tests for Program 4...

   @Test(expected = NoSuchElementException.class)
   public void test34_getAtConstruction() {
      BST<Integer> bst = new BST<Integer>();
      bst.get(99);
   }

   @Test(expected = NoSuchElementException.class)
   public void test35_getNoSuchElement() {
      BST<Integer> bst = new BST<Integer>();
      bst.insert(-44);
      bst.get(99);
   }

   @Test
   public void test36_getBasic() {
      Integer[] array = new Integer[]{550, -880, 2200, 660, 770, -110, 330, 440, 1100, -2200};
      BST<Integer> bst = makeTree(array);

      for (int i = 0; i < array.length; i++)
         assertEquals(new Integer(array[i]), bst.get(array[i]));
   }
   
   @Test
   public void test37_getStrings() {
      String[] array = new String[]{"Hello", "these", "are", "some", "random", "strings.", "If", "this", 
                                    "test", "fails", "it's", "likely", "because", "the", "BST", "code", "being", "tested",
                                    "uses", "compareTo", "incorrectly", "by", "looking", "for", "1", "and", "-1", "instead", 
                                    "of", "> 0 and", "< 0."};
      BST<String> bst = new BST<String>();

      for (int i = 0; i < array.length; i++)
         bst.insert(array[i]);
         
      for (int i = 0; i < array.length; i++)
         assertEquals(new String(array[i]), bst.get(array[i]));

   }
   
   @Test
   public void test38_getRandomBig() {
      Integer[] array = makeRandomArray(5000, -11);
      BST<Integer> bst = makeTree(array);

      for (int i = 0; i < array.length; i++)
      {
         // Made sure deep Integer objects work in getBasic test.
         assertEquals(new Integer(array[i]), bst.get(array[i]));
      }
   }

   @Test(timeout=1200)
   public void test39_getBigOh() {
      for (Integer value : globalKeys)
      {
         assertEquals(value, globalBst.get(value));
      }
   }

   @Test
   public void test40_iteratorEmpty() {
      BST<Integer> bst = new BST<Integer>();

      for(Integer i : bst)
      {
         fail();
      }
   }
  
   @Test
   public void test41_iteratorBasic() {
      Integer[] array = new Integer[]{550, -880, 2200, 660, 770, -1100, 330, 440, 1100, -220};
      BST<Integer> bst = makeTree(array);
      Arrays.sort(array);
      int i = 0;

      for(Integer element : bst)
      {
         assertEquals(new Integer(array[i]), element);
         i++;
      }

      assertEquals(array.length, i);

      Iterator<Integer> itOne = bst.iterator();
      Iterator<Integer> itTwo = bst.iterator();

      assertTrue(itOne != itTwo);
   }

   @Test
   public void test42_iteratorRandomBig() {
      Integer[] array = makeRandomArray(5000, -117);
      BST<Integer> bst = makeTree(array);
      Arrays.sort(array);
      int i = 0;

      for(Integer element : bst)
      {
         assertEquals(new Integer(array[i]), element);
         i++;
      }
      
      assertEquals(array.length, i);
   }

    /* This test should detect if code constructs an O(N) stack
    * at construction (should be O(logN) stack).
    */
   @Test(timeout = 200)
   public void test43_iteratorConstructionBigOh() {
      for(int i = 0; i < 10000; i++)
      {
         Iterator<Integer> it = globalBst.iterator();
      }
   }

   @Test(timeout = 2500)
   public void test44_iteratorNextBigOh() {
      int sum = 0;
      
      for(int i = 0; i < 10; i++){
         for (Integer element : globalBst) {
            sum += element;
         }
      }
   }

   @Test(expected = UnsupportedOperationException.class)
   public void test45_iteratorRemove() {
      BST<Integer> bst = new BST<Integer>();
      bst.iterator().remove();
   }

   private Integer[] makeRandomArray(int size, int seed) {
      Integer[] array = new Integer[size];
      Random rand = new Random(seed);

      for (int i = 0; i < size; i++)
      {
         array[i] = rand.nextInt();
      }

      return array;
   }

   private BST<Integer> makeTree(Integer[] array) {
      BST<Integer> bst = new BST<Integer>();

      for (int i = 0; i < array.length; i++)
      {
         bst.insert(array[i]);
      }

      return bst;
   }
} 