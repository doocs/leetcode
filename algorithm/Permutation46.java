package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 全排列（LeetCode 46）
 *
 * 时间复杂度：O(n! × n)
 * - 共有n!个排列，每个排列需要O(n)时间构造
 *
 * 空间复杂度：O(n)
 * - 递归调用栈深度为n
 * - 需要额外的数组存储当前路径和使用状态
 */
public class Permutation46 {

    /**
     * 生成数组的所有全排列
     *
     * 算法思路：
     * 使用回溯算法，通过递归和状态重置生成所有可能的排列
     * 1. 使用一个布尔数组记录每个元素是否已被使用
     * 2. 递归构建排列，每次选择一个未使用的元素
     * 3. 当排列长度等于原数组长度时，得到一个完整排列
     * 4. 回溯时重置状态，尝试其他可能
     *
     * 执行过程分析（以nums=[1,2,3]为例）：
     *
     * 递归树：
     *                    []
     *              /      |      \
     *            [1]     [2]     [3]
     *           /  \    /  \    /  \
     *       [1,2] [1,3][2,1][2,3][3,1][3,2]
     *         |     |   |    |    |    |
     *      [1,2,3][1,3,2][2,1,3][2,3,1][3,1,2][3,2,1]
     *
     * 详细执行过程：
     *
     * 第一条路径：[1] -> [1,2] -> [1,2,3]
     * 1. choose(1): used=[T,F,F], path=[1]
     * 2. choose(2): used=[T,T,F], path=[1,2]
     * 3. choose(3): used=[T,T,T], path=[1,2,3]
     * 4. path.size() == nums.length，添加[1,2,3]到结果
     * 5. backtrack: remove(3), used=[T,T,F]
     * 6. backtrack: remove(2), used=[T,F,F]
     * 7. choose(3): used=[T,F,T], path=[1,3]
     * 8. choose(2): used=[T,T,T], path=[1,3,2]
     * 9. path.size() == nums.length，添加[1,3,2]到结果
     * ...
     *
     * 最终结果：[[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
     *
     * 时间复杂度分析：
     * - 递归深度为n，每层需要O(n)时间遍历所有元素
     * - 总共有n!个排列
     * - 总时间复杂度：O(n! × n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(n)
     * - path列表存储：O(n)
     * - used数组存储：O(n)
     * - result列表存储所有排列：O(n! × n)
     * - 总空间复杂度：O(n! × n)
     *
     * @param nums 不含重复数字的数组
     * @return 所有可能的全排列
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        // 开始回溯
        backtrack(nums, path, used, result);

        return result;
    }

    /**
     * 回溯辅助方法
     *
     * 算法思路：
     * 1. 当路径长度等于数组长度时，说明得到一个完整排列
     * 2. 否则遍历所有元素，选择未使用的元素加入路径
     * 3. 递归处理剩余位置
     * 4. 回溯时撤销选择
     *
     * 时间复杂度分析：
     * - 递归深度为n
     * - 每层需要O(n)时间遍历所有元素
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(n)
     *
     * @param nums 原数组
     * @param path 当前构建的排列
     * @param used 元素使用状态数组
     * @param result 存储所有排列的结果列表
     */
    private void backtrack(int[] nums, List<Integer> path, boolean[] used, List<List<Integer>> result) {
        // 递归终止条件：当前路径长度等于数组长度
        if (path.size() == nums.length) {
            // 将当前路径添加到结果中（需要创建新列表避免引用问题）
            result.add(new ArrayList<>(path));
            return;
        }

        // 尝试每个未使用的元素
        for (int i = 0; i < nums.length; i++) {
            // 如果元素已被使用，跳过
            if (used[i]) {
                continue;
            }

            // 做选择：将元素添加到当前路径
            path.add(nums[i]);
            used[i] = true;

            // 递归：继续构建排列
            backtrack(nums, path, used, result);

            // 撤销选择：回溯，移除元素并重置状态
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    /**
     * 方法2：交换元素的回溯法
     *
     * 算法思路：
     * 通过交换数组元素的位置生成排列
     * 1. 固定前缀，递归处理后缀
     * 2. 通过交换生成不同的排列
     *
     * 时间复杂度分析：
     * - 与 [permute](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/Permutation46.java#L57-L67) 方法相同：O(n! × n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(n)
     * - list列表存储：O(n)
     * - result列表存储所有排列：O(n! × n)
     * - 总空间复杂度：O(n! × n)
     *
     * @param nums 不含重复数字的数组
     * @return 所有可能的全排列
     */
    public List<List<Integer>> permuteSwap(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        backtrackSwap(list, 0, result);
        return result;
    }

    /**
     * 交换回溯辅助方法
     *
     * 算法思路：
     * 1. 当start等于列表长度时，得到一个完整排列
     * 2. 否则从start位置开始，与后续每个位置交换
     * 3. 递归处理start+1位置
     * 4. 回溯时交换回来恢复状态
     *
     * 时间复杂度分析：
     * - 递归深度为n
     * - 每层需要O(n)时间遍历所有元素
     * - 总时间复杂度：O(n! × n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(n)
     *
     * @param list 当前排列列表
     * @param start 当前处理的起始位置
     * @param result 存储所有排列的结果列表
     */
    private void backtrackSwap(List<Integer> list, int start, List<List<Integer>> result) {
        // 递归终止条件
        if (start == list.size()) {
            result.add(new ArrayList<>(list));
            return;
        }

        // 尝试每个位置的元素
        for (int i = start; i < list.size(); i++) {
            // 交换元素
            swap(list, start, i);

            // 递归处理下一个位置
            backtrackSwap(list, start + 1, result);

            // 回溯：交换回来
            swap(list, start, i);
        }
    }

    /**
     * 交换列表中两个元素
     *
     * 时间复杂度分析：
     * - 常数时间操作：O(1)
     *
     * @param list 列表
     * @param i 第一个元素索引
     * @param j 第二个元素索引
     */
    private void swap(List<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     * 辅助方法：读取用户输入的数组
     *
     * 时间复杂度分析：
     * - 处理输入字符串：O(m)，m为输入字符数
     * - 转换为整数：O(n)，n为元素个数
     *
     * 空间复杂度分析：
     * - 存储字符串数组：O(m)
     * - 存储整数数组：O(n)
     *
     * @return 用户输入的整数数组
     */
    public static int[] readArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入不重复的整数数组（用空格分隔）：");
        String input = scanner.nextLine();
        String[] strArray = input.split(" ");

        int[] nums = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            nums[i] = Integer.parseInt(strArray[i]);
        }

        return nums;
    }

    /**
     * 辅助方法：打印排列结果
     *
     * 时间复杂度分析：
     * - 遍历所有排列：O(n! × n)
     *
     * @param result 排列结果
     */
    public static void printPermutations(List<List<Integer>> result) {
        System.out.println("所有排列：");
        for (int i = 0; i < result.size(); i++) {
            System.out.println((i + 1) + ": " + result.get(i));
        }
        System.out.println("总共 " + result.size() + " 个排列");
    }

    /**
     * 主函数：处理用户输入并生成全排列
     */
    public static void main(String[] args) {
        System.out.println("全排列");

        // 读取用户输入的数组
        int[] nums = readArray();
        System.out.println("输入数组: " + Arrays.toString(nums));

        // 生成全排列
        Permutation46 solution = new Permutation46();
        List<List<Integer>> result1 = solution.permute(nums);
        List<List<Integer>> result2 = solution.permuteSwap(nums);

        // 输出结果
        System.out.println("方法1结果：");
        printPermutations(result1);

        System.out.println("\n方法2结果：");
        printPermutations(result2);
    }
}
