package com.lyr.source.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树专题：
 * 297.二叉树的序列化与反序列化
 *
 * //序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
 * //式重构得到原数据。
 * //
 * // 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
 * //反序列化为原始的树结构。
 * //
 * // 示例:
 * //
 * // 你可以将以下二叉树：
 * //
 * //    1
 * //   / \
 * //  2   3
 * //     / \
 * //    4   5
 * //
 * //序列化为 "[1,2,3,null,null,4,5]"
 * //
 * // 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这
 * //个问题。
 * //
 * // 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 * // Related Topics 树 设计
 *
 * Difficulty: Hard
 *
 * @Author LinYuRong
 * @Date 2021/1/11 16:06
 * @Version 1.0
 */
public class Codec297 {

    /////////////////////////////////
    // 前序遍历
    /**
     * Encodes a tree to a single string.
     *
     * 【限定条件】：1）序列化+反序列化；2）方法无状态
     *
     * 思路过程：
     * 1）序列化的过程其实就是树遍历，但考虑到还要反序列化，需要记录空指针信息；该过程可采用前中后序遍历和按层遍历；
     * 2）反序列化时，一个关键是如何找到root以及左右子树的边界。此时中序遍历无法满足，故中序遍历不适用本场景。
     *   此外，前序和后续的处理逻辑基本一致，不过是root的位置1个在前头，1个在末尾，同时，左右子树的位置也是相反的；
     * 3）采用前序、按层遍历两种方式来实现；
     *
     * @param root
     * @return
     */
    public String preOrderSerialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();

        String SEP = ",";
        String NULL = "#";
        if (root == null) {
            return NULL;
        }
        doPreOrderSerialize(root, builder, SEP, NULL);

        return builder.toString();
    }

    private void doPreOrderSerialize(TreeNode root, StringBuilder builder, String SEP, String NULL) {
        if (root == null) {
            builder.append(NULL).append(SEP);
        } else {
            // 遍历框架
            builder.append(root.val).append(SEP);
            doPreOrderSerialize(root.left, builder, SEP, NULL);
            doPreOrderSerialize(root.right, builder, SEP, NULL);
        }
    }

    /**
     * Decodes your encoded data to tree.
     *
     * 基于前序遍历
     *
     * @param data
     * @return
     */
    public TreeNode preOrderDeserialize(String data) {
        String SEP = ",";
        String NULL = "#";

        if (data == null || data.equals(NULL)) {
            return null;
        }

        String[] splits = data.split(SEP);
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < splits.length; i++) {
            list.add(splits[i]);
        }

        return doPreOrderDeserialize(list, NULL);
    }

    private TreeNode doPreOrderDeserialize(LinkedList<String> list, String NUll) {
        String rootVal = list.pollFirst();

        if (NUll.equals(rootVal)) {
            return null;
        } else {
            TreeNode root = new TreeNode(Integer.valueOf(rootVal));
            root.left = doPreOrderDeserialize(list, NUll);
            root.right = doPreOrderDeserialize(list, NUll);
            return root;
        }
    }

    /////////////////////////////////
    // 按层遍历

    /**
     * 按层遍历序列化
     *
     * @param root
     * @return
     */
    public String levelOrderSerialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();

        String SEP = ",";
        String NULL = "#";
        if (root == null) {
            return NULL;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    builder.append(NULL).append(SEP);
                    continue;
                }

                builder.append(node.val).append(SEP);
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }

        return builder.toString();
    }


    /**
     * 按层遍历反序列化
     *
     * @param data
     * @return
     */
    public TreeNode levelOrderDeserialize(String data) {
        String SEP = ",";
        String NULL = "#";

        if (data == null || data.equals(NULL)) {
            return null;
        }

        LinkedList<String> list = new LinkedList<>();
        String[] splits = data.split(SEP);
        for (String str : splits) {
            list.add(str);
        }

        String rootVal = list.pollFirst();
        TreeNode root = new TreeNode(Integer.valueOf(rootVal));

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty() && !list.isEmpty()) {
            TreeNode parent = queue.poll();

            // 左节点
            String val = list.pollFirst();
            if (NULL.equals(val)) {
                parent.left = null;
            } else {
                parent.left = new TreeNode(Integer.valueOf(val));
                queue.offer(parent.left);
            }

            // 右节点
            val = list.pollFirst();
            if (NULL.equals(val)) {
                parent.right = null;
            } else {
                parent.right = new TreeNode(Integer.valueOf(val));
                queue.offer(parent.right);
            }
        }

        return root;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(null);
        System.out.println(queue.poll());
    }
}
