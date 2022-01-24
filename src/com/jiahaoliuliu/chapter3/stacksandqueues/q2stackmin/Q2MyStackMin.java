package com.jiahaoliuliu.chapter3.stacksandqueues.q2stackmin;

import java.util.EmptyStackException;

public class Q2MyStackMin {

    private Node top;

    private static class Node {
        private final int data;
        /**
         * The minimum is used to take a snapshot of the minimum
         * values on its substack
         */
        private int minimum;
        private Node next;

        public Node(int data) {
            this.data = data;
            this.minimum = Integer.MAX_VALUE;
        }
    }

    public void push(int data) {
        Node newNode = new Node(data);
        if (top == null) {
            newNode.minimum = data;
        } else {
            newNode.minimum = Math.min(data, top.minimum);
        }
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        if (top == null) throw new EmptyStackException();
        int item = top.data;
        top = top.next;
        return item;
    }

    public int peek() {
        if (top == null) throw new EmptyStackException();
        return top.data;
    }

    public int min() {
        if (top == null) throw new EmptyStackException();
        return top.minimum;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
