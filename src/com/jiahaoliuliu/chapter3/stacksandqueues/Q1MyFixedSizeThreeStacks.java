package com.jiahaoliuliu.chapter3.stacksandqueues;


import java.util.EmptyStackException;

public class Q1MyFixedSizeThreeStacks {

    private final Integer[] data;
    private final Integer[] lastItemPositions;
    private final int individualCapacity;

    public Q1MyFixedSizeThreeStacks(int capacity) {
        this.individualCapacity = capacity;
        this.data = new Integer[capacity * 3];
        this.lastItemPositions = new Integer[] {-1, capacity - 1, (capacity * 2) - 1};
    }

    /* stack = 1, 2, 3						*/
    public void push (int item, int stackNumber) {
        if (stackNumber < 0 || stackNumber > 3) throw new IllegalArgumentException("The stack number must be 1, 2 or 3");
        int lastItemPosition = lastItemPositions[stackNumber - 1];
        // if the stack is already full
        if (lastItemPosition >= individualCapacity * stackNumber) {
            throw new IllegalStateException("Stack " + stackNumber + " is full");
        }

        lastItemPosition++;
        data[lastItemPosition] = item;
        lastItemPositions[stackNumber - 1] = lastItemPosition;
    }

    // Check if the stack is empty
    public int pop(int stackNumber) {
        if (stackNumber < 0 || stackNumber > 3) throw new IllegalArgumentException("The stack number must be 1, 2 or 3");
        int lastItemPosition = lastItemPositions[stackNumber - 1];
        if (lastItemPosition < (stackNumber - 1) * individualCapacity) {
            throw new EmptyStackException();
        }
        int item = data[lastItemPosition];
        lastItemPosition--;
        lastItemPositions[stackNumber - 1] = lastItemPosition;
        return item;
    }
    public int peek(int stackNumber) {
        if (stackNumber < 0 || stackNumber > 3) throw new IllegalArgumentException("The stack number must be 1, 2 or 3");
        int lastItemPosition = lastItemPositions[stackNumber - 1];
        if (lastItemPosition < (stackNumber - 1) * individualCapacity) {
            throw new EmptyStackException();
        }
        return data[lastItemPosition];
    }

    public boolean isEmpty(int stackNumber) {
        if (stackNumber < 0 || stackNumber > 3) throw new IllegalArgumentException("The stack number must be 1, 2 or 3");
        int lastItemPosition = lastItemPositions[stackNumber - 1];
        return lastItemPosition < (stackNumber - 1) * individualCapacity;
    }
}
