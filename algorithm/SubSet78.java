package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 子集（LeetCode 78）
 *
 * 时间复杂度：O(2^n × n)
 * - 共有2^n个子集，每个子集平均需要O(n)时间构造
 *
 * 空间复杂度：O(n)
 * - 递归调用栈深度为n
 * - 需要额外的列表存储当前路径
 */
public class SubSet78 {

    /**
     * 生成数组的所有子集
     *
     * 算法思路：
     * 使用回溯算法，通过递归和状态重置生成所有可能的子集
     * 1. 对于每个元素，有两种选择：包含或不包含
     * 2. 递归构建子集，在每个节点都将当前路径添加到结果中
     * 3. 回溯时重置状态，尝试其他可能
     *
     * 执行过程分析（以nums=[1,2,3]为例）：
     *
     * 递归树：
     *                      []
     *                 /          \
     *              [1]            []
     *             /  \          /   \
     *        [1,2]   [1]      [2]   []
     *       /  |     / |     / |    / \
     * [1,2,3][1,2][1,3][1] [2,3][2] [3] []
     *
     * 详细执行过程：
     *
     * 1. start=0, path=[]
     *    - 添加[]到结果
     *    - choose(1): path=[1], start=1
     *      - 添加[1]到结果
     *      - choose(2): path=[1,2], start=2
     *        - 添加[1,2]到结果
     *        - choose(3): path=[1,2,3], start=3
     *          - 添加[1,2,3]到结果
     *        - backtrack: remove(3), path=[1,2]
     *      - backtrack: remove(2), path=[1]
     *      - choose(3): path=[1,3], start=3
     *        - 添加[1,3]到结果
     *      - backtrack: remove(3), path=[1]
     *    - backtrack: remove(1), path=[]
     *    - choose(2): path=[2], start=2
     *      - 添加[2]到结果
     *      - choose(3): path=[2,3], start=3
     *        - 添加[2,3]到结果
     *      - backtrack: remove(3), path=[2]
     *    - backtrack: remove(2), path=[]
     *    - choose(3): path=[3], start=3
     *      - 添加[3]到结果
     *    - backtrack: remove(3), path=[]
     *
     * 最终结果：[[], [1], [1,2], [1,2,3], [1,3], [2], [2,3], [3]]
     *
     * 时间复杂度分析：
     * - 总共有2^n个子集需要生成
     * - 每个子集平均需要O(n)时间构造和复制
     * - 总时间复杂度：O(2^n × n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(n)
     * - path列表存储当前路径：O(n)
     * - result列表存储所有子集：O(2^n × n)
     * - 总空间复杂度：O(2^n × n)
     *
     * @param nums 整数数组（元素互不相同）
     * @return 所有可能的子集
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        // 开始回溯
        backtrack(nums, 0, path, result);

        return result;
    }

    /**
     * 回溯辅助方法
     *
     * 算法思路：
     * 1. 每次递归都将当前路径添加到结果中
     * 2. 从start位置开始遍历元素，避免重复
     * 3. 选择元素加入路径，递归处理后续元素
     * 4. 回溯时移除元素，尝试其他可能
     *
     * 时间复杂度分析：
     * - 递归深度最多为n
     * - 每层需要O(n)时间遍历元素
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(n)
     *
     * @param nums 原数组
     * @param start 当前处理的起始位置
     * @param path 当前构建的子集
     * @param result 存储所有子集的结果列表
     */
    private void backtrack(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        // 将当前路径添加到结果中（需要创建新列表避免引用问题）
        result.add(new ArrayList<>(path));

        // 从start开始尝试每个元素
        for (int i = start; i < nums.length; i++) {
            // 做选择：将元素添加到当前路径
            path.add(nums[i]);

            // 递归：继续构建子集，从下一个位置开始
            backtrack(nums, i + 1, path, result);

            // 撤销选择：回溯，移除元素
            path.remove(path.size() - 1);
        }
    }


