package com.jiahaoliuliu.chapter2.linkedlists;

public class Node {

    private Node next = null;
    private int data;

    public Node(int newData) {
        data = newData;
    }

    public int getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void insert(int newData) {
        Node nextNode = this;
        while (nextNode.next != null) {
            nextNode = nextNode.next;
        }
        nextNode.next = new Node(newData);
    }

    public int size() {
        Node nextNode = this;
        int size = 1;
        while (nextNode.next != null) {
            nextNode = nextNode.next;
            size++;
        }
        return size;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(next);
    }

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof Node)) return false;
        if (((Node) that).getData() == getData()) {
            if (next == null) {
                return (((Node) that).next == null);
            } else {
                return next.equals(((Node) that).next);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(data);
        Node nextNode = this;
        while(nextNode.next != null) {
            nextNode = nextNode.next;
            stringBuilder.append("-->").append(nextNode.getData());
        }
        return stringBuilder.toString();
    }
}