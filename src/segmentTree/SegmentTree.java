package segmentTree;

/**
 * @Auther yusiming
 * @Date 2018/12/4 10:14
 */
public class SegmentTree<T> {
    private T[] tree;
    private T[] data;
    private Merger<T> merger;

    @SuppressWarnings("unchecked")
    public SegmentTree(T[] arr, Merger<T> merger) {
        data = (T[]) new Object[arr.length];
        this.merger = merger;
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (T[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    /**
     * 递归方法构建线段树
     *
     * @param treeIndex 要构建的节点的索引
     * @param left      左边界
     * @param right     右边界
     */
    private void buildSegmentTree(int treeIndex, int left, int right) {
        // 如果区间大小为1，证明是一个叶子节点，直接赋值即可
        if (left == right) {
            tree[treeIndex] = data[left];
            return;
        }
        // 找到左子节点的索引
        int leftTreeIndex = leftChild(treeIndex);
        // 找到右子节点的索引
        int rightTreeIndex = rightChild(treeIndex);
        // 为了防止两个int值相加可能会超出int类型可以表示的最大值
        int middle = left + (right - left) / 2;
        // 构建左右子节点
        buildSegmentTree(leftTreeIndex, left, middle);
        buildSegmentTree(rightTreeIndex, middle + 1, right);
        // 通过左右子节点构建当前节点，这里的逻辑由用户自己定义
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public T query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("参数不合法");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 递归查询以treeIndex为根节点中的某个区间的信息
     *
     * @param treeIndex 查询的节点
     * @param left      左边界
     * @param right     右边界
     * @param queryL    查询的左边界
     * @param queryR    查询的右边界
     * @return 查询结果
     */
    public T query(int treeIndex, int left, int right, int queryL, int queryR) {
        if (left == queryL && right == queryR) {
            return tree[treeIndex];
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int middle = left + (right - left) / 2;
        if (queryL >= middle + 1) {
            return query(rightTreeIndex, middle + 1, right, queryL, queryR);
        } else if (queryR <= middle) {
            return query(leftTreeIndex, left, middle, queryL, queryR);
        }
        T leftResult = query(leftTreeIndex, left, middle, queryL, middle);
        T rightResult = query(rightTreeIndex, middle + 1, right, middle, queryR);
        return merger.merge(leftResult, rightResult);
    }

    public T get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("参数不合法" + index);
        }
        return data[index];
    }

    /**
     * 更新数组中某一个数据的值
     *
     * @param index 要更新的元素的索引
     * @param t     新的值
     */
    public void set(int index, T t) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("参数不合法");
        }
        data[index] = t;
        set(0, 0, data.length - 1, index, t);
    }

    /**
     * 递归更新，以treeIndex为根节点的区间中，index位置的元素的值
     *
     * @param treeIndex
     * @param left
     * @param right
     * @param index
     * @param t
     */
    private void set(int treeIndex, int left, int right, int index, T t) {
        if (left == right) {
            tree[treeIndex] = t;
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int middle = left + (right - left) / 2;
        if (index <= middle) {
            set(leftTreeIndex, left, middle, index, t);
        } else {
            set(rightTreeIndex, middle + 1, right, index, t);
        }
        // 更新信息
        merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize() {
        return data.length;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 0};
        SegmentTree<Integer> segmentTree = new SegmentTree<Integer>(arr, new Merger<Integer>() {
            @Override
            public Integer merge(Integer left, Integer right) {
                return left + right;
            }
        });
        System.out.println(segmentTree.query(1, 2));
    }
}
