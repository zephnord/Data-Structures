import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * @author Zeph Nord
 * @version Program02
 * @version CPE 103-03
 * @version Winter 2017
 */
public class SimpleLinkedStackTests {

	@Test
	public void testPush() {
		SimpleLinkedStack<Integer> stack = new SimpleLinkedStack<Integer>();
		
		stack.push(1);
		assertEquals(1, stack.size());
		assertEquals((Integer) 1, stack.peek());
	}
	
	@Test
	public void testPop() {
		SimpleLinkedStack<Integer> stack = new SimpleLinkedStack<Integer>();  
		
		stack.push(1);
		assertEquals((Integer) 1, stack.pop());
		assertEquals(0, stack.size());
	}
	
	@Test
	public void testLargeLinkedStack() {
		SimpleLinkedStack<Integer> stack = new SimpleLinkedStack<Integer>();  
		int size = 50;
		
		for(int i = 0; i < size; i++) {
			stack.push(i);
		}
		assertEquals((Integer) 49, stack.peek());
		assertEquals(size, stack.size());
		
		for(int i = 0; i < 20; i++) {
			stack.pop();
		}
		
		assertEquals((Integer) 29, stack.peek());		
	}
	
	@Test
	public void testEmptyLinkedStack() {
		SimpleLinkedStack<Integer> stack = new SimpleLinkedStack<Integer>();  
		int size = 50;
		for(int i = 0; i < size; i++) {
			stack.push(i);
		}
		
		for(int i = 0; i < size; i++) {
			stack.pop();
		}
		
		assertEquals(0, stack.size());
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testPeekFromEmptyLinkedStack() {
		SimpleLinkedStack<Integer> stack = new SimpleLinkedStack<Integer>();  

		stack.peek();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testPopFromEmptyLinkedStack() {
		SimpleLinkedStack<Integer> stack = new SimpleLinkedStack<Integer>();  

		stack.pop();
	}
}
