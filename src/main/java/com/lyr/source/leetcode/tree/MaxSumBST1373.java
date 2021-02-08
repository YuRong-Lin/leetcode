package com.lyr.source.leetcode.tree;

/**
 * 二叉树专题：
 * 1373.二叉搜索子树的最大键值和
 * <p>
 * //给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
 * //
 * // 二叉搜索树的定义如下：
 * //
 * //
 * // 任意节点的左子树中的键值都 小于 此节点的键值。
 * // 任意节点的右子树中的键值都 大于 此节点的键值。
 * // 任意节点的左子树和右子树都是二叉搜索树。
 * //
 * //
 * //
 * //
 * // 示例 1：
 * //
 * //
 * //
 * // 输入：root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
 * //输出：20
 * //解释：键值为 3 的子树是和最大的二叉搜索树。
 * //
 * //
 * // 示例 2：
 * //
 * //
 * //
 * // 输入：root = [4,3,null,1,2]
 * //输出：2
 * //解释：键值为 2 的单节点子树是和最大的二叉搜索树。
 * //
 * //
 * // 示例 3：
 * //
 * // 输入：root = [-4,-2,-5]
 * //输出：0
 * //解释：所有节点键值都为负数，和最大的二叉搜索树为空。
 * //
 * //
 * // 示例 4：
 * //
 * // 输入：root = [2,1,3]
 * //输出：6
 * //
 * //
 * // 示例 5：
 * //
 * // 输入：root = [5,4,8,3,null,6,3]
 * //输出：7
 * //
 * //
 * //
 * //
 * // 提示：
 * //
 * //
 * // 每棵树最多有 40000 个节点。
 * // 每个节点的键值在 [-4 * 10^4 , 4 * 10^4] 之间。
 * //
 * // Related Topics 二叉搜索树 动态规划
 * <p>
 * Difficulty: Hard
 *
 * @Author LinYuRong
 * @Date 2021/2/8 10:46
 * @Version 1.0
 */
public class MaxSumBST1373 {
    /**
     * 【限定条件】：找子树为二叉搜索树的最大键值和
     * 思路过程：
     * 1）以当前节点来看，需要知道哪些信息：
     * 1、左右子树是否为二叉搜索树（只有左右子树为BST，以当前节点为根才【可能】是BST，要保证为BST还得满足2）
     * 2、左子树的最大值和右子树的最小值（用于判断以当前节点为根是否仍是BST）
     * 3、左右子树的键值和
     * 2）要知道以上3点信息，当前节点只能等左右子树的结果出来了才能知道，因此，这里适合采用后续遍历方式处理。
     *
     * @param root
     * @return
     */
    private int maxSum;

    public int maxSumBST(TreeNode root) {
        handle(root);
        return maxSum;
    }

    /**
     * @param root
     * @return 大小为4的数组，分别返回：
     * [0]-以root为根的子树否为BST（1-是，0-否）;
     * [1]-以root为根的最小节点值;
     * [2]-以root为根的最大节点值;
     * [3]-以root为根的键值和
     */
    private int[] handle(TreeNode root) {
        // base case
        if (root == null) {
            return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }

        int[] left = handle(root.left);
        int[] right = handle(root.right);

        int[] res = new int[4];
        if (left[0] == 1 && right[0] == 1 && root.val > left[2] && root.val < right[1]) {
            res[0] = 1;
            res[1] = Math.min(left[1], root.val);
            res[2] = Math.max(right[2], root.val);
            res[3] = left[3] + right[3] + root.val;
        } else {
            res[0] = 0;
        }

        maxSum = Math.max(maxSum, res[3]);

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
