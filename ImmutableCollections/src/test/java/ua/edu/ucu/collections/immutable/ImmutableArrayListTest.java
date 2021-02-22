package ua.edu.ucu.collections.immutable;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ImmutableArrayListTest {

    @Test
    public void testAdd() {
        Object[] emptyArray = new Object[]{};
        Object[] oneArray = new Object[]{5};
        Object[] fullArray = new Object[]{3, 5, 1, -5, 78, 765};
        ImmutableList arr = new ImmutableArrayList();
        ImmutableList newArr;
        newArr = arr.add(oneArray);
        assertArrayEquals(arr.toArray(), emptyArray);
        assertEquals(1, newArr.size());
        arr.addAll(fullArray);
        assertEquals(0, arr.size());
        ImmutableList secArray;
        secArray = arr.addAll(fullArray);
        assertArrayEquals(fullArray, secArray.toArray());
        ImmutableList target;
        target = secArray.add(5, 1);
        assertArrayEquals(new Object[]{3, 5, 1, -5, 78, 1, 765}, target.toArray());
        assertArrayEquals(new Object[]{3, 5, 1, -5, 78, 1, 765, 5}, target.addAll(7, oneArray).toArray());
        assertArrayEquals(new Object[]{3, 5, 1, -5, 78, 1, 765}, target.addAll(7, emptyArray).toArray());
    }

    @Test
    public void testGet() {
        Object[] fullArray = new Object[]{3, 5, 1, -5, 78, 765};
        ImmutableList arr = new ImmutableArrayList();
        assertEquals(1, arr.addAll(fullArray).get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexCheck() {
        ImmutableList arr = new ImmutableArrayList();
        arr.add(2, 34);
        arr.addAll(1, new Object[]{4, 6, 5});
        arr.get(4);
        arr.remove(1);
        arr.set(5, 4);
    }

    @Test
    public void testRemove() {
        ImmutableList lst = new ImmutableArrayList(new Object[]{5, 6, 7, 9});
        assertArrayEquals(new Object[]{6, 7, 9}, lst.remove(0).toArray());
        assertEquals(4, lst.size());
    }

    @Test
    public void testSet() {
        ImmutableList lst = new ImmutableArrayList(new Object[]{5, 6, 7, 9});
        assertArrayEquals(new Object[]{-5, 6, 7, 9}, lst.set(0, -5).toArray());
        assertArrayEquals(new Object[]{5, 6, 7, 9}, lst.toArray());
    }

    @Test
    public void testIndex() {
        ImmutableList lst = new ImmutableArrayList(new Object[]{5, 6, 6, 7, 9});
        assertEquals(1, lst.indexOf(6));
        assertEquals(-1, lst.indexOf(987));
    }

    @Test
    public void testSize() {
        ImmutableList empty = new ImmutableArrayList();
        assertEquals(0, empty.size());
        ImmutableList full;
        full = empty.addAll(new Object[]{4, 5, 6, 7});
        assertEquals(4, full.size());
    }

    @Test
    public void testClear() {
        ImmutableList empty = new ImmutableArrayList();
        assertArrayEquals(new Object[]{}, empty.clear().toArray());
        ImmutableList full;
        full = empty.addAll(new Object[]{4, 5, 6, 7});
        assertArrayEquals(new Object[]{}, full.clear().toArray());
    }

    @Test
    public void testEmpty() {
        ImmutableList empty = new ImmutableArrayList();
        assertTrue(empty.isEmpty());
        ImmutableList full;
        full = empty.addAll(new Object[]{4, 5, 6, 7});
        assertFalse(full.isEmpty());
        assertTrue(empty.isEmpty());
    }

    @Test
    public void testConverting() {
        ImmutableList empty = new ImmutableArrayList();
        assertArrayEquals(new Object[]{}, empty.toArray());
        ImmutableList full;
        full = empty.addAll(new Object[]{4, 5, 6, 7});
        assertArrayEquals(new Object[]{4, 5, 6, 7}, full.toArray());
    }

    @Test
    public void testString() {
        ImmutableList arr = new ImmutableArrayList(new Object[]{4, 5, 6, 7});
        Object[] tmp = new Object[]{4, 5, 6, 7};
        assertEquals(Arrays.toString(tmp), arr.toString());
    }
}
