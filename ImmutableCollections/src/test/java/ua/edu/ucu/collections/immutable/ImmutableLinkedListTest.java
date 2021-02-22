package ua.edu.ucu.collections.immutable;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ImmutableLinkedListTest {

    @Test
    public void testAdd() {
        Object[] emptyArray = new Object[]{};
        Object[] oneArray = new Object[]{5};
        Object[] fullArray = new Object[]{3, 5, 1, -5, 78, 765};
        ImmutableLinkedList arr = new ImmutableLinkedList();
        ImmutableLinkedList newArr;
        newArr = arr.add(oneArray);
        assertArrayEquals(arr.toArray(), emptyArray);
        assertEquals(1, newArr.size());
        arr.addAll(fullArray);
        assertEquals(0, arr.size());
        ImmutableLinkedList secArray;
        secArray = arr.addAll(fullArray);
        assertArrayEquals(fullArray, secArray.toArray());
        ImmutableLinkedList target;
        target = secArray.add(5, 1);
        assertArrayEquals(new Object[]{3, 5, 1, -5, 78, 1, 765}, target.toArray());
        assertArrayEquals(new Object[]{3, 5, 1, -5, 78, 1, 765, 5}, target.addAll(7, oneArray).toArray());
        assertArrayEquals(new Object[]{3, 5, 1, -5, 78, 1, 765}, target.addAll(7, emptyArray).toArray());
    }

    @Test
    public void testGet() {
        Object[] fullArray = new Object[]{3, 5, 1, -5, 78, 765};
        ImmutableLinkedList arr = new ImmutableLinkedList();
        assertEquals(1, arr.addAll(fullArray).get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexCheck() {
        ImmutableLinkedList arr = new ImmutableLinkedList();
        arr.add(2, 34);
        arr.addAll(1, new Object[]{4, 6, 5});
        arr.remove(1);
        arr.set(5, 4);
        arr.removeFirst();
        arr.removeLast();
        arr.getLast();
        arr.idxCheck(4);

    }

    @Test
    public void testRemove() {
        ImmutableLinkedList lst = new ImmutableLinkedList(new Object[]{5, 6, 7, 9});
        assertArrayEquals(new Object[]{6, 7, 9}, lst.remove(0).toArray());
        assertEquals(4, lst.size());
    }

    @Test
    public void testSet() {
        ImmutableLinkedList lst = new ImmutableLinkedList(new Object[]{5, 6, 7, 9});
        assertArrayEquals(new Object[]{-5, 6, 7, 9}, lst.set(0, -5).toArray());
        assertArrayEquals(new Object[]{5, 6, 7, 9}, lst.toArray());
    }

    @Test
    public void testIndex() {
        ImmutableLinkedList lst = new ImmutableLinkedList(new Object[]{5, 6, 6, 7, 9});
        assertEquals(1, lst.indexOf(6));
        assertEquals(-1, lst.indexOf(987));
    }

    @Test
    public void testSize() {
        ImmutableLinkedList empty = new ImmutableLinkedList();
        assertEquals(0, empty.size());
        ImmutableLinkedList full;
        full = empty.addAll(new Object[]{4, 5, 6, 7});
        assertEquals(4, full.size());
    }

    @Test
    public void testClear() {
        ImmutableLinkedList empty = new ImmutableLinkedList();
        assertArrayEquals(new Object[]{}, empty.clear().toArray());
        ImmutableLinkedList full;
        full = empty.addAll(new Object[]{4, 5, 6, 7});
        assertArrayEquals(new Object[]{}, full.clear().toArray());
    }

    @Test
    public void testEmpty() {
        ImmutableLinkedList empty = new ImmutableLinkedList();
        assertTrue(empty.isEmpty());
        ImmutableLinkedList full;
        full = empty.addAll(new Object[]{4, 5, 6, 7});
        assertFalse(full.isEmpty());
        assertTrue(empty.isEmpty());
    }

    @Test
    public void testConverting() {
        ImmutableLinkedList empty = new ImmutableLinkedList();
        assertArrayEquals(new Object[]{}, empty.toArray());
        ImmutableLinkedList full;
        full = empty.addAll(new Object[]{4, 5, 6, 7});
        assertArrayEquals(new Object[]{4, 5, 6, 7}, full.toArray());
    }

    @Test
    public void testString() {
        ImmutableLinkedList arr = new ImmutableLinkedList(new Object[]{4, 5, 6, 7});
        Object[] tmp = new Object[]{4, 5, 6, 7};
        assertEquals(Arrays.toString(tmp), arr.toString());
    }

    @Test
    public void testAddFirst() {
        ImmutableLinkedList arr = new ImmutableLinkedList(new Object[]{4, 5, 6, 7});
        assertArrayEquals(new Object[]{-765, 4, 5, 6, 7}, arr.addFirst(-765).toArray());
        ImmutableLinkedList empty = new ImmutableLinkedList();
        assertArrayEquals(new Object[]{-765}, empty.addFirst(-765).toArray());
    }

    @Test
    public void testAddLast() {
        ImmutableLinkedList arr = new ImmutableLinkedList(new Object[]{4, 5, 6, 7});
        assertArrayEquals(new Object[]{4, 5, 6, 7, -765}, arr.addLast(-765).toArray());
        ImmutableLinkedList empty = new ImmutableLinkedList();
        assertArrayEquals(new Object[]{-765}, empty.addLast(-765).toArray());
    }

    @Test
    public void testGetFirst() {
        ImmutableLinkedList arr = new ImmutableLinkedList(new Object[]{4, 5, 6, 7});
        assertEquals(4, arr.getFirst());
    }

    @Test
    public void testGetLast() {
        ImmutableLinkedList arr = new ImmutableLinkedList(new Object[]{4, 5, 6, 7});
        assertEquals(7, arr.getLast());
    }

    @Test
    public void testRemoveFirst() {
        ImmutableLinkedList arr = new ImmutableLinkedList(new Object[]{5, 7, 8, 1, 112});
        assertArrayEquals(new Object[]{7, 8, 1, 112}, arr.removeFirst().toArray());
    }

    @Test
    public void testRemoveLast() {
        ImmutableLinkedList arr = new ImmutableLinkedList(new Object[]{5, 7, 8, 1, 112});
        assertArrayEquals(new Object[]{5, 7, 8, 1}, arr.removeLast().toArray());
    }
}
