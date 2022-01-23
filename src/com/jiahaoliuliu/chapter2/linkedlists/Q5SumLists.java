package com.jiahaoliuliu.chapter2.linkedlists;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored
 * in reverse order, such that 1's digit is at the head of the list. Write a function that adds the two numbers and
 * returns the sum as a linked list.
 * <ul>
 *     Example:
 *     <li>Input: (7 -> 1 -> 6) + (5 -> 9 -> 2). That is, 617 + 295</li>
 *     <li>Output: 2 -> 1 -> 9. That is, 912</li>
 * </ul>
 *
 * <ul>
 *     Follow up:
 *     Suppose the digits are stored in forward order. Repeat the above problem.
 *     <p>Example:</p>
 *     <li>Input: (6 -> 1 -> 7) + (2 -> 9 -> 5). That is, 617 + 295.</li>
 *     <li>Output: 9 > 1 -> 2. That is, 912</li>
 * </ul>
 *
 * <ul>
 *     Hint:
 *     <li>7: Of course, you could convert the linked lists to integers, compute the sum, and then
 *            convert it back to a new linked list. If you did this in an interview, your interviewer
 *            would likely accept the answer, and then see if you could do this without converting it
 *            to a number and back.</li>
 *     <li>30: Try recursion. Suppose you have two lists, A = 1->5->9 (representing 951) and
 *             B = 2->3->6->7 (representing 7632), and a function that operates on the reminder of the
 *             lists (5->9 and 3->6->7). Could you use this to create the sum method? What is the relationship
 *             between sum(1->5->9, 2->3->6->7) and sum(5->9, 3->6->7)</li>
 *     <li>71: Make sure you have considered linked lists that are not the same length</li>
 *     <li>95: Does your algorithm work on linked lists like 9->7->8 and 6->8->5? Double check that.</li>
 *     <li>109: For the follow-up question: The issue is that when the linked lists aren't the same length,
 *              the head of one linked list might represent the 1000's place while the other represents
 *              the 10's place. What if you made them the same length? Is there a way to modify the linked
 *              list to do that, without changing the value it represents?</li>
 * </ul>
 */
public class Q5SumLists {

    private Node sumLists(Node leftNode, Node rightNode) {
        return sumLists(leftNode, rightNode, false);
    }

    private Node sumLists(Node leftNode, Node rightNode, boolean carryOne) {
        // Base cases
        if (leftNode == null && rightNode == null) {
            return null;
        }

        // If any of the nodes is null
        if (leftNode == null || rightNode == null) {
            Node nonNullNode = leftNode == null? rightNode : leftNode;
            Node newNode = new Node(nonNullNode.getData() + (carryOne? 1 : 0));
            newNode.setNext(sumLists(nonNullNode.getNext(), null, false));
            return newNode;
        }

        int sum = leftNode.getData() + rightNode.getData() + (carryOne? 1 : 0);
        Node newNode = new Node(sum % 10);
        newNode.setNext(sumLists(leftNode.getNext(), rightNode.getNext(), sum > 9));
        return newNode;
    }

    /**
     * This is when the numbers of reverse list does not represent 1's at the beginning but at the end
     * @param leftNode
     * @param rightNode
     * @return
     */
    private Node sumListsReverse(Node leftNode, Node rightNode) {

        CompensatedNodes compensatedNodes = compensateNodes(leftNode, rightNode);

        return sumListsReverse(compensatedNodes);
    }

    /**
     * Complexity: O(n) where n is max(leftNode.length, rightNode.length)
     * This does not work when there are two items need to be carried, like when 99 + 3
     * @param compensatedNodes
     * @return
     */
    private Node sumListsReverse(CompensatedNodes compensatedNodes) {
        Node result = null;
        Node previousNode = null;

        Node leftNode = compensatedNodes.leftNode;
        Node rightNode = compensatedNodes.rightNode;

        while (leftNode != null && rightNode != null) {
            int sum = leftNode.getData() + rightNode.getData();
            Node newNode = new Node(sum % 10);

            // Update previous node
            if (sum > 9) {
                if (previousNode == null) {
                    previousNode = new Node(1);
                    previousNode.setNext(newNode);
                } else {
                    // Add 1 to the previous node
                    previousNode.setData(previousNode.getData() + 1);
                    previousNode.setNext(newNode);
                }
            }

            // Set result only once
            if (result == null) {
                // If the sum was bigger than 9, then the previous node was created
                if (sum > 9) {
                    result = previousNode;
                // Else the new node is the head of the linked list
                } else {
                    result = newNode;
                }
            }

            // Advance 1 node
            leftNode = leftNode.getNext();
            rightNode = rightNode.getNext();

            if (previousNode == null) {
                previousNode = newNode;
            } else {
                previousNode = previousNode.getNext();
            }
        }

        return result;
    }

