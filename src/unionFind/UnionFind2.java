package unionFind;

/**
 * @Auther yusiming
 * @Date 2018/12/7 11:16
 */
public class UnionFind2 implements UF {
    private int[] a;

    public UnionFind2(int capacity) {
        a = new int[capacity];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
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
        a[pRoot] = qRoot;
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
