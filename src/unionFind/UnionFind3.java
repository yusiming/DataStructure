package unionFind;

/**
 * @Auther yusiming
 * @Date 2018/12/7 11:16
 */
public class UnionFind3 implements UF {
    private int[] a;
    private int[] sz;

    public UnionFind3(int capacity) {
        a = new int[capacity];
        sz = new int[capacity];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
            sz[i] = 1;
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
        if (sz[pRoot] < sz[qRoot]) {
            a[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            a[qRoot] = a[pRoot];
            sz[pRoot] += sz[qRoot];
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
        while (a[p] != p) {
            p = a[p];
        }
        return p;
    }
}
