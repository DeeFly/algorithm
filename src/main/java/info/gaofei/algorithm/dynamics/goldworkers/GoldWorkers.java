package info.gaofei.algorithm.dynamics.goldworkers;

import java.util.HashMap;
import java.util.Map;

/**
 * N个金矿，W个工人
 * 第i个金矿的含金量为G[i-1]
 * 第i个金矿需要的挖金人为P[i-1]
 * 一个工人不能到两个矿上去挖
 * 每个金矿要么不挖，要么全部挖
 * 求可以挖到的最大的黄金数量
 *
 * g 各金矿含金量
 * p 各金矿需要的挖金工人数
 * n 需要挖的金矿数量
 * w 挖矿工人数
 * 数学建模：
 * f(n,w) = 0 ; n < 1
 * f(n,w) = 0 ; n = 1 && w < p[0]
 * f(n,w) = g[0] ; n = 1 && w >= p[0]
 * f(n,w) = f(n - 1, w) ; n > 1, w < p[n -1]
 * f(n,w) = max(f(n - 1, w), f(n - 1, w - p[n - 1]) + g[n - 1])
 * Created by GaoQingming on 2018/12/12 0012.
 */
public class GoldWorkers {
    public static void main(String[] args) {
        int[] g = {400, 500, 200, 300, 350};
        int[] p = {5, 5, 3, 4, 3};
        System.out.println(maxGold(g, p, 10));
        System.out.println(calculateCount);
    }

    private static int calculateCount = 0;
    private static Map<String, Integer> resultCache = new HashMap<>();

    public static int maxGold(int[] G, int[] P, int w) {
        if (G == null || G.length == 0) {
            return 0;
        }

        if (P == null || P.length == 0) {
            return 0;
        }

        if (P.length < G.length) {
            throw new RuntimeException("工人数组不能少于金矿数组");
        }

        if (w < 1) {
            throw new RuntimeException("工人数不能小于1");
        }

        return doExcavateMaxGold(G, P, G.length, w);
    }

    /**
     * @param g 各金矿含金量
     * @param p 各金矿需要的挖金工人数
     * @param n 需要挖的金矿数量
     * @param w 挖矿工人数
     * @return 给定金矿数和挖矿工人下的最大挖矿数。
     */
    private static int doExcavateMaxGold(int[] g, int[] p, int n, int w) {
        String cacheKey = n + ":" + w;
        Integer cacheValue = resultCache.get(cacheKey);
        if (cacheValue != null) {
            return cacheValue;
        }

        calculateCount++;
        int result;
        if (n < 1) {
            return 0;
        }

        if (n == 1 && w < p[0]) {
            return 0;
        }

        if (n == 1 && w >= p[0]) {
            return g[0];
        }

        if (n > 1 && w < p[n - 1]) {
            result = doExcavateMaxGold(g, p, n - 1, w);
        } else {
            result = Math.max(doExcavateMaxGold(g, p, n - 1, w), doExcavateMaxGold(g, p, n - 1, w - p[n - 1]) + g[n - 1]);
        }

        resultCache.put(cacheKey, result);
        return result;
    }
}
