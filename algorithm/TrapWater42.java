package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 接雨水问题（LeetCode 42）
 *
 * 时间复杂度：O(n)
 * - 使用双指针技术，每个元素最多被访问一次
 * - 总共需要遍历 n 个元素
 *
 * 空间复杂度：O(1)
 * - 只使用了常数级别的额外空间
 * - 没有使用与输入数组大小相关的额外存储空间
 */
public class TrapWater42 {
    public static void main(String[] args) {
        // 创建 Scanner 对象读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入整数数组
        System.out.print("请输入柱子的高度数组（用空格分隔）：");

        // 读取用户输入的一行字符串
        String line = scanner.nextLine();

        // 通过空格分隔字符串
        String[] str = line.split(" ");

        // 获取输入的柱子数量
        int n = str.length;

        // 创建高度数组
        int[] height = new int[n];

        // 将每个字符串转换为整数并存入高度数组
        for (int i = 0; i < n; i++) {
            height[i] = Integer.parseInt(str[i]);
        }

        // 调用 trap 方法计算积水量
        int result = trap(height);

        // 输出结果
        System.out.println("可以接的雨水量为：" + result);
    }

    /**
     * 计算可以接住的雨水量
     * 使用双指针技术，从数组两端向中间遍历
     *
     * 算法思路：
     * 1. 使用两个指针分别从数组的两端开始
     * 2. 维护左侧最大高度和右侧最大高度
     * 3. 每次移动较短边的指针，因为积水高度由较短边决定
     * 4. 累加每个位置能够接住的雨水量
     *
     * 核心原理：
     * 每个位置能接的雨水量 = min(左侧最大高度, 右侧最大高度) - 当前位置高度
     * 使用双指针技巧，始终移动较短边的指针，因为我们知道该位置的积水只取决于较短边
     *
     * 示例过程（以数组 [0,1,0,2,1,0,1,3,2,1,2,1] 为例）：
     * 索引:  0 1 2 3 4 5 6 7 8 9 10 11
     * 高度:  0 1 0 2 1 0 1 3 2 1 2  1
     *
     * 初始状态: left=0, right=11, leftMax=0, rightMax=0, maxwater=0
     *
     * 步骤1: height[0]=0 < height[11]=1
     *        leftMax = max(0, 0) = 0
     *        积水 = leftMax - height[0] = 0 - 0 = 0
     *        maxwater = 0 + 0 = 0
     *        left++ → left=1
     *
     * 步骤2: height[1]=1 > height[11]=1
     *        rightMax = max(0, 1) = 1
     *        积水 = rightMax - height[11] = 1 - 1 = 0
     *        maxwater = 0 + 0 = 0
     *        right-- → right=10
     *
     * 步骤3: height[1]=1 < height[10]=2
     *        leftMax = max(0, 1) = 1
     *        积水 = leftMax - height[1] = 1 - 1 = 0
     *        maxwater = 0 + 0 = 0
     *        left++ → left=2
     *
     * 步骤4: height[2]=0 < height[10]=2
     *        leftMax = max(1, 0) = 1
     *        积水 = leftMax - height[2] = 1 - 0 = 1
     *        maxwater = 0 + 1 = 1
     *        left++ → left=3
     *
     * ...继续执行直到 left >= right
     *
     * 时间复杂度分析：
     * - 双指针遍历数组：O(n)，其中n为输入数组`height`的长度
     * - 每次循环进行常数时间操作：O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用了几个变量存储指针和最大高度：O(1)
     * - 没有使用额外数组：O(1)
     * - 总空间复杂度：O(1)
     *
     * @param height 表示柱子高度的数组
     * @return 可以接住的雨水总量
     */
    public static int trap(int[] height) {
        // 用于存储积水总量
        int maxwater = 0;

        // 初始化左右指针，分别指向数组的两端
        int left = 0, right = height.length - 1;

        // 左侧和右侧的最大高度初始化为0
        int leftMax = 0, rightMax = 0;

        // 当左右指针没有重合时继续循环
        while (left < right) {
            // 更新左侧和右侧最大高度
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            // 如果左侧柱子的高度小于右侧柱子的高度
            if (height[left] < height[right]) {
                // 计算左侧当前柱子可以接住的雨水量，等于leftMax - height[left]
                // 由于左侧高度较小，当前位置的积水高度由左侧最大高度决定
                maxwater += leftMax - height[left];
                // 左指针右移，处理下一个柱子
                left++;
            } else {
                // 右侧柱子更高或相等，计算右侧当前柱子可以接住的雨水量，等于rightMax - height[right]
                // 由于右侧高度较小或相等，当前位置的积水高度由右侧最大高度决定
                maxwater += rightMax - height[right];
                // 右指针左移，处理下一个柱子
                right--;
            }
        }

        // 返回最终的积水总量
        return maxwater;
    }

