package com.lyr.source.leetcode.tree;

/**
 * 二叉搜索树专题：
 * 700.二叉搜索树中的搜索
 * 
 * //给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。 
 * //
 * // 例如， 
 * //
 * // 
 * //给定二叉搜索树:
 * //
 * //        4
 * //       / \
 * //      2   7
 * //     / \
 * //    1   3
 * //
 * //和值: 2
 * // 
 * //
 * // 你应该返回如下子树: 
 * //
 * // 
 * //      2     
 * //     / \   
 * //    1   3
 * // 
 * //
 * // 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。 
 * // Related Topics 树 
 *
 * Difficulty：easy
 *
 * @Author LinYuRong
 * @Date 2021/1/8 18:06
 * @Version 1.0
 */
public class SearchBST700 {

    /**
     * 【限定条件】：二叉搜索树
     *
     * 思路过程：
     * 1）利用二叉搜索树的特性，将搜索值与根的值比较，相等则直接返回，否则到左子树或右子树搜索。
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        return find(root, val);
    }

    private TreeNode find(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        } else if (root.val > val) {
            return find(root.left, val);
        } else {
            return find(root.right, val);
        }
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
