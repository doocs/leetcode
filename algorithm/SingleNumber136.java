package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

/**
 * 只出现一次的数字（LeetCode 136）
 *
 * 时间复杂度：
 * - 方法1（异或运算）：O(n)
 *   需要遍历数组一次
 * - 方法2（哈希表）：O(n)
 *   需要遍历数组一次，哈希表操作平均O(1)
 * - 方法3（排序）：O(n log n)
 *   排序需要O(n log n)时间
 *
 * 空间复杂度：
 * - 方法1（异或运算）：O(1)
 *   只使用常数个额外变量
 * - 方法2（哈希表）：O(n)
 *   需要哈希表存储元素计数
 * - 方法3（排序）：O(1)
 *   如果允许修改原数组，则为O(1)；否则需要O(n)空间复制数组
 */
public class SingleNumber136 {

    /**
     * 主函数：处理用户输入并找出只出现一次的元素
     *
     * 算法流程：
     * 1. 读取用户输入的整数数组
     * 2. 调用[singleNumber](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/SingleNumber136.java#L139-L152)方法找出只出现一次的元素
     * 3. 输出结果
     */
    public static void main(String[] args) {
        // 创建 Scanner 对象用于获取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入整数数组
        System.out.print("请输入整数数组（用空格分隔）：");
        String line = scanner.nextLine();
        String[] strArray = line.split(" ");

        // 将字符串数组转换为整数数组
        int[] nums = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            nums[i] = Integer.parseInt(strArray[i]); // 转换字符串为整数
        }

        // 保存原始数组用于输出
        int[] original = Arrays.copyOf(nums, nums.length);
        System.out.println("输入的数组: " + Arrays.toString(original));

        // 调用不同方法找出只出现一次的元素
        SingleNumber136 solution = new SingleNumber136();
        int result1 = solution.singleNumber(nums);           // 异或方法
        int result2 = solution.singleNumberHashMap(nums);    // 哈希表方法
        int result3 = solution.singleNumberSort(nums);       // 排序方法

        // 输出结果
        System.out.println("异或方法结果: " + result1);
        System.out.println("哈希表方法结果: " + result2);
        System.out.println("排序方法结果: " + result3);
    }

    /**
     * 方法1：异或运算解法（最优解）
     *
     * 算法思路：
     * 利用异或运算的性质：
     * 1. a ^ a = 0（相同数字异或为0）
     * 2. a ^ 0 = a（任何数字与0异或等于自身）
     * 3. 异或运算满足交换律和结合律
     *
     * 因为除了一个元素外，其他元素都出现两次，
     * 所以将所有元素进行异或运算，成对出现的元素会相互抵消为0，
     * 最终只剩下只出现一次的元素
     *
     * 执行过程分析（以数组 [4,1,2,1,2] 为例）：
     *
     * 初始状态：unique = 0
     *
     * 遍历过程：
     * 1. unique = 0 ^ 4 = 4
     * 2. unique = 4 ^ 1 = 5
     * 3. unique = 5 ^ 2 = 7
     * 4. unique = 7 ^ 1 = 6
     * 5. unique = 6 ^ 2 = 4
     *
     * 最终结果：unique = 4
     *
     * 二进制分析：
     * 4 = 100, 1 = 001, 2 = 010
     * 000 ^ 100 = 100 (4)
     * 100 ^ 001 = 101 (5)
     * 101 ^ 010 = 111 (7)
     * 111 ^ 001 = 110 (6)
     * 110 ^ 010 = 100 (4)
     *
     * 时间复杂度分析：
     * - 遍历数组一次：O(n)
     * - 异或运算：O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用一个额外变量：O(1)
     *
     * @param nums 整数数组，除了一个元素只出现一次外，其他元素都出现两次
     * @return 只出现一次的元素
     */
    public static int singleNumber(int[] nums) {
        int unique = 0; // 初始化唯一元素的变量

        // 遍历数组，进行异或运算
        for (int num : nums) {
            unique ^= num; // 异或运算
        }

        // 返回只出现一次的元素
        return unique;
    }

    /**
     * 方法2：哈希表解法
     *
     * 算法思路：
     * 使用哈希表统计每个元素出现的次数，然后找出出现次数为1的元素
     *
     * 时间复杂度分析：
     * - 遍历数组统计次数：O(n)
     * - 遍历哈希表查找结果：O(n)
     * - 哈希表操作平均时间复杂度：O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 哈希表存储元素计数：O(n)
     * - 最坏情况下需要存储所有不同元素：O(n)
     *
     * @param nums 整数数组
     * @return 只出现一次的元素
     */
    public int singleNumberHashMap(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();

        // 统计每个元素的出现次数
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // 找出出现次数为1的元素
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        // 根据题目保证，一定存在只出现一次的元素
        return -1;
    }

    /**
     * 方法3：排序解法
     *
     * 算法思路：
     * 对数组进行排序，相同的元素会相邻，只出现一次的元素必然与相邻元素不同
     *
     * 时间复杂度分析：
     * - 排序操作：O(n log n)
     * - 遍历查找：O(n)
     * - 总时间复杂度：O(n log n)
     *
     * 空间复杂度分析：
     * - 如果允许修改原数组，则为O(1)
     * - 否则需要O(n)空间复制数组
     * - 排序算法本身可能需要额外空间，取决于具体实现
     *
     * @param nums 整数数组
     * @return 只出现一次的元素
     */
    public int singleNumberSort(int[] nums) {
        Arrays.sort(nums);

        // 检查第一个元素
        if (nums.length == 1 || nums[0] != nums[1]) {
            return nums[0];
        }

        // 检查中间元素
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] != nums[i-1] && nums[i] != nums[i+1]) {
                return nums[i];
            }
        }

        // 检查最后一个元素
        return nums[nums.length - 1];
    }
}
