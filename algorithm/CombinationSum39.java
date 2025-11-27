package com.funian.algorithm.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

/**
 * 组合总和（LeetCode 39）
 *
 * 时间复杂度：O(N^(T/M))
 * - N是数组长度，T是目标值，M是数组中最小值
 * - 最坏情况下递归深度为T/M，每层有N个选择
 *
 * 空间复杂度：O(T/M)
 * - 递归调用栈深度最坏为T/M
 * - 需要额外的列表存储当前路径
 */
public class CombinationSum39 {

    public static void main(String[] args) {
        // 初始化输入读取器
        Scanner scanner = new Scanner(System.in);

        // 获取用户输入的候选数字数组
        System.out.print("请输入候选数字（以空格分隔）：");
        String line = scanner.nextLine();
        String[] str = line.split(" ");
        int[] candidates = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            candidates[i] = Integer.parseInt(str[i]);
        }

        // 获取用户输入的目标值
        System.out.print("请输入目标值 target：");
        int target = scanner.nextInt();

        // 调用核心算法计算所有可能的组合
        List<List<Integer>> result = combinationSum(candidates, target);

        // 输出所有找到的组合结果
        System.out.println("所有可能的组合：");
        for (List<Integer> combination : result) {
            System.out.println(combination);
        }
    }

    /**
     * 获取可以使和为 target 的所有组合
     *
     * 算法思路：
     * 使用回溯算法，通过递归和状态重置生成所有可能的组合
     * 1. 数组中的数字可以无限制重复使用
     * 2. 递归时从当前位置开始（允许重复选择），避免重复组合
     * 3. 当剩余目标值为0时找到一个有效组合
     * 4. 当剩余目标值小于0时剪枝
     *
     * 执行过程分析（以`candidates=[2,3,6,7]`, `target=7`为例）：
     *
     * 递归树：
     *                    []
     *              /    /    \    \
     *            [2]   [3]   [6]  [7]
     *           /  |    |     |
     *       [2,2] [2,3] [3,3] [6] (剪枝)
     *        /     |     |
     *    [2,2,2] [2,3] [3,3] (剪枝)
     *       |      |
     *   [2,2,2] [2,3,2] (剪枝)
     *       X      X
     *    (remain<0) (remain<0)
     *
     * 详细执行过程：
     *
     * 1. `start=0`, `remain=7`: 尝试`candidates[0]=2`
     *    - choose(2): `tempList=[2]`, `remain=5`, `start=0`（允许重复）
     *      - choose(2): `tempList=[2,2]`, `remain=3`, `start=0`
     *        - choose(2): `tempList=[2,2,2]`, `remain=1`, `start=0`
     *          - choose(2): `tempList=[2,2,2,2]`, `remain=-1`, `start=0` (剪枝)
     *          - choose(3): `tempList=[2,2,2,3]`, `remain=-2`, `start=1` (剪枝)
     *          - ...
     *        - choose(3): `tempList=[2,2,3]`, `remain=0`, `start=1`
     *          - `remain=0`，找到解[2,2,3]
     *      - choose(3): `tempList=[2,3]`, `remain=2`, `start=1`
     *        - choose(3): `tempList=[2,3,3]`, `remain=-1`, `start=1` (剪枝)
     *        - ...
     *    - choose(3): `tempList=[3]`, `remain=4`, `start=1`
     *      - choose(3): `tempList=[3,3]`, `remain=1`, `start=1`
     *        - choose(3): `tempList=[3,3,3]`, `remain=-2`, `start=1` (剪枝)
     *        - ...
     *    - choose(6): `tempList=[6]`, `remain=1`, `start=2`
     *      - choose(6): `tempList=[6,6]`, `remain=-5`, `start=2` (剪枝)
     *      - ...
     *    - choose(7): `tempList=[7]`, `remain=0`, `start=3`
     *      - `remain=0`，找到解[7]
     *
     * 最终结果：[[2,2,3], [7]]
     *
     * @param candidates 无重复元素的整数数组
     * @param target 目标值
     * @return 所有和为目标值的组合
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 初始化结果集，用于存储所有满足条件的组合
        List<List<Integer>> result = new ArrayList<>();
        // 启动回溯算法寻找所有可能组合
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    /**
     * 递归回溯方法
     *
     * 算法思路：
     * 递归地尝试所有可能的数字组合，通过回溯生成所有有效组合
     *
     * @param result 存储所有有效组合的结果列表
     * @param tempList 当前构建的组合
     * @param candidates 候选数组
     * @param remain 剩余目标值
     * @param start 当前处理的起始位置
     */
    private static void backtrack(List<List<Integer>> result, List<Integer> tempList,
                                 int[] candidates, int remain, int start) {
        // 基础情况1：找到一个有效组合
        if (remain == 0) {
            // 将当前路径添加到结果集中，使用新列表避免引用问题
            result.add(new ArrayList<>(tempList));
            return;
        }
        // 基础情况2：当前路径和已经超过目标值，剪枝
        if (remain < 0) {
            return;
        }
        // 遍历候选数组，尝试添加每个数字到组合中
        for (int i = start; i < candidates.length; i++) {
            // 选择：将当前候选数字添加到临时路径中
            tempList.add(candidates[i]);
            // 递归：继续寻找组合，允许重复使用当前数字(i)，更新剩余目标值
            backtrack(result, tempList, candidates, remain - candidates[i], i);
            // 回溯：移除刚才添加的数字，尝试其他可能
            tempList.remove(tempList.size() - 1);
        }
    }


    /**
     * 方法2：优化版（排序后剪枝）
     *
     * 算法思路：
     * 先对候选数组排序，然后在回溯过程中进行剪枝优化
     *
     * @param candidates 无重复元素的整数数组
     * @param target 目标值
     * @return 所有和为目标值的组合
     */
    public List<List<Integer>> combinationSumOptimized(int[] candidates, int target) {
        // 初始化结果集和当前路径
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        // 对候选数组进行排序，为剪枝优化做准备
        Arrays.sort(candidates);

        // 启动优化版回溯算法
        backtrackOptimized(candidates, target, 0, path, result);

        return result;
    }

    /**
     * 优化版回溯辅助方法
     *
     * 算法思路：
     * 在排序后的数组上进行回溯，利用有序性进行剪枝优化
     *
     * @param candidates 排序后的候选数组
     * @param target 剩余目标值
     * @param start 当前处理的起始位置
     * @param path 当前构建的组合
     * @param result 存储所有有效组合的结果列表
     */
    private void backtrackOptimized(int[] candidates, int target, int start,
                                   List<Integer> path, List<List<Integer>> result) {
        // 基础情况：找到一个有效组合
        if (target == 0) {
            // 将当前路径添加到结果集中
            result.add(new ArrayList<>(path));
            return;
        }

        // 遍历候选数组，尝试添加每个数字到组合中
        for (int i = start; i < candidates.length; i++) {
            // 优化剪枝：如果当前数字已经大于剩余目标值，
            // 由于数组已排序，后续数字只会更大，可以直接跳出循环
            if (candidates[i] > target) {
                break;
            }

            // 选择：将当前候选数字添加到路径中
            path.add(candidates[i]);

            // 递归：继续寻找组合，允许重复使用当前数字(i)，更新剩余目标值
            backtrackOptimized(candidates, target - candidates[i], i, path, result);

            // 回溯：移除刚才添加的数字，尝试其他可能
            path.remove(path.size() - 1);
        }
    }
}
