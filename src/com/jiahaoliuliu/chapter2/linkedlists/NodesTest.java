package com.jiahaoliuliu.chapter2.linkedlists;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NodesTest {

    @Test
    public void testInsertion1() {
        // Given
        Node head = new Node(0);

        // When
        head.insert(1);

        // Then
        assertEquals(2, head.size());
    }

    @Test
    public void testInsertion2() {
        // Given
        Node head = new Node(0);
        head.insert(1);

        // When
        head.insert(2);

        // Then
        assertEquals(3, head.size());
    }
}
