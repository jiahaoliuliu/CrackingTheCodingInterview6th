package com.jiahaoliuliu.chapter2.linkedlists;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Write code to partition a linked list around a value x, such that all nodes less than x come before all nodes
 * greater than or equal to x. If x is contained within the list, the values of x only need to be after the
 * elements less than x (see below). The partition element x can appear anywhere in the "right position";
 * it does not need to appear between the left and right partitions.
 * <ul>Example
 *  <li>Input:  3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [Partition = 5]</li>
 *  <li>Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8 </li>
 * </ul>
 *
 * <ul>Hint
 *  <li>3: There are many solutions to this problem, most of which are equally optimal in runtime. Some have shorter,
 *         cleaner code than others. Can you brainstorm different solutions?</li>
 *  <li>24: Consider that the elements don't have to stay in the same relative order. We only need to ensure that
 *          elements less than the pivot must be before elements greater than the pivot. Does that help you come up
 *          with more solutions?</li>
 * </ul>
 */
public class Q4Partition {

    /**
     * <ul>Complexity
     *  <li>Time: O(n) where n is the length of the list</li>
     *  <li>Space: O(n) where n is the length of the list</li>
     * </ul>
     */
    private Node partition(Node head, int partition) {
        Node smallerThanPartitionNodes = null;
        Node biggerOrEqualThanPartitionNodes = null;
        Node lastSmallerPartition = null;
        while (head != null) {
            if (head.getData() < partition) {
                if (smallerThanPartitionNodes == null) {
                    smallerThanPartitionNodes = new Node(head.getData());
                    lastSmallerPartition = smallerThanPartitionNodes;
                } else {
                    smallerThanPartitionNodes.insert(head.getData());
                    lastSmallerPartition = lastSmallerPartition.getNext();
                }
            } else {
                if (biggerOrEqualThanPartitionNodes == null) {
                    biggerOrEqualThanPartitionNodes = new Node(head.getData());
                } else {
                    biggerOrEqualThanPartitionNodes.insert(head.getData());
                }
            }

            head = head.getNext();
        }
        if (lastSmallerPartition != null) {
            lastSmallerPartition.setNext(biggerOrEqualThanPartitionNodes);
        }

        return smallerThanPartitionNodes;
    }

    /**
     * Book solution 1: Create two linked list
     */
    private Node bookSol1partition(Node node, int x) {
        Node beforeStart = null;
        Node beforeEnd = null;
        Node afterStart = null;
        Node afterEnd = null;

        /* Partition list */
        while (node != null) {
            Node next = node.getNext();
            node.setNext(null);
            if (node.getData() < x) {
                /* Insert node into end of before list */
                if (beforeStart == null) {
                    beforeStart = node;
                    beforeEnd = beforeStart;
                } else {
                    beforeEnd.setNext(node);
                    beforeEnd = node;
                }
            } else {
                /* Insert node into end of after list */
                if (afterStart == null) {
                    afterStart = node;
                    afterEnd = afterStart;
                } else {
                    afterEnd.setNext(node);
                    afterEnd = node;
                }
            }
            node = next;
        }

        if (beforeStart == null) {
            return afterStart;
        }

        /* Merge before list and after list */
        beforeEnd.setNext(afterStart);
        return beforeStart;
    }

    /**
     * Book solution 2. Inserting into head and tail
     */
    private Node bookSol2Partition(Node node, int x) {
        Node head = node;
        Node tail = node;

        while (node != null) {
            Node next = node.getNext();
            if (node.getData() < x) {
                /* Insert node at head */
                node.setNext(head);
                head = node;
            } else {
                /* Insert node at tail */
                tail.setNext(node);
                tail = node;
            }
            node = next;
        }
        tail.setNext(null);

        // The head has changed, so we need to return it to the user
        return head;
    }

    @Test
    public void test1() {
        // Given
        int partition = 5;

        Node head = new Node(3);
        head.insert(5);
        head.insert(8);
        head.insert(5);
        head.insert(10);
        head.insert(2);
        head.insert(1);

        // When
        Node result = bookSol1partition(head, partition);

        // Then
        assertTrue(result.getData() < partition);
        assertTrue(result.getNext().getData() < partition);
        assertTrue(result.getNext().getNext().getData() < partition);
    }
}
