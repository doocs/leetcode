package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 搜索旋转排序数组（LeetCode 33）
 *
 * 时间复杂度：O(log n)
 * - 使用二分查找，每次将搜索范围减半
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量
 */
public class Search33 {

    /**
     * 主函数：处理用户输入并搜索目标值
     *
     * 算法流程：
     * 1. 读取用户输入的旋转排序数组和目标值
     * 2. 调用search方法查找目标值的下标
     * 3. 输出结果
     */
    public static void main(String[] args) {
        // 创建 Scanner 对象用于获取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入数组长度
        System.out.print("请输入数组长度：");
        int n = scanner.nextInt();

        // 创建数组并读取元素
        int[] nums = new int[n];
        System.out.println("请输入数组元素（用空格分隔）：");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt(); // 读取数组元素
        }

        // 提示用户输入目标值
        System.out.print("请输入目标值：");
        int target = scanner.nextInt();

        // 调用 search 方法查找目标值的下标
        int result = search(nums, target);

        // 输出结果
        System.out.println("目标值 " + target + " 的下标：" + result);
    }

    /**
     * 在旋转排序数组中搜索目标值
     *
     * 算法思路：
     * 使用修改版的二分查找
     * 1. 数组被旋转后，至少有一半是有序的
     * 2. 每次判断哪一半是有序的
     * 3. 判断目标值是否在有序的那一半中
     * 4. 根据判断结果调整搜索范围
     *
     * @param nums 旋转排序数组（无重复元素）
     * @param target 目标值
     * @return 目标值的下标，如果不存在返回-1
     */
    public static int search(int[] nums, int target) {
        // 左指针，指向搜索范围的左边界
        int left = 0;
        // 右指针，指向搜索范围的右边界
        int right = nums.length - 1;

        // 二分查找
        while (left <= right) {
            // 计算中间索引，避免整数溢出
            int mid = left + (right - left) / 2;

            // 检查中间元素是否是目标值
            if (nums[mid] == target) {
                return mid; // 返回目标值的下标
            }

            // 判断哪部分是有序的
            // 关键判断条件：nums[left] <= nums[mid]
            // 如果成立，说明左半部分是有序的
            if (nums[left] <= nums[mid]) {
                // 左半部分有序
                // 判断目标值是否在左半部分的有序区间内
                if (nums[left] <= target && target < nums[mid]) {
                    // 目标在左半部分，缩小搜索范围到左半部分
                    right = mid - 1;
                } else {
                    // 目标在右半部分，缩小搜索范围到右半部分
                    left = mid + 1;
                }
            } else {
                // 右半部分有序
                // 判断目标值是否在右半部分的有序区间内
                if (nums[mid] < target && target <= nums[right]) {
                    // 目标在右半部分，缩小搜索范围到右半部分
                    left = mid + 1;
                } else {
                    // 目标在左半部分，缩小搜索范围到左半部分
                    right = mid - 1;
                }
            }
        }

        // 如果未找到目标值，返回 -1
        return -1;
    }

    /**
     * 方法2：递归版本
     *
     * 算法思路：
     * 使用递归实现修改版的二分查找
     *
     * @param nums 旋转排序数组
     * @param target 目标值
     * @return 目标值的下标，如果不存在返回-1
     */
    public int searchRecursive(int[] nums, int target) {
        // 调用递归辅助方法
        return searchHelper(nums, 0, nums.length - 1, target);
    }

    private int searchHelper(int[] nums, int left, int right, int target) {
        // 检查递归终止条件
        if (left > right) {
            // 返回-1表示未找到
            return -1;
        }

        // 计算中间索引
        int mid = left + (right - left) / 2;

        // 检查中间元素是否等于目标值
        if (nums[mid] == target) {
            // 返回目标值下标
            return mid;
        }

        // 判断左半部分是否有序
        if (nums[left] <= nums[mid]) {
            // 判断目标值是否在左半部分有序区间内
            if (nums[left] <= target && target < nums[mid]) {
                // 递归搜索左半部分
                return searchHelper(nums, left, mid - 1, target);
            } else {
                // 递归搜索右半部分
                return searchHelper(nums, mid + 1, right, target);
            }
        } else {
            // 判断目标值是否在右半部分有序区间内
            if (nums[mid] < target && target <= nums[right]) {
                // 递归搜索右半部分
                return searchHelper(nums, mid + 1, right, target);
            } else {
                // 递归搜索左半部分
                return searchHelper(nums, left, mid - 1, target);
            }
        }
    }

    /**
     * 方法3：先找到旋转点，再进行二分查找
     *
     * 算法思路：
     * 1. 先找到旋转点（最小元素位置）
     * 2. 在两个有序部分分别进行二分查找
     *
     * @param nums 旋转排序数组
     * @param target 目标值
     * @return 目标值的下标，如果不存在返回-1
     */
    public int searchFindPivot(int[] nums, int target) {
        // 检查数组是否为空
        if (nums == null || nums.length == 0) {
            // 返回-1
            return -1;
        }

        // 找到旋转点
        int pivot = findPivot(nums);

        // 在两个有序部分分别进行二分查找
        int result1 = Arrays.binarySearch(nums, 0, pivot + 1, target);
        int result2 = Arrays.binarySearch(nums, pivot + 1, nums.length, target);

        // 检查是否在左半部分找到
        if (result1 >= 0) {
            // 返回左半部分查找结果
            return result1;
        } else if (result2 >= 0) {
            // 返回右半部分查找结果
            return result2;
        } else {
            // 返回-1表示未找到
            return -1;
        }
    }

    /**
     * 找到旋转点（最小元素的索引）
     *
     * 算法思路：
     * 使用二分查找找到最小元素的位置
     */
    private int findPivot(int[] nums) {
        // 初始化左指针
        int left = 0;
        // 初始化右指针
        int right = nums.length - 1;

        // 当左指针小于右指针时循环
        while (left < right) {
            // 计算中间索引
            int mid = left + (right - left) / 2;
            // 比较中间元素和右边界元素
            if (nums[mid] > nums[right]) {
                // 更新左指针
                left = mid + 1;
            } else {
                // 更新右指针
                right = mid;
            }
        }

        // 返回旋转点索引
        return left;
    }
}
