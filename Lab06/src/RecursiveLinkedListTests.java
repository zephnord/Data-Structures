
/**
 * JUnit tests to verify the expected stack depths of the recursion.
 *
 * NOTE: These tests assume all methods work and only checks the stack depth.
 *       YOU ARE EXPECTED TO DEVELOP YOU OWN TESTS FOR THE OTHER BEHAVIORS!
 *
 * @author Paul Hatalsky
 * @version 1/27/2016 Developed for CPE 103 Lab 6 
 * 
 * @author Zeph Nord
 * @version 2/10/2017 Added additional Unit tests
 */
import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import java.util.NoSuchElementException;
import org.junit.runners.MethodSorters;
import org.junit.rules.*;
import org.junit.runner.Description;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecursiveLinkedListTests {
	@Rule
	public TestRule watcher = new TestWatcher() {
		protected void starting(Description description) {
			System.out.printf("\b\bStarting: %-48s", description.getMethodName());
		}
	};
	@Rule
	public Stopwatch sw = new Stopwatch() {
		String s;

		protected void finished(long nanos, Description description) {
			System.out.println(s);
		}

		protected void succeeded(long nanos, Description description) {
			s = "Passed" + " (" + runtime(TimeUnit.MILLISECONDS) + " ms)";
		}

		protected void failed(long nanos, Throwable e, Description description) {
			s = "FAILED" + " (" + runtime(TimeUnit.MILLISECONDS) + " ms)";
		}
	};

	// Very incomplete set of tests below!
	@Test
	public void test01_stackDepthAtConstruction() {
		int startDepth, depth;
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		startDepth = (new Throwable()).getStackTrace().length;

		list.size();
		depth = list.stackTrace().getStackTrace().length;

		assertEquals(startDepth + 2, depth);
	}

	@Test
	public void test02_addEndStackDepth() {
		int startDepth, depth;
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		startDepth = (new Throwable()).getStackTrace().length;

		list.add(1);
		depth = list.stackTrace().getStackTrace().length;
		assertEquals(startDepth + 2, depth);

		list.add(2);
		depth = list.stackTrace().getStackTrace().length;
		assertEquals(startDepth + 3, depth);

		list.add(3);
		depth = list.stackTrace().getStackTrace().length;
		assertEquals(startDepth + 4, depth);
	}

	@Test
	public void test03_addEndSizeStackDepth() {
		int startDepth, depth;
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		startDepth = (new Throwable()).getStackTrace().length;

		list.add(1);
		depth = list.stackTrace().getStackTrace().length;
		assertEquals(startDepth + 2, depth);

		list.size();
		depth = list.stackTrace().getStackTrace().length;
		assertEquals(startDepth + 3, depth);

		list.add(2);
		depth = list.stackTrace().getStackTrace().length;
		assertEquals(startDepth + 3, depth);

		list.size();
		depth = list.stackTrace().getStackTrace().length;
		assertEquals(startDepth + 4, depth);
	}

	@Test
	public void test04_addIndexStackDepth() {
		int startDepth, depth;
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		startDepth = (new Throwable()).getStackTrace().length;

		list.add(0, 1);
		depth = list.stackTrace().getStackTrace().length;
		assertEquals(startDepth + 2, depth);

		list.add(0, 2);
		depth = list.stackTrace().getStackTrace().length;
		assertEquals(startDepth + 2, depth);

		list.add(1, 3);
		depth = list.stackTrace().getStackTrace().length;
		assertEquals(startDepth + 3, depth);

		list.add(2, 3);
		depth = list.stackTrace().getStackTrace().length;
		assertEquals(startDepth + 4, depth);

		list.add(4, 3);
		depth = list.stackTrace().getStackTrace().length;
		assertEquals(startDepth + 6, depth);
	}

	@Test
	public void test05_getStackDepth() {
		int startDepth, depth;
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		startDepth = (new Throwable()).getStackTrace().length;

		list.add(1);
		list.add(2);
		list.add(3);

		list.get(0);
		depth = list.stackTrace().getStackTrace().length;
		assertEquals(startDepth + 2, depth);

		list.get(1);
		depth = list.stackTrace().getStackTrace().length;
		assertEquals(startDepth + 3, depth);
	}

	@Test
	public void test06_removeStackDepth() {
		int startDepth, depth;
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		startDepth = (new Throwable()).getStackTrace().length;

		list.add(1);
		list.add(2);
		list.add(3);

		list.remove(2);
		depth = list.stackTrace().getStackTrace().length;
		assertEquals(startDepth + 4, depth);

		list.remove(1);
		depth = list.stackTrace().getStackTrace().length;
		assertEquals(startDepth + 3, depth);
	}

	// get() Tests

	@Test(expected = IndexOutOfBoundsException.class)
	public void test07_get_negativeIndex() {
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);

		assertEquals((Integer) 1, list.get(-1));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void test08_get_sizeLessThanIndex() {
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);

		assertEquals((Integer) 1, list.get(4));
	}

	@Test
	public void test09_get_singleElement() {
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		list.addSimple(5);

		assertEquals((Integer) 5, list.get(0));
	}

	@Test
	public void test10_get_valid() {
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);

		assertEquals((Integer) 1, list.get(0));
		assertEquals((Integer) 3, list.get(2));
	}


	// addNodeAtIndex(Node, int, T) tests

	@Test
	public void test11_add_valid() {
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);

		list.add(1, 4);

		assertEquals((Integer) 4, list.get(1));
		assertEquals((Integer) 2, list.get(2));
		assertEquals((Integer) 3, list.get(3));
		assertEquals(4, list.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void test12_add_negativeIndex() {
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		list.add(-1, 5);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void test13_add_sizeLessThanIndex() {
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4, 4);
	}

	@Test
	public void test14_add_toEndOfList() {
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(3, 4);
	}

	@Test
	public void test15_add_toEmptyList() {
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		list.add(0, 1);
		assertEquals((Integer) 1, list.get(0));
	}

	// remove(int) tests

	@Test
	public void test16_removeIndex_Valid() {
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);

		assertEquals(3, list.size());
		assertEquals((Integer) 1, list.remove(0));
		assertEquals((Integer) 2, list.remove(0));
		assertEquals((Integer) 3, list.remove(0));
		assertEquals(0, list.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void test17_removeIndex_negativeIndex() {
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		list.remove(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test18_removeIndex_sizeLessThanIndex() {
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		list.remove(list.size());
		list.remove(list.size() + 1);
	}
	
	@Test
	public void test_19_removeIndex_fromEnd() {
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		assertEquals(4, list.size());
		assertEquals((Integer) 4, list.remove(list.size() - 1));
		assertEquals((Integer) 3, list.remove(list.size() - 1));
		assertEquals((Integer) 2, list.remove(list.size() - 1));
		assertEquals((Integer) 1, list.remove(list.size() - 1));
		
		assertEquals(0, list.size());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_20_removeIndex_fromEmpty() {
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		list.remove(0);
	}
	
	@Test
	public void test_21_removeIndex_fromSizeOne() {
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		list.addSimple(1);
		assertEquals((Integer) 1, list.remove(0));
		assertEquals(0, list.size());
	}
	
	@Test
	public void test_22_removeIndex_fromMiddle() {
		RecursiveLinkedList<Integer> list = new RecursiveLinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		
		assertEquals((Integer) 3, list.remove(2));		
		assertEquals((Integer) 1, list.get(0));
		assertEquals((Integer) 2, list.get(1));
		assertEquals((Integer) 4, list.get(2));
		assertEquals((Integer) 5, list.get(3));
		assertEquals((Integer) 6, list.get(4));
		
	}
}
