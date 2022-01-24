package com.jiahaoliuliu.chapter3.stacksandqueues.q1threeinone;

import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.*;

/**
 * Describe how you could use a single array to implement three stacks
 * <ul>Hints:
 *  <li>2: A stack is simply a data structure in which the most recently added elements are removed first.
 *         Can you simulate a single stack using an array? Remember that there are many possible solutions,
 *         and there are tradeoffs of each.</li>
 *  <li>12: We would simulate three stacks in an array by just allocating the first third of the array to
 *          the first stack, the second third to the second stack, and the final third to the third stack.
 *          One might actually be much bigger than the other, though. Can we be more flexible with the division?</li>
 *  <li>38: If you want to allow for flexible divisions, you can shift the stack around. Can you ensure that all
 *          available capacity is used?</li>
 *  <li>58: Try thinking about the array as circular, such that the end of the array "wraps around" to the
 *          start of the array</li>
 * </ul>
 */
public class Q1ThreeInOne {

    @Test
    public void testSingleStack1() {
        // Given
        int capacity = 10;
        Q1MyFixedSizeStack q1MyFixedSizeStack = new Q1MyFixedSizeStack(capacity);

        // When
        boolean result = q1MyFixedSizeStack.isEmpty();

        // Then
        assertTrue(result);
    }

    @Test
    public void testSingleStack2() {
        // Given
        int capacity = 10;
        Q1MyFixedSizeStack q1MyFixedSizeStack = new Q1MyFixedSizeStack(capacity);

        // When
        q1MyFixedSizeStack.push(1);

        // Then
        assertFalse(q1MyFixedSizeStack.isEmpty());
    }

    @Test
    public void testSingleStack3() {
        // Given
        int capacity = 10;
        Q1MyFixedSizeStack q1MyFixedSizeStack = new Q1MyFixedSizeStack(capacity);
        q1MyFixedSizeStack.push(1);

        // When
        int item = q1MyFixedSizeStack.pop();

        // Then
        assertTrue(q1MyFixedSizeStack.isEmpty());
        assertEquals(1, item);
    }

    @Test
    public void testSingleStack4() {
        // Given
        int capacity = 10;
        Q1MyFixedSizeStack q1MyFixedSizeStack = new Q1MyFixedSizeStack(capacity);
        q1MyFixedSizeStack.push(1);

        // When
        int item = q1MyFixedSizeStack.peek();

        // Then
        assertFalse(q1MyFixedSizeStack.isEmpty());
        assertEquals(1, item);
    }

    @Test(expected = EmptyStackException.class)
    public void testSingleStack5() {
        // Given
        int capacity = 10;
        Q1MyFixedSizeStack q1MyFixedSizeStack = new Q1MyFixedSizeStack(capacity);

        // When
        q1MyFixedSizeStack.pop();

        // Then
        // Expect exception
    }

    @Test(expected = IllegalStateException.class)
    public void testSingleStack6() {
        // Given
        int capacity = 10;
        Q1MyFixedSizeStack q1MyFixedSizeStack = new Q1MyFixedSizeStack(capacity);
        q1MyFixedSizeStack.push(1);
        q1MyFixedSizeStack.push(2);
        q1MyFixedSizeStack.push(3);
        q1MyFixedSizeStack.push(4);
        q1MyFixedSizeStack.push(5);
        q1MyFixedSizeStack.push(6);
        q1MyFixedSizeStack.push(7);
        q1MyFixedSizeStack.push(8);
        q1MyFixedSizeStack.push(9);
        q1MyFixedSizeStack.push(10);

        // When
        q1MyFixedSizeStack.push(11);

        // Then
        // Expect exception
    }

    @Test
    public void testTripleStack1() {
        // Given
        int capacity = 5;
        Q1MyFixedSizeThreeStacks q1MyFixedSizeThreeStacks = new Q1MyFixedSizeThreeStacks(capacity);

        // When
        boolean isFirstStackEmpty = q1MyFixedSizeThreeStacks.isEmpty(1);
        boolean isSecondEmpty = q1MyFixedSizeThreeStacks.isEmpty(2);
        boolean isThirdEmpty = q1MyFixedSizeThreeStacks.isEmpty(3);

        // Then
        assertTrue(isFirstStackEmpty);
        assertTrue(isSecondEmpty);
        assertTrue(isThirdEmpty);
    }

