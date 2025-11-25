package com.funian.algorithm.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 两数之和
 *
 * 时间复杂度：O(n)
 * - 只需要遍历数组一次
 * - 哈希表的查找和插入操作平均时间复杂度为 O(1)
 *
 * 空间复杂度：O(n)
 * - 需要使用哈希表存储数组元素及其索引，最坏情况下需要存储所有元素
 */
public class TwoSum1 {
    public static void main(String[] args) {
        // 创建 Scanner 对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入数组
        System.out.println("输出数组：");
        // 读取一行输入
        String line = scanner.nextLine();
        // 按空格分割字符串得到字符串数组
        String[] strs = line.split(" ");

        // 获取数组长度
        int n = strs.length;
        // 创建整型数组
        int[] nums = new int[n];
        // 将字符串数组转换为整型数组
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(strs[i]);
        }

        // 提示用户输入目标值
        System.out.println("输入目标值：");
        // 读取目标值
        int target = scanner.nextInt();

        // 调用 twoSum 方法查找两数之和
        int[] result = twoSum(nums, target);
        // 输出结果
        System.out.println("结果为：" + result[0] + " " + result[1]);
    }

    /**
     * 两数之和
     * 在给定数组中找到两个数，使其和等于目标值
     * <p>
     * 算法思路：
     * 使用哈希表存储已遍历过的元素及其索引
     * 对于每个元素，计算其补数（target - 当前元素）
     * 如果补数已在哈希表中，则找到了答案
     * 否则将当前元素存入哈希表继续遍历
     * <p>
     * 示例过程（以数组 [2,7,11,15]，target=9 为例）：
     * <p>
     * i=0: nums[0]=2, complement=9-2=7
     * map中不包含7，将(2,0)存入map
     * map = {2:0}
     * <p>
     * i=1: nums[1]=7, complement=9-7=2
     * map中包含2，返回[0,1]
     * <p>
     * 结果：[0,1]，因为nums[0]+nums[1]=2+7=9
     * <p>
     * 时间复杂度分析：
     * - 数组遍历一次：O(n)，其中n为输入数组nums的长度
     * - 哈希表查找和插入操作：平均O(1)
     * - 总时间复杂度：O(n)
     * <p>
     * 空间复杂度分析：
     * - 哈希表存储空间：最坏情况下O(n)，需要存储所有数组元素
     * - 其他变量占用常数空间：O(1)
     * - 总空间复杂度：O(n)
     *
     * @param nums   输入的整数数组
     * @param target 目标和
     * @return 返回两个数的索引数组，如果未找到则返回 null
     */
    public static int[] twoSum(int[] nums, int target) {
        // 获取数组长度
        int n = nums.length;
        // 创建哈希表存储数组元素和对应的索引
        Map<Integer, Integer> map = new HashMap<>();
        // 遍历数组
        for (int i = 0; i < n; i++) {
            // 计算当前元素的补数（目标值减去当前元素）
            int complement = target - nums[i];
            // 如果哈希表中包含补数，说明找到了两个数
            if (map.containsKey(complement)) {
                // 返回补数的索引和当前元素的索引
                return new int[]{map.get(complement), i};
            }
            // 将当前元素和索引存入哈希表
            map.put(nums[i], i);
        }
        // 未找到符合条件的两个数，返回 null
        return null;
    }
}
