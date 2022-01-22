package com.jiahaoliuliu.chapter2.linkedlists;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Implement an algorithm to find the kth to last element of a singly linked list.
 * For example:
 * - if k = 0 -> Return the last element
 * - if k = 1 -> Return the second to last element
 * - if k = 2 -> Return the third to last element
 *
 * <ul>Hints:
 *  <li> 8: What if you knew the linked list size? What is the difference between finding the Kth-to-last
 *      element and finding the Xth element?</li>
 *  <li> 25: If you don't know the linked list size, can you compute it? How does this impact the runtime?</li>
 *  <li> 41: Try implementing it recursively. If you could find the (K-1)th to last element, can you find
 *       the kth element?</li>
 *  <li> 67: You might find it useful to return multiple values. Some languages don't directly support this,
 *       but there are workarounds essentially any language. What are some of those workarounds? </li>
 *  <li> 126: Can you do it iteratively? Imagine if you had two pointers pointing to adjacent nodes and they
 *        were moving at the same speed through the linked list. When one hits the end of the linked list,
 *        where will the other be? </li>
 * </ul>
 */
public class Q2ReturnKthToLast {

    /**
     * O(n + (n-k)) in time where n is the size of the linked list and kth element to the last
     */
    private Node returnKthToLast(Node head, int kthPositionToLast) {
        // Find the linked list size
        int linkedListSize = findLinkedListSize(head);
        int xThElement = linkedListSize - kthPositionToLast;
        return findElementAtKthPosition(head, xThElement);
    }

    private int findLinkedListSize(Node head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.getNext();
        }

        return size;
    }

    private Node findElementAtKthPosition(Node head, int position) {
        while (position > 1 && head != null) {
            head = head.getNext();
            position--;
        }

        return head;
    }

    /**
     * Book solution 1 - Don't return the element
     * Printing the kth to last element
     * The pass back the value of the counter through return values
     * Only if the interviewer say it is valid
     */
    private int bookSol1PrintKthToLast(Node head, int k) {
        if (head == null) {
            return 0;
        }
        int index = bookSol1PrintKthToLast(head.getNext(), k) + 1;
        if (index == k) {
            System.out.println(k + "th to last node is " + head.getData());
        }
        return index;
    }

    /**
     * Book solution 3 (solution 2 was written in C++)
     * Recursive
     */
    class Index {
        public int value = 0;
    }

    private Node bookSol3KthToLast(Node head, int k) {
        Index idx = new Index();
        return bookSol3KthToLast(head, k, idx);
    }

    private Node bookSol3KthToLast(Node head, int k, Index idx) {
        if (head.getNext() == null) return null;

        Node node = bookSol3KthToLast(head.getNext(), k, idx);
        idx.value = idx.value + 1;
        if (idx.value == k) {
            return head;
        }

        return node;
    }

    /**
     * Book Solution 4
     * Double pointer: p1 p2
     * 1. Move p2 k position
     * 2. Place p1 on the first node
     * 3. Move p1 and p2 at the same time until p2 reaches to the end
     * 4. Return the position of p1
     */
    private Node bookSol4NthToLast(Node head, int k) {
        Node p1 = head;
        Node p2 = head;

        /* Move p1 k nodes into the list. */
        for (int i = 0; i < k+1; i++) {
            if (p1 == null) return null; // Out of bounds
            p1 = p1.getNext();
        }

        /* Move them at the same pace. When p1 hits the end, p2 will be at the right element */
        while (p1 != null) {
            p1 = p1.getNext();
            p2 = p2.getNext();
        }

        return p2;
    }

    @Test
    public void test1() {
        // Given
        Node head = new Node(1);
        head.insert(2);
        head.insert(3);
        head.insert(4);
        head.insert(5);

        // When
        Node result = bookSol4NthToLast(head, 2);

        // Then

        assertEquals(findElementAtKthPosition(head, 3), result);
    }

    @Test
    public void test2() {
        // Given
        Node head = new Node(1);
        head.insert(2);
        head.insert(3);
        head.insert(4);
        head.insert(5);

        // When
        Node result = bookSol4NthToLast(head, 1);

        // Then

        assertEquals(findElementAtKthPosition(head, 4), result);
    }

    @Test
    public void test3() {
        // Given
        Node head = new Node(1);
        head.insert(2);
        head.insert(3);

        // When
        Node result = bookSol4NthToLast(head, 2);

        // Then

        assertEquals(findElementAtKthPosition(head, 1), result);
    }
}
