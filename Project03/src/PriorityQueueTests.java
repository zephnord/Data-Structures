import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.MethodSorters;

/**
 * Contains the JUnit tests for the PriorityQueue class
 * 
 * @author Zeph Nord
 * @version Program3
 * @version CPE 103-03
 * @version Winter 2017
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PriorityQueueTests {
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

	// enqueue(T) tests

	@Test
	public void test00_enqueue_validMinimum() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.enqueue(1);
		pq.enqueue(2);
		pq.enqueue(0);

		assertEquals((Integer) 0, pq.peek());
	}

	@Test
	public void test01_enqueue_doubleSize() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		assertEquals(0, pq.size());
		// assertEquals(10, pq.returnQueueForTesting());
		for (int i = 0; i < 9; i++) {
			pq.enqueue(i);
		}
		assertEquals(9, pq.size());
		// assertEquals(10, pq.returnQueueForTesting());

		pq.enqueue(9);
		assertEquals(10, pq.size());
		// assertEquals(20, pq.returnQueueForTesting());
	}

	@Test
	public void test02_enqueue_validMaximum() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(true);
		pq.enqueue(0);
		pq.enqueue(1);
		pq.enqueue(3);

		assertEquals((Integer) 3, pq.peek());
	}

	@Test
	public void test12_enqueue_sizeLessThanArrayLengthMin() {
		Integer[] integerArray = { 2, 3, 5, 1, 9, 21, 0, 10 };
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(integerArray, 5, false);

		assertEquals((Integer) 1, pq.dequeue());
		assertEquals((Integer) 2, pq.dequeue());
		assertEquals((Integer) 3, pq.dequeue());
		assertEquals((Integer) 5, pq.dequeue());
		assertEquals((Integer) 9, pq.dequeue());
		// assertEquals((Integer) 5, integerArray[4]);
	}

	@Test
	public void test13_enqueue_sizeLessThanArrayLengthMax() {
		Integer[] integerArray = { 2, 3, 5, 1, 9, 21, 0, 10 };
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(integerArray, 5, true);

		assertEquals((Integer) 9, pq.dequeue());
		assertEquals((Integer) 5, pq.dequeue());
		assertEquals((Integer) 3, pq.dequeue());
		assertEquals((Integer) 2, pq.dequeue());
		assertEquals((Integer) 1, pq.dequeue());
		// assertEquals((Integer) 5, integerArray[4]);
	}
	// need more enqueue tests...

	// Constructor tests

	@Test
	public void test03_PriorityQueue_minArray() {
		Integer[] integerArray = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(integerArray, 11, false);

		assertEquals(11, pq.size());
		assertEquals((Integer) 0, pq.peek());
	}

	@Test
	public void test04_PriorityQueue_maxArray() {
		Integer[] integerArray = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(integerArray, 11, true);

		assertEquals(11, pq.size());
		assertEquals((Integer) 10, pq.peek());
	}

	// dequeue() tests

	@Test
	public void test05_dequeue_min() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.enqueue(1);
		pq.enqueue(2);
		pq.enqueue(3);
		pq.enqueue(4);
		pq.enqueue(0);

		assertEquals((Integer) 0, pq.dequeue());
	}

	@Test
	public void test06_dequeue_max() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(true);
		pq.enqueue(1);
		pq.enqueue(2);
		pq.enqueue(3);
		pq.enqueue(4);
		pq.enqueue(0);

		assertEquals((Integer) 4, pq.dequeue());
	}

	@Test
	public void test07_dequeue_minArray() {
		Integer[] integerArray = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(integerArray, 11, false);

		assertEquals((Integer) 0, pq.dequeue());
		assertEquals(10, pq.size());
	}

	@Test(expected = NoSuchElementException.class)
	public void test08_dequeue_emptyQueue() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.dequeue();
	}

	@Test
	public void test09_dequeue_dequeueThenPeek() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(false);
		pq.enqueue(1);
		pq.enqueue(2);
		pq.enqueue(3);
		pq.enqueue(4);
		pq.enqueue(0);

		assertEquals((Integer) 0, pq.dequeue());
		assertEquals((Integer) 1, pq.peek());
		assertEquals((Integer) 1, pq.dequeue());
		assertEquals((Integer) 2, pq.peek());
	}

	// sort tests

	@Test
	public void test10_sort() {
		Integer[] integerArray = { 2, 3, 5, 1, 0, 21, 54, 10 };
		PriorityQueue.sort(integerArray, integerArray.length);
		/*
		 * for(int i = 0; i < integerArray.length; i++) {
		 * System.out.println(integerArray[i]); }
		 */
		assertEquals((Integer) 0, integerArray[0]);
		assertEquals((Integer) 54, integerArray[7]);
	}

	// Write a test to ensure array sort is not calling a max queue
	@Test
	public void test11_sort_max() {
		Integer[] integerArray = { 2, 3, 5, 1, 0, 21, 54, 10 };
		PriorityQueue.sort(integerArray, integerArray.length);
		/*
		 * for(int i = 0; i < integerArray.length; i++) {
		 * System.out.println(integerArray[i]); }
		 */
		assertFalse((Integer) 54 == integerArray[0]);
		assertFalse((Integer) 0 == integerArray[7]);
	}

	@Test
	public void test14_kth_min() {
		Integer[] integerArray = { 2, 3, 5, 1, 0, 21, 54, 10 };
		assertEquals((Integer) 21, PriorityQueue.kth(integerArray, integerArray.length, 2));
		assertEquals((Integer) 54, PriorityQueue.kth(integerArray, integerArray.length, 1));
		assertEquals((Integer) 5, PriorityQueue.kth(integerArray, integerArray.length, 4));
		assertEquals((Integer) 3, PriorityQueue.kth(integerArray, integerArray.length, 5));
		assertFalse((Integer) 3 == PriorityQueue.kth(integerArray, integerArray.length, 4));
	}

	@Test
	public void test15_kth_max() {
		Integer[] integerArray = { 2, 3, 5, 1, 0, 21, 54, 10 };
		assertEquals((Integer) 1, PriorityQueue.kth(integerArray, integerArray.length, 7));
		assertEquals((Integer) 0, PriorityQueue.kth(integerArray, integerArray.length, 8));
	}

	@Test
	public void test16_kth_sizeLessThanLength() {
		Integer[] integerArray = { 2, 3, 5, 1, 0, 21, 54, 10 };
		assertEquals((Integer) 3, PriorityQueue.kth(integerArray, integerArray.length - 2, 3));
	}
	
	@Test
	public void test17_kth_minBigOh() {
		Integer tmp = -8812984;
		Integer[] integerArray = { -722312, 123124, 33424, -8812984, 213123123,-900000, 54, 10 };
		assertEquals(tmp, PriorityQueue.kth(integerArray, integerArray.length, integerArray.length));
	}
}
