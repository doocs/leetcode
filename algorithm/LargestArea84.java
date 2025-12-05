package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Stack;
import java.util.Arrays;

/**
 * 柱状图中最大的矩形（LeetCode 84）- 单调栈
 *
 * 时间复杂度：O(n)
 * - 每个元素最多入栈和出栈一次
 *
 * 空间复杂度：O(n)
 * - 栈最多存储n个元素
 */
public class LargestArea84 {

    /**
     * 主函数：处理用户输入并计算柱状图中最大矩形面积
     *
     * 算法流程：
     * 1. 读取用户输入的柱状图高度数组
     * 2. 调用largestRectangleArea方法计算最大面积
     * 3. 输出结果
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入柱状图的高度（以空格分隔）：");
        String line = scanner.nextLine();
        String[] strHeights = line.split(" ");
        int[] heights = new int[strHeights.length];

        // 将输入的字符串高度转换为整数数组
        for (int i = 0; i < strHeights.length; i++) {
            heights[i] = Integer.parseInt(strHeights[i]);
        }

        int maxArea = largestRectangleArea(heights);
        System.out.println("能够勾勒出来的最大矩形面积是: " + maxArea);
    }

    /**
     * 计算柱状图中能够勾勒出来的最大矩形面积
     *
     * 算法思路：
     * 使用单调递增栈存储柱子的索引
     * 1. 遍历柱子高度数组（并在末尾添加一个高度为0的虚拟柱子）
     * 2. 当当前高度小于栈顶索引对应的高度时，说明找到了右边界
     * 3. 弹出栈顶元素作为矩形的高度，计算宽度并更新最大面积
     * 4. 将当前索引入栈
     *
     * 执行过程分析（以heights=[2,1,5,6,2,3]为例）：
     *
     * 添加虚拟柱子后：[2,1,5,6,2,3,0]
     *
     * 初始状态：
     * stack = []
     * maxArea = 0
     *
     * 遍历过程：
     * i=0, height=2:
     *   栈为空，将0入栈
     *   stack = [0]
     *
     * i=1, height=1:
     *   1 < heights[0]=2，找到右边界
     *   弹出0，height=2，width=1-(-1)-1=1（栈为空时宽度为i）
     *   area=2*1=2，maxArea=max(0,2)=2
     *   将1入栈
     *   stack = [1]
     *
     * i=2, height=5:
     *   5 > heights[1]=1，将2入栈
     *   stack = [1,2]
     *
     * i=3, height=6:
     *   6 > heights[2]=5，将3入栈
     *   stack = [1,2,3]
     *
     * i=4, height=2:
     *   2 < heights[3]=6，找到右边界
     *   弹出3，height=6，width=4-2-1=1
     *   area=6*1=6，maxArea=max(2,6)=6
     *
     *   2 < heights[2]=5，找到右边界
     *   弹出2，height=5，width=4-1-1=2
     *   area=5*2=10，maxArea=max(6,10)=10
     *
     *   2 > heights[1]=1，将4入栈
     *   stack = [1,4]
     *
     * i=5, height=3:
     *   3 > heights[4]=2，将5入栈
     *   stack = [1,4,5]
     *
     * i=6, height=0:
     *   0 < heights[5]=3，找到右边界
     *   弹出5，height=3，width=6-4-1=1
     *   area=3*1=3，maxArea=max(10,3)=10
     *
     *   0 < heights[4]=2，找到右边界
     *   弹出4，height=2，width=6-1-1=4
     *   area=2*4=8，maxArea=max(10,8)=10
     *
     *   0 < heights[1]=1，找到右边界
     *   弹出1，height=1，width=6-(-1)-1=6（栈为空）
     *   area=1*6=6，maxArea=max(10,6)=10
     *
     * 最终结果：maxArea = 10
     * 最大矩形：高度为5和6的柱子组成的矩形，面积=5*2=10
     *
     * @param heights 柱状图的高度数组
     * @return 最大矩形面积
     */
    public static int largestRectangleArea(int[] heights) {
        // 记录最大面积
        int maxArea = 0;
        // 使用单调递增栈存储柱子的索引
        Stack<Integer> stack = new Stack<>();
        // 柱子数量
        int n = heights.length;

        // 遍历柱子（包括一个虚拟的0高度柱子）
        for (int i = 0; i <= n; i++) {
            // 处理柱子的高度，末尾加一个高度为0的柱子以处理剩余的柱子
            // 当i==n时，使用0高度来确保栈中所有元素都被处理
            int currentHeight = (i == n) ? 0 : heights[i];

            // 当栈不为空且当前高度小于栈顶索引对应的高度时
            // 说明栈顶柱子找到了右边界，可以计算以该柱子为高的最大矩形面积
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                // 弹出栈顶元素作为矩形的高度
                int height = heights[stack.pop()];

                // 计算矩形的宽度
                // 如果栈为空，说明该柱子是目前为止最矮的，宽度为i
                // 如果栈不为空，宽度为 i - stack.peek() - 1
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;

                // 更新最大面积
                maxArea = Math.max(maxArea, height * width);
            }

            // 将当前柱子的索引入栈
            stack.push(i);
        }

        // 返回最大面积
        return maxArea;
    }

    /**
     * 方法2：不使用虚拟柱子的版本
     *
     * @param heights 柱状图的高度数组
     * @return 最大矩形面积
     */
    public int largestRectangleAreaAlternative(int[] heights) {
        if (heights == null || heights.length == 0) return 0;

        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        // 处理栈中剩余的元素
        while (!stack.isEmpty()) {
            int height = heights[stack.pop()];
            int width = stack.isEmpty() ? heights.length : heights.length - stack.peek() - 1;
            maxArea = Math.max(maxArea, height * width);
        }

        return maxArea;
    }

    /**
     * 方法3：分治法（仅供学习，效率较低）
     *
     * @param heights 柱状图的高度数组
     * @return 最大矩形面积
     */
    public int largestRectangleAreaDivideConquer(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        return divideConquer(heights, 0, heights.length - 1);
    }

    private int divideConquer(int[] heights, int start, int end) {
        if (start > end) return 0;

        // 找到最小高度的索引
        int minIndex = start;
        for (int i = start; i <= end; i++) {
            if (heights[i] < heights[minIndex]) {
                minIndex = i;
            }
        }

        // 以最小高度为高的矩形面积
        int areaWithMinHeight = heights[minIndex] * (end - start + 1);

        // 递归计算左右两部分的最大面积
        int leftMax = divideConquer(heights, start, minIndex - 1);
        int rightMax = divideConquer(heights, minIndex + 1, end);

        return Math.max(areaWithMinHeight, Math.max(leftMax, rightMax));
    }
}
