package com.jiahaoliuliu.chapter3.stacksandqueues;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;

/**
 * write a program to sort a stack such that the smallest items are on the top. You can use
 * an additional temporary stack, but you may not copy the elements into any other data structure
 * (such as an array). The stack supports the following operations: push, pop, peek and isEmpty
 * <ul>Hints
 *  <li>15: One way of sorting an array is to iterate through the array and insert each element into a new
 *          array in sorted order. Can you do this ith a stack.</li>
 *  <li>32: Imagine your secondary stack is sorted. Can you insert elements into it in sorted order?
 *          You might need some extra storage. What could you use for extra storage?</li>
 *  <li>43: Keep the secondary stack in sorted order, with the biggest element on the top. Use the primary
 *          stack for additional storage.</li>
 * </ul>
 *
 */
public class Q5SortStack {

    private void sortStack(Stack<Integer> s) {
        Stack<Integer> tmpStack = new Stack<>();
        int tmpItem;
        while (!s.isEmpty()) {
            if (tmpStack.isEmpty()) {
                tmpStack.push(s.pop());
                continue;
            }
            tmpItem = s.pop();
            if (tmpItem >= tmpStack.peek()) {
                tmpStack.push(tmpItem);
                continue;
            }

            while (!tmpStack.isEmpty() && tmpStack.peek() >= tmpItem) {
                s.push(tmpStack.pop());
            }
            tmpStack.push(tmpItem);
        }

        while (!tmpStack.isEmpty()) {
            s.push(tmpStack.pop());
        }
    }

    /**
     * Book solution 1
     */
    private void bookSol1Sort(Stack<Integer> s) {
        Stack<Integer> r = new Stack<Integer>();
        while (!s.isEmpty()) {
            /* Insert each element in s in sorted order into r.*/
            int tmp = s.pop();
            while(!r.isEmpty() && r.peek() > tmp) {
                s.push(r.pop());
            }
            r.push(tmp);
        }

        /* Copy the elements from r back into s */
        while (!r.isEmpty()) {
            s.push(r.pop());
        }
    }

    @Test
    public void test1() {
        // Given
        Stack<Integer> myStack = new Stack<>();

        // When
        sortStack(myStack);

        // Then
        assertTrue(myStack.isEmpty());
    }

    @Test
    public void test2() {
        // Given
        Stack<Integer> myStack = new Stack<Integer>();
        myStack.push(1);

        // When
        sortStack(myStack);

        // Then
        assertFalse(myStack.isEmpty());
        int result = myStack.pop();
        assertEquals(1, result);
    }

    @Test
    public void test3() {
        // Given
        Stack<Integer> myStack = new Stack<Integer>();
        myStack.push(1);
        myStack.push(2);

        // When
        sortStack(myStack);

        // Then
        assertFalse(myStack.isEmpty());
        int result = myStack.pop();
        assertEquals(1, result);
        int result2 = myStack.pop();
        assertEquals(2, result2);
    }

    @Test
    public void test4() {
        // Given
        Stack<Integer> myStack = new Stack<Integer>();
        myStack.push(1);
        myStack.push(3);
        myStack.push(5);
        myStack.push(2);

        // When
        sortStack(myStack);

        // Then
        assertFalse(myStack.isEmpty());
        int result = myStack.pop();
        assertEquals(1, result);
        int result2 = myStack.pop();
        assertEquals(2, result2);
        int result3 = myStack.pop();
        assertEquals(3, result3);
        int result4 = myStack.pop();
        assertEquals(5, result4);
    }

    @Test
    public void test5() {
        // Given
        Stack<Integer> myStack = new Stack<>();

        // When
        bookSol1Sort(myStack);

        // Then
        assertTrue(myStack.isEmpty());
    }

    @Test
    public void test6() {
        // Given
        Stack<Integer> myStack = new Stack<Integer>();
        myStack.push(1);

        // When
        bookSol1Sort(myStack);

        // Then
        assertFalse(myStack.isEmpty());
        int result = myStack.pop();
        assertEquals(1, result);
    }

    @Test
    public void test7() {
        // Given
        Stack<Integer> myStack = new Stack<Integer>();
        myStack.push(1);
        myStack.push(2);

        // When
        bookSol1Sort(myStack);

        // Then
        assertFalse(myStack.isEmpty());
        int result = myStack.pop();
        assertEquals(1, result);
        int result2 = myStack.pop();
        assertEquals(2, result2);
    }

    @Test
    public void test8() {
        // Given
        Stack<Integer> myStack = new Stack<Integer>();
        myStack.push(1);
        myStack.push(3);
        myStack.push(5);
        myStack.push(2);

        // When
        bookSol1Sort(myStack);

        // Then
        assertFalse(myStack.isEmpty());
        int result = myStack.pop();
        assertEquals(1, result);
        int result2 = myStack.pop();
        assertEquals(2, result2);
        int result3 = myStack.pop();
        assertEquals(3, result3);
        int result4 = myStack.pop();
        assertEquals(5, result4);
    }

}
