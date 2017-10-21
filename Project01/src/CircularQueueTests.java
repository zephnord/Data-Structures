import static org.junit.Assert.*;
import org.junit.Test;
import java.util.NoSuchElementException;

/**
 * Contains JUnit Tests for the CircularQueue class
 * @author Zeph Nord
 * @version Lab01 
 * @version CPE 103-03
 * @version Winter 2017
 */

public class CircularQueueTests {
	CircularQueue<Integer> queue = new CircularQueue<Integer>();

    @Test
    public void testNewQueueIsEmpty() {
    	CircularQueue<Integer> queue = new CircularQueue<Integer>();
        assertEquals(queue.size(), 0);
    }

    @Test
    public void testInsertsToEmptyQueue() {
    	CircularQueue<Integer> queue = new CircularQueue<Integer>();
        int numberOfInserts = 6;
        for (int i = 0; i < numberOfInserts; i++) {
            queue.enqueue(11);
        }
        assertTrue(queue.size() != 0);
        assertEquals(queue.size(), numberOfInserts);
    }

    @Test
    public void testEnqueueThenDequeue() {
    	CircularQueue<Integer> queue = new CircularQueue<Integer>();
        int add = 50;
        queue.enqueue(add);
        assertTrue(queue.dequeue() == add);
    }

    @Test
    public void testEnqueueThenPeek() {
    	CircularQueue<Integer> queue = new CircularQueue<Integer>();
        int add = 27;
        queue.enqueue(add);
        int size = 1;
        assertEquals((int)queue.peek(), add);
        assertEquals(queue.size(), size);
    }

    @Test
    public void testFiftyInThenFiftyOut() {
    	CircularQueue<Integer> queue = new CircularQueue<Integer>();
        for (int i = 0; i < 50; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 50; i++) {
            assertEquals(((Integer)queue.dequeue()).intValue(), i);
        }
    }

    @Test
    public void testRemovingDownToEmpty() {
    	CircularQueue<Integer> queue = new CircularQueue<Integer>();
        int numberOfRemoves = (int)(Math.random() * 20 + 1);
        for (int i = 0; i < numberOfRemoves; i++) {
            queue.enqueue(41);
        }
        for (int i = 0; i < numberOfRemoves; i++) {
            queue.dequeue();
        }
        assertTrue(queue.size() == 0);
    }
    
    @Test
    public void testDoubleSize() {
    	CircularQueue<Integer> queue = new CircularQueue<Integer>(10);
    	int size = 20;
    	for(int i = 0; i < size; i++) {
    		queue.enqueue(i);
    	}
    	assertEquals(queue.size(), size);
    	
    	for(int i = 0; i < size; i++) {
    		queue.dequeue();
    	}
    	assertEquals(queue.size(), 0);
    }
    
    @SuppressWarnings("unused")
	@Test(expected=CircularQueue.MyException.class)
    public void testMyException() {
    	CircularQueue<Card> queue = new CircularQueue<Card>(0);
    }
    /*
    @Test
    public void addTwentyElementsCheckPrint() {
    	CircularQueue<Integer> queue = new CircularQueue<Integer>();
        
        for (int i = 0; i < 20; i++) {
            queue.enqueue(i);
        }
        Object[] tmpArr = queue.unusualMethodForTestingPurposesOnly();
        for (int i = 0; i < tmpArr.length; i++) {
            System.out.println(tmpArr[i]);
        }
    }
    */
    
    @Test
    public void testFillQueueEmptyQueueThenEnqueue() {
    	CircularQueue<Integer> queue = new CircularQueue<Integer>(52);
    	int size = queue.size();
    	for(int i = 0; i < size; i++) {
    		queue.enqueue(5);
    	}
    	for(int i = 0; i < size; i++) {
    		queue.dequeue();
    	}
    	for(int i = 0; i < size; i++) {
    		queue.enqueue(5);
    	}
    }

    @Test(expected=NoSuchElementException.class)
    public void testRemoveOnEmptyQueue() {
    	CircularQueue<Integer> queue = new CircularQueue<Integer>();
        assertTrue(queue.size() == 0);
        queue.dequeue();
    }

    @Test(expected=NoSuchElementException.class)
    public void testPeekIntoEmptyQueue() {
    	CircularQueue<Integer> queue = new CircularQueue<Integer>();
        assertTrue(queue.size() == 0);
        queue.dequeue();
    }
    
    @Test(expected=RuntimeException.class)
    public void testInitializeNonPositiveCapacity() {
    	@SuppressWarnings("unused")
		CircularQueue<Integer> queue = new CircularQueue<Integer>(-1);
    }
    
    
    //need to be able to wrap around my circular queue and not grow it.
    
    @Test
    public void wrapNotGrow() {
    	CircularQueue<Integer> queue = new CircularQueue<Integer>(5);
    	for(int i = 0; i < 7; i++) {
    		queue.enqueue(10);
    		queue.dequeue();
    	}
    	assertEquals(5, queue.unusualMethodForTestingPurposesOnly().length);
    }
    
    @Test
    public void ensureQueueSize() {
    	int size = 10;
    	CircularQueue<Integer> queue = new CircularQueue<Integer>(size);    	
    	for(int i = 0; i < size; i++) {
    		queue.enqueue(i);
    	}
    	assertEquals(size, queue.unusualMethodForTestingPurposesOnly().length);
    }
}