package com.jiahaoliuliu.chapter3.stacksandqueues.q2stackmin;

import java.util.Collections;
import java.util.Stack;

/**
 * Book solution 1. We kept track of the minimum at each state.
 */
public class Q2StackWithMin extends Stack<NodeWithMin> {
    public void push(int value) {
        int newMin = Math.min(value, min());
        super.push(new NodeWithMin(value, newMin));
    }

    public int min() {
        if (this.isEmpty()) {
            return Integer.MAX_VALUE; // Error value
        } else {
            return peek().min;
        }
    }
}

class NodeWithMin {
    public int value;
    public int min;
    public NodeWithMin(int v, int min) {
        value = v;
        this.min = min;
    }
}