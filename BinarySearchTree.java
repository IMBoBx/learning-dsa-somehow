public class BinarySearchTree <T extends Comparable<T>> {
    private int nodeCount = 0;
    private Node root = null;

    private class Node {
        T data;
        Node left, right;

        public Node(Node left, Node right, T elem) {
            this.data = elem;
            this.left = left;
            this.right = right;
        }
    }

    public int size() {
        return nodeCount;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(T elem) {
        if (contains(elem)) return false; // duplicates not allowed in a BST

        root = add(root, elem);
        nodeCount++;
        return true;
    }

    private Node add(Node node, T elem) {

        // base case: leaf node
        if (node == null) {
            node = new Node(null, null, elem);

        } else {
            int cmp = elem.compareTo(node.data);
            if (cmp < 0) { // place in left subtree if lower
                node.left = add(node.left, elem);

            } else { // place in right subtree if higher
                node.right = add(node.right, elem);
            }
        }

        return node;
    }

    // private Node remove(Node node, T elem) {
    //     if (node == null) return null;

    //     int cmp = elem.compareTo(node.data);
    //     if (cmp < 0) {
    //         node.left = remove(node.left, elem);
    //     } else if (cmp > 0) {
    //         node.right = remove(node.right, elem);
    //     } else {
             
    //     }
    // }

    // check if the element is part of this BST or not
    public boolean contains(T elem) {
        return contains(root, elem);
    }

    private boolean contains(Node node, T elem) {
        if (node == null) return false; // if it hits null during this process it means element is not present in this BST

        int cmp = elem.compareTo(node.data);
        if (cmp < 0) {
            return contains(node.left, elem); // search left subtree if the element is less than current note
        } else if (cmp > 0) {
            return contains(node.right, elem); // search right subtree if its greater than current notde
        } else {
            return true; // return true if its equal to the current node (element found)
        }
    }
    
    // calculates the height of the BST in O(n) time complexity
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }
}
