package com.lyr.source.leetcode.linkedlist;

/**
 * 链表专题：
 * 206.反转链表
 *
 * //反转一个单链表。
 * //
 * // 示例:
 * //
 * // 输入: 1->2->3->4->5->NULL
 * //输出: 5->4->3->2->1->NULL
 * //
 * // 进阶:
 * //你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * // Related Topics 链表
 *
 * Difficulty: easy
 *
 * @Author LinYuRong
 * @Date 2021/1/11 18:17
 * @Version 1.0
 */
public class ReverseList206 {

    /*
     * 思考过程：
     * 1）递归
     *      1、拆分子问题：要反转以head为起点的链表，可以先反转以head.next为起点的链表，依此类推；
     *           head-> (A->B->C->null) -> head-> (C->B->A->NULL)
     *      2、处理head节点与以head.next为起点的链表反转的next关系处理：
     *           NULL<-head-(A->B->C)
     *      3、递归的要点是通过一个有明确定义的函数来处理反转的逻辑，而不是人脑推敲反转细节。相信并保证该函数的逻辑正确，然后拆分字问题并处理好边界
     *
     * 2）迭代
     *
     */

    /**
     * 递归方式
     *
     * @param head
     * @return
     */
    public ListNode reverseList01(ListNode head) {
        if (head == null) {
            return null;
        }
        return reverse(head);
    }

    private ListNode reverse(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 迭代法
     *
     * @param head
     * @return
     */
    public ListNode reverseList02(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode node = head.next;
        ListNode last = head;
        while (node != null) {
            ListNode temp = node.next;
            node.next = last;
            last = node;
            node = temp;
        }
        head.next = null;
        return last;
    }

    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
