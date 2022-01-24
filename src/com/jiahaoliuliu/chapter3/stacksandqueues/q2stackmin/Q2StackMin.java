package com.jiahaoliuliu.chapter3.stacksandqueues.q2stackmin;

import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.assertEquals;

/**
 * How would you design a stack which, in addition to push and pop, has a function min
 * which returns the minimum element? Psh, po and min should all operate in O(1) time.
 * <ul>Hint
 *  <li>27: Observe that the minimum element doesn't change very often. It only changes
 *          when a smaller element is added, or hen the smallest element is popped</li>
 *  <li>59: What if we keep tack of extra data at each stack node? What sort of data might
 *          make it easier to solve the problem?</li>
 *  <li>78: Consider having each node know the minimum of its "substack", all the elements
 *          beneath it, including itself</li>
 * </ul>
 */
public class Q2StackMin {

    @Test
    public void test1() {
        // Given
        Q2MyStackMin q2MyStackMin = new Q2MyStackMin();
        q2MyStackMin.push(1);

        // When
        int result = q2MyStackMin.min();

        // Then
        assertEquals(1, result);
    }

    @Test
    public void test2() {
        // Given
        Q2MyStackMin q2MyStackMin = new Q2MyStackMin();
        q2MyStackMin.push(1);
        q2MyStackMin.push(2);

        // When
        int result = q2MyStackMin.min();

        // Then
        assertEquals(1, result);
    }

    @Test
    public void test3() {
        // Given
        Q2MyStackMin q2MyStackMin = new Q2MyStackMin();
        q2MyStackMin.push(1);
        q2MyStackMin.push(2);
        q2MyStackMin.push(3);

        // When
        int result = q2MyStackMin.min();

        // Then
        assertEquals(1, result);
    }

    @Test
    public void test4() {
        // Given
        Q2MyStackMin q2MyStackMin = new Q2MyStackMin();
        q2MyStackMin.push(2);
        q2MyStackMin.push(1);
        q2MyStackMin.push(3);

        // When
        int result = q2MyStackMin.min();

        // Then
        assertEquals(1, result);
    }

    @Test
    public void test5() {
        // Given
        Q2MyStackMin q2MyStackMin = new Q2MyStackMin();
        q2MyStackMin.push(3);
        q2MyStackMin.push(2);
        q2MyStackMin.push(1);

        // When
        int result = q2MyStackMin.min();

        // Then
        assertEquals(1, result);
    }

    @Test
    public void test6() {
        // Given
        Q2MyStackMin q2MyStackMin = new Q2MyStackMin();
        q2MyStackMin.push(3);
        q2MyStackMin.push(2);
        q2MyStackMin.push(1);
        q2MyStackMin.pop();

        // When
        int result = q2MyStackMin.min();

        // Then
        assertEquals(2, result);
    }

    @Test
    public void test7() {
        // Given
        Q2MyStackMin q2MyStackMin = new Q2MyStackMin();
        q2MyStackMin.push(3);
        q2MyStackMin.push(2);
        q2MyStackMin.push(1);
        q2MyStackMin.pop();
        q2MyStackMin.pop();

        // When
        int result = q2MyStackMin.min();

        // Then
        assertEquals(3, result);
    }

    @Test(expected = EmptyStackException.class)
    public void test8() {
        // Given
        Q2MyStackMin q2MyStackMin = new Q2MyStackMin();
        q2MyStackMin.push(3);
        q2MyStackMin.push(2);
        q2MyStackMin.push(1);
        q2MyStackMin.pop();
        q2MyStackMin.pop();
        q2MyStackMin.pop();

        // When
        int result = q2MyStackMin.min();

        // Then
        // Expect exception
    }

    @Test
    public void test9() {
        // Given
        Q2StackWithMin q2StackWithMin = new Q2StackWithMin();
        q2StackWithMin.push(1);

        // When
        int result = q2StackWithMin.min();

        // Then
        assertEquals(1, result);
    }

    @Test
    public void test10() {
        // Given
        Q2StackWithMin q2StackWithMin = new Q2StackWithMin();
        q2StackWithMin.push(1);
        q2StackWithMin.push(2);

        // When
        int result = q2StackWithMin.min();

        // Then
        assertEquals(1, result);
    }

    @Test
    public void test11() {
        // Given
        Q2StackWithMin q2StackWithMin = new Q2StackWithMin();
        q2StackWithMin.push(1);
        q2StackWithMin.push(2);
        q2StackWithMin.push(3);

        // When
        int result = q2StackWithMin.min();

        // Then
        assertEquals(1, result);
    }

    @Test
    public void test12() {
        // Given
        Q2StackWithMin q2StackWithMin = new Q2StackWithMin();
        q2StackWithMin.push(2);
        q2StackWithMin.push(1);
        q2StackWithMin.push(3);

        // When
        int result = q2StackWithMin.min();

        // Then
        assertEquals(1, result);
    }

    @Test
    public void test13() {
        // Given
        Q2StackWithMin q2StackWithMin = new Q2StackWithMin();
        q2StackWithMin.push(3);
        q2StackWithMin.push(2);
        q2StackWithMin.push(1);

        // When
        int result = q2StackWithMin.min();

        // Then
        assertEquals(1, result);
    }

    @Test
    public void test14() {
        // Given
        Q2StackWithMin q2StackWithMin = new Q2StackWithMin();
        q2StackWithMin.push(3);
        q2StackWithMin.push(2);
        q2StackWithMin.push(1);
        q2StackWithMin.pop();

        // When
        int result = q2StackWithMin.min();

        // Then
        assertEquals(2, result);
    }

    @Test
    public void test15() {
        // Given
        Q2StackWithMin q2StackWithMin = new Q2StackWithMin();
        q2StackWithMin.push(3);
        q2StackWithMin.push(2);
        q2StackWithMin.push(1);
        q2StackWithMin.pop();
        q2StackWithMin.pop();

        // When
        int result = q2StackWithMin.min();

        // Then
        assertEquals(3, result);
    }

    @Test
    public void test16() {
        // Given
        Q2StackWithMin q2StackWithMin = new Q2StackWithMin();
        q2StackWithMin.push(3);
        q2StackWithMin.push(2);
        q2StackWithMin.push(1);
        q2StackWithMin.pop();
        q2StackWithMin.pop();
        q2StackWithMin.pop();

        // When
        int result = q2StackWithMin.min();

        // Then
        assertEquals(Integer.MAX_VALUE, result);
    }
}
