package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public final class ImmutableLinkedList implements ImmutableList {
    private final int size;
    private Node head;


    public ImmutableLinkedList() {
        size = 0;
        head = null;
    }

    public ImmutableLinkedList(Object[] elements) {
        this.size = elements.length;
        Node[] givenList = new Node[this.size];
        for (int idx = 0; idx < this.size; idx++) {
            Node data = new Node(elements[idx], null);
            givenList[idx] = data;
            if (idx > 0) {
                givenList[idx - 1].nextNode = data;
            }
        }
        if (this.size > 0) {
            this.head = givenList[0];
        }
    }

    public static class Node {
        private Node nextNode;
        private final Object data;

        public Node(Object data, Node nextNode) {
            this.data = data;
            this.nextNode = nextNode;
        }
    }

    public void idxCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return add(size, e);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {

        return addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(size, c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Object[] updated = new Object[size + c.length];
        Node node = head;
        int start = 0, end = 0;
        while (start != index) {
            updated[start] = node.data;
            node = node.nextNode;
            start++;
        }
        while (end < c.length) {
            updated[start + end] = c[end];
            end++;
        }
        while (node != null) {
            updated[start + end] = node.data;
            node = node.nextNode;
            start++;
        }
        return new ImmutableLinkedList(updated);
    }

    @Override
    public Object get(int index) {
        idxCheck(index);
        Node node = head;
        Object target = null;
        int idx = 0;
        while (node != null) {
            if (idx == index) {
                target = node.data;
                break;
            }
            idx++;
            node = node.nextNode;
        }
        return target;
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        idxCheck(index);
        Object[] updated = new Object[size - 1];
        Node node = head;
        int start = 0, end = 0;
        while (node != null) {
            if (start != index) {
                updated[end] = node.data;
                end++;
            }
            node = node.nextNode;
            start++;
        }
        return new ImmutableLinkedList(updated);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size()];
        Node node = head;
        int idx = 0;
        while (node != null) {
            arr[idx] = node.data;
            node = node.nextNode;
            idx++;
        }
        return arr;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    @Override
    public int indexOf(Object e) {
        Node node = head;
        int i = 0;
        while (node != null) {
            if (node.data.equals(e)) {
                return i;
            }
            node = node.nextNode;
            i++;
        }
        return -1;
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        idxCheck(index);
        Object[] updated = new Object[size];
        Node node = head;
        int idx = 0;
        while (node != null) {
            if (idx == index) {
                updated[idx] = e;
            } else {
                updated[idx] = node.data;
            }
            node = node.nextNode;
            idx++;
        }
        return new ImmutableLinkedList(updated);
    }


    public ImmutableLinkedList addFirst(Object e) {
        //додає елемент у початок зв'язаного списку
        return add(0, e);
    }


    public ImmutableLinkedList addLast(Object e) {
        //додає елемент у кінець зв'язаного списку
        return add(size(), e);
    }

    public Object getFirst() {
        return get(0);
    }

    public Object getLast() {
        return get(size() - 1);
    }

    public ImmutableLinkedList removeFirst() {
        //видаляє перший елемент
        return remove(0);
    }

    public ImmutableLinkedList removeLast() {
        //видаляє останній елемент
        return remove(size() - 1);
    }

}
