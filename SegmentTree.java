package SegmentTree;

public class SegmentTree {
    public static class Node {
        int data;
        int startInterval;
        int endInterval;
        Node left;
        Node right;

        public Node(int startInterval, int endInterval) {
            this.startInterval = startInterval;
            this.endInterval = endInterval;
        }
    }

    public SegmentTree(int[] arr) {
        root = insert(arr, 0, arr.length - 1);
    }

    public int query(int qsi, int qei) {
        return query(root, qsi, qei);
    }

    private int query(Node node, int qsi, int qei) {
        if (node.startInterval >= qsi && node.endInterval <= qei) {
            return node.data;
        } else if (node.startInterval > qei || node.endInterval < qsi) {
            return 0;
        } else {
            return query(node.left, qsi, qei) + query(node.right, qsi, qei);
        }
    }

    public void update(int index, int value) {
        root.data = update(root, index, value);
    }

    private int update(Node node, int index, int value) {
        if (index >= node.startInterval && index <= node.endInterval) {
            if (index == node.startInterval && index == node.endInterval) {
                node.data = value;
                return node.data;
            } else {
                int leftData = update(node.left, index, value);
                int rightData = update(node.right, index, value);
                node.data = leftData + rightData;
                return node.data;
            }
        }
        return node.data;
    }

    private Node root;

    private Node insert(int[] arr, int start, int end) {
        if (start == end) {
            Node leaf = new Node(start, end);
            leaf.data = arr[start];
            return leaf;
        }
        Node node = new Node(start, end);
        int mid = start + (end - start) / 2;
        node.left = insert(arr, start, mid);
        node.right = insert(arr, mid + 1, end);
        node.data = node.left.data + node.right.data;
        return node;
    }
}
