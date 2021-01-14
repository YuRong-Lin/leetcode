package com.lyr.source.leetcode.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树专题：
 * 341.扁平化嵌套列表迭代器
 *
 * //给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 * //
 * // 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
 * //
 * //
 * //
 * // 示例 1:
 * //
 * // 输入: [[1,1],2,[1,1]]
 * //输出: [1,1,2,1,1]
 * //解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
 * //
 * // 示例 2:
 * //
 * // 输入: [1,[4,[6]]]
 * //输出: [1,4,6]
 * //解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
 * //
 * // Related Topics 栈 设计
 *
 * Difficulty: Medium
 *
 * @Author lyr
 * @Date 2021/1/15 上午12:30
 * @Version 1.0
 */
public class NestedIterator341 implements Iterator<Integer> {
    private LinkedList<NestedInteger> nestedIntegers;

    public NestedIterator341(List<NestedInteger> nestedList) {
        this.nestedIntegers = new LinkedList<>(nestedList);
    }

    /**
     * 【限定条件】：嵌套整型列表
     *
     * 思路过程：
     * 1）一种思路是一开始就将这个嵌套整型列表全部转成一维列表，再转成迭代器对象即可，但这种有一个缺点是不能按需扁平化（即可能只需要取部分数据）；
     * 2）第二种思路是想办法让嵌套列表的第1个元素始终为整型：每次hasNext判断时，如果第1位为列表，则循环转换它为一维列表，直到第1位为整型。
     *
     * @return
     */
    @Override
    public Integer next() {
        return nestedIntegers.pollFirst().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!nestedIntegers.isEmpty() && !nestedIntegers.peekFirst().isInteger()) {
            List<NestedInteger> list = nestedIntegers.pollFirst().getList();
            for (int i = list.size() - 1; i >= 0; i--) {
                nestedIntegers.addFirst(list.get(i));
            }
        }
        return !nestedIntegers.isEmpty();
    }
    
    private interface NestedInteger {
        
         // @return true if this NestedInteger holds a single integer, rather than a nested list.
         public boolean isInteger();

         // @return the single integer that this NestedInteger holds, if it holds a single integer
         // Return null if this NestedInteger holds a nested list
         public Integer getInteger();

         // @return the nested list that this NestedInteger holds, if it holds a nested list
         // Return null if this NestedInteger holds a single integer
         public List<NestedInteger> getList();
    }
}