    /**
     * Complexity O(n) where n is max(leftNode.length, rightNode.length)
     * Space complexity: O(n)?
     * @param leftNode
     * @param rightNode
     * @return
     */
    private Node sumListReverseRecursive(Node leftNode, Node rightNode) {
        CompensatedNodes compensatedNodes = compensateNodes(leftNode, rightNode);
        SumResults sumResults = sumListReverseRecursiveHelper(compensatedNodes.leftNode, compensatedNodes.rightNode);
        if (sumResults.carryOne) {
            Node newHead = new Node(1);
            newHead.setNext(sumResults.node);
            return newHead;
        } else {
            return sumResults.node;
        }
    }

    private SumResults sumListReverseRecursiveHelper(Node leftNode, Node rightNode) {
        // Both nodes they have the same length, it does not matter
        if (leftNode == null || rightNode == null) {
            return new SumResults(null, false);
        }

        SumResults nextResults = sumListReverseRecursiveHelper(leftNode.getNext(), rightNode.getNext());
        int tmpResults = leftNode.getData() + rightNode.getData() + (nextResults.carryOne? 1 : 0);
        Node newNode = new Node(tmpResults % 10);
        newNode.setNext(nextResults.node);
        return new SumResults(newNode, tmpResults >= 10);
    }

    static class SumResults {
        Node node;
        boolean carryOne;

        SumResults(Node node, boolean carryOne) {
            this.node = node;
            this.carryOne = carryOne;
        }
    }

    /**
     * Complexity: O(n) where n is max(leftNode.length, rightNode.length)
     * @param leftNode
     * @param rightNode
     * @return
     */
    private CompensatedNodes compensateNodes(Node leftNode, Node rightNode) {
        Node leftHead = leftNode;
        Node rightHead = rightNode;
        while (leftNode != null || rightNode != null) {
            // If right node has more nodes
            if (leftNode == null) {
                // Create a node 0 and link it to the left node. That is the new left head
                Node newNode = new Node(0);
                newNode.setNext(leftHead);
                leftHead = newNode;
                rightNode = rightNode.getNext();
            } else  if (rightNode == null) {
                // Create a node 0 and link it to the right node. That is the new right head
                Node newNode = new Node(0);
                newNode.setNext(rightHead);
                rightHead = newNode;
                leftNode = leftNode.getNext();
            } else {
                // If none of them are null, check for next node
                leftNode = leftNode.getNext();
                rightNode = rightNode.getNext();
            }
        }

        return new CompensatedNodes(leftHead, rightHead);
    }

    static class CompensatedNodes {
        Node leftNode;
        Node rightNode;

        CompensatedNodes(Node leftNode, Node rightNode) {
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }
    }

    /**
     * Book solution 1 - reverse list
     */
    private Node bookSol1AddLists(Node l1, Node l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        int value = carry;
        if (l1 != null) {
            value += l1.getData();
        }

        if (l2 != null) {
            value += l2.getData();
        }
        Node result = new Node(value % 10); /* Second digit of number */

        /* Recursive */
        if (l1 != null || l2 != null) {
            Node more = bookSol1AddLists(
                    l1 == null ? null : l1.getNext(),
                    l2 == null ? null : l2.getNext(),
                    value > 10 ? 1 : 0);
            result.setNext(more);
        }

        return result;
    }

    /**
     * Book solution 2 - Normal list
     */
    static class PartialSum {
        public Node sum = null;
        public int carry = 0;
    }

