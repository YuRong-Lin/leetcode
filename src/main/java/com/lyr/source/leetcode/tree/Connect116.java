package com.lyr.source.leetcode.tree;

/**
 * 二叉树专题
 * 116.填充每个节点的下一个右侧节点指针
 *
 * //给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * //
 * //
 * //struct Node {
 * //  int val;
 * //  Node *left;
 * //  Node *right;
 * //  Node *next;
 * //}
 * //
 * // 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * //
 * // 初始状态下，所有 next 指针都被设置为 NULL。
 * //
 * //
 * //
 * // 进阶：
 * //
 * //
 * // 你只能使用常量级额外空间。
 * // 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * //
 * //
 * //
 * //
 * // 示例：
 * //
 * //
 * //
 * //
 * //输入：root = [1,2,3,4,5,6,7]
 * //输出：[1,#,2,3,#,4,5,6,7,#]
 * //解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由
 * //next 指针连接，'#' 标志着每一层的结束。
 *
 * @Author LinYuRong
 * @Date 2020/12/30 18:20
 * @Version 1.0
 */
public class Connect116 {

    /**
     * 【要点】：确定当前节点应该做什么，然后递归调用子节点做同样的事情
     *
     *  思考过程：
     *  1）细化到每一层，其实就是从左往右，将【相邻】的两个节点连起来；
     *  2）关键问题：父节点不同的两个相邻节点必须在父节点那一层才能处理，其实，这两个相邻节点分别是它们的父节点的右节点和左节点；
     *  3）根据2），容易想到：
     *      1、从根往下逐层处理；
     *      2、采用新函数（用左右两个节点作为参数），将问题转化为：将相邻的两两节点拆成3种情况，不断往下递归
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        connectTwoNode(root.left, root.right);
        return root;
    }

    private void connectTwoNode(Node left, Node right) {
        if (left != null) {
            left.next = right;
            connectTwoNode(left.left, left.right);
        }

        if (right != null) {
            connectTwoNode(right.left, right.right);
        }

        if (left != null && right != null) {
            connectTwoNode(left.right, right.left);
        }
    }

    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
