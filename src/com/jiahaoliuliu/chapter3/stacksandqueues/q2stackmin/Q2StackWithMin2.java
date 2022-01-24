package com.jiahaoliuliu.chapter3.stacksandqueues.q2stackmin;

import java.util.Stack;

/**
 * Book solution 2 - A stack inside another stack to reduce the space complexity
 */
public class Q2StackWithMin2 extends Stack<Integer> {
    private Stack<Integer> s2;
    public Q2StackWithMin2() {
        s2 = new Stack<Integer>();
    }

    public void push(int value) {
        if (value <= min()) {
            s2.push(value);
        }
        super.push(value);
    }

    public Integer pop() {
        int value = super.pop();
        if (value == min()) {
            s2.pop();
        }
        return value;
    }

    public int min() {
        if (s2.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return s2.peek();
        }
    }

}
