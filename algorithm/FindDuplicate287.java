package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 寻找重复数（LeetCode 287）
 *
 * 时间复杂度：O(n)
 * - 需要遍历数组常数次
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量
 * - 没有使用额外的数据结构
 */
public class FindDuplicate287 {

    /**
     * 主函数：处理用户输入并找出重复元素
     *
     * 算法流程：
     * 1. 读取用户输入的整数数组
     * 2. 调用findDuplicate方法找出重复元素
     * 3. 输出结果
     */
    public static void main(String[] args) {
        // 创建 Scanner 对象用于获取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入整数数组
        System.out.print("请输入整数数组（用空格分隔，包含重复元素）：");
        String line = scanner.nextLine();
        String[] strArray = line.split(" ");

        // 将字符串数组转换为整数数组
        int[] nums = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            nums[i] = Integer.parseInt(strArray[i]); // 转换字符串为整数
        }

        // 调用 findDuplicate 方法找出重复的元素
        int duplicate = findDuplicate(nums);

        // 输出结果
        System.out.println("重复的元素是：" + duplicate);
    }

    /**
     * 寻找重复数（Floyd判圈算法）
     *
     * 算法思路：
     * 将数组看作链表，数组下标作为节点，数组值作为next指针
     * 由于存在重复元素，必然存在环，使用Floyd判圈算法找到环的入口
     *
     * 执行过程分析（以数组 [1,3,4,2,2] 为例）：
     *
     * 数组索引: 0 1 2 3 4
     * 数组值:   1 3 4 2 2
     *
     * 构建的链表结构：
     * 0 -> 1 -> 3 -> 2 -> 4 -> 2 (形成环)
     *
     * Floyd算法执行步骤：
     *
     * 第一阶段：寻找相遇点
     * 初始：slow = nums[0] = 1, fast = nums[0] = 1
     * 第1步：slow = nums[1] = 3, fast = nums[nums[1]] = nums[3] = 2
     * 第2步：slow = nums[3] = 2, fast = nums[nums[2]] = nums[4] = 2
     * 第3步：slow = nums[2] = 4, fast = nums[nums[2]] = nums[4] = 2
     * 第4步：slow = nums[4] = 2, fast = nums[nums[2]] = nums[4] = 2
     * 相遇点：slow = fast = 2
     *
     * 第二阶段：寻找环的入口（重复元素）
     * slow = nums[0] = 1, fast = 2 (保持相遇点)
     * 第1步：slow = nums[1] = 3, fast = nums[2] = 4
     * 第2步：slow = nums[3] = 2, fast = nums[4] = 2
     * 相遇，环的入口为2，即重复元素
     *
     * 数学原理：
     * 假设链表头到环入口距离为a，环入口到相遇点距离为b，相遇点到环入口距离为c
     * 环的长度为 b+c
     *
     * 当快慢指针相遇时：
     * - 慢指针走过的距离：a + b
     * - 快指针走过的距离：a + b + c + b = a + 2b + c
     * - 快指针走过的距离是慢指针的2倍：2(a + b) = a + 2b + c
     * - 化简得：a = c
     *
     * 因此，从头节点到环入口的距离等于从相遇点到环入口的距离
     *
     * 时间复杂度分析：
     * - 第一阶段寻找相遇点：O(n)
     * - 第二阶段寻找环入口：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用了两个指针变量：O(1)
     *
     * @param nums 包含n+1个整数的数组，数字都在[1,n]范围内
     * @return 重复的数字
     */
    public static int findDuplicate(int[] nums) {
        // 步骤 1：使用快慢指针寻找相遇点
        int slow = nums[0];
        int fast = nums[0];

        // 循环直到快慢指针相遇
        // do-while循环确保至少执行一次
        do {
            slow = nums[slow];          // 慢指针每次移动一步
            fast = nums[nums[fast]];    // 快指针每次移动两步
        } while (slow != fast); // 当快慢指针相遇时结束循环

        // 步骤 2：寻找环的入口
        slow = nums[0]; // 将慢指针放回起始位置
        // 快指针保持在相遇点位置
        while (slow != fast) { // 当快慢指针再次相遇时找到环入口
            slow = nums[slow];  // 每次移动一步
            fast = nums[fast];  // 每次移动一步
        }

        // 返回重复的元素（环的入口）
        return slow; // 或者 return fast;
    }

    /**
     * 方法2：二分查找解法
     *
     * 算法思路：
     * 利用抽屉原理，对于区间[1,n]中的任意数字k，
     * 如果数组中小于等于k的数字个数大于k，则重复数字在[1,k]区间内
     * 否则重复数字在[k+1,n]区间内
     *
     * 时间复杂度分析：
     * - 二分查找执行次数：O(log n)
     * - 每次二分查找需要遍历数组统计：O(n)
     * - 总时间复杂度：O(n log n)
     *
     * 空间复杂度分析：
     * - 只使用了常数个变量（left, right, mid, count）：O(1)
     *
     * @param nums 包含n+1个整数的数组，数字都在[1,n]范围内
     * @return 重复的数字
     */
    public static int findDuplicateBinarySearch(int[] nums) {
        int left = 1;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = 0;

            // 统计数组中小于等于mid的数字个数
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }

            // 根据抽屉原理判断重复数字所在的区间
            if (count > mid) {
                right = mid;  // 重复数字在[left, mid]区间内
            } else {
                left = mid + 1;  // 重复数字在[mid+1, right]区间内
            }
        }

        return left;
    }
}
