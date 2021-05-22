package com.bhansali.view;

import java.util.Set;

public class Node {
    String name;
    Set<Node> children;

    Node(){}

    public Node(String name, Set<Node> children){
        this.name = name;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Node> getChildren() {
        return children;
    }

    public void setChildren(Set<Node> children) {
        this.children = children;
    }
}
