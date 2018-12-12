package unionFind;

import java.util.Random;

/**
 * @Auther yusiming
 * @Date 2018/12/7 13:36
 */
public class Main {
    private static double testUF(UF uf, int m) {
        int size = uf.getSize();
        Random random = new Random();
        long startTime = System.nanoTime();
        for (int i = 0; i < m; i++) {
            uf.union(random.nextInt(size), random.nextInt(size));
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int size = 10000000;
        int m = 10000000;
        UF uf = new UnionFInd1(size);
        // System.out.println("UnionFInd1 :" + testUF(uf, m));
        uf = new UnionFind2(size);
        // System.out.println("UnionFInd2 :" + testUF(uf, m));
        uf = new UnionFind3(size);
        System.out.println("UnionFInd3 :" + testUF(uf, m));
        uf = new UnionFind4(size);
        System.out.println("UnionFInd4 :" + testUF(uf, m));
        uf = new UnionFind5(size);
        System.out.println("UnionFInd5 :" + testUF(uf, m));
        uf = new UnionFind6(size);
        System.out.println("UnionFInd6 :" + testUF(uf, m));
        // UnionFInd3 :3.659531892
        // UnionFInd4 :3.461341764
        // UnionFInd5 :2.661875899
        // UnionFInd6 :2.652768497
    }
}
