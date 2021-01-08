package com.lyr.source.leetcode.tree;

/**
 * 二叉搜索树专题：
 * 538.把二叉搜索时转换为累加树
 * 
 * //给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于
 * // node.val 的值之和。 
 * //
 * // 提醒一下，二叉搜索树满足下列约束条件： 
 * //
 * // 
 * // 节点的左子树仅包含键 小于 节点键的节点。 
 * // 节点的右子树仅包含键 大于 节点键的节点。 
 * // 左右子树也必须是二叉搜索树。 
 * // 
 * //
 * // 注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-s
 * //um-tree/ 相同 
 * //
 * // 
 * //
 * // 示例 1： 
 * //
 * // 
 * //
 * // 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * //输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 * // 
 * //
 * // 示例 2： 
 * //
 * // 输入：root = [0,null,1]
 * //输出：[1,null,1]
 * // 
 * //
 * // 示例 3： 
 * //
 * // 输入：root = [1,0,2]
 * //输出：[3,3,2]
 * // 
 * //
 * // 示例 4： 
 * //
 * // 输入：root = [3,2,4,1]
 * //输出：[7,9,4,10]
 * // 
 * //
 * // 
 * //
 * // 提示： 
 * //
 * // 
 * // 树中的节点数介于 0 和 104 之间。 
 * // 每个节点的值介于 -104 和 104 之间。 
 * // 树中的所有值 互不相同 。 
 * // 给定的树为二叉搜索树。 
 * // 
 * // Related Topics 树 
 * 
 * @Author LinYuRong
 * @Date 2021/1/8 17:05
 * @Version 1.0
 */
public class ConvertBST538 {

    /**
     * 【限定条件】：1）二叉搜索树；2）使每个节点 node 的新值等于原树中大于或等于node.val的值之和。
     *
     * 思路过程：
     * 1）根节点需要知道右子树所有值的和（右子树节点的值都大于根的值）；根的左节点需要知道根及根右子树所有值的和；
     * 2）根据1），想到遍历顺序应该为：右子树->根->左子树。这其实是中序遍历，但结果为降序序列。
     * 3）根据降序序列的特点，增加一个外部变量来累计遍历过程中节点的值之和，巧妙解决问题。
     *
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        calcValue(root);
        return root;
    }

    int sum = 0;
    private void calcValue(TreeNode root) {
        if (root == null) {
            return;
        }

        calcValue(root.right);
        sum += root.val;
        root.val = sum;
        calcValue(root.left);
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
