package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 跳跃游戏 II（LeetCode 45）
 *
 * 时间复杂度：O(n)
 * - n是数组长度
 * - 只需要遍历数组一次
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量
 */
public class Jump45 {

    /**
     * 主函数：处理用户输入并计算到达最后一个下标的最小跳跃次数
     *
     * 算法流程：
     * 1. 读取用户输入的跳跃数组
     * 2. 调用 [jump](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/Jump45.java#L97-L128)方法计算最小跳跃次数
     * 3. 输出结果
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入跳跃数组（以空格分隔）：");
        String line = scanner.nextLine();
        String[] strNums = line.split(" ");
        int n = strNums.length;
        int[] nums = new int[n];

        // 将输入的字符串转换为整数数组
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(strNums[i]);
        }

        int jumps = jump(nums);
        System.out.println("到达最后一个下标的最小跳跃次数：" + jumps);
    }

    /**
     * 计算到达最后一个下标的最小跳跃次数
     *
     * 算法思路：
     * 贪心算法，采用"跳跃区间"的思想
     * 维护三个变量：
     * 1. jumps：已经完成的跳跃次数
     * 2. currentEnd：当前这次跳跃能到达的最远位置
     * 3. farthest：在当前跳跃区间内，下一次跳跃能到达的最远位置
     *
     * 策略：在当前跳跃区间内，不断更新下一次跳跃能到达的最远位置
     * 当遍历完当前跳跃区间时，必须进行下一次跳跃，并更新相关变量
     *
     * 执行过程分析（以`nums=[2,3,1,1,4]`为例）：
     *
     * 初始状态：
     * jumps = 0
     * currentEnd = 0 （当前跳跃能到达的最远位置）
     * farthest = 0 （下一次跳跃能到达的最远位置）
     *
     * 遍历过程（注意：只需要遍历到n-2，因为题目保证能到达最后位置）：
     *
     * i=0, nums[0]=2:
     *   farthest = max(0, 0+2) = 2
     *   i=0 == currentEnd=0，需要跳跃
     *   jumps = 1
     *   currentEnd = farthest = 2
     *
     * i=1, nums[1]=3:
     *   farthest = max(2, 1+3) = 4
     *   i=1 < currentEnd=2，不需要跳跃
     *
     * i=2, nums[2]=1:
     *   farthest = max(4, 2+1) = 4
     *   i=2 == currentEnd=2，需要跳跃
     *   jumps = 2
     *   currentEnd = farthest = 4
     *
     * i=3, nums[3]=1:
     *   farthest = max(4, 3+1) = 4
     *   i=3 < currentEnd=4，不需要跳跃
     *
     * 循环结束（i=4=n-1，不进入循环）
     *
     * 最终结果：jumps = 2
     * 最优策略：[2] -> [3] -> [4]（下标0->1->4，跳跃2次）
     *
     * 时间复杂度分析：
     * - 遍历数组：O(n)
     * - 每次操作：O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param nums 跳跃数组，`nums[i]`表示在下标i处可以跳跃的最大长度
     * @return 到达最后一个下标的最小跳跃次数
     */
    public static int jump(int[] nums) {
        // 跳跃次数，初始为0
        int jumps = 0;

        // 当前这次跳跃能够达到的最远位置
        int currentEnd = 0;

        // 在当前跳跃区间内，下一次跳跃能到达的最远位置
        int farthest = 0;

        int n = nums.length;

        // 遍历数组，注意只需要遍历到n-2
        // 因为题目假设总是能到达最后位置，不需要检查最后一个元素
        for (int i = 0; i < n - 1; i++) {
            // 更新在当前跳跃区间内，下一次跳跃能到达的最远位置
            // 从位置i最远可以跳到 i + nums[i]
            farthest = Math.max(farthest, i + nums[i]);

            // 如果到达当前跳跃的结束位置，说明必须进行下一次跳跃了
            // 这是贪心策略的关键：在必须跳跃时，选择能跳得最远的方案
            if (i == currentEnd) {
                jumps++; // 跳跃次数加1
                currentEnd = farthest; // 更新当前跳跃能够达到的最远位置
            }
        }

        return jumps;
    }

    /**
     * 方法2：动态规划解法
     *
     * 算法思路：
     * `dp[i]`表示到达位置i的最小跳跃次数
     *
     * 时间复杂度分析：
     * - 外层循环：O(n)
     * - 内层循环：O(nums[i])
     * - 最坏情况总时间复杂度：O(n²)
     *
     * 空间复杂度分析：
     * - dp数组：O(n)
     *
     * @param nums 跳跃数组
     * @return 到达最后一个下标的最小跳跃次数
     */
    public int jumpDP(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;

        int[] dp = new int[nums.length];
        Arrays.fill(dp, nums.length); // 初始化为一个大值
        dp[0] = 0; // 起始位置需要0次跳跃

        for (int i = 0; i < nums.length; i++) {
            // 从位置i可以跳到的所有位置
            for (int j = 1; j <= nums[i] && i + j < nums.length; j++) {
                dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
            }
        }

        return dp[nums.length - 1];
    }

    /**
     * 方法3：BFS解法
     *
     * 算法思路：
     * 将问题看作图论中的最短路径问题
     * 每个位置是一个节点，能跳跃的关系是边
     * 使用BFS找到从位置0到位置n-1的最短路径
     *
     * 时间复杂度分析：
     * - BFS遍历：O(n²)（最坏情况）
     * - 队列操作：O(1)
     * - 总时间复杂度：O(n²)
     *
     * 空间复杂度分析：
     * - 队列存储空间：O(n)
     * - visited数组：O(n)
     * - 总空间复杂度：O(n)
     *
     * @param nums 跳跃数组
     * @return 到达最后一个下标的最小跳跃次数
     */
    public int jumpBFS(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;

        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        boolean[] visited = new boolean[nums.length];

        queue.offer(0);
        visited[0] = true;

        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;

            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                // 从当前位置可以跳到的所有位置
                for (int j = 1; j <= nums[current] && current + j < nums.length; j++) {
                    int next = current + j;

                    if (next == nums.length - 1) {
                        return level;
                    }

                    if (!visited[next]) {
                        queue.offer(next);
                        visited[next] = true;
                    }
                }
            }
        }

        return level;
    }
}
