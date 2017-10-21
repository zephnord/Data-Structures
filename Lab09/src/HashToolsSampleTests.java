/**
 * JUnit tests for HashTools assignment.
 *
 * NOTE: Contains tests for multiple files:
 *
 *   1) PrimeTools class
 *   2) Hashable interface
 *   3) StringHash class
 *   4) BetterHash class
 *   5) MyHash class
 *   6) HashTools class
 *
 * @author Paul Hatalsky
 * @version 2/12/2016 Developed for CPE 103 Lab 9 
 */
import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import org.junit.runners.MethodSorters;
import org.junit.rules.*;
import org.junit.runner.Description;
import java.util.concurrent.TimeUnit;
import java.io.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HashToolsSampleTests {
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

   private static int[] primes = new int[] {2, 3, 5, 7, 11};

   ////////////////////////////////////////////////////////////////////////////
   // PrimeTools tests...
   

   // isPrime should be true for each of the following: 2, 3, 5, 7, 11                         
   @Test
   public void test01_isPrimeWithPrimes() { // points = 1
      for (int i = 0; i < primes.length; i++) {
         assertTrue(PrimeTools.isPrime(primes[i]));
      }
   }

   // For each value < =100, nextPrime should return one of the following: 2  3, 5, 7, 11
   @Test
   public void test02_nextPrime() { // points = 1
      int from = 0;

      for (int i = 0; i < primes.length; i++) {
         for (int j = from; j < primes[i]; j++) {
            assertEquals("" + PrimeTools.nextPrime(j) + " " + primes[i],
                  primes[i], PrimeTools.nextPrime(j));
         }
         from = primes[i] + 1;
      }
   }

   // Use StringHash
   @Test
   public void test03_hashStringHash() { 
      Hashable<String> hashable = new StringHash();

         int stringHash = hashable.hash("Greetings From Mars");
   }

   // Use BetterHash
   @Test
   public void test04_hashBetterHash() { 
      Hashable<String> hashable = new BetterHash();

      int betterHash = hashable.hash("Greetings From Mars");
   }

   // Use myHash
   @Test
   public void test05_hashMyHash() { 

      Hashable<String> hashable = new MyHash();
      int myHash = hashable.hash("Greetings From Mars");
   }

   // See @BeforeClass tagged method for initialization.
   //
   // Opens smallTestFile.txt and creates a list of the words in the file
   private static ArrayList<String> list;

   @BeforeClass
   public static void buildList() throws FileNotFoundException {
      list = new ArrayList<String>();

      Scanner scanner = new Scanner(new File("smallTestFile.txt"));

      while(scanner.hasNext()) {
         list.add(scanner.next());
      }
   }

   @Test
   public void test06_avgChainLength() { 
      assertEquals(2.25, HashTools.avgChainLength(list, 4, new StringHash()), .001);
   }
   
   @Test
   public void test07_collisionsListSizeTable() { 
      assertEquals(5, HashTools.collisions(list, 4, new StringHash()));
   }

   @Test
   public void test08_maxCollisionsListHalfSizeTable() { 
      assertEquals(3, HashTools.maxCollisions(list, 4, new StringHash()));
   }

   @Test
   public void test09_unusedListSizeTable() { 
      assertEquals(1, HashTools.unused(list, 4, new StringHash()));
   }
}