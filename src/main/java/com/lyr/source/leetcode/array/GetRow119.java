package com.lyr.source.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组专题：
 * 119.杨辉三角 II
 *
 * //给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * //
 * //
 * //
 * // 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * //
 * // 示例:
 * //
 * // 输入: 3
 * //输出: [1,3,3,1]
 * //
 * //
 * // 进阶：
 * //
 * // 你可以优化你的算法到 O(k) 空间复杂度吗？
 *
 * @Author LinYuRong
 * @Date 2020/12/31 17:18
 * @Version 1.0
 */
public class GetRow119 {

    /**
     * 【限定条件】：非负索引
     *
     * 思路过程：
     * 1）每层的结果可通过上一层推导出来；
     * 2）从上往下，逐层求解每一层；空间复杂度在于保存上一层的结果
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0) {
            Arrays.asList(1);
        }

        List<Integer> preList = Arrays.asList(1);
        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> newList = new ArrayList<>();
            newList.add(1);
            for (int j = 1; j < i; j++) {
                newList.add(preList.get(j - 1) + preList.get(j));
            }
            newList.add(1);
            preList = newList;
        }
        return preList;
    }
}
