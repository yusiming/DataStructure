package tree;

import queue.LinkedListQueue;
import queue.Queue;
import stack.LinkedListStack;
import stack.Stack;

/**
 * BinarySearchTree 二分搜索树
 * T要具有可比性
 * 注意：二分搜索树中没有重复元素
 *
 * @Auther yusiming
 * @Date 2018/11/27 10:41
 */
public class BST<T extends Comparable<T>> {
    private class Node {
        T t;
        Node left;
        Node right;

        public Node(T t, Node left, Node right) {
            this.t = t;
            this.left = left;
            this.right = right;
        }

        public Node(T t) {
            this(t, null, null);
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 暴露给外部使用的方法，向树中添加元素
     *
     * @param t 被插入的元素的值
     */
    public void add(T t) {
        root = this.add(root, t);
    }

    /**
     * 内部使用的递归算法
     *
     * @param node 在哪一个树中添加节点
     * @param t    被添加的元素的值
     * @return 插入新节点后的二分搜索树的根
     */
    private Node add(Node node, T t) {
        if (node == null) {
            size++;
            return new Node(t);
        }
        int compare = t.compareTo(node.t);
        if (compare > 0) {
            node.right = add(node.right, t);
        } else if (compare < 0) {
            node.left = add(node.left, t);
        }
        return node;
    }

    /**
     * 查询指定元素是否存在于二分搜索树中
     *
     * @param t 要查询的元素
     * @return 如果该元素存在于树中返回true，否则返回false
     */
    public boolean contains(T t) {
        return contains(root, t);
    }

    /**
     * 在指定树中查询是否包含指定元素t
     *
     * @param node 子树
     * @param t    元素的值
     * @return 如果包含返回true，否则返回false
     */
    private boolean contains(Node node, T t) {
        if (node == null) {
            return false;
        }
        int compare = t.compareTo(node.t);
        if (compare > 0) {
            return contains(node.right, t);
        } else if (compare < 0) {
            return contains(node.left, t);
        }
        return true;
    }

    /**
     * 二分搜索树的前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 前序遍历递归算法
     */
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.t);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 非递归的前序遍历，使用一个栈来保存遍历逻辑
     */
    public void preOrderNR() {
        Stack<Node> stack = new LinkedListStack<>();
        // 先将root压入栈
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.t);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /**
     * 二分搜索树的中序遍历
     */
    public void midOrder() {
        midOrder(root);
    }

    /**
     * 中序遍历递归算法
     */
    public void midOrder(Node node) {
        if (node == null) {
            return;
        }
        midOrder(node.left);
        System.out.println(node.t);
        midOrder(node.right);
    }

    /**
     * 中序遍历非递归实现
     */
    public void midOrderNR() {
    }

    /**
     * 二分搜索树的后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 后序遍历递归算法
     */
    public void postOrder(Node node) {
        if (node == null) {
            return;
        }
        midOrder(node.left);
        midOrder(node.right);
        System.out.println(node.t);
    }

    /**
     * 层序遍历（广度搜索），使用一个队列来保存遍历逻辑
     * 广度搜索
     */
    public void deepSearchOrder() {
        Queue<Node> queue = new LinkedListQueue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node node = queue.dequeue();
            System.out.println(node.t);
            if (node.left != null) {
                queue.enqueue(node.left);
            }
            if (node.right != null) {
                queue.enqueue(node.right);
            }
        }
    }

    public T max() {
        if (isEmpty()) {
            throw new IllegalArgumentException("树为空");
        }
        return max(root).t;
    }

    public Node max(Node node) {
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    public T min() {
        if (isEmpty()) {
            throw new IllegalArgumentException("树为空");
        }
        return min(root).t;
    }

    public Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }
    /**
     * 删除最大值
     */
    public void removeMax() {
        if (isEmpty()) {
            return;
        }
        root = removeMax(root);
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            size--;
            return node.left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除最小值
     */
    public void removeMin() {
        root = removeMin(root);
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            size--;
            // 当右子树为null时，逻辑也是正确的，当右子树不为null时，逻辑同样也是正确的
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 删除指定元素
     *
     * @param t 被删除的元素
     */
    public void remove(T t) {
        root = remove(root, t);
    }

    public Node remove(Node node, T t) {
        if (node == null) {
            return null;
        }
        int compare = t.compareTo(node.t);
        if (compare > 0) {
            node.right = remove(node.right, t);
            return node;
        } else if (compare < 0) {
            node.left = remove(node.left, t);
            return node;
        } else {
            if (node.left == null) {
                size--;
                return node.right;
            }
            if (node.right == null) {
                size--;
                return node.left;
            }
            Node min = min(node.right);
            min.right = removeMin(node.right);
            min.left = node.left;
            node.left = node.right = null;
            //size--; 注意这里不需要size--，removeMin操作已经size-- 了
            return min;
        }
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        /////////////////////////////////////
        ///             20                ///
        ///         16      30            ///
        ///      5    19  27   32         ///
        /////////////////////////////////////
        bst.add(20);
        bst.add(16);
        bst.add(5);
        bst.add(19);
        bst.add(30);
        bst.add(27);
        // bst.add(32);
        /*bst.preOrder();
        System.out.println();
        bst.preOrderNR();
        System.out.println();
        bst.midOrder();
        System.out.println();
        bst.postOrder();*/
        // bst.deepSearchOrder();
        bst.removeMax();
        bst.deepSearchOrder();
        bst.removeMin();
        bst.deepSearchOrder();
    }
}
