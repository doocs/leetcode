package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 最大子数组和（LeetCode 53）
 *
 * 时间复杂度：O(n)
 * - 只需要遍历数组一次
 * - 每次迭代都进行常数时间的操作
 *
 * 空间复杂度：O(1)
 * - 只使用了常数级别的额外空间
 * - 没有使用与输入数组大小相关的额外存储空间
 */
public class MaxSubArray53 {
    public static void main(String[] args) {
        // 创建 Scanner 对象读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入数组
        System.out.println("请输入整数数组（用空格分隔）：");

        // 读取输入的数组
        String line = scanner.nextLine();

        // 按空格分割字符串得到字符串数组
        String[] str = line.split(" ");

        // 获取数组长度
        int n = str.length;

        // 创建整型数组
        int[] nums = new int[n];

        // 将字符串数组转换为整型数组
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(str[i]);
        }

        // 调用 maxSubArray 方法计算最大子数组和
        int result = maxSubArray(nums);

        // 输出结果
        System.out.println("最大子数组和为：" + result);
    }

    /**
     * 使用动态规划方法求解最大子数组和（Kadane算法）
     *
     * 算法思路：
     * 1. 对于每个元素，我们决定是将其加入到之前的子数组中，还是从当前元素重新开始
     * 2. 状态转移方程：dp[i] = max(nums[i], dp[i-1] + nums[i])
     * 3. 其中 dp[i] 表示以第 i 个元素结尾的最大子数组和
     * 4. 由于只需要前一个状态，可以使用一个变量代替整个 dp 数组
     *
     * 示例过程（以数组 [-2,1,-3,4,-1,2,1,-5,4] 为例）：
     * 索引:  0   1  2   3  4  5  6   7  8
     * 元素: -2   1 -3   4 -1  2  1  -5  4
     *
     * i=0: currentSum=-2, maxSum=-2
     * i=1: currentSum=max(1, -2+1)=1, maxSum=max(-2,1)=1
     * i=2: currentSum=max(-3, 1-3)=-2, maxSum=max(1,-2)=1
     * i=3: currentSum=max(4, -2+4)=4, maxSum=max(1,4)=4
     * i=4: currentSum=max(-1, 4-1)=3, maxSum=max(4,3)=4
     * i=5: currentSum=max(2, 3+2)=5, maxSum=max(4,5)=5
     * i=6: currentSum=max(1, 5+1)=6, maxSum=max(5,6)=6
     * i=7: currentSum=max(-5, 6-5)=1, maxSum=max(6,1)=6
     * i=8: currentSum=max(4, 1+4)=5, maxSum=max(6,5)=6
     *
     * 结果：最大子数组和为6，对应子数组[4,-1,2,1]
     *
     * 时间复杂度分析：
     * - 单次遍历数组：O(n)，其中n为输入数组`nums`的长度
     * - 每次迭代进行常数时间操作：O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用了几个变量存储状态：O(1)
     * - 没有使用额外数组：O(1)
     * - 总空间复杂度：O(1)
     *
     * @param nums 输入的整数数组
     * @return 最大子数组和
     */
    public static int maxSubArray(int[] nums) {
        // 初始化最大和为第一个元素
        int maxSum = nums[0];

        // 当前子数组的最大和初始化为第一个元素
        int currentSum = nums[0];

        // 从第二个元素开始遍历数组
        for (int i = 1; i < nums.length; i++) {
            // 当前子数组和为当前元素或加上前一个子数组和的较大值
            // 这表示我们决定是继续之前的子数组还是从当前元素重新开始
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            // 更新全局最大和
            maxSum = Math.max(maxSum, currentSum);
        }

        // 返回最大子数组和
        return maxSum;
    }

    /**
     * 方法2：分治法解法
     *
     * 算法思路：
     * 将数组分为两部分，最大子数组和可能出现在：
     * 1. 左半部分
     * 2. 右半部分
     * 3. 跨越中点的子数组
     *
     * 示例过程（以数组 [-2,1,-3,4,-1,2,1,-5,4] 为例）：
     *
     * 1. 初始调用: maxSubArrayHelper(nums, 0, 8)
     *    mid = 4, 左半部分[0,4], 右半部分[5,8]
     *
     * 2. 递归处理左半部分[-2,1,-3,4,-1]:
     *    mid = 2, 左半部分[0,2], 右半部分[3,4]
     *    左半部分最大和: [-2,1,-3] = -1
     *    右半部分最大和: [4,-1] = 4
     *    跨越中点最大和: [1,-3,4] = 2
     *    左半部分结果: max(-1,4,2) = 4
     *
     * 3. 递归处理右半部分[2,1,-5,4]:
     *    mid = 6, 左半部分[5,6], 右半部分[7,8]
     *    左半部分最大和: [2,1] = 3
     *    右半部分最大和: [-5,4] = 4
     *    跨越中点最大和: [1,-5,4] = 0
     *    右半部分结果: max(3,4,0) = 4
     *
     * 4. 计算跨越初始中点最大和:
     *    左侧最大和: [4,-1,2,1] = 6
     *    右侧最大和: [-5,4] = 4
     *    跨越中点最大和: 6 + 4 = 10
     *
     * 5. 最终结果: max(4,4,10) = 10
     *
     * 时间复杂度分析：
     * - 递归深度：O(log n)，其中n为输入数组`nums`的长度
     * - 每层递归处理：O(n)
     * - 总时间复杂度：O(n log n)
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(log n)
     * - 其他变量：O(1)
     * - 总空间复杂度：O(log n)
     *
     * @param nums 输入的整数数组
     * @return 最大子数组和
     */
    public static int maxSubArrayDivideConquer(int[] nums) {
        return maxSubArrayHelper(nums, 0, nums.length - 1);
    }

    /**
     * 分治法辅助方法
     *
     * 算法思路：
     * 1. 将数组递归分为左右两部分
     * 2. 分别计算左半部分、右半部分和跨越中点的最大子数组和
     * 3. 返回三者中的最大值
     *
     * @param nums 数组
     * @param left 左边界
     * @param right 右边界
     * @return 最大子数组和
     */
    private static int maxSubArrayHelper(int[] nums, int left, int right) {
        // 基础情况：只有一个元素
        if (left == right) {
            return nums[left];
        }

        // 计算中点
        int mid = left + (right - left) / 2;

        // 递归计算左半部分和右半部分的最大子数组和
        int leftSum = maxSubArrayHelper(nums, left, mid);
        int rightSum = maxSubArrayHelper(nums, mid + 1, right);

        // 计算跨越中点的最大子数组和
        int leftMax = Integer.MIN_VALUE;
        int sum = 0;
        // 从中点向左计算最大和
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftMax = Math.max(leftMax, sum);
        }

        int rightMax = Integer.MIN_VALUE;
        sum = 0;
        // 从中点+1向右计算最大和
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightMax = Math.max(rightMax, sum);
        }

        int crossSum = leftMax + rightMax;

        // 返回三者中的最大值
        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }

    /**
     * 方法3：返回最大子数组的起始和结束位置
     *
     * 算法思路：
     * 在计算最大子数组和的同时，记录子数组的起始和结束位置
     *
     * 示例过程（以数组 [-2,1,-3,4,-1,2,1,-5,4] 为例）：
     *
     * 初始化: maxSum=-2, currentSum=-2, start=0, end=0, tempStart=0
     *
     * i=1, nums[1]=1: currentSum<0, currentSum=1, tempStart=1
     *                 currentSum>maxSum, maxSum=1, start=1, end=1
     *
     * i=2, nums[2]=-3: currentSum>=0, currentSum=1+(-3)=-2
     *                  currentSum<maxSum, 不更新
     *
     * i=3, nums[3]=4: currentSum<0, currentSum=4, tempStart=3
     *                 currentSum>maxSum, maxSum=4, start=3, end=3
     *
     * i=4, nums[4]=-1: currentSum>=0, currentSum=4+(-1)=3
     *                  currentSum<maxSum, 不更新
     *
     * i=5, nums[5]=2: currentSum>=0, currentSum=3+2=5
     *                 currentSum>maxSum, maxSum=5, start=3, end=5
     *
     * i=6, nums[6]=1: currentSum>=0, currentSum=5+1=6
     *                 currentSum>maxSum, maxSum=6, start=3, end=6
     *
     * i=7, nums[7]=-5: currentSum>=0, currentSum=6+(-5)=1
     *                  currentSum<maxSum, 不更新
     *
     * i=8, nums[8]=4: currentSum>=0, currentSum=1+4=5
     *                 currentSum<maxSum, 不更新
     *
     * 最终结果: [6, 3, 6]，最大子数组为[4,-1,2,1]
     *
     * 时间复杂度分析：
     * - 单次遍历数组：O(n)，其中n为输入数组`nums`的长度
     * - 每次迭代进行常数时间操作：O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用了几个变量存储状态：O(1)
     * - 返回数组：O(1)
     * - 总空间复杂度：O(1)
     *
     * @param nums 输入的整数数组
     * @return 包含最大子数组和、起始索引和结束索引的数组
     */
    public static int[] maxSubArrayWithIndices(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{0, -1, -1};
        }

        int maxSum = nums[0];
        int currentSum = nums[0];
        int start = 0, end = 0, tempStart = 0;

        for (int i = 1; i < nums.length; i++) {
            if (currentSum < 0) {
                currentSum = nums[i];
                tempStart = i;
            } else {
                currentSum += nums[i];
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
            }
        }

        return new int[]{maxSum, start, end};
    }
}
