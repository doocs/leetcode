package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 寻找旋转排序数组中的最小值（LeetCode 153）
 *
 * 时间复杂度：O(log n)
 * - 使用二分查找，每次将搜索范围减半
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量
 */
public class FindMin153 {

    /**
     * 寻找旋转排序数组中的最小值
     *
     * 算法思路：
     * 使用二分查找，关键在于判断最小值在哪一半
     * 1. 比较中间元素和右边界元素
     * 2. 如果中间元素小于右边界元素，说明右半部分有序，最小值在左半部分（包括中间）
     * 3. 如果中间元素大于右边界元素，说明左半部分有序，最小值在右半部分
     *
     * @param nums 旋转排序数组（无重复元素）
     * @return 数组中的最小值
     */
    public int findMin(int[] nums) {
        // 边界情况：空数组
        if (nums == null || nums.length == 0) {
            // 抛出异常
            throw new IllegalArgumentException("数组不能为空");
        }

        // 如果数组只有一个元素，直接返回
        if (nums.length == 1) {
            // 返回唯一元素
            return nums[0];
        }

        // 左右指针
        int left = 0;
        int right = nums.length - 1;

        // 如果数组没有旋转，第一个元素就是最小值
        if (nums[left] < nums[right]) {
            // 返回第一个元素
            return nums[left];
        }

        // 二分查找
        while (left < right) {
            // 计算中间索引
            int mid = left + (right - left) / 2;

            // 关键判断：比较中间元素和右边界元素
            if (nums[mid] > nums[right]) {
                // 中间元素大于右边界元素，说明最小值在右半部分
                left = mid + 1;
            } else {
                // 中间元素小于等于右边界元素，说明最小值在左半部分（包括中间）
                right = mid;
            }
        }

        // left == right时，指向最小值
        return nums[left];
    }

    /**
     * 方法2：比较中间元素和左边界元素的版本
     *
     * 算法思路：
     * 通过比较中间元素和左边界元素来判断最小值位置
     *
     * @param nums 旋转排序数组
     * @return 数组中的最小值
     */
    public int findMinAlternative(int[] nums) {
        if (nums == null || nums.length == 0) {
            // 抛出异常
            throw new IllegalArgumentException("数组不能为空");
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // 比较中间元素和左边界元素
            if (nums[mid] > nums[left]) {
                // 左半部分有序
                if (nums[left] < nums[right]) {
                    // 整个数组有序，最小值在最左边
                    return nums[left];
                } else {
                    // 最小值在右半部分
                    left = mid + 1;
                }
            } else {
                // 右半部分有序，最小值在左半部分（包括中间）
                right = mid;
            }
        }

        return nums[left];
    }

    /**
     * 方法3：线性搜索解法（仅供对比）
     *
     * 算法思路：
     * 遍历数组找到最小值
     *
     * @param nums 旋转排序数组
     * @return 数组中的最小值
     */
    public int findMinLinear(int[] nums) {
        if (nums == null || nums.length == 0) {
            // 抛出异常
            throw new IllegalArgumentException("数组不能为空");
        }

        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                // 更新最小值
                min = nums[i];
            }
        }
        return min;
    }

    /**
     * 辅助方法：读取用户输入的数组
     *
     * @return 用户输入的整数数组
     */
    public static int[] readArray() {
        // 创建Scanner对象
        Scanner scanner = new Scanner(System.in);
        // 打印提示信息
        System.out.println("请输入旋转排序数组（用空格分隔）：");
        // 读取输入
        String input = scanner.nextLine();
        // 分割字符串
        String[] strArray = input.split(" ");

        // 创建整数数组
        int[] nums = new int[strArray.length];
        // 遍历字符串数组
        for (int i = 0; i < strArray.length; i++) {
            // 转换为整数
            nums[i] = Integer.parseInt(strArray[i]);
        }

        // 返回整数数组
        return nums;
    }

    /**
     * 主函数：处理用户输入并找出数组中的最小值
     */
    public static void main(String[] args) {
        // 打印程序标题
        System.out.println("寻找旋转排序数组中的最小值");

        // 调用readArray方法读取数组
        int[] nums = readArray();
        // 打印输入数组
        System.out.println("输入数组: " + Arrays.toString(nums));

        // 计算最小值
        FindMin153 solution = new FindMin153();
        // 调用findMin方法
        int result1 = solution.findMin(nums);
        // 调用findMinAlternative方法
        int result2 = solution.findMinAlternative(nums);
        // 调用findMinLinear方法
        int result3 = solution.findMinLinear(nums);

        // 输出结果
        // 打印二分查找方法结果
        System.out.println("二分查找方法结果: " + result1);
        // 打印替代方法结果
        System.out.println("替代方法结果: " + result2);
        // 打印线性搜索方法结果
        System.out.println("线性搜索方法结果: " + result3);
    }
}
