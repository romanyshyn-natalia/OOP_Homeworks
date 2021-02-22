package ua.edu.ucu.collections;

import org.junit.Test;
import ua.edu.ucu.collections.immutable.ImmutableLinkedList;


import static org.junit.Assert.*;

public class StackTest {

    @Test
    public void testPeek() {
        Stack st = new Stack(new ImmutableLinkedList(new Object[]{4, 3, 5, 6}));
        assertEquals(6, st.peek());
    }

    @Test
    public void testPop() {
        Stack st = new Stack(new ImmutableLinkedList(new Object[]{4, 3, 5, 6}));
        assertEquals(6, st.pop());
    }

    @Test
    public void testPush() {
        Stack st = new Stack(new ImmutableLinkedList(new Object[]{4, 3, 5, 6}));
        st.push(567);
        assertArrayEquals(new Object[]{4, 3, 5, 6, 567}, st.getStack().toArray());
    }

}
