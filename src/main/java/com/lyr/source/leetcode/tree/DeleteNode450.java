package com.lyr.source.leetcode.tree;

/**
 * 二叉搜索树专题：
 * 450.删除二叉搜索树中的节点
 *
 * //给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的
 * //根节点的引用。
 * //
 * // 一般来说，删除节点可分为两个步骤：
 * //
 * //
 * // 首先找到需要删除的节点；
 * // 如果找到了，删除它。
 * //
 * //
 * // 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 * //
 * // 示例:
 * //
 * //
 * //root = [5,3,6,2,4,null,7]
 * //key = 3
 * //
 * //    5
 * //   / \
 * //  3   6
 * // / \   \
 * //2   4   7
 * //
 * //给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * //
 * //一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 * //
 * //    5
 * //   / \
 * //  4   6
 * // /     \
 * //2       7
 * //
 * //另一个正确答案是 [5,2,6,null,4,null,7]。
 * //
 * //    5
 * //   / \
 * //  2   6
 * //   \   \
 * //    4   7
 * //
 * // Related Topics 树
 *
 * Difficulty: Medium
 *
 * @Author lyr
 * @Date 2021/1/9 下午9:50
 * @Version 1.0
 */
public class DeleteNode450 {

    /**
     * 【限定条件】：二叉搜索树
     *
     * 思路过程：
     * 1）要找到删除的节点，根据二叉搜索树的特性，有以下的查找规则：
     *      if (root.val == key) {
     *          // 有三种情况：
     *          1、左右子树均为空
     *          2、左子树或右子树为空
     *          3、左右子树均不为空（比较复杂）
     *      } else if (root.val > key) {
     *          // 左子树找
     *      } else {
     *          // 右子树找
     *      }
     * 2）但要删除某个节点，同时不能破坏二叉搜索树，需要处理好以下关系：
     *      假设要删除节点为A：
     *      处理方式有：a、找其左子树的最大节点替换它（leftMax）；b、也可以找其右子树的最小节点替换它（rightMin），本解法采用a方式；
     *      涉及节点：节点A，节点A的父节点，leftMax及leftMax的左节点
     *      节点关系调整：
     *          1、root.val == A.val:
     *              a)root.left == null
     *                  return root.right即可
     *              b)root.right == null
     *                  return root.left即可
     *              c)root.left != null && root.right != null
     *                  找到左子树的最大节点leftMax,并将leftMax的左子树赋值给leftMax的父节点的右节点
     *                  将leftMax替换root:
     *                      当leftMax != root.left时，将root.left赋给leftMax.left
     *                      leftMax.right = root.right
     *                  return leftMax
     *
     *          2、root.val > A.val:
     *              到左子树中处理
     *          3、root.val < A.val:
     *              到右子树中处理
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        return doDeleteNode(root, key);
    }

    /**
     * 返回删除指定节点后的树的根节点
     *
     * @param root
     * @param key
     * @return
     */
    private TreeNode doDeleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            // 获取左子树的最大值节点并处理其节点关系
            TreeNode leftMax = handleLeftMaxAndGet(root.left);
            // 将leftMax替换root
            if (root.left != leftMax) {
                leftMax.left = root.left;
            }
            leftMax.right = root.right;
            return leftMax;
        } else if (root.val > key) {
            root.left = doDeleteNode(root.left, key);
        } else {
            root.right = doDeleteNode(root.right, key);
        }
        return root;
    }

    /**
     * 找到左子树中的最大节点，同时处理其节点关系
     *
     * @param root 左子树的根
     * @return
     */
    private TreeNode handleLeftMaxAndGet(TreeNode root) {
        TreeNode leftMax = root;
        TreeNode leftMaxParent = root;
        while (leftMax.right != null) {
            leftMaxParent = leftMax;
            leftMax = leftMax.right;
        }
        // 将leftMax（右节点肯定为空）的左节点赋给其父节点的右节点
        leftMaxParent.right = leftMax.left;
        return leftMax;
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
