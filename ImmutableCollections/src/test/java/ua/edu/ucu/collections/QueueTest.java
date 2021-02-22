package ua.edu.ucu.collections;

import org.junit.Test;
import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

import static org.junit.Assert.*;

public class QueueTest {

    @Test
    public void testPeek() {
        Queue myQueue = new Queue(new ImmutableLinkedList(new Object[]{4, 6, 2, 7, 90}));
        assertEquals(4, myQueue.peek());
    }

    @Test
    public void testDequeue() {
        Queue myQueue = new Queue(new ImmutableLinkedList(new Object[]{4, 6, 2, 7, 90}));
        assertEquals(4, myQueue.dequeue());
    }

    @Test
    public void testEnqueue() {
        Queue myQueue = new Queue(new ImmutableLinkedList(new Object[]{4, 6, 2, 7, 90}));
        myQueue.enqueue(356);
        assertArrayEquals(new Object[]{4, 6, 2, 7, 90, 356}, myQueue.getQueue().toArray());
    }


}
