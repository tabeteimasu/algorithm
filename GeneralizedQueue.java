import java.util.ArrayList;

public class GeneralizedQueue {
    private Node root;
    private ArrayList<Node> array;

    public GeneralizedQueue() {
        this.root = null;
        this.array = new ArrayList<>();
    }

    private int size(Node node) {
        return node == null ? 0 : node.size;
    }

    private void updateSize(Node node) {
        if (node != null) {
            node.size = 1 + size(node.left) + size(node.right);
        }
    }

    public void append(int value) {
	Node newNode = new Node(value);
        root = insert(root, newNode);
        array.add(newNode);
    }

    private Node insert(Node node, Node newNode) {
        if (node == null) {
            return newNode;
        }
        node.right = insert(node.right, newNode);
        updateSize(node);
        return node;
    }

    public void removeFront() {
        if (array.isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty");
        }
        Node node = array.remove(0);
        root = delete(root, node.value);
    }

    private Node delete(Node node, int value) {
        if (node == null) {
            return null;
        }
        if (value < node.value) {
            node.left = delete(node.left, value);
        } else if (value > node.value) {
            node.right = delete(node.right, value);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node temp = node;
            node = minValueNode(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        updateSize(node);
        return node;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        updateSize(node);
        return node;
    }

    public int get(int i) {
        if (i < 0 || i >= array.size()) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        return array.get(i).value;
    }

    public void remove(int i) {
        if (i < 0 || i >= array.size()) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        Node node = array.remove(i);
        root = delete(root, node.value);
    }

    public static void main(String[] args) {
        GeneralizedQueue queue = new GeneralizedQueue();
        queue.append(1);
        queue.append(2);
        queue.append(3);
	queue.append(4);
        queue.removeFront();
        queue.remove(1);
        System.out.println(queue.get(0));
    }
}

class Node {
    int value;
    int size;
    Node left, right;

    Node(int value) {
        this.value = value;
        this.size = 1;
        this.left = this.right = null;
    }
}
