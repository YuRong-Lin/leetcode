package com.lyr.source.leetcode.tree;

/**
 * 二叉树专题
 * 114.二叉树展开为链表
 *
 * //给定一个二叉树，原地将它展开为一个单链表。
 * //
 * //
 * //
 * // 例如，给定二叉树
 * //
 * //     1
 * //   / \
 * //  2   5
 * // / \   \
 * //3   4   6
 * //
 * // 将其展开为：
 * //
 * // 1
 * // \
 * //  2
 * //   \
 * //    3
 * //     \
 * //      4
 * //       \
 * //        5
 * //         \
 * //          6
 * // Related Topics 树 深度优先搜索
 *
 * @Author LinYuRong
 * @Date 2020/12/31 16:13
 * @Version 1.0
 */
public class Flatten114 {

    /**
     * 【限定条件】：原地展开（空间复杂度为o(1)）
     *
     * 思路过程：
     * 1）将左右子树展开（对应后续遍历）
     * 2）将右子树接到左子树的最右节点，将左子树接到root的右节点
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        flatten(root.left);
        flatten(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;

        // 左子树作为root的右节点
        root.left = null;
        root.right = left;

        // 右子树放到原左子树的最右节点上
        TreeNode temp = root;
        while (temp.right != null) {
            temp = temp.right;
        }
        temp.right = right;
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
