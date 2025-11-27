package com.funian.algorithm.algorithm;

import java.util.*;

/**
 * 全排列生成器
 *
 * 时间复杂度：O(n! × n)
 * - n个不同元素的全排列总数为n!
 * - 生成每个排列需要n次操作（添加元素）
 * - 回溯过程中每个排列需要进行n次递归调用
 * - 总时间复杂度为 O(n! × n)
 *
 * 空间复杂度：O(n! × n)
 * - 结果存储需要 O(n! × n) 空间（n!个排列，每个排列n个元素）
 * - 递归调用栈深度为 O(n)
 * - visited数组需要 O(n) 空间
 * - tempList临时列表需要 O(n) 空间
 * - 总空间复杂度主要由结果存储决定，为 O(n! × n)
 */
public class PermuteNumber46 {
    /**
     * 主函数：处理用户输入并输出全排列结果
     *
     * 算法流程：
     * 1. 读取用户输入的整数数组
     * 2. 调用permute方法生成全排列
     * 3. 输出所有可能的排列
     */
    public static void main(String[] args) {
        // 创建 Scanner 对象用于输入
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入不含重复数字的数组元素，以空格分隔：");

        // 读取一行输入并按空格分隔，生成字符串数组
        String line = scanner.nextLine();
        String[] str = line.split(" ");
        int[] nums = new int[str.length];

        // 将输入的字符串数组转换为整数数组
        for (int i = 0; i < str.length; i++) {
            nums[i] = Integer.parseInt(str[i]);
        }

        // 调用 permute 方法生成全排列
        List<List<Integer>> result = permute(nums);

        // 输出所有可能的全排列
        System.out.println("所有可能的全排列为：");
        for (List<Integer> permutation : result) {
            System.out.println(permutation);
        }
    }

    /**
     * 生成给定整数数组的所有可能全排列
     *
     * 算法思路：
     * 使用回溯算法生成全排列。通过递归和回溯的方式，尝试所有可能的元素组合。
     * 对于n个元素的数组，共有n!种不同的排列方式。
     *
     * @param nums 输入的整数数组
     * @return 包含所有全排列的列表
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(); // 用于存储所有排列结果
        boolean[] visited = new boolean[nums.length]; // 标记数组元素是否已被访问
        backtrack(result, new ArrayList<>(), nums, visited); // 调用回溯方法构建排列
        return result; // 返回结果
    }

    /**
     * 回溯算法核心函数，用于递归构建所有排列
     *
     * 算法执行过程分析（以输入 [1,2,3] 为例）：
     *
     * 决策树结构：
     *                    []
     *              /      |      \
     *            [1]     [2]     [3]
     *           /  \    /  \    /  \
     *        [1,2] [1,3][2,1][2,3][3,1][3,2]
     *         /     |    |    |    |    \
     *     [1,2,3][1,3,2][2,1,3][2,3,1][3,1,2][3,2,1]
     *
     * 执行步骤详解：
     * 1. 初始调用: backtrack(result, [], [1,2,3], [false,false,false])
     * 2. 第一层选择:
     *    - 选择1: tempList=[1], visited=[true,false,false]
     *    - 选择2: tempList=[2], visited=[false,true,false]
     *    - 选择3: tempList=[3], visited=[false,false,true]
     * 3. 以选择1为例继续:
     *    - backtrack(result, [1], [1,2,3], [true,false,false])
     *    - 第二层可选: 2或3
     *    - 选择2: tempList=[1,2], visited=[true,true,false]
     *    - 选择3: tempList=[1,3], visited=[true,false,true]
     * 4. 继续深入直到形成完整排列:
     *    - [1,2,3] 和 [1,3,2] 被添加到结果中
     *    - 然后通过回溯尝试其他起始数字
     * 5. 最终结果: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
     *
     * @param result 最终的排列结果列表
     * @param tempList 临时存储当前排列的列表
     * @param nums 输入的整数数组
     * @param visited 标记数组中的元素是否已被使用
     */
    private static void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, boolean[] visited) {
        // 基础情况：如果临时列表的大小等于输入数组的大小，表示找到一个完整的排列
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList)); // 将临时列表添加到结果中（注意：需要深拷贝）
        } else {
            // 遍历输入数组中的每个元素
            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) continue; // 如果当前数字已被访问，跳过

                // 做选择阶段
                visited[i] = true; // 标记当前数字为已访问
                tempList.add(nums[i]); // 将当前数字添加到临时列表

                // 递归调用，继续构建排列
                backtrack(result, tempList, nums, visited);

                // 撤销选择，回溯：移除最后添加的数字
                tempList.remove(tempList.size() - 1);
                visited[i] = false; // 恢复当前数字为未访问，准备尝试下一个排列
            }
        }
    }
}
