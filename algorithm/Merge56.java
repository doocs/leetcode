package com.funian.algorithm.algorithm;

import java.util.*;

/**
 * 合并区间（LeetCode 56）
 *
 * 时间复杂度：O(n log n)
 * - 排序需要 O(n log n) 时间
 * - 遍历数组需要 O(n) 时间
 * - 总时间复杂度为 O(n log n)
 *
 * 空间复杂度：O(n)
 * - 存储合并结果的列表需要 O(n) 空间
 * - 排序可能需要 O(log n) 的递归栈空间
 */
public class Merge56 {
    /**
     * 合并重叠区间
     *
     * 算法思路：
     * 1. 首先按照区间的起始位置对所有区间进行排序
     * 2. 遍历排序后的区间，逐个检查是否与前一个区间重叠
     * 3. 如果重叠则合并，否则将当前区间添加到结果中
     *
     * 重叠判断条件：当前区间起始位置 <= 前一个区间结束位置
     * 合并方法：新区间的结束位置为两个区间结束位置的最大值
     *
     * 示例过程（以 intervals=[[1,3],[2,6],[8,10],[15,18]] 为例）：
     *
     * 排序后: [[1,3],[2,6],[8,10],[15,18]]
     *
     * 步骤1: merged = [[1,3]]
     * 步骤2: 检查[2,6]，2 <= 3，重叠，合并为[1,6]，merged = [[1,6]]
     * 步骤3: 检查[8,10]，8 > 6，不重叠，添加到结果，merged = [[1,6],[8,10]]
     * 步骤4: 检查[15,18]，15 > 10，不重叠，添加到结果，merged = [[1,6],[8,10],[15,18]]
     *
     * 结果：[[1,6],[8,10],[15,18]]
     *
     * 时间复杂度分析：
     * - 排序：O(n log n)，其中n为输入区间数组`intervals`的长度
     * - 遍历：O(n)
     * - 总时间复杂度：O(n log n)
     *
     * 空间复杂度分析：
     * - 结果列表：O(n)
     * - 排序递归栈：O(log n)
     * - 总空间复杂度：O(n)
     *
     * @param intervals 输入的区间数组
     * @return 合并后的不重叠区间数组
     */
public int[][] merge(int[][] intervals) {
        // 如果输入为空，直接返回空数组
        if (intervals.length == 0) return new int[0][0];

        // 按照每个区间的起始值进行排序
        Arrays.sort(intervals);

        // 存储合并后的区间
        List<int[]> merged = new ArrayList<>();

        // 将第一个区间添加到合并列表中
        merged.add(intervals[0]);

        // 从第二个区间开始遍历
        for (int i = 1; i < intervals.length; i++) {
            // 获取当前区间
            int[] current = intervals[i];

            // 获取已合并区间中的最后一个区间
            int[] lastMerged = merged.get(merged.size() - 1);

            // 检查当前区间是否与最后一个合并区间重叠
            if (current[0] <= lastMerged[1]) {
                // 如果重叠，合并区间
                lastMerged[1] = Math.max(lastMerged[1], current[1]);
            } else {
                // 如果不重叠，直接添加当前区间到合并列表
                merged.add(current);
            }
        }

        // 将 List 转换为二维数组并返回
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        // 创建 Scanner 对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 输入区间数
        System.out.print("请输入区间的数量: ");

        // 读取区间数量
        int n = Integer.parseInt(scanner.nextLine());

        // 创建二维数组存储区间
        int[][] intervals = new int[n][2];

        // 输入区间
        System.out.println("请输入区间 (每个区间用空格分隔，格式为 start end):");

        // 读取每个区间
        for (int i = 0; i < n; i++) {
            // 按空格分割输入字符串
            String[] strArray = scanner.nextLine().split(" ");

            // 解析起始位置
            intervals[i][0] = Integer.parseInt(strArray[0]);

            // 解析结束位置
            intervals[i][1] = Integer.parseInt(strArray[1]);
        }

        // 创建 Merge56 实例并合并区间
        Merge56 solution = new Merge56();

        // 调用 merge 方法合并区间
        int[][] mergedIntervals = solution.merge(intervals);

        // 输出结果
        System.out.println("合并后的区间:");

        // 遍历并打印合并后的区间
        for (int[] interval : mergedIntervals) {
            System.out.println(Arrays.toString(interval));
        }
    }


