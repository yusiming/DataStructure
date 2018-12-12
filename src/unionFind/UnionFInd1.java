package unionFind;

/**
 * @Auther yusiming
 * @Date 2018/12/7 10:50
 */
public class UnionFInd1 implements UF {
    private int[] a;

    public UnionFInd1(int capaciy) {
        a = new int[capaciy];
        // 刚开始时，每一个节点都是属于不同的集合
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
        for (int i = 0; i < a.length; i++) {
            if (find(i) == find(q)) {
                a[i] = find(p);
            }
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
        return a[p];
    }
}
