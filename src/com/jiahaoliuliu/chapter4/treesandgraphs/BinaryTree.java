package com.jiahaoliuliu.chapter4.treesandgraphs;

public class BinaryTree {
    static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
        }

        public String toString() {
            StringBuilder buffer = new StringBuilder(50);
            print(buffer, "", "");
            return buffer.toString();
        }

        private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
            buffer.append(prefix);
            buffer.append(value);
            buffer.append('\n');
            if (left != null) {
                left.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
            }
            if (right != null) {
                right.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
//            for (Iterator<Node> it = children.iterator(); it.hasNext();) {
//                TreeNode next = it.next();
//                if (it.hasNext()) {
//                    next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
//                } else {
//                    next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
//                }
//            }
        }
    }

}
