package com.lyr.source.leetcode.tree;

/**
 * 二叉树专题：
 * 105.从前序遍历与中序遍历序列构造二叉树
 *
 * //根据一棵树的前序遍历与中序遍历构造二叉树。
 * //
 * // 注意:
 * //你可以假设树中没有重复的元素。
 * //
 * // 例如，给出
 * //
 * // 前序遍历 preorder = [3,9,20,15,7]
 * //中序遍历 inorder = [9,3,15,20,7]
 * //
 * // 返回如下的二叉树：
 * //
 * //     3
 * //   / \
 * //  9  20
 * //    /  \
 * //   15   7
 * // Related Topics 树 深度优先搜索 数组
 *
 * @Author lyr
 * @Date 2021/1/2 10:31 下午
 * @Version 1.0
 */
public class BuildTree105 {

    /**
     * 【限定条件】：假设树中没有重复的元素
     *
     * 思考过程：
     * 1）前序遍历的性质：根->左子树->右子树；中序遍历的性质：左子树->根->右子树
     * 2）确定根：前序遍历的第1个值即为根；
     * 3）确定左右子树：根据1）得到根后，到中序遍历序列中找到其索引，该索引左边部分即为左子树的序列集合，右边部分为右子树序列集合；
     * 4）最后，只要通过在前序/中序遍历序列中将根、左子树、右子树的数组索引标识出来，递归构建左右子树。
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        int boardIndex = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                boardIndex = i;
            }
        }
        // 左子树节点树
        int leftSize = boardIndex - inStart;

        root.left = build(preorder, preStart + 1, preStart + leftSize, inorder, inStart, boardIndex - 1);
        root.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, boardIndex + 1, inEnd);

        return root;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
