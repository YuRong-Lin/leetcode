package com.lyr.source.leetcode.tree;

/**
 * 二叉树专题：
 * 106.从前序遍历与中序遍历序列构造二叉树
 *
 * //根据一棵树的中序遍历与后序遍历构造二叉树。
 * //
 * // 注意:
 * //你可以假设树中没有重复的元素。
 * //
 * // 例如，给出
 * //
 * // 中序遍历 inorder = [9,3,15,20,7]
 * //后序遍历 postorder = [9,15,7,20,3]
 * //
 * // 返回如下的二叉树：
 * //
 * //     3
 * //   / \
 * //  9  20
 * //    /  \
 * //   15   7
 * //
 * // Related Topics 树 深度优先搜索 数组
 *
 * @Author lyr
 * @Date 2021/1/2 11:09 下午
 * @Version 1.0
 */
public class BuildTree106 {

    /**
     *【限定条件】：假设树中没有重复的元素
     *
     * 思考过程：
     * 1）后序遍历的性质：左子树->右子树->根；中序遍历的性质：左子树->根->右子树
     * 2）确定根：后序遍历的最后1个值即为根；
     * 3）确定左右子树：根据1）得到根后，到中序遍历序列中找到其索引，该索引左边部分即为左子树的序列集合，右边部分为右子树序列集合；
     * 4）最后，只要通过在前序/中序遍历序列中将根、左子树、右子树的数组索引标识出来，递归构建左右子树。
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) {
            return null;
        }
        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode build(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        int rootVal = postorder[postEnd];
        int boardIndex = -1;

        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                boardIndex = i;
                break;
            }
        }

        int leftSize = boardIndex - inStart;

        TreeNode root = new TreeNode(rootVal);
        root.left = build(inorder, inStart, boardIndex - 1, postorder, postStart, postStart + leftSize - 1);
        root.right = build(inorder, boardIndex + 1, inEnd, postorder, postStart + leftSize, postEnd - 1);

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
