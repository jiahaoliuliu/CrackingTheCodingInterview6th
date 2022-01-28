package com.jiahaoliuliu.chapter4.treesandgraphs;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth
 * (e.g. if you have a tree with depth D, you'll have D linked lists).
 * <ul>Hints:
 *  <li>107: Try modifying a graph search algorithm to track the depth from the root</li>
 *  <li>123: A hash table or array that maps from level number to nodes at that level might also be useful</li>
 *  <li>135: You should be able to come up with an algorithm involving both depth-first search and breadth-first
 *           search</li>
 * </ul>
 */
public class Q3ListOfDepths {

    private Map<Integer, LinkedList<BinaryTree.Node>> createLevelLinkedList(BinaryTree.Node node) {
        if (node == null) return null;
        Map<Integer, LinkedList<BinaryTree.Node>> result = new HashMap<>();
        List<BinaryTree.Node> level0NodesList = new ArrayList<BinaryTree.Node>();
        level0NodesList.add(node);
        return createLevelLinkedList(result, level0NodesList, 0);
    }

    private Map<Integer, LinkedList<BinaryTree.Node>> createLevelLinkedList(Map<Integer, LinkedList<BinaryTree.Node>> nodesLinkedList,
                                                                            List<BinaryTree.Node> nodesList, int currentLevel) {
        LinkedList<BinaryTree.Node> currentLevelLinkedList = nodesLinkedList.get(currentLevel);
        if (currentLevelLinkedList == null) {
            currentLevelLinkedList = new LinkedList<BinaryTree.Node>();
            nodesLinkedList.put(currentLevel, currentLevelLinkedList);
        }

        List<BinaryTree.Node> nextLevelNodesList = new ArrayList<BinaryTree.Node>();

        for (BinaryTree.Node node: nodesList) {
            currentLevelLinkedList.add(node);
            if (node.left != null) nextLevelNodesList.add(node.left);
            if (node.right != null) nextLevelNodesList.add(node.right);
        }

        // Stop condition
        if (nextLevelNodesList.isEmpty()) {
            return nodesLinkedList;
        } else {
            return createLevelLinkedList(nodesLinkedList, nextLevelNodesList, currentLevel + 1);
        }
    }

    /**
     * Book solution 1
     */
    private ArrayList<LinkedList<BinaryTree.Node>> bookSol1CreateLeLevel1LinkedList(BinaryTree.Node root) {
        ArrayList<LinkedList<BinaryTree.Node>> lists = new ArrayList<LinkedList<BinaryTree.Node>>();
        bookSol1CreateLeLevel1LinkedList(root, lists, 0);
        return lists;
    }

    private void bookSol1CreateLeLevel1LinkedList(BinaryTree.Node root, ArrayList<LinkedList<BinaryTree.Node>> lists, int level) {
        if (root == null) return; // Base case

        LinkedList<BinaryTree.Node> list = null;
        if (lists.size() == level) { // Level not contained in the list
            list = new LinkedList<>();
            /* Levels are always traversed in order. So, if this is the first time we've
             * visited level i, we must have seen level 0 through i - 1. We can
             * therefore safely add the level at the end */
            lists.add(list);
        } else {
            list = lists.get(level);
        }
        list.add(root);
        bookSol1CreateLeLevel1LinkedList(root.left, lists, level + 1);
        bookSol1CreateLeLevel1LinkedList(root.right, lists, level + 1);
    }

    /**
     * Book solution 2 - breadth-first search
     */
    private ArrayList<LinkedList<BinaryTree.Node>> bookSol2CreateLevelLinkedList(BinaryTree.Node root) {
        ArrayList<LinkedList<BinaryTree.Node>> result = new ArrayList<LinkedList<BinaryTree.Node>>();
        /* "Visit" the root */
        LinkedList<BinaryTree.Node> current = new LinkedList<>();
        if (root != null) {
            current.add(root);
        }

        while (current.size() > 0) {
            result.add(current); // Add previous level
            LinkedList<BinaryTree.Node> parents = current; // Go to next level
            current = new LinkedList<BinaryTree.Node>();
            for (BinaryTree.Node parent: parents) {
                /* Visit the children */
                if (parent.left != null) {
                    current.add(parent.left);
                }

                if (parent.right != null) {
                    current.add(parent.right);
                }
            }
        }
        return result;
    }

    private Q2MinimalTree q2MinimalTree;

    @Before
    public void setUp() {
        q2MinimalTree = new Q2MinimalTree();
    }

    @Test
    public void testCreateLevelLinkedList1() {
        // Given
        int[] array = new int[] {0, 1, 2, 3, 4, 5, 6};
        BinaryTree.Node binarySearchTree = q2MinimalTree.binarySearchTree(array);

        // When
        Map<Integer, LinkedList<BinaryTree.Node>> result = createLevelLinkedList(binarySearchTree);

        // Then
        assertEquals(3, result.size());

        // Level 0
        LinkedList<BinaryTree.Node> level0LinkedList = result.get(0);
        assertFalse(level0LinkedList.isEmpty());
        assertEquals(1, level0LinkedList.size());
        assertEquals(3, level0LinkedList.get(0).value);

        // Level 1
        LinkedList<BinaryTree.Node> level1LinkedList = result.get(1);
        assertFalse(level1LinkedList.isEmpty());
        assertEquals(2, level1LinkedList.size());
        assertEquals(1, level1LinkedList.get(0).value);
        assertEquals(5, level1LinkedList.get(1).value);

        // Level 2
        LinkedList<BinaryTree.Node> level2LinkedList = result.get(2);
        assertFalse(level2LinkedList.isEmpty());
        assertEquals(4, level2LinkedList.size());
        assertEquals(0, level2LinkedList.get(0).value);
        assertEquals(2, level2LinkedList.get(1).value);
        assertEquals(4, level2LinkedList.get(2).value);
        assertEquals(6, level2LinkedList.get(3).value);
    }

    @Test
    public void testCreateLevelLinkedList2() {
        // Given
        int[] array = new int[] {};
        BinaryTree.Node binarySearchTree = q2MinimalTree.binarySearchTree(array);

        // When
        Map<Integer, LinkedList<BinaryTree.Node>> result = createLevelLinkedList(binarySearchTree);

        // Then
        assertNull(result);
    }

    @Test
    public void testCreateLevelLinkedList3() {
        // Given
        int[] array = new int[] { 1 };
        BinaryTree.Node binarySearchTree = q2MinimalTree.binarySearchTree(array);

        // When
        Map<Integer, LinkedList<BinaryTree.Node>> result = createLevelLinkedList(binarySearchTree);

        // Then
        assertEquals(1, result.size());

        // Level 0
        LinkedList<BinaryTree.Node> level0LinkedList = result.get(0);
        assertFalse(level0LinkedList.isEmpty());
        assertEquals(1, level0LinkedList.size());
        assertEquals(1, level0LinkedList.get(0).value);
    }

    @Test
    public void testBookSol1CreateLeLevel1LinkedList1() {
        // Given
        int[] array = new int[] {0, 1, 2, 3, 4, 5, 6};
        BinaryTree.Node binarySearchTree = q2MinimalTree.binarySearchTree(array);

        // When
        ArrayList<LinkedList<BinaryTree.Node>> result = bookSol1CreateLeLevel1LinkedList(binarySearchTree);

        // Then
        assertEquals(3, result.size());

        // Level 0
        LinkedList<BinaryTree.Node> level0LinkedList = result.get(0);
        assertFalse(level0LinkedList.isEmpty());
        assertEquals(1, level0LinkedList.size());
        assertEquals(3, level0LinkedList.get(0).value);

        // Level 1
        LinkedList<BinaryTree.Node> level1LinkedList = result.get(1);
        assertFalse(level1LinkedList.isEmpty());
        assertEquals(2, level1LinkedList.size());
        assertEquals(1, level1LinkedList.get(0).value);
        assertEquals(5, level1LinkedList.get(1).value);

        // Level 2
        LinkedList<BinaryTree.Node> level2LinkedList = result.get(2);
        assertFalse(level2LinkedList.isEmpty());
        assertEquals(4, level2LinkedList.size());
        assertEquals(0, level2LinkedList.get(0).value);
        assertEquals(2, level2LinkedList.get(1).value);
        assertEquals(4, level2LinkedList.get(2).value);
        assertEquals(6, level2LinkedList.get(3).value);
    }

    @Test
    public void testBookSol1CreateLeLevel1LinkedList2() {
        // Given
        int[] array = new int[] {};
        BinaryTree.Node binarySearchTree = q2MinimalTree.binarySearchTree(array);

        // When
        ArrayList<LinkedList<BinaryTree.Node>> result = bookSol1CreateLeLevel1LinkedList(binarySearchTree);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    public void testBookSol1CreateLeLevel1LinkedList3() {
        // Given
        int[] array = new int[] { 1 };
        BinaryTree.Node binarySearchTree = q2MinimalTree.binarySearchTree(array);

        // When
        ArrayList<LinkedList<BinaryTree.Node>> result = bookSol1CreateLeLevel1LinkedList(binarySearchTree);

        // Then
        assertEquals(1, result.size());

        // Level 0
        LinkedList<BinaryTree.Node> level0LinkedList = result.get(0);
        assertFalse(level0LinkedList.isEmpty());
        assertEquals(1, level0LinkedList.size());
        assertEquals(1, level0LinkedList.get(0).value);
    }

}
