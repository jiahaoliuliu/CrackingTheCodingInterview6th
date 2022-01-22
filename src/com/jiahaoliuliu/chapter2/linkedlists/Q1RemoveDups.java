package com.jiahaoliuliu.chapter2.linkedlists;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Write code to remove duplicates from an unsorted linked list.
 * FOLLOW UP
 * - How would you solve this problem if a temporally buffer is not allowed?
 * Hints:
 * - 9: Have you tried a hash table? You should be able to do this in a single pass of the linked list
 * - 40: Without extra space, you'll need O(N^2) time. Try using two pointers, where the second one
 *       searches ahead of the first one
 */
public class Q1RemoveDups {

    private void removeDups(Node head) {
        if (head == null || head.getNext() == null) return;
        Map<Integer, Integer> dataSet = new HashMap<>();
        Node currentNode = head;
        Node previousNode;
        dataSet.put(head.getData(), 1);
        while (currentNode.getNext() != null) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
            int data = currentNode.getData();
            // Duplication found
            if (dataSet.containsKey(data)) {
                // Remove the current Node
                previousNode.setNext(currentNode.getNext());
                currentNode.setNext(null);
                currentNode = previousNode;
            } else {
                dataSet.put(data, 1);
            }
        }
    }

    private void removeDupsWithOnePointer(Node head) {
        if (head == null || head.getNext() == null) return;
        Map<Integer, Integer> dataSet = new HashMap<>();
        Node currentNode = head;
        dataSet.put(head.getData(), 1);
        while (currentNode.getNext() != null) {
            int data = currentNode.getNext().getData();
            // Duplication found
            if (dataSet.containsKey(data)) {
                Node nextNode = currentNode.getNext();
                // Remove the current Node
                currentNode.setNext(currentNode.getNext().getNext());
                nextNode.setNext(null);
            } else {
                dataSet.put(data, 1);
                currentNode = currentNode.getNext();
            }
        }
    }

    private void removeDupsWithoutExtraSpace(Node head) {
        if (head == null || head.getNext() == null) return;
        // Init the data
        Node currentNode = head;
        // Check for each one of the current node
        while(currentNode.getNext() != null) {
            // Check for next nodes
            Node nextNode = currentNode;
            // Check for all the next node
            while (nextNode.getNext() != null) {
                // If the next node has the same data, remove it
                if (nextNode.getNext().getData() == currentNode.getData()) {
                    Node tmpNode = nextNode.getNext();
                    // Remove the current Node
                    nextNode.setNext(tmpNode.getNext());
                    tmpNode.setNext(null);
                } else {
                    nextNode = nextNode.getNext();
                }
            }
            if (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
        }
    }

    /**
     * Book solution 1. O(N)
     */
    private void bookSol1DeleteDups(Node n) {
        HashSet<Integer> set = new HashSet<>();
        Node previous = null;
        while (n != null) {
            if (set.contains(n.getData())) {
                previous.setNext(n.getNext());
            } else {
                set.add(n.getData());
                previous = n;
            }
            n = n.getNext();
        }
    }

    /**
     * Book solution 2. No Buffer Allowed
     * Use two pointer
     * - current which iterates through the linked list
     * - runner which checks all subsequent nodes for duplicates
     * O(1) spaces O(N^2) time
     */
    private void bookSol2DeleteDups(Node head) {
        Node current = head;
        while (current != null) {
            /* Remove all future nodes that have the same value */
            Node runner = current;
            while (runner.getNext() != null) {
                if (runner.getNext().getData() == current.getData()) {
                    runner.setNext(runner.getNext().getNext());
                } else {
                    runner = runner.getNext();
                }
            }
            current = current.getNext();
        }
    }

    @Test
    public void test1() {
        // Given
        Node head = new Node(1);
        head.insert(2);
        head.insert(3);
        head.insert(4);
        head.insert(3);
        head.insert(2);
        head.insert(1);

        // When
        bookSol2DeleteDups(head);

        // Then
        print(head);
        Node result = new Node(1);
        result.insert(2);
        result.insert(3);
        result.insert(4);
        print(result);

        assertEquals(result, head);
    }

    @Test
    public void test2() {
        // Given
        Node head = null;

        // When
        bookSol2DeleteDups(head);

        // Then
        assertEquals(null, head);
    }

    @Test
    public void test3() {
        // Given
        Node head = new Node(1);
        head.insert(2);
        head.insert(3);
        head.insert(4);

        // When
        bookSol2DeleteDups(head);

        // Then
        print(head);
        Node result = new Node(1);
        result.insert(2);
        result.insert(3);
        result.insert(4);
        print(result);

        assertEquals(result, head);
    }

    @Test
    public void test4() {
        // Given
        Node head = new Node(1);
        head.insert(2);
        head.insert(3);
        head.insert(3);
        head.insert(3);

        // When
        bookSol2DeleteDups(head);

        // Then
        print(head);
        Node result = new Node(1);
        result.insert(2);
        result.insert(3);
        print(result);

        assertEquals(result, head);
    }

    @Test
    public void test5() {
        // Given
        Node head = new Node(4);
        head.insert(3);
        head.insert(2);
        head.insert(1);
        head.insert(1);
        head.insert(2);
        head.insert(3);
        head.insert(4);

        // When
        bookSol2DeleteDups(head);

        // Then
        print(head);
        Node result = new Node(4);
        result.insert(3);
        result.insert(2);
        result.insert(1);
        print(result);

        assertEquals(result, head);
    }

    private void print(Object object) {
        System.out.println(object);
    }
}
