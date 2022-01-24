package com.jiahaoliuliu.chapter3.stacksandqueues.q1threeinone;

import java.util.EmptyStackException;

public class Q1MyFixedSizeStack {

    private Integer[] data;
    private int lastItemPosition = -1;

    public Q1MyFixedSizeStack(int capacity) {
        data = new Integer[capacity];
    }

    public void push (int item) {
        if (lastItemPosition >= data.length - 1) {
            throw new IllegalStateException("Stack is full");
        }

        lastItemPosition++;
        data[lastItemPosition] = item;
    }

    public int pop() {
        if (lastItemPosition < 0) {
            throw new EmptyStackException();
        }
        int item = data[lastItemPosition];
        lastItemPosition--;
        return item;
    }
    public int peek() {
        if (lastItemPosition < 0) {
            throw new EmptyStackException();
        }
        return data[lastItemPosition];
    }

    public boolean isEmpty() {
        return lastItemPosition < 0;
    }
}