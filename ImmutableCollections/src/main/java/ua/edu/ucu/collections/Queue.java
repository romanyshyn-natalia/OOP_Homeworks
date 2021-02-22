package ua.edu.ucu.collections;

import lombok.Getter;
import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    @Getter
    private ImmutableLinkedList queue;

    public Queue(ImmutableLinkedList queue) {
        this.queue = queue;
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

    void enqueue(Object e) {
        // Adds an object to the end of the Queue.
        queue = queue.addLast(e);
    }
}
