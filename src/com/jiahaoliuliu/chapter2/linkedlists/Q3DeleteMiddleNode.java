package com.jiahaoliuliu.chapter2.linkedlists;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Implement an algorithm to delete a node in the middle (i.e. any node but the first and the last node, not necessary
 * the exact middle) of a singly linked list, given only access to that node.
 * <ul>Example
 *  <li>Input: The node c from the linked list a->b->c->d->e->f</li>
 *  <li>Result: nothing is returned, but the new linked list looks like a->b->d->e->f</li>
 * </ul>
 * <ul>Hint:
 * <li>72: Picture the list 1->5->9->12. Removing 9 would make it look like 1->5->12. You only have access
 *         to the 9 node. Can you make it look like the correct answer?</li>
 * </ul>
 */
public class Q3DeleteMiddleNode {

    private void deleteMiddleNode(Node node) {
        while (node != null) {
            // Copy the values from next node
            if (node.getNext() != null) {
                node.setData(node.getNext().getData());
                // Remove the last node
                if (node.getNext().getNext() == null) {
                    node.setNext(null);
                }
            }
            node = node.getNext();
        }
    }

    /**
     * Book solution 1
     */
    private boolean bookSol1DeleteNode(Node n) {
        if (n == null || n.getNext() == null) {
            return false; // Failure
        }
        Node next = n.getNext();
        n.setData(next.getData());
        n.setNext(next.getNext());
        return true;
    }

    @Test
    public void test1() {
        // Given
        Node head = new Node(1);
        head.insert(2);
        head.insert(3);
        head.insert(4);
        head.insert(5);
        Node node = head.getNext().getNext();

        // When
        bookSol1DeleteNode(node);

        // Then
        Node result = new Node(1);
        result.insert(2);
        result.insert(4);
        result.insert(5);
        assertEquals(result, head);
    }
}
