package com.funian.algorithm.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 和为K的子数组（LeetCode 560）
 *
 * 时间复杂度：O(n)
 * - 只需要遍历数组一次
 * - HashMap的查找和插入操作平均时间复杂度为O(1)
 *
 * 空间复杂度：O(n)
 * - 最坏情况下，HashMap需要存储n个不同的前缀和
 */
public class SubarraySum560 {
    public static void main(String[] args) {
        // 创建 Scanner 对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入数组
        System.out.println("输入数组：");

        // 读取一行输入
        String line = scanner.nextLine();

        // 按空格分割字符串得到字符串数组
        String[] strs = line.split(" ");

        // 创建整型数组
        int[] nums = new int[strs.length];

        // 将字符串数组转换为整型数组
        for (int i = 0; i < strs.length; i++) {
            nums[i] = Integer.parseInt(strs[i]);
        }

        // 提示用户输入目标和k
        System.out.println("输入k：");

        // 读取目标和k
        int k = scanner.nextInt();

        // 调用 subarraySum 方法计算和为k的子数组个数
        int result = subarraySum(nums, k);

        // 输出结果
        System.out.println("结果为：" + result);
    }

    /**
     * 给定一个整数数组和一个整数 k，找到该数组中和为 k 的连续子数组的个数
     *
     * 算法思路：
     * 使用前缀和 + HashMap 的方法
     * 1. 维护一个前缀和变量 currentSum，记录从数组开始到当前位置的元素和
     * 2. 使用 HashMap 记录每个前缀和出现的次数
     * 3. 对于当前位置的前缀和 sum，如果存在之前的前缀和为 (sum - k)
     *    则说明存在子数组的和为 k
     * 4. 这是因为：如果前缀和[j] - 前缀和[i] = k，则子数组[i+1, j]的和为k
     *
     * 示例过程（以数组 [1,1,1], k=2 为例）：
     * 索引:  0  1  2
     * 元素:  1  1  1
     *
     * i=0: currentSum=1, 需要找前缀和=-1的次数, 不存在, map:{0:1, 1:1}, count=0
     * i=1: currentSum=2, 需要找前缀和=0的次数, 有1次, map:{0:1, 1:1, 2:1}, count=1
     * i=2: currentSum=3, 需要找前缀和=1的次数, 有1次, map:{0:1, 1:1, 2:1, 3:1}, count=2
     *
     * 结果：2个子数组 [1,1] 和 [1,1]
     *
     * 时间复杂度分析：
     * - 单次遍历数组：O(n)，其中n为输入数组`nums`的长度
     * - HashMap操作：O(1)，平均情况下的查找和插入操作
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - HashMap存储前缀和：O(n)，最坏情况下存储n个不同的前缀和
     * - 其他变量：O(1)
     * - 总空间复杂度：O(n)
     *
     * @param nums 整数数组
     * @param k 目标和
     * @return 和为k的连续子数组个数
     */
    public static int subarraySum(int[] nums, int k) {
        // 使用 HashMap 存储前缀和及其出现次数
        Map<Integer, Integer> map = new HashMap<>();

        // 初始化：前缀和为0出现1次（表示空数组）
        map.put(0, 1);

        // 记录当前前缀和
        int currentSum = 0;

        // 记录和为k的子数组个数
        int count = 0;

        // 遍历数组中的每个元素
        for (int num : nums) {
            // 更新当前前缀和
            currentSum += num;

            // 检查是否存在前缀和为 (currentSum - k) 的情况
            // 如果存在，说明有子数组的和为k
            if (map.containsKey(currentSum - k)) {
                // 累加该前缀和出现的次数到结果中
                count += map.get(currentSum - k);
            }

            // 将当前前缀和加入HashMap，更新其出现次数
            map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        }

        // 返回和为k的子数组个数
        return count;
    }

    /**
     * 方法2：暴力解法（仅供学习对比，效率较低）
     *
     * 算法思路：
     * 双重循环遍历所有可能的子数组，计算每个子数组的和
     *
     * 示例过程（以数组 [1,1,1], k=2 为例）：
     *
     * i=0: 子数组[1],和=1≠2; 子数组[1,1],和=2=2, count=1; 子数组[1,1,1],和=3≠2
     * i=1: 子数组[1],和=1≠2; 子数组[1,1],和=2=2, count=2
     * i=2: 子数组[1],和=1≠2
     *
     * 结果：count=2
     *
     * 时间复杂度分析：
     * - 双重循环：O(n²)，其中n为输入数组`nums`的长度
     * - 计算子数组和：O(1)
     * - 总时间复杂度：O(n²)
     *
     * 空间复杂度分析：
     * - 只使用了几个变量：O(1)
     * - 总空间复杂度：O(1)
     *
     * @param nums 整数数组
     * @param k 目标和
     * @return 和为k的连续子数组个数
     */
    public static int subarraySumBruteForce(int[] nums, int k) {
        int count = 0;

        // 双重循环遍历所有可能的子数组
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * 方法3：前缀和数组解法
     *
     * 算法思路：
     * 预先计算前缀和数组，然后通过前缀和差值计算子数组和
     *
     * 示例过程（以数组 [1,1,1], k=2 为例）：
     *
     * 1. 构建前缀和数组:
     *    prefixSum[0] = 0
     *    prefixSum[1] = 0+1 = 1
     *    prefixSum[2] = 1+1 = 2
     *    prefixSum[3] = 2+1 = 3
     *    prefixSum = [0,1,2,3]
     *
     * 2. 遍历所有子数组:
     *    i=0,j=0: sum = prefixSum[1]-prefixSum[0] = 1-0 = 1 ≠ 2
     *    i=0,j=1: sum = prefixSum[2]-prefixSum[0] = 2-0 = 2 = 2, count=1
     *    i=0,j=2: sum = prefixSum[3]-prefixSum[0] = 3-0 = 3 ≠ 2
     *    i=1,j=1: sum = prefixSum[2]-prefixSum[1] = 2-1 = 1 ≠ 2
     *    i=1,j=2: sum = prefixSum[3]-prefixSum[1] = 3-1 = 2 = 2, count=2
     *    i=2,j=2: sum = prefixSum[3]-prefixSum[2] = 3-2 = 1 ≠ 2
     *
     * 结果：count=2
     *
     * 时间复杂度分析：
     * - 构建前缀和数组：O(n)，其中n为输入数组`nums`的长度
     * - 双重循环计算子数组和：O(n²)
     * - 总时间复杂度：O(n²)
     *
     * 空间复杂度分析：
     * - 前缀和数组：O(n)，需要额外存储n+1个前缀和值
     * - 其他变量：O(1)
     * - 总空间复杂度：O(n)
     *
     * @param nums 整数数组
     * @param k 目标和
     * @return 和为k的连续子数组个数
     */
    public static int subarraySumPrefixArray(int[] nums, int k) {
        int count = 0;
        int n = nums.length;

        // 构建前缀和数组
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        // 遍历所有可能的子数组
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // 子数组[i,j]的和 = prefixSum[j+1] - prefixSum[i]
                if (prefixSum[j + 1] - prefixSum[i] == k) {
                    count++;
                }
            }
        }

        return count;
    }
}
