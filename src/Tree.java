import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

interface TwoThreeTree {
    boolean insert(int x);

    int size(int x);
}


public class Tree implements TwoThreeTree {

    private Node root;

    public Tree() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
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
        int size = n.getSize();
        size += size(n.getLeft());
        size += size(n.getMiddle());
        size += size(n.getRight());
        return size;
    }

    public Node search(int x, Node start) {
        if (start == null) {
            return null;
        }
        if (start.contains(x)) {
            return start;
        }
        return search(x, start.getSubTree(x)); // continue searching with next appropriate subtree based on value of x
    }

    @Override
    public String toString() {
        return toString(root);
    }

    private String toString(Node n) {
        String tree = "";
        if (n == null) {
            return tree;
        }
        tree += n.getKeys();
        tree += "(" + toString(n.getLeft()) + "," + toString(n.getMiddle()) + "," + toString(n.getRight()) + ")";
        return tree;
    }


    class Node {


        private List<Integer> keys = new ArrayList<>();
        private Node left = null;
        private Node middle = null;
        private Node right = null;
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

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getMiddle() {
            return middle;
        }

        public void setMiddle(Node middle) {
            this.middle = middle;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Integer getFirst() {
            return keys.get(0);
        }

        public List<Integer> getKeys() {
            return keys;
        }

        public boolean isLeaf() {
            if (keys.size() == 1) {
                // 2-node
                return (left == null && right == null);
            } else {
                // 3-node
                return (left == null && middle == null && right == null);
            }
        }

        public boolean needsSplitting() {
            return keys.size() > 2;
        }

        public Node splitLeft() {
            Node left = new Node(keys.get(0), parent);
            keys.remove(0);
            return left;
        }

        public Node splitRight() {
            Node right = new Node(keys.get(keys.size() - 1), parent);
            keys.remove(keys.size() - 1);
            return right;
        }

        public Node getSubTree(int key) {
            if (keys.size() == 1) {
                // 2-node
                if (key < keys.get(0)) {
                    return left;
                } else {
                    return right;
                }
            } else {
                // 3 - node
                if (key < keys.get(0)) {
                    return left;
                } else if (key < keys.get(keys.size() - 1)) {
                    return middle;
                } else {
                    return right;
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
            return keys.size();
        }

        public void split() {
            if (!needsSplitting()) {
                return;
            }
            if (getParent() == null) {
                // make new nodes
                Node left = splitLeft();
                Node right = splitRight();
                // set their parents to the root
                left.setParent(this);
                right.setParent(this);
                // set left and right of new root
                setLeft(left);
                setRight(right);
                return;
            }
            // make new nodes
            Node parent = getParent();
            Node left = splitLeft();
            Node right = splitRight();
            // add middle key to parent node
            parent.addKey(getFirst());
            // check if current is smallest or largest
            if (parent.getFirst().intValue() == getFirst().intValue()) {
                parent.setLeft(left);
                parent.setMiddle(right);
            } else {
                parent.setMiddle(left);
                parent.setRight(right);
            }
            parent.split();
        }

    }


}
