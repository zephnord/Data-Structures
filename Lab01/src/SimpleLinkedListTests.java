
/**
 * JUnit tests for SimpleLinkedList.
 *
 * @author Paul Hatalsky 
 * @author Zeph Nord - Added additional tests
 * @version Lab 1
 */
import org.junit.*;
import static org.junit.Assert.*;
//import java.lang.reflect.*;

public class SimpleLinkedListTests {
	@Test
	public void testSize_empty() {
		SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
		assertEquals(0, list.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAdd_atConstuctionOutOfBounds() {
		SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
		list.add(25, null);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemove_atConstruction() {
		SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
		list.remove(0);
	}

	//Add Tests
	@Test
	public void testAddSize() {
		SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();

		list.add(5);
		list.add(10);
		list.add(15);
		list.add(3, 20);
		listEquals(list, new Integer[] { 5, 10, 15, 20 });
	}

	@Test
	public void testAdd() {
		SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();

		list.add(1);
		list.add(10);
		list.add(7);
		listEquals(list, new Integer[] { 1, 10, 7 });
	}
	
	@Test
	public void testAdd_StringValues() {
		SimpleLinkedList<String> list = new SimpleLinkedList<String>();
		
		list.add("Hello");
		list.add("World");
		listEquals(list, new String[] {"Hello", "World"});
	}

	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAdd_OutOfBounds() {
		SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();

		list.add(1);
		list.add(10);
		list.add(7);
		list.add(5, list.size() + 1);
		listEquals(list, new Integer[] { 1, 10, 7 });
	}

	@Test
	public void testRemove() {
		SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();

		list.add(5);
		list.add(10);
		list.add(15);
		list.add(3, 20);
		list.remove(3);
		listEquals(list, new Integer[] { 5, 10, 15});
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemove_NonEmptyPositive() {
		SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();

		list.add(5);
		list.add(10);
		list.add(15);
		list.add(3, 20);
		list.remove(4);		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemove_NonEmptyNegative() {
		SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();

		list.add(5);
		list.add(10);
		list.add(15);
		list.add(3, 20);
		list.remove(-1);		
	}
	

	private void listEquals(SimpleLinkedList<?> list, Object[] a) {
		assertEquals(a.length, list.size());
		for (int i = 0; i < a.length; i++) {
			assertEquals(a[i], list.get(i));
		}
	}
	
	@Test
	public void test_get() {
		SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();

		list.add(5);
		list.add(10);
		list.add(15);
		list.add(3, 20);
		assertEquals((Integer) 5, list.get(0));		
	}
}