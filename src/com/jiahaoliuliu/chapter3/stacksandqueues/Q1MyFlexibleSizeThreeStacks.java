package com.jiahaoliuliu.chapter3.stacksandqueues;

import java.util.EmptyStackException;

public class Q1MyFlexibleSizeThreeStacks {

    static class StackPointer {
        Integer[] stackPointer;
        int lastItemPointerPosition ;

        public StackPointer(Integer[] stackPointer, int lastItemPointerPosition) {
            this.stackPointer = stackPointer;
            this.lastItemPointerPosition = lastItemPointerPosition;
        }
    }

    private final StackPointer[] stackPointersArray;

    private final Integer[] data;

    private final boolean[] usedPositions;

    private final int numberOfStacks;

    public Q1MyFlexibleSizeThreeStacks(int individualCapacity, int numberOfStacks) {
        this.data = new Integer[individualCapacity * numberOfStacks];
        this.numberOfStacks = numberOfStacks;
        stackPointersArray = new StackPointer[numberOfStacks];
        for (int i = 0; i < numberOfStacks; i++) {
            stackPointersArray[i] = new StackPointer(new Integer[individualCapacity * numberOfStacks], -1);
        }
        usedPositions = new boolean[individualCapacity * numberOfStacks];
    }

    public void push(int item, int stackNumber) {
        // Precondition
        if (stackNumber < 1 || stackNumber > numberOfStacks)
            throw new IllegalStateException("The stack number must be between 1 and " + numberOfStacks);

        int nextEmptyPosition = findNextEmptyPosition();
        // If it is full
        if (nextEmptyPosition == -1 ) throw new IllegalStateException("The stack is full");

        // Save the item
        data[nextEmptyPosition] = item;

        // Save the position of the item in the corresponding stack
        StackPointer stackPointer = stackPointersArray[stackNumber -1];
        stackPointer.lastItemPointerPosition++;
        stackPointer.stackPointer[stackPointer.lastItemPointerPosition] = nextEmptyPosition;
        usedPositions[stackPointer.stackPointer[stackPointer.lastItemPointerPosition]] = true;
    }

    public int pop(int stackNumber) {
        // if it is empty
        if (isEmpty()) throw new EmptyStackException();
        if (stackNumber < 1 || stackNumber > numberOfStacks)
            throw new IllegalStateException("The stack number must be between 1 and " + numberOfStacks);

        // Find the item
        StackPointer stackPointer = stackPointersArray[stackNumber - 1];
        int item = data[stackPointer.stackPointer[stackPointer.lastItemPointerPosition]];

        // Update the values
        usedPositions[stackPointer.stackPointer[stackPointer.lastItemPointerPosition]] = false;
        stackPointer.lastItemPointerPosition--;
        return item;
    }

    public int peek(int stackNumber) {
        // if it is empty
        if (isEmpty()) throw new EmptyStackException();
        if (stackNumber < 1 || stackNumber > numberOfStacks)
            throw new IllegalStateException("The stack number must be between 1 and " + numberOfStacks);

        // Find the item
        StackPointer stackPointer = stackPointersArray[stackNumber - 1];
        int item = data[stackPointer.stackPointer[stackPointer.lastItemPointerPosition]];

        // Pop the item
        return data[stackPointer.stackPointer[stackPointer.lastItemPointerPosition]];
    }

    public boolean isEmpty() {
        for (Boolean usedPosition : usedPositions) {
            if (usedPosition) return false;
        }

        return true;
    }

    private int findNextEmptyPosition() {
        for (int i = 0; i < usedPositions.length; i++) {
            if (!usedPositions[i]) {
                return i;
            }
        }
        // There is no empty positions
        return -1;
    }
}