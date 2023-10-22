package com.example.demo.stack;

// this is an interface for a stack. 
public interface Stackable<E> { // this is a generic interface. 
    // E is a generic type that will be replaced by a real type when the class is used. 
    public boolean isEmpty(); // this is a method that returns a boolean value.
    // isEmpty() returns true if the stack is empty, false otherwise.
    public void push(E newItem); // this is a method that returns nothing.
    // push() adds a new item to the top of the stack.
    public E pop() throws StackException; // this is a method that returns an E.
    // pop() removes the top item from the stack and returns it.
    public void popAll(); // this is a method that returns nothing.
    // popAll() removes all items from the stack.
    public E peek() throws StackException; // this is a method that returns an E.
    // peek() returns the top item from the stack without removing it.
}
