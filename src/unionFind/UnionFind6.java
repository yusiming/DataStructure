package unionFind;

/**
 * @Auther yusiming
 * @Date 2018/12/7 11:16
 */
public class UnionFind6 implements UF {
    private int[] a;
    private int[] rank;

    public UnionFind6(int capacity) {
        a = new int[capacity];
        rank = new int[capacity];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return a.length;
    }

    @Override
    public void union(int p, int q) {
        if (p < 0 || p >= a.length || q < 0 || q >= a.length) {
            throw new IllegalArgumentException("参数不合法");
        }
        if (isConnected(p, q)) {
            return;
        }
        int pRoot = find(p);
        int qRoot = find(q);
        // 如果两个数的高度不一致，那么合并之后的整个高度是不变的
        if (rank[pRoot] < rank[qRoot]) {
            a[pRoot] = qRoot;
        } else if ((rank[qRoot] < rank[pRoot])) {
            a[qRoot] = pRoot;
        } else {
            // 如果两个数的高度相同，那么整体的高度就会加1
            a[qRoot] = pRoot;
            rank[pRoot] += 1;
        }

    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        if (p < 0 || p >= a.length) {
            throw new IllegalArgumentException("参数不合法:" + p);
        }
        if (a[p] != p) {
            // 递归的将所有节点都直接指向根节点
            a[p] = find(a[p]);
        }
        return p;
    }
}
