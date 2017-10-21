import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.MethodSorters;

/**
 * @author Zeph Nord
 * @version Lab09
 * @version Date
 * @version Winter 2017
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HashToolsTests {
	   @Rule
	   public TestRule watcher = new TestWatcher() {
	      protected void starting(Description description) {
	         System.out.printf("\b\bStarting: %-60s", description.getMethodName());
	      }
	   };
	   @Rule
	   public Stopwatch sw = new Stopwatch() {
	      String s;
	      protected void finished(long nanos, Description description) {
	         System.out.println(s);
	      }
	      protected void succeeded(long nanos, Description description) {
	         s = " Passed" + " (" + runtime(TimeUnit.MILLISECONDS) + " ms)";
	      }
	      protected void failed(long nanos, Throwable e, Description description) {
	         s = " FAILED" + " (" + runtime(TimeUnit.MILLISECONDS) + " ms)";
	      }
	   };
	   
	
	   // See @BeforeClass tagged method for initialization.
	   //
	   // Opens smallTestFile.txt and creates a list of the words in the file
	   private static ArrayList<String> list;

	   @BeforeClass
	   public static void buildList() throws FileNotFoundException {
	      list = new ArrayList<String>();

	      Scanner scanner = new Scanner(new File("dictionary.txt"));

	      while(scanner.hasNext()) {
	         list.add(scanner.next());
	      }
	   }
	   
	   private int tableSize1 = 500;
	   private int talbeSize2 = 25000;

	   //**Test set 1 with tableSize = tableSize1
	   //-------------StringHash------------------------------
	   @Test
	   public void test06_StringHash_collisions() { 
	      assertEquals(178186, HashTools.collisions(list, tableSize1, new StringHash()), .001);
	   }
	   
	   @Test
	   public void test07_StringHash_maxCollisions() { 
	      assertEquals(405, HashTools.maxCollisions(list, tableSize1, new StringHash()));
	   }

	   @Test
	   public void test08_StringHash_avgChainLength() { 
	      assertEquals(355.247, HashTools.avgChainLength(list, tableSize1, new StringHash()), .001);
	   }

	   @Test
	   public void test09_StringHash_unused() { 
	      assertEquals(0, HashTools.unused(list, tableSize1, new StringHash()));
	   }
	   
	   //-------------BetterHash--------------------------------
	   
	   @Test
	   public void test10_BetterHash_collisions() { 
		   assertEquals(178186, HashTools.collisions(list, tableSize1, new BetterHash()), .001);
	   }
	   
	   @Test
	   public void test11_BetterHash_maxCollisions() { 
		   assertEquals(407, HashTools.maxCollisions(list, tableSize1, new BetterHash()));
	   }

	   @Test
	   public void test12_BetterHash_avgChainLength() { 
		   assertEquals(355.247, HashTools.avgChainLength(list, tableSize1, new BetterHash()), .001);
	   }

	   @Test
	   public void test13_BetterHash_unused() { 
		   assertEquals(0, HashTools.unused(list, tableSize1, new BetterHash()));
	   }
	   
	   //----------------------MyHash--------------------------------
	   
	   @Test
	   public void test14_MyHash_collisions() { 
		   assertEquals(178186, HashTools.collisions(list, tableSize1, new MyHash()), .001);
	   }
	   
	   @Test
	   public void test15_MyHash_maxCollisions() { 
		   assertEquals(703, HashTools.maxCollisions(list, tableSize1, new MyHash()));
	   }

	   @Test
	   public void test16_MyHash_avgChainLength() { 
		   assertEquals(355.247, HashTools.avgChainLength(list, tableSize1, new MyHash()), .001);
	   }

	   @Test
	   public void test17_MyHash_unused() { 
		   assertEquals(0, HashTools.unused(list, tableSize1, new MyHash()));
	   }
	   
	 //**Test set 2 with tableSize = 25,000
	   //-------------StringHash------------------------------
	   @Test
	   public void test18_StringHash_collisions() { 
	      assertEquals(153697, HashTools.collisions(list, talbeSize2, new StringHash()), .001);
	   }
	   
	   @Test
	   public void test19_StringHash_maxCollisions() { 
	      assertEquals(20, HashTools.maxCollisions(list, talbeSize2, new StringHash()));
	   }

	   @Test
	   public void test20_StringHash_avgChainLength() { 
	      assertEquals(7.151, HashTools.avgChainLength(list, talbeSize2, new StringHash()), .01);
	   }

	   @Test
	   public void test21_StringHash_unused() { 
	      assertEquals(21, HashTools.unused(list, talbeSize2, new StringHash()));
	   }
	   
	   //-------------BetterHash--------------------------------
	   
	   @Test
	   public void test22_BetterHash_collisions() { 
		   assertEquals(153701, HashTools.collisions(list, talbeSize2, new BetterHash()), .001);
	   }
	   
	   @Test
	   public void test23_BetterHash_maxCollisions() { 
		   assertEquals(19, HashTools.maxCollisions(list, talbeSize2, new BetterHash()));
	   }

	   @Test
	   public void test24_BetterHash_avgChainLength() { 
		   assertEquals(7.151, HashTools.avgChainLength(list, talbeSize2, new BetterHash()), .001);
	   }

	   @Test
	   public void test25_BetterHash_unused() { 
		   assertEquals(25, HashTools.unused(list, talbeSize2, new BetterHash()));
	   }
	   
	   //----------------------MyHash--------------------------------
	   
	   @Test
	   public void test26_MyHash_collisions() { 
		   assertEquals(177651, HashTools.collisions(list, talbeSize2, new MyHash()), .001);
	   }
	   
	   @Test
	   public void test27_MyHash_maxCollisions() { 
		   assertEquals(676, HashTools.maxCollisions(list, talbeSize2, new MyHash()));
	   }

	   @Test
	   public void test28_MyHash_avgChainLength() { 
		   assertEquals(172.147, HashTools.avgChainLength(list, talbeSize2, new MyHash()), .001);
	   }

	   @Test
	   public void test29_MyHash_unused() { 
		   assertEquals(23975, HashTools.unused(list, talbeSize2, new MyHash()));
	   }
	   
}