    @Test
    public void testTripleStack2() {
        // Given
        int capacity = 5;
        Q1MyFixedSizeThreeStacks q1MyFixedSizeThreeStacks = new Q1MyFixedSizeThreeStacks(capacity);
        q1MyFixedSizeThreeStacks.push(1, 1);
        q1MyFixedSizeThreeStacks.push(2, 2);
        q1MyFixedSizeThreeStacks.push(3, 3);

        // When
        boolean isFirstStackEmpty = q1MyFixedSizeThreeStacks.isEmpty(1);
        boolean isSecondEmpty = q1MyFixedSizeThreeStacks.isEmpty(2);
        boolean isThirdEmpty = q1MyFixedSizeThreeStacks.isEmpty(3);

        // Then
        assertFalse(isFirstStackEmpty);
        assertFalse(isSecondEmpty);
        assertFalse(isThirdEmpty);
    }

    @Test
    public void testTripleStack3() {
        // Given
        int capacity = 5;
        Q1MyFixedSizeThreeStacks q1MyFixedSizeThreeStacks = new Q1MyFixedSizeThreeStacks(capacity);
        q1MyFixedSizeThreeStacks.push(1, 1);
        q1MyFixedSizeThreeStacks.push(2, 2);
        q1MyFixedSizeThreeStacks.push(3, 3);

        // When
        int firstStackItem = q1MyFixedSizeThreeStacks.pop(1);
        int secondStackItem = q1MyFixedSizeThreeStacks.pop(2);
        int thirdStackItem = q1MyFixedSizeThreeStacks.pop(3);

        // Then
        assertTrue(q1MyFixedSizeThreeStacks.isEmpty(1));
        assertEquals(1, firstStackItem);

        assertTrue(q1MyFixedSizeThreeStacks.isEmpty(2));
        assertEquals(2, secondStackItem);

        assertTrue(q1MyFixedSizeThreeStacks.isEmpty(3));
        assertEquals(3, thirdStackItem);
    }

    @Test
    public void testTripleStack4() {
        // Given
        int capacity = 5;
        Q1MyFixedSizeThreeStacks q1MyFixedSizeThreeStacks = new Q1MyFixedSizeThreeStacks(capacity);
        q1MyFixedSizeThreeStacks.push(1, 1);
        q1MyFixedSizeThreeStacks.push(2, 2);
        q1MyFixedSizeThreeStacks.push(3, 3);

        // When
        int firstStackItem = q1MyFixedSizeThreeStacks.peek(1);
        int secondStackItem = q1MyFixedSizeThreeStacks.peek(2);
        int thirdStackItem = q1MyFixedSizeThreeStacks.peek(3);

        // Then
        assertFalse(q1MyFixedSizeThreeStacks.isEmpty(1));
        assertEquals(1, firstStackItem);

        assertFalse(q1MyFixedSizeThreeStacks.isEmpty(2));
        assertEquals(2, secondStackItem);

        assertFalse(q1MyFixedSizeThreeStacks.isEmpty(3));
        assertEquals(3, thirdStackItem);
    }

    @Test
    public void testTripleStackFlexible1() {
        // Given
        int capacity = 5;
        int numberOfStacks = 3;
        Q1MyFlexibleSizeThreeStacks q1MyFlexibleSizeThreeStacks = new Q1MyFlexibleSizeThreeStacks(capacity, numberOfStacks);

        // When
        boolean isStackEmpty = q1MyFlexibleSizeThreeStacks.isEmpty();

        // Then
        assertTrue(isStackEmpty);
    }

    @Test
    public void testTripleStackFlexible2() {
        // Given
        int capacity = 5;
        int numberOfStacks = 3;
        Q1MyFlexibleSizeThreeStacks q1MyFlexibleSizeThreeStacks = new Q1MyFlexibleSizeThreeStacks(capacity, numberOfStacks);
        q1MyFlexibleSizeThreeStacks.push(1, 1);

        // When
        boolean isStackEmpty = q1MyFlexibleSizeThreeStacks.isEmpty();

        // Then
        assertFalse(isStackEmpty);
    }

    @Test
    public void testTripleStackFlexible3() {
        // Given
        int capacity = 5;
        int numberOfStacks = 3;
        Q1MyFlexibleSizeThreeStacks q1MyFlexibleSizeThreeStacks = new Q1MyFlexibleSizeThreeStacks(capacity, numberOfStacks);
        q1MyFlexibleSizeThreeStacks.push(2, 2);

        // When
        boolean isStackEmpty = q1MyFlexibleSizeThreeStacks.isEmpty();

        // Then
        assertFalse(isStackEmpty);
    }

