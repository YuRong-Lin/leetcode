package com.lyr.source.leetcode.dp;

import java.util.Arrays;

/**
 * 动态规划专题
 *
 * 64.最小路径和
 *
 * //给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * //
 * // 说明：每次只能向下或者向右移动一步。
 * //
 * //
 * //
 * // 示例 1：
 * //
 * //
 * //输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * //输出：7
 * //解释：因为路径 1→3→1→1→1 的总和最小。
 * //
 * //
 * // 示例 2：
 * //
 * //
 * //输入：grid = [[1,2,3],[4,5,6]]
 * //输出：12
 * //
 * //
 * //
 * //
 * // 提示：
 * //
 * //
 * // m == grid.length
 * // n == grid[i].length
 * // 1 <= m, n <= 200
 * // 0 <= grid[i][j] <= 100
 * //
 * // Related Topics 数组 动态规划
 *
 * @Author LinYuRong
 * @Date 2020/12/22 10:40
 * @Version 1.0
 */
public class MinPathSum64 {

    /**
     * 思考过程：
     * 【目标】：左上角到右下角路径上的数字总和【最小】
     * 【限定】：只能【向下】或【向右】移动
     *
     * 【推导】：根据限定条件，到(i,j)会经过(i-1, j)或者(i, j-1).
     * 【状态转移方程】：因此，min(sum[(0,0)->(i,j)]) = min(sum[(0,0)->(i-1,j)], sum[(0,0)->(i,j-1)]) + value(i,j)
     *
     * 实现一：自顶向下
     * 定义函数：dp(int[][] grid, int i , int j) 表示从（0,0）到(i,j)的最小路径和。
     * 结果：令m为行长度，n为列长度：dp(grid, m - 1, n - 1)
     *
     * 优化：去除重复计算（缓存结果）
     * 依据：从（i-1,j-1）->(i,j)有多条路径：（i-1,j）->(i,j)、(i,j-1)->(i,j)。说明（i,j）存在重复计算
     *
     * @param grid
     * @return
     */
    private int[][] cache;
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        cache = new int[m][n];
        for (int[] row : cache) {
            Arrays.fill(row, -1);
        }
        return dp(grid, m - 1, n - 1);
    }

    private int dp(int[][] grid, int i, int j) {
        if (i == 0 && j == 0) {
            return grid[0][0];
        }

        // 越界，返回一个很大的值
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }

        if (cache[i][j] != -1) {
            return cache[i][j];
        }

        cache[i][j] = Math.min(dp(grid, i - 1, j), dp(grid, i, j - 1)) + grid[i][j];
        return cache[i][j];
    }
}
