import java.util.ArrayList;
import java.util.List;

interface TwoThreeTree {
    boolean insert(int x);

    int size(int x);
}


public class Tree implements TwoThreeTree {

    private Node root;

    public Tree() {
        this.root = null;
    }

    public boolean insert(int x) {
        if (root == null) {
            root = new Node(x, null);
            return true;
        }
        Node current = root;
        // while we haven't reached an end point,
        while (true) {
            if (current.contains(x)) {
                return false;
            }
            if (current.add(x)) {
                break;
            } else {
                current = current.getSubTree(x);
            }
        }
        current.split();

        return true;
    }

    public int size(int x) {
        // find Node with a key of value x
        Node n = search(x, root);
        if (n == null) {
            return 0;
        }
        return size(n);
    }

    public int size(Node n) {
        if (n == null) {
            return 0;
        }
        return n.getSize();
    }

    public Node search(int x, Node start) {
        if (start == null) {
            return null;
        }
        if (start.contains(x)) {
            return start;
        }
        if (start.isLeaf()) {
            return null;
        }
        return search(x, start.getSubTree(x)); // continue searching with next appropriate subtree based on value of x
    }

//    @Override
//    public String toString() {
//        return toString(root);
//    }
//
//    private String toString(Node n) {
//        String tree = "";
//        if (n == null) {
//            return tree;
//        }
//        tree += n.getKeys();
//        tree += "(";
//        for (Node c : n.getChildren()) {
//            tree += toString(c) + ",";
//        }
//        tree += ")";
//        return tree;
//    }


    class Node {


        private List<Integer> keys = new ArrayList<>();
        private List<Node> children = new ArrayList<>();
        private Node parent;

        public Node(int key1, Node parent) {
            keys.add(key1);
            this.parent = parent;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Integer getFirst() {
            return keys.get(0);
        }

        public Integer getLast() {
            return keys.get(keys.size() - 1);
        }

        public List<Node> getChildren() {
            return children;
        }

        public boolean isLeaf() {
            return children.size() == 0;
        }

        public boolean needsSplitting() {
            return keys.size() > 2;
        }

        public Node splitLeft() {
            // System.out.println("Left:" + keys.get(0));
            Node left = new Node(keys.get(0), this);
            if (getChildren().size() == 4) {
                left.getChildren().add(getChildren().get(0));
                left.getChildren().add(getChildren().get(1));
            }
            for (Node n : left.getChildren()) {
                n.setParent(left);
            }
            keys.remove(0);
            return left;
        }

        public Node splitRight() {
            // System.out.println("Right:" + keys.get(keys.size() - 1));
            Node right = new Node(keys.get(keys.size() - 1), this);
            if (getChildren().size() == 4) {
                right.getChildren().add(getChildren().get(2));
                right.getChildren().add(getChildren().get(3));
            }
            for (Node n : right.getChildren()) {
                n.setParent(right);
            }
            keys.remove(keys.size() - 1);
            return right;
        }

        public Node getSubTree(int key) {
            if (keys.size() == 1) {
                // 2-node
                if (key < keys.get(0)) {
                    return children.get(0);
                } else {
                    return children.get(1);
                }
            } else {
                // 3 - node
                if (key < keys.get(0)) {
                    return children.get(0);
                } else if (key < keys.get(keys.size() - 1)) {
                    return children.get(1);
                } else {
                    return children.get(2);
                }
            }
        }

        public boolean contains(int x) {
            if (keys.size() == 1) {
                return x == keys.get(0);
            }
            return (x == keys.get(0) || x == keys.get(keys.size() - 1));
        }

        public boolean add(int x) {
            if (isLeaf()) {
                addKey(x);
                return true;
            } else {
                return false;
            }
        }

        public void addKey(int x) {
            keys.add(x);
            for (int i = 0; i < keys.size() - 1; i++) {
                for (int j = i + 1; j < keys.size(); j++) {
                    if (keys.get(i) > keys.get(j)) {
                        Integer temp = keys.get(i);
                        keys.set(i, keys.get(j));
                        keys.set(j, temp);
                    }
                }
            }
        }

        public int getSize() {
            int s = keys.size();
            for (Node n : children) {
                s += n.getSize();
            }
            return s;
        }

        public void split() {
            if (!needsSplitting()) {
                return;
            }
            // make new nodes
            Node parent = getParent();
            Node left = splitLeft();
            Node right = splitRight();
            getChildren().clear();
            // add middle key to parent node
            if (parent == null) {
                children.add(0, left);
                children.add(1, right);
                return;
            } else {
                parent.addKey(getFirst());
                parent.getChildren().remove(this);
                // check if current is smallest or largest
                if (parent.getFirst().intValue() == getFirst().intValue()) {
                    parent.getChildren().add(0, left);
                    parent.getChildren().add(1, right);
                } else if (parent.getLast().intValue() == getFirst().intValue()) {
                    parent.getChildren().add(left);
                    parent.getChildren().add(right);
                } else {
                    parent.getChildren().add(1, left);
                    parent.getChildren().add(2, right);
                }
                left.setParent(parent);
                right.setParent(parent);
                parent.split();
            }
        }

    }


}
