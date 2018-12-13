package avl;

import map.Map;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用AVL树实现map数据结构
 *
 * @Auther yusiming
 * @Date 2018/11/30 10:28
 */
public class AVLTreeMap<K extends Comparable<K>, V> implements Map<K, V> {
    private Node root;
    private int size;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        int height;

        public Node() {
            this(null, null, null, null);
        }

        public Node(K key, V value) {
            this(key, value, null, null);
        }

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            // 叶子节点的高度为1
            height = 1;
        }

        @Override
        public String toString() {
            return key.toString() + ":" + value.toString();
        }
    }

    public AVLTreeMap() {
        root = null;
        size = 0;
    }

    /**
     * 查找node节点对应的高度值
     *
     * @param node node节点
     * @return 节点的高度值
     */
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * 计算每个节点的平衡因子
     *
     * @param node 节点
     * @return 平衡因子
     */
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    @Override
    public void add(K key, V value) {
        root = add(key, value, root);
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(key, root);
            return node.value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("参数不合法");
        }
        return this.getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = this.getNode(root, key);
        return node != null ? node.value : null;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node != null) {
            node.value = value;
            return;
        }
        throw new IllegalArgumentException("key对应的node不存在");
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            return getNode(node.left, key);
        } else if (compare > 0) {
            return getNode(node.right, key);
        }
        return node;
    }

    private Node add(K key, V value, Node node) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = add(key, value, node.left);
        } else if (compare > 0) {
            node.right = add(key, value, node.right);
        } else {
            node.value = value;
        }
        // 更新height的值,从树的底部往上依次更新
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        // 重新计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        // 如果平衡因子 > 1,并且节点的左子节点的平衡因子 > 0 时，需要右旋转操作来保持树的平衡性
        if (balanceFactor > 1 && getBalanceFactor(node.left) > 0) {
            node = rotateRight(node);
        }
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = rotateLeft(node.left);
            node = rotateRight(node);
        }
        if (balanceFactor < -1 && getBalanceFactor(node.right) < 0) {
            node = rotateLeft(node);
        }
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
        }
        return node;
    }

    /**
     * AVL树的右旋转操作
     * ///////////////////////////////////////////////////////////
     * //                y                           x          //
     * //             /   \                        /   \        //
     * //           x     T4      右旋转        z      y        //
     * //         /  \           -------->    /  \    /  \      //
     * //       z    T3                    T1   T2   T3   T4    //
     * //     /  \                                              //
     * //   T1    T2                                            //
     * //////////////////////////////////////////////////////////
     *
     * @param node 需要进行右旋转操作的树的根节点（y节点）
     * @return 经过右旋转操作之后的树的根节点（也就是x节点）
     */
    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        // 更新height的值
        // z节点的高度没有改变，只有y节点的高度和x节点的高度改变了
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    /**
     * AVL树的左旋转操作
     * ///////////////////////////////////////////////////////////
     * //        y                        x                     //
     * //     /   \       左旋转       /   \                    //
     * //   T4    x    ---------->   y      z                   //
     * //      /   \                /  \   /  \                 //
     * //    T3    z              T4   T3 T1  T2                //
     * //        /  \                                           //
     * //       T1   T2                                         //
     * ///////////////////////////////////////////////////////////
     *
     * @param node 需要进行右旋转操作的树的根节点（y节点）
     * @return 经过右旋转操作之后的树的根节点（也就是x节点）
     */
    private Node rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    private Node remove(K key, Node node) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = remove(key, node.left);
            return node;
        } else if (compare > 0) {
            node.right = remove(key, node.right);
            return node;
        } else {
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            Node min = min(node.right);
            min.right = removeMin(node.right);
            min.left = node.left;
            node.left = node.right = null;
            return min;
        }
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            size--;
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    /**
     * 判断该二叉树是否一颗平衡二叉树
     *
     * @return 如果是返回true，否则返回false
     */
    private boolean isBST() {
        List<K> keyList = new ArrayList<>();
        inOrder(root, keyList);
        for (int i = 1; i < keyList.size(); i++) {
            if (keyList.get(i - 1).compareTo(keyList.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 中序遍历将所有的key放入keyList中
     *
     * @param node    递归遍历的节点
     * @param keyList list集合
     */
    private void inOrder(Node node, List<K> keyList) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keyList);
        keyList.add(node.key);
        inOrder(node.right, keyList);
    }

    /**
     * 判断该二叉树是否是一颗平衡二叉树
     *
     * @return 如果是平衡的返回true，否则返回false
     */
    private boolean isBalanced() {
        return isBalanced(root);
    }

    /**
     * 递归判断以某一个节点为根节点的树是否是一颗二分搜索树
     *
     * @param node 根节点
     * @return 如果是返回true，否则返回false
     */
    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }
}
