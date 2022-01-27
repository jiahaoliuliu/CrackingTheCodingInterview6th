package com.jiahaoliuliu.chapter4.treesandgraphs;

enum State { Unvisited, Visited, Visiting}

public class Graph {
    public Node[] nodes;

}

class Node {
    public String name;
    public State state = State.Unvisited;
    public Node[] children;

    public Node(String name) {
        this.name = name;
    }
}