    /**
     * 方法2：不修改原数组的版本
     *
     * 算法思路：
     * 1. 创建原数组的副本以避免修改原数组
     * 2. 对副本进行排序和合并操作
     *
     * 示例过程（以 intervals=[[1,3],[2,6],[8,10],[15,18]] 为例）：
     *
     * 1. 创建副本: intervalList = [[1,3],[2,6],[8,10],[15,18]]
     * 2. 排序: intervalList = [[1,3],[2,6],[8,10],[15,18]] (已有序)
     * 3. 合并过程:
     *    merged = [[1,3]]
     *    处理[2,6]: 2 <= 3, 重叠, 合并为[1,6]
     *    处理[8,10]: 8 > 6, 不重叠, 添加到结果
     *    处理[15,18]: 15 > 10, 不重叠, 添加到结果
     * 4. 最终结果: [[1,6],[8,10],[15,18]]
     *
     * 时间复杂度分析：
     * - 创建副本：O(n)，其中n为输入区间数组`intervals`的长度
     * - 排序：O(n log n)
     * - 合并：O(n)
     * - 总时间复杂度：O(n log n)
     *
     * 空间复杂度分析：
     * - 副本列表：O(n)
     * - 结果列表：O(n)
     * - 排序递归栈：O(log n)
     * - 总空间复杂度：O(n)
     *
     * @param intervals 输入的区间数组
     * @return 合并后的不重叠区间数组
     */
    public int[][] mergeImmutable(int[][] intervals) {
        // 边界条件检查
        if (intervals.length == 0) return new int[0][0];

        // 创建区间列表的副本
        List<int[]> intervalList = new ArrayList<>();
        // 遍历原数组，为每个区间创建副本并添加到列表中
        for (int[] interval : intervals) {
            intervalList.add(new int[]{interval[0], interval[1]});
        }

        // 排序
        intervalList.sort((a, b) -> Integer.compare(a[0], b[0]));

        // 合并
        List<int[]> merged = new ArrayList<>();
        // 添加第一个区间到结果列表
        merged.add(intervalList.get(0));

        // 从第二个区间开始遍历
        for (int i = 1; i < intervalList.size(); i++) {
            // 获取当前区间
            int[] current = intervalList.get(i);
            // 获取结果列表中的最后一个区间
            int[] lastMerged = merged.get(merged.size() - 1);

            // 检查是否重叠
            if (current[0] <= lastMerged[1]) {
                // 重叠则合并，更新结束位置为较大值
                lastMerged[1] = Math.max(lastMerged[1], current[1]);
            } else {
                // 不重叠则直接添加
                merged.add(current);
            }
        }

        // 将List转换为二维数组并返回
        return merged.toArray(new int[merged.size()][]);
    }

    /**
     * 方法3：使用栈实现
     *
     * 算法思路：
     * 1. 排序区间数组
     * 2. 使用栈存储已处理的区间
     * 3. 对于每个新区间，检查是否与栈顶区间重叠
     * 4. 重叠则合并，不重叠则入栈
     *
     * 示例过程（以 intervals=[[1,3],[2,6],[8,10],[15,18]] 为例）：
     *
     * 1. 排序后: [[1,3],[2,6],[8,10],[15,18]]
     * 2. 栈操作过程:
     *    压入[1,3]: stack = [[1,3]]
     *    处理[2,6]: 2 <= 3, 重叠, 合并栈顶为[1,6]
     *    处理[8,10]: 8 > 6, 不重叠, 压入[8,10], stack = [[1,6],[8,10]]
     *    处理[15,18]: 15 > 10, 不重叠, 压入[15,18], stack = [[1,6],[8,10],[15,18]]
     * 3. 最终结果: [[1,6],[8,10],[15,18]]
     *
     * 时间复杂度分析：
     * - 排序：O(n log n)，其中n为输入区间数组`intervals`的长度
     * - 遍历处理：O(n)
     * - 总时间复杂度：O(n log n)
     *
     * 空间复杂度分析：
     * - 栈空间：O(n)
     * - 排序递归栈：O(log n)
     * - 总空间复杂度：O(n)
     *
     * @param intervals 输入的区间数组
     * @return 合并后的不重叠区间数组
     */
    public int[][] mergeStack(int[][] intervals) {
        // 边界条件检查
        if (intervals.length == 0) return new int[0][0];

        // 排序
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // 使用栈存储合并后的区间
        Stack<int[]> stack = new Stack<>();
        // 将第一个区间压入栈
        stack.push(intervals[0]);

        // 从第二个区间开始处理
        for (int i = 1; i < intervals.length; i++) {
            // 获取当前区间
            int[] current = intervals[i];
            // 获取栈顶区间（不弹出）
            int[] top = stack.peek();

            // 检查是否重叠
            if (current[0] <= top[1]) {
                // 重叠，合并区间
                top[1] = Math.max(top[1], current[1]);
            } else {
                // 不重叠，将当前区间压入栈
                stack.push(current);
            }
        }

        // 转换为数组
        return stack.toArray(new int[stack.size()][]);
    }

    /**
     * 方法4：一次遍历优化版本
     *
     * 算法思路：
     * 在遍历过程中直接判断是否需要合并，避免了获取最后一个元素的操作
     *
     * 示例过程（以 intervals=[[1,3],[2,6],[8,10],[15,18]] 为例）：
     *
     * 1. 排序后: [[1,3],[2,6],[8,10],[15,18]]
     * 2. 遍历处理:
     *    merged为空, 添加[1,3], merged = [[1,3]]
     *    处理[2,6]: 3 >= 2, 重叠, 合并为[1,6]
     *    处理[8,10]: 6 < 8, 不重叠, 添加[8,10], merged = [[1,6],[8,10]]
     *    处理[15,18]: 10 < 15, 不重叠, 添加[15,18], merged = [[1,6],[8,10],[15,18]]
     * 3. 最终结果: [[1,6],[8,10],[15,18]]
     *
     * 时间复杂度分析：
     * - 排序：O(n log n)，其中n为输入区间数组`intervals`的长度
     * - 遍历：O(n)
     * - 总时间复杂度：O(n log n)
     *
     * 空间复杂度分析：
     * - 结果列表：O(n)
     * - 排序递归栈：O(log n)
     * - 总空间复杂度：O(n)
     *
     * @param intervals 输入的区间数组
     * @return 合并后的不重叠区间数组
     */
    public int[][] mergeOptimized(int[][] intervals) {
        if (intervals.length == 0) return new int[0][0];

        // 排序
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // 使用数组列表存储结果
        List<int[]> merged = new ArrayList<>();

        // 遍历所有区间
        for (int[] interval : intervals) {
            // 如果结果列表为空或当前区间与上一个区间不重叠
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                // 直接添加当前区间
                merged.add(interval);
            } else {
                // 否则合并区间，更新上一个区间的结束位置
                merged.get(merged.size() - 1)[1] = Math.max(
                    merged.get(merged.size() - 1)[1], interval[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
}