    /**
     * 方法2：位运算解法
     *
     * 算法思路：
     * 使用位运算生成所有子集
     * 1. 共有2^n个子集，可以用0到2^n-1的数字表示
     * 2. 每个数字的二进制表示中，1表示包含对应元素，0表示不包含
     *
     * 执行过程分析（以nums=[1,2,3]为例）：
     *
     * i=0 (000): []
     * i=1 (001): [3]
     * i=2 (010): [2]
     * i=3 (011): [2,3]
     * i=4 (100): [1]
     * i=5 (101): [1,3]
     * i=6 (110): [1,2]
     * i=7 (111): [1,2,3]
     *
     * 时间复杂度分析：
     * - 外层循环执行2^n次
     * - 内层循环每次执行n次
     * - 总时间复杂度：O(2^n × n)
     *
     * 空间复杂度分析：
     * - subset列表存储当前子集：O(n)
     * - result列表存储所有子集：O(2^n × n)
     * - 总空间复杂度：O(2^n × n)
     *
     * @param nums 整数数组
     * @return 所有可能的子集
     */
    public List<List<Integer>> subsetsBitwise(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        // 遍历0到2^n-1的所有数字
        for (int i = 0; i < (1 << n); i++) {
            List<Integer> subset = new ArrayList<>();

            // 检查每一位是否为1
            for (int j = 0; j < n; j++) {
                // 如果第j位为1，包含nums[j]
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }

            result.add(subset);
        }

        return result;
    }

    /**
     * 方法3：迭代解法
     *
     * 算法思路：
     * 逐个添加元素，每次添加新元素时，将该元素添加到已有的所有子集中
     *
     * 执行过程分析（以nums=[1,2,3]为例）：
     *
     * 初始：result = [[]]
     *
     * 添加1：result = [[], [1]]
     *
     * 添加2：对[]和[1]分别添加2
     * result = [[], [1], [2], [1,2]]
     *
     * 添加3：对[], [1], [2], [1,2]分别添加3
     * result = [[], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3]]
     *
     * 时间复杂度分析：
     * - 外层循环执行n次
     * - 内层循环在第i次执行时执行2^i次
     * - 总时间复杂度：O(2^n × n)
     *
     * 空间复杂度分析：
     * - 存储所有子集：O(2^n × n)
     * - 创建新子集时的临时空间：O(n)
     * - 总空间复杂度：O(2^n × n)
     *
     * @param nums 整数数组
     * @return 所有可能的子集
     */
    public List<List<Integer>> subsetsIterative(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // 添加空集

        // 逐个处理每个元素
        for (int num : nums) {
            int size = result.size();
            // 对已有的每个子集添加当前元素
            for (int i = 0; i < size; i++) {
                List<Integer> newSubset = new ArrayList<>(result.get(i));
                newSubset.add(num);
                result.add(newSubset);
            }
        }

        return result;
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
        System.out.println("请输入整数数组（用空格分隔）：");
        String input = scanner.nextLine();
        String[] strArray = input.split(" ");

        int[] nums = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            nums[i] = Integer.parseInt(strArray[i]);
        }

        return nums;
    }

    /**
     * 辅助方法：打印子集结果
     *
     * 时间复杂度分析：
     * - 遍历所有子集：O(2^n)
     * - 打印每个子集：O(n)
     * - 总时间复杂度：O(2^n × n)
     *
     * @param result 子集结果
     */
    public static void printSubsets(List<List<Integer>> result) {
        System.out.println("所有子集：");
        for (int i = 0; i < result.size(); i++) {
            System.out.println((i + 1) + ": " + result.get(i));
        }
        System.out.println("总共 " + result.size() + " 个子集");
    }

    /**
     * 主函数：处理用户输入并生成所有子集
     */
    public static void main(String[] args) {
        System.out.println("子集");

        // 读取用户输入的数组
        int[] nums = readArray();
        System.out.println("输入数组: " + Arrays.toString(nums));

        // 生成所有子集
        SubSet78 solution = new SubSet78();
        List<List<Integer>> result1 = solution.subsets(nums);
        List<List<Integer>> result2 = solution.subsetsBitwise(nums);
        List<List<Integer>> result3 = solution.subsetsIterative(nums);

        // 输出结果
        System.out.println("回溯方法结果：");
        printSubsets(result1);

        System.out.println("\n位运算方法结果：");
        printSubsets(result2);

        System.out.println("\n迭代方法结果：");
        printSubsets(result3);
    }
}
