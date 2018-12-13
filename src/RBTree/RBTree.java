package RBTree;

import map.Map;

/**
 * 红黑树
 *
 * @Auther yusiming
 * @Date 2018/12/10 13:14
 */
public class RBTree<K extends Comparable<K>, V> implements Map<K, V> {
    private Node root;
    private int size;
    private static final boolean RED = true;
    private static final boolean BLACK = true;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            // 创建的新节点的颜色总是红色的
            color = RED;
        }
    }

    /**
     * 左旋转
     * ///////////////////////////////////////////////////////////
     * //      node   红                  x                     //
     * //     /   \ <---   左旋转      /   \                    //
     * //   T4    x    ---------->  node    z                   //
     * //      /   \                /  \   /  \                 //
     * //    T3    z              T4   T3 T1  T2                //
     * //        /  \                                           //
     * //       T1   T2                                         //
     * ///////////////////////////////////////////////////////////
     *
     * @param node 被旋转的根节点
     * @return 返回旋转之后的根节点
     */
    private Node rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    /**
     * 右旋转
     * ///////////////////////////////////////////////////////////
     * //     红       node                           x          //
     * //    ---->    /   \                        /   \        //
     * //    红     x     T4      右旋转        z     node      //
     * //  ---->  /  \           -------->    /  \    /  \      //
     * //       z    T3                    T1   T2   T3   T4    //
     * //     /  \                                              //
     * //   T1    T2                                            //
     * //////////////////////////////////////////////////////////
     *
     * @param node 被旋转的根节点
     * @return 返回经过旋转之后的根节点
     */
    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color == RED;
    }

    /**
     * 调整node节点及其左右子节点的颜色
     * 这步操作实际上是将根节点向上传递
     *
     * @param node 需要调整颜色节点
     */
    private void fileColors(Node node) {
        node.left.color = BLACK;
        node.right.color = BLACK;
        node.color = RED;
    }

    @Override
    public void add(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("参数不合法!");
        }
        root = add(root, key, value);
        // 保持根节点为黑色
        root.color = BLACK;
        java.util.Map map;
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = add(node.left, key, value);
        } else if (compare > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        // 下面这三步其实是一个执行链
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            fileColors(node);
        }
        return node;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void set(K k, V value) {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
