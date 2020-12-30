package com.lyr.source.leetcode.tree;

/**
 * 二叉树专题
 *
 * 226.翻转一棵二叉树
 *
 * // 示例：
 * //
 * // 输入：
 * //
 * //      4
 * //   /   \
 * //  2     7
 * // / \   / \
 * //1   3 6   9
 * //
 * // 输出：
 * //
 * //      4
 * //   /   \
 * //  7     2
 * // / \   / \
 * //9   6 3   1
 *
 * @Author LinYuRong
 * @Date 2020/12/30 17:22
 * @Version 1.0
 */
public class InvertTree226 {

    /**
     * 【要点】：确定当前节点应该做什么，然后递归调用子节点做同样的事情
     *
     * 思考过程：
     * 1）要让整棵树翻转，只要左右子树先翻转，再对调左右子树即可
     * 2）先序、后序遍历都能实现
     *
     * @param root
     * @return
     */
    public TreeNode invertTree01(TreeNode root) {
        if (root == null) {
            return null;
        }
        invertTree01(root.left);
        invertTree01(root.right);

        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        return root;
    }

    public TreeNode invertTree02(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;

        invertTree02(root.left);
        invertTree02(root.right);

        return root;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
