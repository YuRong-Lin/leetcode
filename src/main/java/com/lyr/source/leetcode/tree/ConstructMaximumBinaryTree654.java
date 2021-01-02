package com.lyr.source.leetcode.tree;

/**
 * 二叉树专题
 * 654.最大二叉树
 * <p>
 * //给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
 * //
 * //
 * // 二叉树的根是数组中的最大元素。
 * // 左子树是通过数组中最大值左边部分构造出的最大二叉树。
 * // 右子树是通过数组中最大值右边部分构造出的最大二叉树。
 * //
 * //
 * // 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
 * //
 * //
 * //
 * // 示例 ：
 * //
 * // 输入：[3,2,1,6,0,5]
 * //输出：返回下面这棵树的根节点：
 * //
 * //      6
 * //    /   \
 * //   3     5
 * //    \    /
 * //     2  0
 * //       \
 * //        1
 *
 * @Author lyr
 * @Date 2021/1/2 10:06 下午
 * @Version 1.0
 */
public class ConstructMaximumBinaryTree654 {

    /**
     * 【限定条件】：不含重复元素、整数数组、最大二叉树定义
     *
     * 思路过程：
     * 1）构建二叉树，就是确定根、左子树、右子树；
     * 2）根据最大二叉树的定义，很容易确定根即给定数组的最大元素，左子树的根即对应子数组的最大元素，右子树的根同理；
     * 3）根据2），这棵最大二叉树的根及其左右节点便确定好了，则以同样的逻辑确定左右子树即可。
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        return build(nums, 0, nums.length - 1);
    }

    /**
     * 构建二叉树，返回根节点
     *
     * @param nums
     * @param lo 对应子树的数组起始索引
     * @param hi 对应子树的数组终止索引
     * @return
     */
    private TreeNode build(int[] nums, int lo, int hi) {
        if (lo > hi) {
            return null;
        }

        // 确定数组最大元素，即根，记录对应的索引值
        int index = -1;
        int rootVal = -1;

        for (int i = lo; i <= hi; i++) {
            if (rootVal < nums[i]) {
                rootVal = nums[i];
                index = i;
            }
        }

        TreeNode root = new TreeNode(rootVal);
        root.left = build(nums, lo, index - 1);
        root.right = build(nums, index + 1, hi);

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
