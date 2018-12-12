package unionFind;

/**
 * @Auther yusiming
 * @Date 2018/12/7 10:19
 */
public interface UF {
    /**
     * 返回并查集中元素的个数
     *
     * @return 元素的个数
     */
    int getSize();

    /**
     * 连接两个节点
     *
     * @param p 节点p
     * @param q 节点q
     */
    void union(int p, int q);

    /**
     * 判断两个节点是否是连通的
     *
     * @param p 节点p
     * @param q 节点q
     * @return 如果连通的返回true，否则返回false
     */
    boolean isConnected(int p, int q);
}
