package com.lyr.source.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树专题
 * 117.填充每个节点的下一个右侧节点指针 ||
 * <p>
 * <p>
 * //给定一个二叉树
 * //
 * // struct Node {
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
 * // 输入：root = [1,2,3,4,5,null,7]
 * //输出：[1,#,2,3,#,4,5,7,#]
 * //解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 *
 * @Author LinYuRong
 * @Date 2020/12/31 11:15
 * @Version 1.0
 */
public class Connect117 {

    /**
     * 【限定条件】：非满二叉树
     * <p>
     * 思考过程：
     * 1）由于该限定条件，只能按层遍历，逐层从左往右连接两节点；
     * 2）按层遍历，一般会使用队列来辅助处理；【见connect01()】
     * 3）另一种实现是借助next指针：当第N层完成next指针处理后，形成一个链表，通过它可确定N+1层的next关系【connect02()】
     *
     * @param root
     * @return
     */
    public Node connect01(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        Node last = null;

        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                Node curr = queue.poll();
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
                if (i != 0) {
                    last.next = curr;
                }
                last = curr;
            }
        }

        return root;
    }

    // 每层遍历时的上一个节点
    private Node lastNode = null;
    // 每层的开始节点
    private Node nextLevelBeginNode = null;
    public Node connect02(Node root) {
        if (root == null) {
            return null;
        }

        Node start = root;
        while (start != null) {
            // 初始化
            lastNode = null;
            nextLevelBeginNode = null;
            // 按层遍历
            for (Node p = start; p != null; p = p.next) {
                if (p.left != null) {
                    handle(p.left);
                }
                if (p.right != null) {
                    handle(p.right);
                }
            }
            start = nextLevelBeginNode;
        }

        return root;
    }

    private void handle(Node node) {
        if (lastNode != null) {
            lastNode.next = node;
        }
        if (nextLevelBeginNode == null) {
            nextLevelBeginNode = node;
        }
        lastNode = node;
    }

    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

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