    /**
     * 方法2：动态规划解法
     *
     * 算法思路：
     * 1. 预处理：计算每个位置左侧和右侧的最大高度
     * 2. 对于每个位置，积水高度 = min(左侧最大高度, 右侧最大高度) - 当前位置高度
     *
     * 示例过程（以数组 [0,1,0,2,1,0,1,3,2,1,2,1] 为例）：
     *
     * 1. 计算左侧最大高度数组:
     *    leftMax[0] = 0
     *    leftMax[1] = max(0,1) = 1
     *    leftMax[2] = max(1,0) = 1
     *    ...
     *    leftMax = [0,1,1,2,2,2,2,3,3,3,3,3]
     *
     * 2. 计算右侧最大高度数组:
     *    rightMax[11] = 1
     *    rightMax[10] = max(1,2) = 2
     *    rightMax[9] = max(2,1) = 2
     *    ...
     *    rightMax = [3,3,3,3,3,3,3,3,2,2,2,1]
     *
     * 3. 计算每个位置的积水量:
     *    i=0: min(0,3)-0 = 0
     *    i=1: min(1,3)-1 = 0
     *    i=2: min(1,3)-0 = 1
     *    ...
     *    总积水量 = 0+0+1+0+1+2+1+0+0+1+0+0 = 6
     *
     * 时间复杂度分析：
     * - 计算左侧最大高度数组：O(n)，其中n为输入数组`height`的长度
     * - 计算右侧最大高度数组：O(n)
     * - 计算总积水量：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 左侧最大高度数组：O(n)，存储n个元素
     * - 右侧最大高度数组：O(n)，存储n个元素
     * - 其他变量：O(1)
     * - 总空间复杂度：O(n)
     *
     * @param height 表示柱子高度的数组
     * @return 可以接住的雨水总量
     */
    public static int trapDP(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int n = height.length;
        // leftMax[i] 表示位置i左侧（包括i）的最大高度
        int[] leftMax = new int[n];
        // rightMax[i] 表示位置i右侧（包括i）的最大高度
        int[] rightMax = new int[n];

        // 计算每个位置左侧的最大高度
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // 计算每个位置右侧的最大高度
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        // 计算总积水量
        int totalWater = 0;
        for (int i = 0; i < n; i++) {
            // 每个位置的积水 = min(左侧最大高度, 右侧最大高度) - 当前高度
            totalWater += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return totalWater;
    }

    /**
     * 方法3：单调栈解法
     *
     * 算法思路：
     * 使用单调递减栈存储索引，当遇到较高柱子时计算积水
     *
     * 示例过程（以数组 [0,1,0,2,1,0,1,3,2,1,2,1] 为例）：
     *
     * 栈中存储的是索引，维护单调递减特性:
     *
     * i=0: height[0]=0, 栈为空，入栈 [0]
     * i=1: height[1]=1 > height[0]=0, 形成凹槽
     *      弹出0作为底部，栈为空，无法计算积水，1入栈 [1]
     * i=2: height[2]=0 < height[1]=1, 2入栈 [1,2]
     * i=3: height[3]=2 > height[2]=0, 形成凹槽
     *      弹出2作为底部，栈顶1，计算积水: width=3-1-1=1, height=min(1,2)-0=1, area=1
     *      继续比较height[3]=2 > height[1]=1
     *      弹出1作为底部，栈为空，无法计算积水，3入栈 [3]
     * ...
     *
     * 时间复杂度分析：
     * - 每个元素最多入栈和出栈一次：O(n)，其中n为输入数组`height`的长度
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 栈空间：最坏情况O(n)，当数组单调递减时
     * - 其他变量：O(1)
     * - 总空间复杂度：O(n)
     *
     * @param height 表示柱子高度的数组
     * @return 可以接住的雨水总量
     */
    public static int trapStack(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        java.util.Stack<Integer> stack = new java.util.Stack<>();
        int totalWater = 0;

        for (int i = 0; i < height.length; i++) {
            // 当栈不为空且当前柱子高度大于栈顶柱子高度时
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                // 弹出栈顶元素作为底部
                int bottom = stack.pop();

                // 如果栈为空，无法形成凹槽，跳出循环
                if (stack.isEmpty()) {
                    break;
                }

                // 计算积水宽度和高度
                int width = i - stack.peek() - 1;
                int minHeight = Math.min(height[stack.peek()], height[i]);
                int waterHeight = minHeight - height[bottom];

                // 累加积水量
                totalWater += width * waterHeight;
            }

            // 将当前索引入栈
            stack.push(i);
        }

        return totalWater;
    }
}
