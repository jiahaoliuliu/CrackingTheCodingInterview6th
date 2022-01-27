package com.jiahaoliuliu.chapter4.treesandgraphs;

import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Given a directed graph, design an algorithm to find out whether there is a route between two nodes
 * <ul>Hint:
 *  <li>127: Two well-known algorithms can do this. What are the tradeoffs between them?</li>
 * </ul>
 */
public class Q1RouteBetweenNodes {

    private boolean depthFirstSearch(Graph graph, Node nodeA, Node nodeB) {
        return depthFirstSearch(nodeA, nodeB);
    }

    private boolean depthFirstSearch(Node initialNode, Node destinationNode) {
        if (initialNode == null) return false;
        if (destinationNode == initialNode) {
            return true;
        } else {
            initialNode.state = State.Visited;
        }

        Node[] adjacentNodes = initialNode.children;
        if (adjacentNodes == null) return false;
        for (Node node : adjacentNodes) {
            if (node.state != State.Visited) {
                if (depthFirstSearch(node, destinationNode)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean breadthFirstSearch(Graph graph, Node nodeA, Node nodeB) {
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(nodeA);

        while (!nodeQueue.isEmpty()) {
            Node node = nodeQueue.remove();
            if (node == nodeB) {
                return true;
            } else {
                node.state = State.Visited;
                if (node.children != null) {
                    Collections.addAll(nodeQueue, node.children);
                }
            }
        }

        return false;
    }

    /**
     * Book solution 1
     */
    private boolean bookSol1Search(Graph g, Node start, Node end) {
        if (start == end) return true;

        // Operates as Queue
        LinkedList<Node> q = new LinkedList<>();

        for (Node u : g.nodes) {
            u.state = State.Unvisited;
        }
        start.state = State.Visiting;
        q.add(start);
        Node u;
        while (!q.isEmpty()) {
            u = q.removeFirst(); // i.e. dequeue()
            if (u != null && u.children != null) {
                for (Node v : u.children) {
                    if (v.state == State.Unvisited) {
                        if (v == end) {
                            return true;
                        } else {
                            v.state = State.Visiting;
                            q.add(v);
                        }
                    }
                }
                u.state = State.Visited;
            }
        }
        return false;
    }

    @Test
    public void testDepthFirstSearch1() {
        // Given
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Graph graph = new Graph();
        graph.nodes = new Node[] { nodeA, nodeB, nodeC, nodeD };
        nodeA.children = new Node[] { nodeC };
        nodeC.children = new Node[] { nodeB, nodeD};

        // When
        boolean isThereConnection = depthFirstSearch(graph, nodeA, nodeB);

        // Then
        assertTrue(isThereConnection);
    }

    @Test
    public void testDepthFirstSearch2() {
        // Given
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Graph graph = new Graph();
        graph.nodes = new Node[] { nodeA, nodeB, nodeC, nodeD, nodeE };
        nodeA.children = new Node[] { nodeC };
        nodeC.children = new Node[] { nodeB, nodeD};

        // When
        boolean isThereConnection = depthFirstSearch(graph, nodeA, nodeE);

        // Then
        assertFalse(isThereConnection);
    }

    @Test
    public void testBreadthFirstSearch1() {
        // Given
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Graph graph = new Graph();
        graph.nodes = new Node[] { nodeA, nodeB, nodeC, nodeD };
        nodeA.children = new Node[] { nodeC };
        nodeC.children = new Node[] { nodeB, nodeD};

        // When
        boolean isThereConnection = breadthFirstSearch(graph, nodeA, nodeB);

        // Then
        assertTrue(isThereConnection);
    }

    @Test
    public void testBreadthFirstSearch2() {
        // Given
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Graph graph = new Graph();
        graph.nodes = new Node[] { nodeA, nodeB, nodeC, nodeD, nodeE };
        nodeA.children = new Node[] { nodeC };
        nodeC.children = new Node[] { nodeB, nodeD};

        // When
        boolean isThereConnection = breadthFirstSearch(graph, nodeA, nodeE);

        // Then
        assertFalse(isThereConnection);
    }

    @Test
    public void testBookSol1Search1() {
        // Given
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Graph graph = new Graph();
        graph.nodes = new Node[] { nodeA, nodeB, nodeC, nodeD };
        nodeA.children = new Node[] { nodeC };
        nodeC.children = new Node[] { nodeB, nodeD};

        // When
        boolean isThereConnection = bookSol1Search(graph, nodeA, nodeB);

        // Then
        assertTrue(isThereConnection);
    }

    @Test
    public void testBookSol1Search2() {
        // Given
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Graph graph = new Graph();
        graph.nodes = new Node[] { nodeA, nodeB, nodeC, nodeD, nodeE };
        nodeA.children = new Node[] { nodeC };
        nodeC.children = new Node[] { nodeB, nodeD};

        // When
        boolean isThereConnection = bookSol1Search(graph, nodeA, nodeE);

        // Then
        assertFalse(isThereConnection);
    }

}
