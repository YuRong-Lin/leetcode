package com.lyr.source.leetcode.tree;

/**
 * 二叉搜索树专题：
 * 230.二叉搜索树中第K小的元素
 * 
 * //给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。 
 * //
 * // 说明： 
 * //你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。 
 * //
 * // 示例 1: 
 * //
 * // 输入: root = [3,1,4,null,2], k = 1
 * //   3
 * //  / \
 * // 1   4
 * //  \
 * //   2
 * //输出: 1 
 * //
 * // 示例 2: 
 * //
 * // 输入: root = [5,3,6,2,4,null,null,1], k = 3
 * //       5
 * //      / \
 * //     3   6
 * //    / \
 * //   2   4
 * //  /
 * // 1
 * //输出: 3 
 * //
 * // 进阶： 
 * //如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？ 
 * // Related Topics 树 二分查找 
 * 
 * @Author LinYuRong
 * @Date 2021/1/6 18:21
 * @Version 1.0
 */
public class KthSmallest230 {

    /**
     *【限定条件】：二叉搜索树；返回第K个最小的元素; 1 ≤ k ≤ 二叉搜索树元素个数
     *
     * 思考过程：
     * 1）二叉搜索树特点：根节点比左子树都大，比右子树都小 --> 中序遍历即升序序列
     * 2）最简单的办法：在中序遍历过程中统计排名，找到第K小元素即退出
     * 3）高效方法：需在节点中维护额外信息（每个节点都记录下以自己为根的总节点数，这样通过node.left推导出自己的排名）
     *
     * @param root
     * @param k
     * @return
     */
    int res = -1;
    int rank = 0;
    public int kthSmallest(TreeNode root, int k) {
        calc(root, k);
        return res;
    }

    private void calc(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        calc(root.left, k);
        rank++;
        if (rank == k) {
            res = root.val;
            return;
        }
        calc(root.right, k);
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
