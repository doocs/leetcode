package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 寻找两个正序数组的中位数（LeetCode 4）
 *
 * 时间复杂度：O(log(min(m,n)))
 * - 使用二分查找在较短的数组上进行搜索
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量
 */
public class FindMediumNumber4 {

    /**
     * 寻找两个正序数组的中位数
     *
     * 算法思路：
     * 使用二分查找，确保较短的数组作为第一个数组
     * 1. 在较短数组上进行二分查找，确定分割点
     * 2. 根据总长度确定另一个数组的分割点
     * 3. 确保左半部分的最大值小于等于右半部分的最小值
     * 4. 根据总长度奇偶性计算中位数
     *
     * 执行过程分析（以`nums1=[1,3]`, `nums2=[2]`为例）：
     *
     * 初始状态：
     * A = [1,3], B = [2]
     * m = 2, n = 1
     * total = 3, half = 1
     *
     * 二分查找过程：
     * left = 0, right = 2
     * i = 1, j = 1-1-0 = 0
     *
     * 分割后：
     * A_left = 1, A_right = 3
     * B_left = -∞, B_right = 2
     *
     * 检查条件：
     * A_left(1) <= B_right(2) ✓
     * B_left(-∞) <= A_right(3) ✓
     *
     * 计算中位数：
     * 总长度为奇数，中位数 = min(A_right, B_right) = min(3, 2) = 2
     *
     * 执行过程分析（以`nums1=[1,2]`, `nums2=[3,4]`为例）：
     *
     * 初始状态：
     * A = [1,2], B = [3,4]
     * m = 2, n = 2
     * total = 4, half = 2
     *
     * 二分查找过程：
     * left = 0, right = 2
     * i = 1, j = 2-1-1 = 0
     *
     * 分割后：
     * A_left = 1, A_right = 2
     * B_left = -∞, B_right = 3
     *
     * 检查条件：
     * A_left(1) <= B_right(3) ✓
     * B_left(-∞) <= A_right(2) ✓
     *
     * 计算中位数：
     * 总长度为偶数，中位数 = (max(1,-∞) + min(2,3)) / 2 = (1 + 2) / 2 = 1.5
     *
     * 时间复杂度分析：
     * - 二分查找：O(log(min(m,n)))
     * - 每次迭代操作：O(1)
     * - 总时间复杂度：O(log(min(m,n)))
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param nums1 第一个正序数组
     * @param nums2 第二个正序数组
     * @return 两个数组的中位数
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 确保nums1是较短的数组，优化时间复杂度
        // if (nums1.length > nums2.length) 检查nums1是否比nums2长
        if (nums1.length > nums2.length) {
            // return findMedianSortedArrays(nums2, nums1) 交换参数递归调用
            return findMedianSortedArrays(nums2, nums1);
        }

        // int m = nums1.length 获取nums1的长度
        int m = nums1.length;
        // int n = nums2.length 获取nums2的长度
        int n = nums2.length;

        // 总长度和左半部分的长度
        // int total = m + n 计算总长度
        int total = m + n;
        // int half = (total + 1) / 2 计算左半部分长度
        int half = (total + 1) / 2;

        // 二分查找的边界
        // int left = 0 初始化左边界
        int left = 0;
        // int right = m 初始化右边界
        int right = m;

        // while (left <= right) 二分查找循环
        while (left <= right) {
            // nums1的分割点
            // int i = left + (right - left) / 2 计算nums1的分割点
            int i = left + (right - left) / 2;
            // nums2的分割点
            // int j = half - i 计算nums2的分割点
            int j = half - i;

            // 分割点左边的元素（如果越界则用极值代替）
            // int A_left = (i > 0) ? nums1[i - 1] : Integer.MIN_VALUE 获取A的左半部分最大值
            int A_left = (i > 0) ? nums1[i - 1] : Integer.MIN_VALUE;
            // int B_left = (j > 0) ? nums2[j - 1] : Integer.MIN_VALUE 获取B的左半部分最大值
            int B_left = (j > 0) ? nums2[j - 1] : Integer.MIN_VALUE;

            // 分割点右边的元素（如果越界则用极值代替）
            // int A_right = (i < m) ? nums1[i] : Integer.MAX_VALUE 获取A的右半部分最小值
            int A_right = (i < m) ? nums1[i] : Integer.MAX_VALUE;
            // int B_right = (j < n) ? nums2[j] : Integer.MAX_VALUE 获取B的右半部分最小值
            int B_right = (j < n) ? nums2[j] : Integer.MAX_VALUE;

            // 检查是否找到正确的分割点
            // if (A_left <= B_right && B_left <= A_right) 检查分割点是否正确
            if (A_left <= B_right && B_left <= A_right) {
                // 找到正确的分割点
                // if (total % 2 == 1) 检查总长度是否为奇数
                if (total % 2 == 1) {
                    // 总长度为奇数，返回左半部分的最大值
                    // return Math.max(A_left, B_left) 返回中位数
                    return Math.max(A_left, B_left);
                } else {
                    // 总长度为偶数，返回左半部分最大值和右半部分最小值的平均值
                    // return (Math.max(A_left, B_left) + Math.min(A_right, B_right)) / 2.0 返回中位数
                    return (Math.max(A_left, B_left) + Math.min(A_right, B_right)) / 2.0;
                }
            } else if (A_left > B_right) {
                // nums1的左半部分太大，需要向左移动
                // right = i - 1 调整右边界
                right = i - 1;
            } else {
                // nums1的左半部分太小，需要向右移动
                // left = i + 1 调整左边界
                left = i + 1;
            }
        }

        // 理论上不会执行到这里
        // return 0.0 返回默认值
        return 0.0;
    }

    /**
     * 方法2：合并后找中位数（简单但效率较低）
     *
     * 算法思路：
     * 先合并两个有序数组，再找中位数
     *
     * 时间复杂度分析：
     * - 合并数组：O(m+n)
     * - 计算中位数：O(1)
     * - 总时间复杂度：O(m+n)
     *
     * 空间复杂度分析：
     * - 合并数组存储空间：O(m+n)
     *
     * @param nums1 第一个正序数组
     * @param nums2 第二个正序数组
     * @return 两个数组的中位数
     */
    public double findMedianSortedArraysMerge(int[] nums1, int[] nums2) {
        // int m = nums1.length 获取nums1的长度
        int m = nums1.length;
        // int n = nums2.length 获取nums2的长度
        int n = nums2.length;
        // int[] merged = new int[m + n] 创建合并后的数组
        int[] merged = new int[m + n];

        // int i = 0, j = 0, k = 0 初始化指针
        int i = 0, j = 0, k = 0;

        // 合并两个有序数组
        // while (i < m && j < n) 当两个数组都未遍历完时循环
        while (i < m && j < n) {
            // if (nums1[i] <= nums2[j]) 比较两个数组当前元素
            if (nums1[i] <= nums2[j]) {
                // merged[k++] = nums1[i++] 将较小元素放入合并数组
                merged[k++] = nums1[i++];
            } else {
                // merged[k++] = nums2[j++] 将较小元素放入合并数组
                merged[k++] = nums2[j++];
            }
        }

        // 复制剩余元素
        // while (i < m) 复制nums1剩余元素
        while (i < m) {
            // merged[k++] = nums1[i++] 复制元素
            merged[k++] = nums1[i++];
        }

        // while (j < n) 复制nums2剩余元素
        while (j < n) {
            // merged[k++] = nums2[j++] 复制元素
            merged[k++] = nums2[j++];
        }

        // 计算中位数
        // int total = m + n 计算总长度
        int total = m + n;
        // if (total % 2 == 1) 检查总长度是否为奇数
        if (total % 2 == 1) {
            // return merged[total / 2] 返回中位数
            return merged[total / 2];
        } else {
            // return (merged[total / 2 - 1] + merged[total / 2]) / 2.0 返回中位数
            return (merged[total / 2 - 1] + merged[total / 2]) / 2.0;
        }
    }

