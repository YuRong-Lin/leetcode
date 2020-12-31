package com.lyr.source.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组专题：
 * 118.杨辉三角
 *
 * //给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * //
 * //
 * //
 * // 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * //
 * // 示例:
 * //
 * // 输入: 5
 * //输出:
 * //[
 * //     [1],
 * //    [1,1],
 * //   [1,2,1],
 * //  [1,3,3,1],
 * // [1,4,6,4,1]
 * //]
 * // Related Topics 数组
 *
 * @Author LinYuRong
 * @Date 2020/12/31 17:14
 * @Version 1.0
 */
public class Generate118 {

    /**
     * 【限定条件】：非负整数
     *
     * 思路过程：
     * 1）每层的开头和结尾都是1
     * 2）每层：从第2个元素起，设索引为k，都等于上一层索引为k-1和k两个数的和；
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows <= 0) {
            return result;
        }

        // base case
        result.add(Arrays.asList(1));
        if (numRows == 1) {
            return result;
        }

        for (int i = 1; i < numRows; i++) {
            List<Integer> newList = new ArrayList<>();
            List<Integer> preList = result.get(i - 1);

            // 第1位为1
            newList.add(1);
            for (int j = 1; j < i; j++) {
                newList.add(preList.get(j - 1) + preList.get(j));
            }
            // 最后1位也为1
            newList.add(1);
            result.add(newList);
        }
        return result;
    }
}