    @Test
    public void testTripleStackFlexible4() {
        // Given
        int capacity = 5;
        int numberOfStacks = 3;
        Q1MyFlexibleSizeThreeStacks q1MyFlexibleSizeThreeStacks = new Q1MyFlexibleSizeThreeStacks(capacity, numberOfStacks);
        q1MyFlexibleSizeThreeStacks.push(3, 3);

        // When
        boolean isStackEmpty = q1MyFlexibleSizeThreeStacks.isEmpty();

        // Then
        assertFalse(isStackEmpty);
    }

    @Test
    public void testTripleStackFlexible5() {
        // Given
        int capacity = 5;
        int numberOfStacks = 3;
        Q1MyFlexibleSizeThreeStacks q1MyFlexibleSizeThreeStacks = new Q1MyFlexibleSizeThreeStacks(capacity, numberOfStacks);
        q1MyFlexibleSizeThreeStacks.push(1, 1);
        q1MyFlexibleSizeThreeStacks.push(2, 2);
        q1MyFlexibleSizeThreeStacks.push(3, 3);

        // When
        int firstStackItem = q1MyFlexibleSizeThreeStacks.peek(1);
        int secondStackItem = q1MyFlexibleSizeThreeStacks.peek(2);
        int thirdStackItem = q1MyFlexibleSizeThreeStacks.peek(3);

        // Then
        assertFalse(q1MyFlexibleSizeThreeStacks.isEmpty());

        assertEquals(1, firstStackItem);
        assertEquals(2, secondStackItem);
        assertEquals(3, thirdStackItem);
    }

    @Test
    public void testTripleStackFlexible6() {
        // Given
        int capacity = 5;
        int numberOfStacks = 3;
        Q1MyFlexibleSizeThreeStacks q1MyFlexibleSizeThreeStacks = new Q1MyFlexibleSizeThreeStacks(capacity, numberOfStacks);
        q1MyFlexibleSizeThreeStacks.push(1, 1);
        q1MyFlexibleSizeThreeStacks.push(2, 1);
        q1MyFlexibleSizeThreeStacks.push(3, 1);
        q1MyFlexibleSizeThreeStacks.push(4, 1);
        q1MyFlexibleSizeThreeStacks.push(5, 1);
        q1MyFlexibleSizeThreeStacks.push(6, 1);

        // When
        int firstItem = q1MyFlexibleSizeThreeStacks.pop(1);
        int secondItem = q1MyFlexibleSizeThreeStacks.pop(1);
        int thirdItem = q1MyFlexibleSizeThreeStacks.pop(1);
        int fourthItem = q1MyFlexibleSizeThreeStacks.pop(1);
        int fifthItem = q1MyFlexibleSizeThreeStacks.pop(1);
        int sixthItem = q1MyFlexibleSizeThreeStacks.pop(1);

        // Then
        assertTrue(q1MyFlexibleSizeThreeStacks.isEmpty());

        assertEquals(6, firstItem);
        assertEquals(5, secondItem);
        assertEquals(4, thirdItem);
        assertEquals(3, fourthItem);
        assertEquals(2, fifthItem);
        assertEquals(1, sixthItem);
    }

    @Test(expected = IllegalStateException.class)
    public void testTripleStackFlexible7Full() {
        // Given
        int capacity = 5;
        int numberOfStacks = 3;
        Q1MyFlexibleSizeThreeStacks q1MyFlexibleSizeThreeStacks = new Q1MyFlexibleSizeThreeStacks(capacity, numberOfStacks);
        q1MyFlexibleSizeThreeStacks.push(1, 1);
        q1MyFlexibleSizeThreeStacks.push(2, 1);
        q1MyFlexibleSizeThreeStacks.push(3, 1);
        q1MyFlexibleSizeThreeStacks.push(4, 1);
        q1MyFlexibleSizeThreeStacks.push(5, 1);
        q1MyFlexibleSizeThreeStacks.push(6, 1);
        q1MyFlexibleSizeThreeStacks.push(7, 1);
        q1MyFlexibleSizeThreeStacks.push(8, 1);
        q1MyFlexibleSizeThreeStacks.push(9, 1);
        q1MyFlexibleSizeThreeStacks.push(10, 1);
        q1MyFlexibleSizeThreeStacks.push(11, 1);
        q1MyFlexibleSizeThreeStacks.push(12, 1);
        q1MyFlexibleSizeThreeStacks.push(13, 1);
        q1MyFlexibleSizeThreeStacks.push(14, 1);
        q1MyFlexibleSizeThreeStacks.push(15, 1);

        // When
        q1MyFlexibleSizeThreeStacks.push(16, 1);

        // Then
    }
}
