package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {

    private ImmutableLinkedList stack;

    public Stack(ImmutableLinkedList stack) {
        this.stack = stack;
    }

    public ImmutableLinkedList getStack() {
        return this.stack;
    }

    public Object peek() {
        //Returns the object from the top of the Stack without removing it
        return stack.getLast();
    }

    public Object pop() {
        // Removes and returns the object from the top of the Stack
        Object target = stack.getLast();
        stack = stack.removeLast();
        return target;
    }

    public void push(Object e) {
        //Adds an object to the the top of the Stack
        stack = stack.addLast(e);
    }

}
