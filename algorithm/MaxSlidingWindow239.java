package com.funian.algorithm.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 滑动窗口最大值（LeetCode 239）
 *
 * 时间复杂度：O(n)
 * - 每个元素最多被加入和移出双端队列各一次
 * - 虽然有内层while循环，但整体上每个元素的操作是常数时间
 *
 * 空间复杂度：O(k)
 * - 双端队列中最多存储k个元素的索引
 * - 结果数组空间不计入，因为它是输出的一部分
 */
public class MaxSlidingWindow239 {
    public static void main(String[] args) {
        // 读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入整数数组
        System.out.print("请输入整数数组 nums（以空格分隔）：");

        // 读取输入并按空格分割
        String[] input = scanner.nextLine().split(" ");

        // 创建整型数组
        int[] nums = new int[input.length];

        // 将字符串转换为整数
        for (int i = 0; i < input.length; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }

        // 提示用户输入滑动窗口大小
        System.out.print("请输入滑动窗口大小 k：");

        // 读取窗口大小
        int k = scanner.nextInt();

        // 调用 maxSlidingWindow 方法计算每个滑动窗口的最大值
        int[] result = maxSlidingWindow(nums, k);

        // 输出结果
        System.out.print("滑动窗口中的最大值为：");
        for (int val : result) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    /**
     * 计算滑动窗口中的最大值
     * 使用双端队列维护一个单调递减的队列
     *
     * 算法思路：
     * 1. 使用双端队列存储数组元素的索引
     * 2. 队列中索引对应的元素值保持单调递减顺序
     * 3. 队列头部始终是当前窗口的最大值索引
     * 4. 遍历数组时维护队列的性质
     *
     * 示例过程（以数组 [1,3,-1,-3,5,3,6,7], k=3 为例）：
     *
     * 窗口位置        队列状态(存储索引)    队列元素值    最大值
     * [1  3 -1] -3 5 3 6 7  [1,2]         [3,-1]       3
     *  1 [3 -1 -3] 5 3 6 7  [1,2,3]       [3,-1,-3]    3
     *  1  3 [-1 -3 5] 3 6 7  [4]           [5]          5
     *  1  3 -1 [-3 5 3] 6 7  [4,5]         [5,3]        5
     *  1  3 -1 -3 [5 3 6] 7  [6]           [6]          6
     *  1  3 -1 -3 5 [3 6 7]  [7]           [7]          7
     *
     * 结果：[3,3,5,5,6,7]
     *
     * 时间复杂度分析：
     * - 每个元素最多入队和出队一次：O(n)，其中n为输入数组`nums`的长度
     * - 内层while循环整体上每个元素最多被处理一次：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 双端队列最多存储k个元素索引：O(k)，k为滑动窗口大小
     * - 结果数组：O(n-k+1)
     * - 总空间复杂度：O(k)
     *
     * @param nums 整数数组
     * @param k 滑动窗口大小
     * @return 每个滑动窗口中的最大值数组
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        // 边界条件检查
        if (nums == null || nums.length == 0 || k == 0) return new int[0];

        // 获取数组长度
        int n = nums.length;

        // 创建结果数组，长度为 n - k + 1
        int[] result = new int[n - k + 1];

        // 使用双端队列存储数组元素的索引
        Deque<Integer> deque = new ArrayDeque<>();

        // 遍历数组
        for (int i = 0; i < n; i++) {
            // 移除滑动窗口外的元素索引
            // 如果队列头部索引超出当前窗口范围，则移除
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // 保持队列递减顺序
            // 移除所有小于当前元素的队列尾部元素
            // 这样保证队列头部始终是当前窗口的最大值索引
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // 添加当前元素下标到队列尾部
            deque.offerLast(i);

            // 当窗口形成时（i >= k - 1），记录当前窗口的最大值
            if (i >= k - 1) {
                // 队列头部索引对应的元素就是当前窗口的最大值
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        // 返回每个滑动窗口的最大值数组
        return result;
    }

    /**
     * 方法2：暴力解法（仅供学习对比，效率较低）
     *
     * 算法思路：
     * 对每个窗口遍历所有元素找出最大值
     *
     * 示例过程（以数组 [1,3,-1,-3,5,3,6,7], k=3 为例）：
     *
     * 窗口[1,3,-1]: max(1,3,-1) = 3
     * 窗口[3,-1,-3]: max(3,-1,-3) = 3
     * 窗口[-1,-3,5]: max(-1,-3,5) = 5
     * 窗口[-3,5,3]: max(-3,5,3) = 5
     * 窗口[5,3,6]: max(5,3,6) = 6
     * 窗口[3,6,7]: max(3,6,7) = 7
     *
     * 结果：[3,3,5,5,6,7]
     *
     * 时间复杂度分析：
     * - 外层循环：O(n-k+1)，n为数组长度
     * - 内层循环：O(k)，k为窗口大小
     * - 总时间复杂度：O(n*k)
     *
     * 空间复杂度分析：
     * - 只使用了几个变量：O(1)
     * - 结果数组：O(n-k+1)
     * - 总空间复杂度：O(n)
     *
     * @param nums 整数数组
     * @param k 滑动窗口大小
     * @return 每个滑动窗口中的最大值数组
     */
    public static int[] maxSlidingWindowBruteForce(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return new int[0];

        int n = nums.length;
        int[] result = new int[n - k + 1];

        // 对每个窗口计算最大值
        for (int i = 0; i <= n - k; i++) {
            int max = nums[i];
            for (int j = 1; j < k; j++) {
                if (nums[i + j] > max) {
                    max = nums[i + j];
                }
            }
            result[i] = max;
        }

        return result;
    }

    /**
     * 方法3：使用优先队列（堆）实现
     *
     * 算法思路：
     * 使用最大堆存储元素值和索引，动态维护窗口内元素
     *
     * 示例过程（以数组 [1,3,-1,-3,5,3,6,7], k=3 为例）：
     *
     * 1. 初始化堆，加入前3个元素: [(3,1), (1,0), (-1,2)]
     *    result[0] = 3
     *
     * 2. 处理第4个元素-3:
     *    加入堆: [(3,1), (1,0), (-1,2), (-3,3)]
     *    移除过期元素(无): [(3,1), (1,0), (-1,2), (-3,3)]
     *    result[1] = 3
     *
     * 3. 处理第5个元素5:
     *    加入堆: [(5,4), (3,1), (1,0), (-1,2), (-3,3)]
     *    移除过期元素(-1,2), (3,1), (1,0): [(5,4), (-3,3)]
     *    result[2] = 5
     *
     * ...继续处理直到结束
     *
     * 时间复杂度分析：
     * - 遍历数组：O(n)，n为数组长度
     * - 堆操作：O(log k)，每次插入和删除操作
     * - 总时间复杂度：O(n*log k)
     *
     * 空间复杂度分析：
     * - 优先队列：O(k)，最多存储k个元素
     * - 结果数组：O(n-k+1)
     * - 总空间复杂度：O(k)
     *
     * @param nums 整数数组
     * @param k 滑动窗口大小
     * @return 每个滑动窗口中的最大值数组
     */
    public static int[] maxSlidingWindowHeap(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return new int[0];

        int n = nums.length;
        int[] result = new int[n - k + 1];

        // 使用优先队列存储<integer, index>对，按值降序排列
        java.util.PriorityQueue<int[]> pq = new java.util.PriorityQueue<>(
            (a, b) -> b[0] - a[0]
        );

        // 初始化前k个元素
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i], i});
        }
        result[0] = pq.peek()[0];

        // 处理后续元素
        for (int i = k; i < n; i++) {
            pq.offer(new int[]{nums[i], i});

            // 移除窗口外的元素
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }

            result[i - k + 1] = pq.peek()[0];
        }

        return result;
    }
}
