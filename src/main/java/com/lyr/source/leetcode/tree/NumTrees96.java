package com.lyr.source.leetcode.tree;

/**
 * 二叉搜索树专题：
 * 96.不同的二叉搜索树
 *
 * //给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * //
 * // 示例:
 * //
 * // 输入: 3
 * //输出: 5
 * //解释:
 * //给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * //
 * //   1         3     3      2      1
 * //    \       /     /      / \      \
 * //     3     2     1      1   3      2
 * //    /     /       \                 \
 * //   2     1         2                 3
 * // Related Topics 树 动态规划
 *
 * Difficulty: Medium
 *
 * @Author LinYuRong
 * @Date 2021/2/5 14:43
 * @Version 1.0
 */
public class NumTrees96 {

    /**
     * 【限定条件】：二叉搜索树
     *
     * 思路过程：
     * 1）先假设根为m（1<=m<=n），则其左子树为[1,m-1]的组合，右子树为[m+1,n]的组合；
     * 2）还是递归的套路：左子树和右子树采用同样的方式处理；
     * 3）根为m的组合=左子树的组合数*右子树的组合数
     * 4）由于1~n都可以为根，所以需要一遍循环，循环内的逻辑即1~3的步骤；
     * 5）考虑base case
     * 6）考虑重复子问题：增加备忘录
     *
     * @param n
     * @return
     */
    int[][] member = null;
    public int numTrees(int n) {
        member = new int[n + 1][n + 1];
        return count(1, n);
    }

    private int count(int lo, int hi) {
        // base case: [] 空树也算一棵
        if (lo > hi) {
            return 1;
        }

        if (member[lo][hi] != 0) {
            return member[lo][hi];
        }

        // 遍历，每个元素都可为根
        int res = 0;
        for (int i = lo; i <= hi; i++) {
            // i为根
            int left = count(lo, i - 1);
            int right = count(i + 1, hi);
            res += left * right;
        }

        member[lo][hi] = res;

        return res;
    }
}
