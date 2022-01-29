package com.jiahaoliuliu.chapter4.treesandgraphs;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Implement a function to check if a binary tree is balanced. For the purpose of this question, a balanced tree is
 * defined to be a tree such that the heights of the two subtrees of any node never differ by more than one.
 * <ul>Hints:
 *  <li>21: Think about the definition of a balanced tree. Can you check that condition for a single node?
 *          can you check it for every node?</li>
 *  <li>33: If you've developed a brute force solution, be careful about its runtime. If you are computing the
 *          height of the subtrees for each node, you could have a pretty inefficient algorithm.</li>
 *  <li>49: What if you could modify the binary tree node class to allow a node to store the height of its subtree?</li>
 *  <li>105: You don't need to modify the binary tree class to store the height of the subtree. Can your recursive
 *           function compute the height of each subtree while also checking if a node is balanced? Try having the
 *           function return multiple values.</li>
 *  <li>124: Actually, you can just have a single checkHeight function that does both the height computation and balance
 *          check. An integer return value can be used to indicate both.</li>
 * </ul>
 */
public class Q4CheckBalanced {

    private boolean isBalanced(BinaryTree.Node root) {
        if (root == null) return true;
        try {
            int leftHeight = checkHeight(root.left);
            int rightHeight = checkHeight(root.right);
            return (Math.abs(leftHeight - rightHeight) <= 1);
        } catch (IllegalStateException illegalStateException) {
            return false;
        }
    }

    private int checkHeight(BinaryTree.Node node) {
        if (node == null) return -1; // Exit condition
        int heightLeft = checkHeight(node.left);
        int heightRight = checkHeight(node.right);

        int maxHeight = Math.max(heightLeft, heightRight);
        int minHeight = Math.min(heightLeft, heightRight);
        if (maxHeight - minHeight > 1) {
            throw new IllegalStateException("The nodes are not balanced");
        }

        return 1 + maxHeight;
    }

    @Test
    public void testIsBalanced1() {
        // Given
        int[] array = new int[] {};
        BinaryTree.Node root = new Q2MinimalTree().binarySearchTree(array);

        // When
        boolean result = isBalanced(root);

        // Then
        assertTrue(result);
    }

    @Test
    public void testIsBalanced2() {
        // Given
        int[] array = new int[] { 0 };
        BinaryTree.Node root = new Q2MinimalTree().binarySearchTree(array);

        // When
        boolean result = isBalanced(root);

        // Then
        assertTrue(result);
    }

    @Test
    public void testIsBalanced3() {
        // Given
        int[] array = new int[] { 0, 1, 2, 3, 4, 5, 6 };
        BinaryTree.Node root = new Q2MinimalTree().binarySearchTree(array);

        // When
        boolean result = isBalanced(root);

        // Then
        assertTrue(result);
    }

    @Test
    public void testIsBalanced4() {
        // Given
        int[] array = new int[] { 0, 1, 2, 3, 4, 5, 6, 7};
        BinaryTree.Node root = new Q2MinimalTree().binarySearchTree(array);
        // Add an extra node on the bottom right
        root.right.right.right.right = new BinaryTree.Node(8);

        // When
        boolean result = isBalanced(root);

        // Then
        assertFalse(result);
    }


    /**
     * Book solution 1. Recursive. For each isBalanced, getHeight is called on each node of the sub tree
     */
    private boolean bookSol1IsBalanced(BinaryTree.Node root) {
        if (root == null) return true; // Base case
        int heightDiff = bookSol1getHeight(root.left) - bookSol1getHeight(root.right);
        if (Math.abs(heightDiff) > 1) {
            return false;
        } else { // Recurse
            return bookSol1IsBalanced(root.left) && bookSol1IsBalanced(root.right);
        }
    }

    private int bookSol1getHeight(BinaryTree.Node node) {
        if (node == null) return -1; // Base case
        return Math.max(bookSol1getHeight(node.left), bookSol1getHeight(node.right)) + 1;
    }

    @Test
    public void testBookSol1IsBalanced1() {
        // Given
        int[] array = new int[] {};
        BinaryTree.Node root = new Q2MinimalTree().binarySearchTree(array);

        // When
        boolean result = bookSol1IsBalanced(root);

        // Then
        assertTrue(result);
    }

    @Test
    public void testBookSol1IsBalanced2() {
        // Given
        int[] array = new int[] { 0 };
        BinaryTree.Node root = new Q2MinimalTree().binarySearchTree(array);

        // When
        boolean result = bookSol1IsBalanced(root);

        // Then
        assertTrue(result);
    }

    @Test
    public void testBookSol1IsBalanced3() {
        // Given
        int[] array = new int[] { 0, 1, 2, 3, 4, 5, 6 };
        BinaryTree.Node root = new Q2MinimalTree().binarySearchTree(array);

        // When
        boolean result = bookSol1IsBalanced(root);

        // Then
        assertTrue(result);
    }

    @Test
    public void testBookSol1IsBalanced4() {
        // Given
        int[] array = new int[] { 0, 1, 2, 3, 4, 5, 6, 7};
        BinaryTree.Node root = new Q2MinimalTree().binarySearchTree(array);
        // Add an extra node on the bottom right
        root.right.right.right.right = new BinaryTree.Node(8);

        // When
        boolean result = bookSol1IsBalanced(root);

        // Then
        assertFalse(result);
    }

    /**
     * Book solution 2: Using Integer.MIN_VALUE as error code each time the height is checked.
     *
     * O(N) time and O(H) space, where H is the height of the tree
     *
     */
    private boolean bookSol2IsBalanced(BinaryTree.Node root) {
        return bookSol2CheckHeight(root) != Integer.MIN_VALUE;
    }

    private int bookSol2CheckHeight(BinaryTree.Node node) {
        if (node == null) return -1;

        int leftHeight = bookSol2CheckHeight(node.left);
        if (leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE; // Pass error up

        int rightHeight = bookSol2CheckHeight(node.right);
        if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE; // Pass error up

        int heightDiff = leftHeight - rightHeight;
        if (Math.abs(heightDiff) > 1) {
            return Integer.MIN_VALUE; // Found error -> Pass it back
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    @Test
    public void testBookSol2IsBalanced1() {
        // Given
        int[] array = new int[] {};
        BinaryTree.Node root = new Q2MinimalTree().binarySearchTree(array);

        // When
        boolean result = bookSol2IsBalanced(root);

        // Then
        assertTrue(result);
    }

    @Test
    public void testBookSol2IsBalanced2() {
        // Given
        int[] array = new int[] { 0 };
        BinaryTree.Node root = new Q2MinimalTree().binarySearchTree(array);

        // When
        boolean result = bookSol2IsBalanced(root);

        // Then
        assertTrue(result);
    }

    @Test
    public void testBookSol2IsBalanced3() {
        // Given
        int[] array = new int[] { 0, 1, 2, 3, 4, 5, 6 };
        BinaryTree.Node root = new Q2MinimalTree().binarySearchTree(array);

        // When
        boolean result = bookSol2IsBalanced(root);

        // Then
        assertTrue(result);
    }

    @Test
    public void testBookSol2IsBalanced4() {
        // Given
        int[] array = new int[] { 0, 1, 2, 3, 4, 5, 6, 7};
        BinaryTree.Node root = new Q2MinimalTree().binarySearchTree(array);
        // Add an extra node on the bottom right
        root.right.right.right.right = new BinaryTree.Node(8);

        // When
        boolean result = bookSol2IsBalanced(root);

        // Then
        assertFalse(result);
    }


}