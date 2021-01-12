package com.lyr.source.leetcode.linkedlist;

/**
 * 链表专题：
 * 19.删除链表的倒数第N个节点
 *
 * //给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * //
 * // 示例：
 * //
 * // 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * //
 * //当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * //
 * //
 * // 说明：
 * //
 * // 给定的 n 保证是有效的。
 * //
 * // 进阶：
 * //
 * // 你能尝试使用一趟扫描实现吗？
 * // Related Topics 链表 双指针
 *
 * Difficulty: Medium
 *
 * @Author LinYuRong
 * @Date 2021/1/12 15:38
 * @Version 1.0
 */
public class RemoveNthFromEnd19 {

    /**
     * 【限定条件】：链表；删除倒数第n个节点，返回头结点；一趟扫描
     *
     * 思路过程：
     * 1）要删除倒数第n个节点，需要定位到倒数第n+1个节点，如下：
     *      A->B->C->D->NULL，假设删除倒数第2（即C），需定位倒数第3（B），从而让B.next = B.next.next;
     * 2）一遍扫描的基本思路：采用快慢指针，让快指针先走n个节点
     * 3）边界处理：令链表长度为len，当n>len，意味着删除不存在节点，直接返回head即可；当n=len，即删除头结点，返回head.next
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        // 快慢指针，fast节点先走n步
        ListNode fast = head;
        while (fast != null && n > 0) {
            fast = fast.next;
            n--;
        }

        // 边界处理
        if (fast == null) {
            // 删除头结点
            if (n == 0) {
                return head.next;
                // 删除不存在结点
            } else {
                return head;
            }
        }

        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // slow的下一结点即为要删除的节点
        slow.next = slow.next.next;

        return head;
    }

    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
