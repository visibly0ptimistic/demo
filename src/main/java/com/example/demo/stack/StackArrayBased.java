package com.example.demo.stack;

import java.util.ArrayList;
import java.util.List;

public class StackArrayBased<E> implements Stackable<E> {

    // array to hold the elements of the stack
    private Object[] items; // Java does not allow Generic arrays
    private int top; // index of the top
    private int MAX = 5;  // Default value

    public void setMaxSize(int newSize) {
        if (newSize < 0) {
            throw new IllegalArgumentException("Size must be positive");
        }
        if (newSize < this.top + 1) {
            throw new StackException("New size cannot be less than current number of elements in the stack.");
        }
        this.MAX = newSize;
        Object[] newItems = new Object[MAX];
        for (int i = 0; i <= this.top; i++) {
            newItems[i] = this.items[i];
        }
        this.items = newItems;
    }    

    public StackArrayBased() {
        this.items = new Object[MAX];
        this.top = -1; // not a valid index -- tells us the stack is empty
    }

    @Override
    public boolean isEmpty() {
        return (this.top == -1); // this.top < 0
    }

    public boolean isFull() {
        return (this.top == MAX - 1);
    }

    @Override
    public void popAll() {
        this.top = -1;
        this.items = new Object[MAX];
    }

    @Override
    public void push(E newItem) throws StackException {
        if (this.isFull())
            throw new StackException("Stack is at maximum capacity.");

        this.top++;
        this.items[top] = newItem;

    }

    @Override
    public E pop() throws StackException {
        if (this.isEmpty())
            throw new StackException("Stack is empty!");

        E item = (E) this.items[this.top];

        // optional free memory
        this.items[this.top] = null;

        this.top--;
        return item;
    }

    @Override
    public E peek() throws StackException {
        if (this.isEmpty())
            throw new StackException("Stack is empty!");

        return (E) this.items[this.top];
    }

    public List<E> toList() {
        List<E> list = new ArrayList<>();
        for (int i = top; i >= 0; i--) {
            list.add((E) items[i]);
        }
        return list;
    }
}
