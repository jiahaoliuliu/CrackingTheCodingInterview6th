package com.jiahaoliuliu.chapter3.stacksandqueues.q1threeinone;

import java.util.EmptyStackException;

/**
 * Book solution 1. Fixed Division.
 * Divide the array in three equal parts and allow the individual stack to grow in that limited space.
 * For stack 1, we will use [0, n/3).
 * For stack 2, we will use [n/3, 2n/3).
 * For stack 3, we will use [2n/3, n)
 */
public class Q1FixedMultiStack {
    static class FullStackException extends RuntimeException{}

    private static final int NUMBER_OF_STACKS = 3;
    private final int stackCapacity;
    private final int[] values;
    private final int[] sizes;

    public Q1FixedMultiStack(int stackSize) {
        stackCapacity = stackSize;
        values = new int[stackSize * NUMBER_OF_STACKS];
        sizes = new int[NUMBER_OF_STACKS];
    }

    /* Push value onto stack. */
    public void push(int stackNum, int value) throws FullStackException {
        /* Check that we have space for next element */
        if (isFull(stackNum)) {
            throw new FullStackException();
        }

        /* Increment stack pointer and then update top value */
        sizes[stackNum]++;
        values[indexOfTop(stackNum)] = value;
    }

    /* Pop item from top stack */
    public int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            throw new EmptyStackException();
        }

        int topIndex = indexOfTop(stackNum);
        int value = values[topIndex]; // Get top
        values[topIndex] = 0; // Clear
        sizes[stackNum]--; // Shrink
        return value;
    }

    /* Return top element */
    public int peek(int stackNum) {
        if (isEmpty(stackNum)) {
            throw new EmptyStackException();
        }
        return values[indexOfTop(stackNum)];
    }

    /* Return if stack is empty */
    public boolean isEmpty(int stackNum) {
        return sizes[stackNum] == 0;
    }

    /* Return if stack is full */
    public boolean isFull(int stackNum) {
        return sizes[stackNum] == stackCapacity;
    }

    /* Return index of the top of the stack */
    private int indexOfTop(int stackNum) {
        int offset = stackNum * stackCapacity;
        int size = sizes[stackNum];
        return offset + size - 1;
    }
}
