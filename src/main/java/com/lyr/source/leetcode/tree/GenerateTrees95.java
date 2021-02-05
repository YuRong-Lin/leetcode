package com.lyr.source.leetcode.tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉搜索树专题：
 * 95.不同的二叉搜索树 ||
 *
 * //给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 * //
 * //
 * //
 * // 示例：
 * //
 * // 输入：3
 * //输出：
 * //[
 * //  [1,null,3,2],
 * //  [3,2,null,1],
 * //  [3,1,null,null,2],
 * //  [2,1,3],
 * //  [1,null,2,null,3]
 * //]
 * //解释：
 * //以上的输出对应以下 5 种不同结构的二叉搜索树：
 * //
 * //   1         3     3      2      1
 * //    \       /     /      / \      \
 * //     3     2     1      1   3      2
 * //    /     /       \                 \
 * //   2     1         2                 3
 * //
 * //
 * //
 * //
 * // 提示：
 * //
 * //
 * // 0 <= n <= 8
 * //
 * // Related Topics 树 动态规划
 *
 * Difficulty: Medium
 *
 * @Author LinYuRong
 * @Date 2021/2/5 14:42
 * @Version 1.0
 */
public class GenerateTrees95 {

    /**
     * 【限定条件】：二叉搜索树
     *
     * 思路过程：
     * 1）假设以m（1<=m<=n），求其左右子树的组合；
     * 2）将左右子树两两组合；
     * 3）左右子树递归处理；
     * 4）base case
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return Collections.emptyList();
        }
        return build(1, n);
    }

    private List<TreeNode> build(int lo, int hi) {
        List<TreeNode> res = new LinkedList<>();
        if (lo > hi) {
            res.add(null);
            return res;
        }

        for (int i = lo; i <= hi; i++) {
            List<TreeNode> leftNodes = build(lo, i - 1);
            List<TreeNode> rightNodes = build(i + 1, hi);

            for (TreeNode left : leftNodes) {
                for (TreeNode right : rightNodes) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
