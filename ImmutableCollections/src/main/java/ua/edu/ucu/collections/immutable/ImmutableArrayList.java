package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public final class ImmutableArrayList implements ImmutableList {
    private final Object[] myArray;
    private final int size;


    public ImmutableArrayList() {
        this.size = 0;
        myArray = new Object[0];
    }

    public ImmutableArrayList(Object[] arrayElements) {
        myArray = new Object[arrayElements.length];
        System.arraycopy(arrayElements, 0, myArray, 0,
                arrayElements.length);
        size = arrayElements.length;
    }

    @Override
    public ImmutableList add(Object e) {
        return addAll(new Object[]{e});
    }

    @Override
    public ImmutableList add(int index, Object e) {
        return addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(size, c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        idxCheck(index);
        Object[] newArrayList = new Object[size + c.length];
        System.arraycopy(myArray, 0, newArrayList, 0, index);
        System.arraycopy(c, 0, newArrayList, index, c.length);
        System.arraycopy(myArray, index, newArrayList, index + c.length,
                size - index);
        return new ImmutableArrayList(newArrayList);
    }

    @Override
    public Object get(int index) {
        idxCheck(index);
        return myArray[index];
    }

    private void idxCheck(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public ImmutableList remove(int index) {
        idxCheck(index);
        Object[] newArrayList = new Object[size - 1];
        System.arraycopy(myArray, 0, newArrayList, 0, index);
        System.arraycopy(myArray, index + 1, newArrayList, index,
                size - index - 1);
        return new ImmutableArrayList(newArrayList);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        idxCheck(index);
        Object[] newArrayList = new Object[size];
        System.arraycopy(myArray, 0, newArrayList, 0, size);
        newArrayList[index] = e;
        return new ImmutableArrayList(newArrayList);
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < size; i++) {
            if (myArray[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ImmutableList clear() {
        Object[] newArrayList = new Object[0];
        return new ImmutableArrayList(newArrayList);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        System.arraycopy(myArray, 0, arr, 0, size);
        return arr;
    }

    public String toString() {
        return Arrays.toString(toArray());
    }
}