    /**
     * 方法3：递归解法（找第k小的元素）
     *
     * 算法思路：
     * 通过递归查找第k小的元素来计算中位数
     *
     * 时间复杂度分析：
     * - 每次递归排除k/2个元素：O(log(m+n))
     * - 总时间复杂度：O(log(m+n))
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(log(m+n))
     *
     * @param nums1 第一个正序数组
     * @param nums2 第二个正序数组
     * @return 两个数组的中位数
     */
    public double findMedianSortedArraysRecursive(int[] nums1, int[] nums2) {
        // int m = nums1.length 获取nums1的长度
        int m = nums1.length;
        // int n = nums2.length 获取nums2的长度
        int n = nums2.length;
        // int total = m + n 计算总长度
        int total = m + n;

        // if (total % 2 == 1) 检查总长度是否为奇数
        if (total % 2 == 1) {
            // return findKthElement(nums1, 0, m - 1, nums2, 0, n - 1, total / 2 + 1) 查找第(total/2+1)小的元素
            return findKthElement(nums1, 0, m - 1, nums2, 0, n - 1, total / 2 + 1);
        } else {
            // int mid1 = findKthElement(nums1, 0, m - 1, nums2, 0, n - 1, total / 2) 查找第(total/2)小的元素
            int mid1 = findKthElement(nums1, 0, m - 1, nums2, 0, n - 1, total / 2);
            // int mid2 = findKthElement(nums1, 0, m - 1, nums2, 0, n - 1, total / 2 + 1) 查找第(total/2+1)小的元素
            int mid2 = findKthElement(nums1, 0, m - 1, nums2, 0, n - 1, total / 2 + 1);
            // return (mid1 + mid2) / 2.0 返回中位数
            return (mid1 + mid2) / 2.0;
        }
    }

