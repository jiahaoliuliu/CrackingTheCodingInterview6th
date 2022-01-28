package com.jiahaoliuliu.chapter4.treesandgraphs;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a binary
 * search tree with minimal height.
 * <ul>Hint:
 *  <li>19: A minimal binary tree has about the same number of nodes on the left of each node as on the light.
 *          let's focus on just the root for now. How would you ensure that about the same number of nodes are on
 *          the left of the root as on the right?</li>
 *  <li>73: You could implement this by finding the "ideal" next element to add and repeatedly calling insertValue.
 *          This will be a bit inefficient, as you would have to repeatedly traverse the tree. Try recursion
 *          instead. Can you divide this problem into subproblems?</li>
 *  <li>116: Imagine we had a createMinimalTree method that returns a minimal tree for a given array (but for some
 *           strange reason doesn't operate on the root of the tree). Could you use this to operate on the root of
 *           the tree? Could you write the base case for the function? Great! Then that's basically the entire
 *           function</li>
 * </ul>
 */
public class Q2MinimalTree {

    public BinaryTree.Node binarySearchTree(int[] array) {
        if (array.length == 0) return null;
        return binarySearchTree(array, 0, array.length - 1);
    }

    private BinaryTree.Node binarySearchTree(int[] array, int start, int end) {
        if (start > end) {
            return null;
        }

        int middlePosition = (start + end)/2;
        BinaryTree.Node node = new BinaryTree.Node(array[middlePosition]);
        node.left = binarySearchTree(array, start, middlePosition - 1);
        node.right = binarySearchTree(array, middlePosition+1, end);
        return node;
    }

    /**
     * Book solution 1
     */
    private BinaryTree.Node bookSol1CreateMinimalBST(int array[]) {
        return bookSol1CreateMinimalBST(array, 0, array.length - 1);
    }

    private BinaryTree.Node bookSol1CreateMinimalBST(int arr[], int start, int end) {
        if (end < start) return null;

        int mid = (start + end) / 2;
        BinaryTree.Node n = new BinaryTree.Node(arr[mid]);
        n.left = bookSol1CreateMinimalBST(arr, start, mid - 1);
        n.right = bookSol1CreateMinimalBST(arr, mid+1, end);
        return n;
    }

    @Test
    public void testBinarySearchTree1() {
        // Given
        int[] array = new int[] {0, 1, 2, 3, 4, 5, 6};

        // When
        BinaryTree.Node binarySearchTree = binarySearchTree(array);

        // Then
        testNode(binarySearchTree, 3, false, false);

        // Node 1
        BinaryTree.Node node1 = binarySearchTree.left;
        testNode(node1, 1, false, false);

        // Node 0
        BinaryTree.Node node0 = node1.left;
        testNode(node0, 0, true, true);

        // Node 2
        BinaryTree.Node node2 = node1.right;
        testNode(node2, 2, true, true);

        // Node 5
        BinaryTree.Node node5 = binarySearchTree.right;
        testNode(node5, 5, false, false);

        // Node 4
        BinaryTree.Node node4 = node5.left;
        testNode(node4, 4, true, true);

        // Node 6
        BinaryTree.Node node6 = node5.right;
        testNode(node6, 6, true, true);
    }

    @Test
    public void testBinarySearchTree2() {
        // Given
        int[] array = new int[] {};

        // When
        BinaryTree.Node binarySearchTree = binarySearchTree(array);

        // Then
        assertNull(binarySearchTree);
    }

    @Test
    public void testBinarySearchTree3() {
        // Given
        int[] array = new int[] {0};

        // When
        BinaryTree.Node binarySearchTree = binarySearchTree(array);

        // Then
        testNode(binarySearchTree, 0, true, true);
    }

    @Test
    public void testBookSol1CreateMinimalBST1() {
        // Given
        int[] array = new int[] {0, 1, 2, 3, 4, 5, 6};

        // When
        BinaryTree.Node binarySearchTree = bookSol1CreateMinimalBST(array);

        // Then
        testNode(binarySearchTree, 3, false, false);

        // Node 1
        BinaryTree.Node node1 = binarySearchTree.left;
        testNode(node1, 1, false, false);

        // Node 0
        BinaryTree.Node node0 = node1.left;
        testNode(node0, 0, true, true);

        // Node 2
        BinaryTree.Node node2 = node1.right;
        testNode(node2, 2, true, true);

        // Node 5
        BinaryTree.Node node5 = binarySearchTree.right;
        testNode(node5, 5, false, false);

        // Node 4
        BinaryTree.Node node4 = node5.left;
        testNode(node4, 4, true, true);

        // Node 6
        BinaryTree.Node node6 = node5.right;
        testNode(node6, 6, true, true);
    }

    @Test
    public void testBookSol1CreateMinimalBST2() {
        // Given
        int[] array = new int[] {};

        // When
        BinaryTree.Node binarySearchTree = bookSol1CreateMinimalBST(array);

        // Then
        assertNull(binarySearchTree);
    }

    @Test
    public void testBookSol1CreateMinimalBST3() {
        // Given
        int[] array = new int[] {0};

        // When
        BinaryTree.Node binarySearchTree = bookSol1CreateMinimalBST(array);

        // Then
        testNode(binarySearchTree, 0, true, true);
    }


    private void testNode(BinaryTree.Node node, int value, boolean leftNull, boolean rightNull) {
        assertNotNull(node);
        assertEquals(node.value, value);
        if (leftNull) {
            assertNull(node.left);
        } else {
            assertNotNull(node.left);
        }

        if (rightNull) {
            assertNull(node.right);
        } else {
            assertNotNull(node.right);
        }
    }

}
