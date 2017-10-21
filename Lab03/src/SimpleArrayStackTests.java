/**
 * JUnit tests for SimpleArrayStack class
 * @author Zeph Nord
 * @version CPE 103-03
 * @version Winter 2017
 * @version Lab03
 */
import static org.junit.Assert.*;
import org.junit.*;
import java.util.NoSuchElementException;
import org.junit.runners.MethodSorters;
import org.junit.rules.*;
import org.junit.runner.Description;
import java.util.concurrent.TimeUnit;
import java.io.*;


public class SimpleArrayStackTests
{
   @Test
   public void test01_simpleTests() {
      SimpleArrayStack<Integer> s = new SimpleArrayStack<Integer>();
      
      assertEquals(10, s.capacity());      
      assertEquals(0, s.size());
      s.push(100);
      assertEquals((Integer)100, s.peek());
      assertEquals((Integer)100, s.pop());
   }
   
   @Test(expected=NoSuchElementException.class)
   public void test02_peekAtConstruction() {
      SimpleArrayStack<Integer> s = new SimpleArrayStack<Integer>();
      s.peek();
   }
   
   @Test
   public void pushThenPeek() {
	   SimpleArrayStack<Integer> stack = new SimpleArrayStack<Integer>();
	   
	   stack.push(5);
	   assertEquals((Integer) 5, stack.peek());
	   assertEquals((Integer) 5, stack.pop());
	   assertEquals(0, stack.size());  
   }
   
   @Test
   public void pushToDouble() {
	   SimpleArrayStack<Integer> stack = new SimpleArrayStack<Integer>();
	   int size = 15;
	   for(int i = 0; i < size; i++) {
		   stack.push(i);
	   }
	   
	   assertEquals(size, stack.size());
	   assertEquals(20, stack.capacity());
   }
   
   @Test
   public void pushPopThenPush() {
	   SimpleArrayStack<Integer> stack = new SimpleArrayStack<Integer>();
	   int size = 10;
	   for(int i = 0; i < size; i++) {
		   stack.push(i);
	   }
	   for(int i = 0; i < size; i++) {
		   assertEquals((Integer) (size - i - 1), stack.pop());
	   }
	   
	   assertEquals(0, stack.size());
	   assertEquals(10, stack.capacity());
   }
   
   @Test(expected=NoSuchElementException.class)
   public void popFromEmpty() {
	   SimpleArrayStack<Integer> stack = new SimpleArrayStack<Integer>();
	   stack.pop();
   }
}