    /**
     * 找到两个有序数组中第k小的元素
     *
     * 算法思路：
     * 通过比较两个数组中第k/2个元素，排除较小的一半
     *
     * @param nums1 第一个数组
     * @param start1 nums1的起始索引
     * @param end1 nums1的结束索引
     * @param nums2 第二个数组
     * @param start2 nums2的起始索引
     * @param end2 nums2的结束索引
     * @param k 第k小的元素
     * @return 第k小的元素值
     */
    private int findKthElement(int[] nums1, int start1, int end1,
                              int[] nums2, int start2, int end2, int k) {
        // int len1 = end1 - start1 + 1 计算nums1的有效长度
        int len1 = end1 - start1 + 1;
        // int len2 = end2 - start2 + 1 计算nums2的有效长度
        int len2 = end2 - start2 + 1;

        // 确保nums1是较短的数组
        // if (len1 > len2) 检查nums1是否比nums2长
        if (len1 > len2) {
            // return findKthElement(nums2, start2, end2, nums1, start1, end1, k) 交换参数递归调用
            return findKthElement(nums2, start2, end2, nums1, start1, end1, k);
        }

        // 如果nums1为空，直接在nums2中找
        // if (len1 == 0) 检查nums1是否为空
        if (len1 == 0) {
            // return nums2[start2 + k - 1] 返回nums2中第k小的元素
            return nums2[start2 + k - 1];
        }

        // 如果k为1，返回两个数组起始元素的较小值
        // if (k == 1) 检查k是否为1
        if (k == 1) {
            // return Math.min(nums1[start1], nums2[start2]) 返回较小值
            return Math.min(nums1[start1], nums2[start2]);
        }

        // 在两个数组中分别找第k/2个元素
        // int i = start1 + Math.min(len1, k / 2) - 1 计算nums1中要比较的元素索引
        int i = start1 + Math.min(len1, k / 2) - 1;
        // int j = start2 + Math.min(len2, k / 2) - 1 计算nums2中要比较的元素索引
        int j = start2 + Math.min(len2, k / 2) - 1;

        // if (nums1[i] > nums2[j]) 比较两个元素
        if (nums1[i] > nums2[j]) {
            // nums2的前k/2个元素可以排除
            // return findKthElement(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1)) 递归查找
            return findKthElement(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            // nums1的前k/2个元素可以排除
            // return findKthElement(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1)) 递归查找
            return findKthElement(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

    /**
     * 辅助方法：读取用户输入的数组
     *
     * 时间复杂度分析：
     * - 读取和解析输入：O(n)
     *
     * 空间复杂度分析：
     * - 存储数组：O(n)
     *
     * @param prompt 提示信息
     * @return 用户输入的整数数组
     */
    public static int[] readArray(String prompt) {
        // Scanner scanner = new Scanner(System.in) 创建Scanner对象
        Scanner scanner = new Scanner(System.in);
        // System.out.println(prompt) 打印提示信息
        System.out.println(prompt);
        // String input = scanner.nextLine() 读取输入
        String input = scanner.nextLine();
        // if (input.trim().isEmpty()) 检查输入是否为空
        if (input.trim().isEmpty()) {
            // return new int[0] 返回空数组
            return new int[0];
        }
        // String[] strArray = input.split(" ") 分割字符串
        String[] strArray = input.split(" ");

        // int[] nums = new int[strArray.length] 创建整数数组
        int[] nums = new int[strArray.length];
        // for (int i = 0; i < strArray.length; i++) 遍历字符串数组
        for (int i = 0; i < strArray.length; i++) {
            // nums[i] = Integer.parseInt(strArray[i]) 转换为整数
            nums[i] = Integer.parseInt(strArray[i]);
        }

        // return nums 返回整数数组
        return nums;
    }

    /**
     * 主函数：处理用户输入并计算两个数组的中位数
     */
    public static void main(String[] args) {
        // System.out.println("寻找两个正序数组的中位数") 打印程序标题
        System.out.println("寻找两个正序数组的中位数");

        // 读取用户输入的数组
        // int[] nums1 = readArray("请输入第一个正序数组（用空格分隔）：") 读取第一个数组
        int[] nums1 = readArray("请输入第一个正序数组（用空格分隔）：");
        // int[] nums2 = readArray("请输入第二个正序数组（用空格分隔）：") 读取第二个数组
        int[] nums2 = readArray("请输入第二个正序数组（用空格分隔）：");

        // System.out.println("第一个数组: " + Arrays.toString(nums1)) 打印第一个数组
        System.out.println("第一个数组: " + Arrays.toString(nums1));
        // System.out.println("第二个数组: " + Arrays.toString(nums2)) 打印第二个数组
        System.out.println("第二个数组: " + Arrays.toString(nums2));

        // 计算中位数
        // FindMediumNumber4 solution = new FindMediumNumber4() 创建解决方案对象
        FindMediumNumber4 solution = new FindMediumNumber4();
        // double result1 = solution.findMedianSortedArrays(nums1, nums2) 调用二分查找方法
        double result1 = solution.findMedianSortedArrays(nums1, nums2);
        // double result2 = solution.findMedianSortedArraysMerge(nums1, nums2) 调用合并方法
        double result2 = solution.findMedianSortedArraysMerge(nums1, nums2);
        // double result3 = solution.findMedianSortedArraysRecursive(nums1, nums2) 调用递归方法
        double result3 = solution.findMedianSortedArraysRecursive(nums1, nums2);

        // 输出结果
        // System.out.println("二分查找方法结果: " + result1) 打印二分查找方法结果
        System.out.println("二分查找方法结果: " + result1);
        // System.out.println("合并方法结果: " + result2) 打印合并方法结果
        System.out.println("合并方法结果: " + result2);
        // System.out.println("递归方法结果: " + result3) 打印递归方法结果
        System.out.println("递归方法结果: " + result3);
    }
}
