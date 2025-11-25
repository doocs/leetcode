package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 盛最多水的容器（LeetCode 11）
 *
 * 时间复杂度：O(n)
 * - 使用双指针技术，两个指针总共最多移动 n 次
 * - 每次移动只进行常数时间的操作
 *
 * 空间复杂度：O(1)
 * - 只使用了常数级别的额外空间
 * - 没有使用与输入数组大小相关的额外存储空间
 */
public class MaxWater11 {
    public static void main(String[] args) {
        // 创建 Scanner 对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入高度数组
        System.out.println("输入高度数组：");

        // 读取一行输入
        String line = scanner.nextLine();

        // 按空格分割字符串得到字符串数组
        String[] strs = line.split(" ");

        // 获取数组长度
        int n = strs.length;

        // 创建整型数组存储高度
        int[] height = new int[n];

        // 将字符串数组转换为整型数组
        for (int i = 0; i < n; i++) {
            height[i] = Integer.parseInt(strs[i]);
        }

        // 调用 maxWater 方法计算最大容量
        int result = maxWater(height);

        // 输出结果
        System.out.println("最大容量为：" + result);
    }

    /**
     * 计算盛最多水的容器容量
     * 给定一个长度为 n 的整数数组 height，有 n 条垂线，
     * 第 i 条线的两个端点是 (i, 0) 和 (i, height[i])。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * 算法思路：
     * 使用双指针技术，从数组两端开始向中间移动
     * 每次移动较短边的指针，因为这样才能可能找到更大的面积
     * 面积 = 宽度 × 高度（高度取两个边的较小值）
     *
     * 示例过程（以数组 [1,8,6,2,5,4,8,3,7] 为例）：
     *
     * 初始状态:
     * height = [1, 8, 6, 2, 5, 4, 8, 3, 7]
     *          L                       R
     * width = 8, height = min(1,7) = 1, area = 8×1 = 8
     *
     * 移动较短边(L):
     * height = [1, 8, 6, 2, 5, 4, 8, 3, 7]
     *             L                    R
     * width = 7, height = min(8,7) = 7, area = 7×7 = 49
     *
     * 移动较短边(R):
     * height = [1, 8, 6, 2, 5, 4, 8, 3, 7]
     *             L                 R
     * width = 6, height = min(8,3) = 3, area = 6×3 = 18
     *
     * 继续移动直到指针相遇...
     *
     * 最大面积: 49
     *
     * 时间复杂度分析：
     * - 双指针遍历数组：O(n)，其中n为输入数组`height`的长度
     * - 每次循环进行常数时间操作：O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用了几个变量存储指针和面积：O(1)
     * - 没有使用额外数组：O(1)
     * - 总空间复杂度：O(1)
     *
     * @param height 输入的高度数组
     * @return 容器能盛的最大水量
     */
    public static int maxWater(int[] height) {
        // 获取数组长度
        int n = height.length;

        // 左指针，指向数组开始位置
        int left = 0;

        // 右指针，指向数组结束位置
        int right = n - 1;

        // 记录最大面积
        int maxArea = 0;

        // 双指针向中间移动，直到相遇
        while (left < right) {
            // 计算当前宽度（两个指针之间的距离）
            int width = right - left;

            // 计算当前高度（两个指针指向高度的较小值）
            int minHeight = Math.min(height[left], height[right]);

            // 计算当前面积
            int currentArea = width * minHeight;

            // 更新最大面积
            maxArea = Math.max(maxArea, currentArea);

            // 移动较短边的指针，因为这样才能可能找到更大的面积
            // 如果移动较长边，面积只会变小或不变
            if (height[left] < height[right]) {
                left++;  // 移动左指针向右
            } else {
                right--; // 移动右指针向左
            }
        }

        // 返回找到的最大面积
        return maxArea;
    }


    /**
     * 方法2：暴力解法（仅供学习对比，效率较低）
     *
     * 算法思路：
     * 遍历所有可能的两条线组合，计算每种情况下的面积，找出最大值
     *
     * 示例过程（以数组 [1,8,6,2,5,4,8,3,7] 为例）：
     *
     * 遍历所有组合计算面积:
     * i=0,j=1: area = (1-0) × min(1,8) = 1 × 1 = 1
     * i=0,j=2: area = (2-0) × min(1,6) = 2 × 1 = 2
     * ...
     * i=1,j=8: area = (8-1) × min(8,7) = 7 × 7 = 49
     * ...
     * i=8,j=8: 结束
     *
     * 最大面积: 49
     *
     * 时间复杂度分析：
     * - 双重循环遍历所有组合：O(n²)，其中n为输入数组`height`的长度
     * - 外层循环：O(n)
     * - 内层循环：O(n)
     * - 每次计算面积：O(1)
     * - 总时间复杂度：O(n²)
     *
     * 空间复杂度分析：
     * - 只使用了几个变量：O(1)
     * - 总空间复杂度：O(1)
     *
     * @param height 输入的高度数组
     * @return 容器能盛的最大水量
     */
    public static int maxWaterBruteForce(int[] height) {
        int maxArea = 0;

        // 双重循环遍历所有可能的两条线组合
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                // 计算面积：宽度 × 高度（取较小值）
                int area = (j - i) * Math.min(height[i], height[j]);
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }

    /**
     * 方法3：优化的双指针法（提前终止）
     *
     * 算法思路：
     * 在标准双指针法基础上增加提前终止条件，当当前最大可能面积小于已知最大面积时提前终止
     *
     * 示例过程（以数组 [1,8,6,2,5,4,8,3,7] 为例）：
     *
     * 与标准双指针法类似，但增加提前终止条件:
     * 1. 预先计算数组最大高度 maxHeight = 8
     * 2. 在每次循环中检查 width × maxHeight ≤ maxArea 是否成立
     * 3. 如果成立则提前终止，因为不可能找到更大的面积
     *
     * 时间复杂度分析：
     * - 最坏情况仍为O(n)，其中n为输入数组`height`的长度
     * - 平均情况下由于提前终止会更快
     * - 计算最大高度：O(n)
     * - 双指针遍历：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 使用了几个额外变量：O(1)
     * - 总空间复杂度：O(1)
     *
     * @param height 输入的高度数组
     * @return 容器能盛的最大水量
     */
    public static int maxWaterOptimized(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        // 记录当前最大高度，用于提前终止
        int maxHeight = 0;
        for (int h : height) {
            maxHeight = Math.max(maxHeight, h);
        }

        while (left < right) {
            int width = right - left;

            // 如果当前最大可能面积已经小于已知最大面积，提前终止
            if (width * maxHeight <= maxArea) {
                break;
            }

            int minHeight = Math.min(height[left], height[right]);
            int currentArea = width * minHeight;
            maxArea = Math.max(maxArea, currentArea);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

}
