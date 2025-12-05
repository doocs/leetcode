package com.funian.algorithm.algorithm;

import java.util.Stack;

/**
 * 最小栈（LeetCode 155）
 *
 * 时间复杂度：O(1)
 * - 所有操作（push, pop, top, getMin）都是常数时间复杂度
 *
 * 空间复杂度：O(n)
 * - 需要两个栈存储元素，最坏情况下需要2n的空间
 */
public class MinStack155 {

    // 主栈用于存储所有元素
    // 按照正常的栈顺序存储所有推入的元素
    private Stack<Integer> stack;

    // 辅助栈用于存储最小元素
    // 栈顶始终存储当前主栈中的最小元素
    private Stack<Integer> minStack;

    // 初始化堆栈对象
    public MinStack155() {
        // 创建主栈
        stack = new Stack<>();
        // 创建辅助栈（用于跟踪最小值）
        minStack = new Stack<>();
    }

    /**
     * 将元素 val 推入堆栈
     *
     * 算法思路：
     * 1. 将元素推入主栈
     * 2. 如果辅助栈为空，或者新元素小于等于辅助栈栈顶元素，则也将其推入辅助栈
     *
     * 执行过程分析（依次推入 -2, 0, -3）：
     *
     * push(-2):
     *   stack.push(-2)    -> stack = [-2]
     *   minStack.isEmpty() = true
     *   minStack.push(-2) -> minStack = [-2]
     *
     * push(0):
     *   stack.push(0)     -> stack = [-2, 0]
     *   0 > -2，不推入辅助栈
     *   minStack = [-2]
     *
     * push(-3):
     *   stack.push(-3)    -> stack = [-2, 0, -3]
     *   -3 < -2，推入辅助栈
     *   minStack.push(-3) -> minStack = [-2, -3]
     *
     * @param val 要推入的整数值
     */
    public void push(int val) {
        // 将元素推入主栈（正常栈操作）
        stack.push(val);

        // 维护辅助栈：如果辅助栈为空，或者 val 小于等于辅助栈的栈顶元素，将 val 推入辅助栈
        // 使用 <= 而不是 < 是为了处理重复的最小值
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    /**
     * 删除堆栈顶部的元素
     *
     * 算法思路：
     * 1. 如果主栈栈顶元素等于辅助栈栈顶元素，说明要删除的是当前最小元素，同时弹出两个栈的栈顶元素
     * 2. 否则只弹出主栈的栈顶元素
     *
     * 执行过程分析（接上面的例子，当前状态：stack=[-2,0,-3], minStack=[-2,-3]）：
     *
     * pop():
     *   stack.peek() = -3, minStack.peek() = -3
     *   两者相等，说明-3是最小值
     *   minStack.pop() -> minStack = [-2]
     *   stack.pop()    -> stack = [-2, 0]
     *
     * pop():
     *   stack.peek() = 0, minStack.peek() = -2
     *   两者不相等，只弹出主栈
     *   stack.pop()    -> stack = [-2]
     */
    public void pop() {
        // 关键边界条件：如果栈顶元素等于辅助栈的栈顶元素，同时弹出两个栈的栈顶元素
        // 使用equals而不是==是为了避免Integer对象比较的问题
        if (stack.peek().equals(minStack.peek())) {
            // 弹出辅助栈栈顶（当前最小值被删除）
            minStack.pop();
        }
        // 弹出主栈栈顶
        stack.pop();
    }

    /**
     * 获取堆栈顶部的元素
     *
     * @return 堆栈顶部的元素
     */
    public int top() {
        // 直接返回主栈栈顶元素
        return stack.peek();
    }

    /**
     * 获取堆栈中的最小元素
     *
     * @return 堆栈中的最小元素
     */
    public int getMin() {
        // 直接返回辅助栈栈顶元素（即当前最小值）
        return minStack.peek();
    }

    /**
     * 方法2：使用单个栈和一个变量实现
     *
     * 算法思路：
     * 使用一个变量存储最小值，栈中存储与最小值的差值
     */
    static class MinStackSingle {
        private Stack<Long> stack;
        private long min;

        public MinStackSingle() {
            stack = new Stack<>();
        }

        public void push(int val) {
            if (stack.isEmpty()) {
                stack.push(0L);
                min = val;
            } else {
                // 存储与当前最小值的差值
                stack.push((long)val - min);
                if (val < min) {
                    min = val;
                }
            }
        }

        public void pop() {
            if (stack.isEmpty()) return;

            long pop = stack.pop();
            if (pop < 0) {
                // 弹出的是最小值，需要恢复之前的最小值
                min = min - pop;
            }
        }

        public int top() {
            long top = stack.peek();
            if (top > 0) {
                return (int)(top + min);
            } else {
                return (int)min;
            }
        }

        public int getMin() {
            return (int)min;
        }
    }
}
