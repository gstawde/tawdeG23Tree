import java.util.TreeSet;

interface TwoThreeTree {
    public boolean insert(int x);

    public int size(int x);
}


public class Tree implements TwoThreeTree {

    Node root;

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
                Node next = current.getSubTree(x);
                current = next;
            }
        }
        split(current);

        return true;
    }

    public void split(Node current) {
        if (!current.needsSplitting()) {
            return;
        }
        if (current.getParent() == null) {
            // make new nodes
            Node left = current.splitLeft();
            Node right = current.splitRight();
            // set their parents to the root
            left.setParent(current);
            right.setParent(current);
            // set left and right of new root
            current.setLeft(left);
            current.setRight(right);
            return;
        }
        // make new nodes
        Node parent = current.getParent();
        Node left = current.splitLeft();
        Node right = current.splitRight();
        // add middle key to parent node
        parent.addKey(current.getFirst());
        // check if current is smallest or largest
        if (parent.getFirst() == current.getFirst()) {
            parent.setLeft(left);
            parent.setMiddle(right);
        } else {
            parent.setMiddle(left);
            parent.setRight(right);
        }
        split(parent);
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


        TreeSet<Integer> keys = new TreeSet<>();
        Node left = null;
        Node middle = null;
        Node right = null;
        Node parent = null;

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
            return keys.first();
        }

        public TreeSet<Integer> getKeys() {
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
            Node left = new Node(keys.first(), parent);
            keys.remove(keys.first());
            return left;
        }

        public Node splitRight() {
            Node right = new Node(keys.last(), parent);
            keys.remove(keys.last());
            return right;
        }

        public Node getSubTree(int key) {
            if (keys.size() == 1) {
                // 2-node
                if (key < keys.first()) {
                    return left;
                } else {
                    return right;
                }
            } else {
                // 3 - node
                if (key < keys.first()) {
                    return left;
                } else if (key < keys.last()) {
                    return middle;
                } else {
                    return right;
                }
            }
        }

        public boolean contains(int x) {
            if (keys.size() == 1) {
                return x == keys.first();
            }
            return (x == keys.first() || x == keys.last());
        }

        public boolean add(int x) {
            if (isLeaf()) {
                keys.add(x);
                return true;
            } else {
                return false;
            }
        }

        public void addKey(int x) {
            keys.add(x);
        }

        public int getSize() {
            return keys.size();
        }
    }


}
