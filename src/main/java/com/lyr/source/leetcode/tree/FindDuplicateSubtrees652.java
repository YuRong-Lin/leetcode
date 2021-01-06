package com.lyr.source.leetcode.tree;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 二叉树专题：
 * 652.寻找重复的子树
 *
 * //给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 * //
 * // 两棵树重复是指它们具有相同的结构以及相同的结点值。
 * //
 * // 示例 1：
 * //
 * //         1
 * //       / \
 * //      2   3
 * //     /   / \
 * //    4   2   4
 * //       /
 * //      4
 * //
 * //
 * // 下面是两个重复的子树：
 * //
 * //       2
 * //     /
 * //    4
 * //
 * //
 * // 和
 * //
 * //     4
 * //
 * //
 * // 因此，你需要以列表的形式返回上述重复子树的根结点。
 * // Related Topics 树
 *
 * @Author LinYuRong
 * @Date 2021/1/4 12:26
 * @Version 1.0
 */
public class FindDuplicateSubtrees652 {

    /**
     * 【限定条件】：
     * 1）两棵树重复：是指它们具有相同的结构以及相同的结点值
     * 2）同一类的重复子树，返回任意一个根节点即可
     *
     * 思路过程：
     * 1）根节点应该做什么？
     * 2）几个关键点：
     *      1、如何表示两棵树重复？-->树序列化为字符串
     *      2、何时判断两棵树重复？-->子树结构已知悉，根节点才知道自己是否和其他子树重复（后续遍历）
     * 3）返回结果去重
     *
     * @param root
     * @return
     */
    private List<TreeNode> duplicate = new LinkedList<>();
    private Map<String, Integer> cache = new HashMap<>(16);

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        serializeTreeAndCalc(root);
        return duplicate;
    }

    /**
     * 序列化树为字符串
     *
     * @param root
     * @return
     */
    private String serializeTreeAndCalc(TreeNode root) {
        if (root == null) {
            // # 代表null
            return "#";
        }

        StringBuilder builder = new StringBuilder();
        String left = serializeTreeAndCalc(root.left);
        String right = serializeTreeAndCalc(root.right);

        builder.append(left).append(",").append(right).append(",").append(root.val);
        String currSubTree = builder.toString();
        int count = cache.getOrDefault(currSubTree, 0);
        if (count == 1) {
            duplicate.add(root);
        }
        cache.put(currSubTree, count + 1);

        return currSubTree;
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
