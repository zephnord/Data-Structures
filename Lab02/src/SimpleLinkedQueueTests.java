import static org.junit.Assert.*;
import org.junit.Test;
import java.util.NoSuchElementException;

/**
 * Contains JUnit Tests for the SimpleLinkedQueue class
 * @author Zeph Nord
 * @version Lab01 
 * @version CPE 103-03
 * @version Winter 2017
 */

public class SimpleLinkedQueueTests {
 

    @Test
    public void testNewQueueIsEmpty() {
    	SimpleLinkedQueue<Integer> queue = new SimpleLinkedQueue<Integer>();
        assertEquals(queue.size(), 0);
    }

    @Test
    public void testInsertsToEmptyQueue() {
    	SimpleLinkedQueue<Integer> queue = new SimpleLinkedQueue<Integer>();
        int numberOfInserts = 6;
        for (int i = 0; i < numberOfInserts; i++) {
            queue.enqueue(11);
        }
        assertTrue(queue.size() != 0);
        assertEquals(queue.size(), numberOfInserts);
    }

    @Test
    public void testEnqueueThenDequeue() {
    	SimpleLinkedQueue<Integer> queue = new SimpleLinkedQueue<Integer>();
        int add = 50;
        queue.enqueue(add);
        assertTrue(queue.dequeue() == add);
    }

    @Test
    public void testEnqueueThenPeek() {
    	SimpleLinkedQueue<Integer> queue = new SimpleLinkedQueue<Integer>();
        int add = 27;
        queue.enqueue(add);
        int size = 1;
        assertTrue(queue.peek() == add);
        assertEquals(queue.size(), size);
    }

    @Test
    public void testFiftyInThenFiftyOut() {
    	SimpleLinkedQueue<Integer> queue = new SimpleLinkedQueue<Integer>();
        for (int i = 0; i < 50; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 50; i++) {
            assertEquals(((Integer)queue.dequeue()).intValue(), i);
        }
    }

    @Test
    public void testRemovingDownToEmpty() {
    	SimpleLinkedQueue<Integer> queue = new SimpleLinkedQueue<Integer>();
        int numberOfRemoves = (int)(Math.random() * 20 + 1);
        for (int i = 0; i < numberOfRemoves; i++) {
            queue.enqueue(41);
        }
        for (int i = 0; i < numberOfRemoves; i++) {
            queue.dequeue();
        }
        assertTrue(queue.size() == 0);
    }

    @Test(expected=NoSuchElementException.class)
    public void testRemoveOnEmptyQueue() {
    	SimpleLinkedQueue<Integer> queue = new SimpleLinkedQueue<Integer>();
        assertTrue(queue.size() == 0);
        queue.dequeue();
    }

    @Test(expected=NoSuchElementException.class)
    public void testPeekIntoEmptyQueue() {
    	SimpleLinkedQueue<Integer> queue = new SimpleLinkedQueue<Integer>();
        assertTrue(queue.size() == 0);
        queue.dequeue();
    }
}