    private Node book1Sol2AddLists(Node l1, Node l2) {
        int len1 = l1.size();
        int len2 = l2.size();

        /* Pad the shorter list with zeros - see note (1) */
        if (len1 < len2) {
            l1 = padList(l1, len2 - len1);
        } else {
            l2 = padList(l2, len1 - len2);
        }

        /* Add lists */
        PartialSum sum = addListHelper(l1, l2);

        /*
         * If there was a carry value lft over, insert this at the front of the list.
         * Otherwise, just return the linked list
         */
        if (sum.carry == 0) {
            return sum.sum;
        } else {
            Node result = insertBefore(sum.sum, sum.carry);
            return result;
        }
    }

    PartialSum addListHelper(Node l1, Node l2) {
        if (l1 == null && l2 == null) {
            PartialSum sum = new PartialSum();
            return sum;
        }
        /* Add smaller digits recursively */
        PartialSum sum = addListHelper(l1.getNext(), l2.getNext());

        /* Add carry to current data */
        int val = sum.carry + l1.getData() + l2.getData();

        /* Insert sum of current digits */
        Node full_result = insertBefore(sum.sum, val % 10);

        /* Return sum so far, and the carry value */
        sum.sum = full_result;
        sum.carry = val / 10;
        return sum;
    }

    Node padList(Node l, int padding) {
        Node head = l;
        for (int i = 0; i < padding; i++) {
            head = insertBefore(head, 0);
        }
        return head;
    }

    /* Helper function to insert node in the front of a linked list */
    Node insertBefore(Node list, int data) {
        Node node = new Node(data);
        if (list != null) {
            node.setNext(list);
        }
        return node;
    }

    @Test
    public void test1() {
        // Given
        Node leftNode = new Node(7);
        leftNode.insert(1);
        leftNode.insert(6);

        Node rightNode = new Node(5);
        rightNode.insert(9);
        rightNode.insert(2);

        // When
        Node result = bookSol1AddLists(leftNode, rightNode, 0);

        // Then
        Node expectedResult = new Node(2);
        expectedResult.insert(1);
        expectedResult.insert(9);
        assertEquals(expectedResult, result);
    }

    @Test
    public void test2() {
        // Given
        Node leftNode = null;

        Node rightNode = new Node(5);
        rightNode.insert(9);
        rightNode.insert(2);

        // When
        Node result = bookSol1AddLists(leftNode, rightNode, 0);

        // Then
        Node expectedResult = new Node(5);
        expectedResult.insert(9);
        expectedResult.insert(2);
        assertEquals(expectedResult, result);
    }

    @Test
    public void test3() {
        // Given
        Node leftNode = new Node(7);
        leftNode.insert(1);
        leftNode.insert(6);

        Node rightNode = null;

        // When
        Node result = bookSol1AddLists(leftNode, rightNode, 0);

        // Then
        Node expectedResult = new Node(7);
        expectedResult.insert(1);
        expectedResult.insert(6);
        assertEquals(expectedResult, result);
    }

    @Test
    public void test4() {
        // Given
        Node leftNode = new Node(1);
        leftNode.insert(5);
        leftNode.insert(9);

        Node rightNode = new Node(2);
        rightNode.insert(3);
        rightNode.insert(6);
        rightNode.insert(7);

        // When
        Node result = bookSol1AddLists(leftNode, rightNode, 0);

        // Then
        Node expectedResult = new Node(3);
        expectedResult.insert(8);
        expectedResult.insert(5);
        expectedResult.insert(8);
        assertEquals(expectedResult, result);
    }

