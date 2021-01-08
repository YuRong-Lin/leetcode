package com.lyr.source.leetcode.tree;

/**
 * 二叉搜索树专题：
 * 98.验证二叉搜索树
 * 
 * //给定一个二叉树，判断其是否是一个有效的二叉搜索树。 
 * //
 * // 假设一个二叉搜索树具有如下特征： 
 * //
 * // 
 * // 节点的左子树只包含小于当前节点的数。 
 * // 节点的右子树只包含大于当前节点的数。 
 * // 所有左子树和右子树自身必须也是二叉搜索树。 
 * // 
 * //
 * // 示例 1: 
 * //
 * // 输入:
 * //    2
 * //   / \
 * //  1   3
 * //输出: true
 * // 
 * //
 * // 示例 2: 
 * //
 * // 输入:
 * //    5
 * //   / \
 * //  1   4
 * //     / \
 * //    3   6
 * //输出: false
 * //解释: 输入为: [5,1,4,null,null,3,6]。
 * //     根节点的值为 5 ，但是其右子节点值为 4 。
 * // 
 * // Related Topics 树 深度优先搜索 递归 
 * 
 * @Author LinYuRong
 * @Date 2021/1/8 17:48
 * @Version 1.0
 */
public class IsValidBST98 {

    /**
     * 【限定条件】：二叉搜索树
     *
     * 思路过程：
     * 1）根据二叉搜索树的定义，根需要大于左子树所有节点且小于右子树所有节点；
     * 2）根据1），需要将根的值传到左右子树中限定它们的值范围（最大、最小），因此定义一个新函数，将限定条件传进去。
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValid(root, null, null);
    }

    private boolean isValid(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.val <= min.val) {
            return false;
        }
        if (max != null && root.val >= max.val) {
            return false;
        }
        return isValid(root.left, min, root) && isValid(root.right, root, max);
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
