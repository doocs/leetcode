package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Stack;
import java.util.Arrays;

/**
 * 每日温度（LeetCode 739）- 单调栈
 *
 * 时间复杂度：O(n)
 * - 每个元素最多入栈和出栈一次
 *
 * 空间复杂度：O(n)
 * - 栈最多存储n个元素
 * - 结果数组需要n个空间
 */
public class DailyTemperature739 {

    /**
     * 主函数：处理用户输入并计算每日温度问题
     *
     * 算法流程：
     * 1. 读取用户输入的温度数组
     * 2. 调用dailyTemperatures方法计算结果
     * 3. 输出结果
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入每天的温度（以空格分隔）：");

        String line = scanner.nextLine();
        String[] strTemps = line.split(" ");
        int[] temperatures = new int[strTemps.length];

        for (int i = 0; i < strTemps.length; i++) {
            temperatures[i] = Integer.parseInt(strTemps[i]);
        }

        int[] answer = dailyTemperatures(temperatures);
        System.out.println("下一个更高温度出现在几天后：" + Arrays.toString(answer));
    }

    /**
     * 计算每天需要等待多少天才能遇到更高的温度
     *
     * 算法思路：
     * 使用单调递减栈存储温度的索引
     * 1. 遍历温度数组
     * 2. 当当前温度大于栈顶索引对应的温度时，说明找到了更高温度
     * 3. 弹出栈顶元素并计算天数差
     * 4. 将当前索引入栈
     *
     * @param temperatures 温度数组
     * @return 等待更高温度的天数数组
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                answer[idx] = i - idx;
            }
            stack.push(i);
        }

        return answer;
    }

    /**
     * 方法2：从右到左的解法
     *
     * 算法思路：
     * 从右到左遍历，利用已计算的结果跳过不必要的比较
     *
     * @param temperatures 温度数组
     * @return 等待更高温度的天数数组
     */
    public int[] dailyTemperaturesReverse(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];

        for (int i = n - 2; i >= 0; i--) {
            int j = i + 1;

            while (j < n && temperatures[j] <= temperatures[i]) {
                if (answer[j] > 0) {
                    j += answer[j];
                } else {
                    j = n;
                }
            }

            if (j < n) {
                answer[i] = j - i;
            }
        }

        return answer;
    }

    /**
     * 方法3：暴力解法（仅供对比）
     *
     * 算法思路：
     * 对于每个温度，向后遍历寻找第一个更高温度
     *
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     *
     * @param temperatures 温度数组
     * @return 等待更高温度的天数数组
     */
    public int[] dailyTemperaturesBruteForce(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {
                    answer[i] = j - i;
                    break;
                }
            }
        }

        return answer;
    }

    /**
     * 方法4：使用数组代替栈（优化空间）
     *
     * 算法思路：
     * 使用数组模拟栈操作，避免使用Stack类的开销
     *
     * @param temperatures 温度数组
     * @return 等待更高温度的天数数组
     */
    public int[] dailyTemperaturesArrayStack(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        int[] stack = new int[n];
        int top = -1;

        for (int i = 0; i < n; i++) {
            while (top >= 0 && temperatures[i] > temperatures[stack[top]]) {
                int idx = stack[top--];
                answer[idx] = i - idx;
            }
            stack[++top] = i;
        }

        return answer;
    }
}