    @Test
    public void test5() {
        // Given
        Node leftNode = new Node(1);

        Node rightNode = new Node(2);
        rightNode.insert(3);
        rightNode.insert(6);
        rightNode.insert(7);

        // When
        Node result = bookSol1AddLists(leftNode, rightNode, 0);

        // Then
        Node expectedResult = new Node(3);
        expectedResult.insert(3);
        expectedResult.insert(6);
        expectedResult.insert(7);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCompensateNodes1() {
        // Given
        Node leftNode = null;
        Node rightNode = new Node(1);
        rightNode.insert(2);
        rightNode.insert(3);

        // When
        CompensatedNodes compensatedNodes = compensateNodes(leftNode, rightNode);

        // Then
        Node expectedLeftNode = new Node(0);
        expectedLeftNode.insert(0);
        expectedLeftNode.insert(0);

        Node expectedRightNode = new Node(1);
        expectedRightNode.insert(2);
        expectedRightNode.insert(3);

        assertEquals(expectedLeftNode, compensatedNodes.leftNode);
        assertEquals(expectedRightNode, compensatedNodes.rightNode);
    }

    @Test
    public void testCompensateNodes2() {
        // Given
        Node leftNode = new Node(1);
        leftNode.insert(2);
        leftNode.insert(3);
        Node rightNode = null;

        // When
        CompensatedNodes compensatedNodes = compensateNodes(leftNode, rightNode);

        // Then
        Node expectedLeftNode = new Node(1);
        expectedLeftNode.insert(2);
        expectedLeftNode.insert(3);

        Node expectedRightNode = new Node(0);
        expectedRightNode.insert(0);
        expectedRightNode.insert(0);

        assertEquals(expectedLeftNode, compensatedNodes.leftNode);
        assertEquals(expectedRightNode, compensatedNodes.rightNode);
    }

    @Test
    public void testCompensateNodes3() {
        // Given
        Node leftNode = new Node(1);
        leftNode.insert(2);
        leftNode.insert(3);

        Node rightNode = new Node(1);
        rightNode.insert(2);
        rightNode.insert(3);

        // When
        CompensatedNodes compensatedNodes = compensateNodes(leftNode, rightNode);

        // Then
        Node expectedLeftNode = new Node(1);
        expectedLeftNode.insert(2);
        expectedLeftNode.insert(3);

        Node expectedRightNode = new Node(1);
        expectedRightNode.insert(2);
        expectedRightNode.insert(3);

        assertEquals(expectedLeftNode, compensatedNodes.leftNode);
        assertEquals(expectedRightNode, compensatedNodes.rightNode);
    }

    @Test
    public void testCompensateNodes4() {
        // Given
        Node leftNode = new Node(1);
        leftNode.insert(2);
        leftNode.insert(3);

        Node rightNode = new Node(1);
        rightNode.insert(2);
        rightNode.insert(3);
        rightNode.insert(4);
        rightNode.insert(5);

        // When
        CompensatedNodes compensatedNodes = compensateNodes(leftNode, rightNode);

        // Then
        Node expectedLeftNode = new Node(0);
        expectedLeftNode.insert(0);
        expectedLeftNode.insert(1);
        expectedLeftNode.insert(2);
        expectedLeftNode.insert(3);

        Node expectedRightNode = new Node(1);
        expectedRightNode.insert(2);
        expectedRightNode.insert(3);
        expectedRightNode.insert(4);
        expectedRightNode.insert(5);

        assertEquals(expectedLeftNode, compensatedNodes.leftNode);
        assertEquals(expectedRightNode, compensatedNodes.rightNode);
    }

    @Test
    public void testSumListsReverse1() {
        // Given
        Node leftNode = new Node(6);
        leftNode.insert(1);
        leftNode.insert(7);

        Node rightNode = new Node(9);
        rightNode.insert(5);

        // When
        Node result = book1Sol2AddLists(leftNode, rightNode);

        // Then
        Node expectedResult = new Node(7);
        expectedResult.insert(1);
        expectedResult.insert(2);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testSumListsReverse2() {
        // Given
        Node leftNode = new Node(6);
        leftNode.insert(1);
        leftNode.insert(7);

        Node rightNode = new Node(2);
        rightNode.insert(9);
        rightNode.insert(5);

        // When
        Node result = book1Sol2AddLists(leftNode, rightNode);

        // Then
        Node expectedResult = new Node(9);
        expectedResult.insert(1);
        expectedResult.insert(2);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testSumListsReverse3() {
        // Given
        Node leftNode = new Node(9);
        leftNode.insert(9);
        leftNode.insert(9);

        Node rightNode = new Node(3);

        // When
        Node result = book1Sol2AddLists(leftNode, rightNode);

        // Then
        Node expectedResult = new Node(1);
        expectedResult.insert(0);
        expectedResult.insert(0);
        expectedResult.insert(2);

        assertEquals(expectedResult, result);
    }
}
