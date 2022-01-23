package com.jiahaoliuliu.chapter3.stacksandqueues;

import java.util.NoSuchElementException;

public class MyQueue<T> {
    private static class QueueNode<T> {
        private T data;
        private QueueNode<T> next;

        public QueueNode(T data) {
            this.data = data;
        }
    }

    private QueueNode<T> first;
    private QueueNode<T> last;

    public void add(T item) {
        QueueNode<T> t = new QueueNode<T>(item);
        // Add the new element to the last position
        if (last != null) {
            last.next = t;
        }
        last = t;

        // If the queue was empty before, the first one is also the last one
        if (first == null) {
            first = last;
        }
    }

    public T remove() {
        if (first == null) throw new NoSuchElementException();
        // Remove the first element
        T data = first.data;
        first = first.next;

        // If after removal there are not more items, update the last one
        if (first == null) {
            last = null;
        }
        return data;
    }

    public T peek() {
        if (first == null) throw new NoSuchElementException();
        return first.data;
    }

    public boolean isEmpty() {
        return first == null;
    }
}