package com.lyr.source.leetcode.tree;

/**
 * 二叉搜索树专题：
 * 701.二叉搜索树中的插入操作
 *
 * //给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值
 * //都不同。
 * //
 * // 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
 * //
 * //
 * //
 * // 示例 1：
 * //
 * //
 * //输入：root = [4,2,7,1,3], val = 5
 * //输出：[4,2,7,1,3,5]
 * //解释：另一个满足题目要求可以通过的树是：
 * //
 * //
 * //
 * // 示例 2：
 * //
 * //
 * //输入：root = [40,20,60,10,30,50,70], val = 25
 * //输出：[40,20,60,10,30,50,70,null,null,25]
 * //
 * //
 * // 示例 3：
 * //
 * //
 * //输入：root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
 * //输出：[4,2,7,1,3,5]
 * //
 * //
 * //
 * //
 * //
 * //
 * // 提示：
 * //
 * //
 * // 给定的树上的节点数介于 0 和 10^4 之间
 * // 每个节点都有一个唯一整数值，取值范围从 0 到 10^8
 * // -10^8 <= val <= 10^8
 * // 新值和原始二叉搜索树中的任意节点值都不同
 * //
 * // Related Topics 树
 *
 * Difficulty: Medium
 *
 * @Author LinYuRong
 * @Date 2021/1/8 18:31
 * @Version 1.0
 */
public class InsertIntoBST701 {

    /**
     * 【限定条件】：二叉搜索树；输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同
     *
     * 思路过程：
     * 1）直接根据插入值和根的值比较，找到对应插入点，新增节点；
     * 2）实现方式1比较直接，很容易想到（新增了一个递归辅助函数）；
     * 3）实现方式2直接在一个函数中递归实现
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST01(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        findAndInsert(root, val);
        return root;
    }

    private void findAndInsert(TreeNode root, int val) {
        if (root == null) {
            return;
        }
        if (root.val > val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                findAndInsert(root.left, val);
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                findAndInsert(root.right, val);
            }
        }
    }

    public TreeNode insertIntoBST02(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val > val) {
            root.left = insertIntoBST02(root.left, val);
        } else {
            root.right = insertIntoBST02(root.right, val);
        }

        return root;
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
