package ua.edu.ucu.immutable;

import lombok.Getter;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue {
    @Getter
    private ImmutableLinkedList queue;

    public Queue() {
        queue = new ImmutableLinkedList();
    }

    public Queue(Object[] elements) {

        queue = new ImmutableLinkedList(elements);
    }

    public Object peek() {
        // Returns the object at the beginning of the Queue without removing it
        return queue.getFirst();
    }

    public Object dequeue() {
        // Removes and returns the object at the beginning of the Queue.
        Object target = queue.getFirst();
        queue = queue.removeFirst();
        return target;
    }

    public void enqueue(Object e) {
        // Adds an object to the end of the Queue.
        queue = queue.addLast(e);
    }

    public <T> Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            public T next() {
                if (queue.isEmpty()) {
                    throw new NoSuchElementException();
                }
                return (T) dequeue();
            }
        };
    }
}
