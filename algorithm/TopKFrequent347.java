package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 前K个高频元素（LeetCode 347）
 *
 * 时间复杂度：
 * - 方法1（堆）：O(n log k)
 *   统计频率O(n)，维护大小为k的堆O(n log k)
 * - 方法2（桶排序）：O(n)
 *   统计频率O(n)，桶排序O(n)
 *
 * 空间复杂度：O(n + k)
 * - 哈希表存储频率O(n)
 * - 堆或桶排序空间O(n + k)
 */
public class TopKFrequent347 {

    /**
     * 方法1：最小堆解法
     *
     * 算法思路：
     * 1. 使用哈希表统计每个元素的频率
     * 2. 使用大小为k的最小堆维护前k个高频元素
     * 3. 遍历频率表，维护堆的大小为k
     * 4. 堆中元素即为前k个高频元素
     *
     * 执行过程分析（以`nums=[1,1,1,2,2,3]`, `k=2`为例）：
     *
     * 第一步：统计频率
     * `frequencyMap` = {1:3, 2:2, 3:1}
     *
     * 第二步：维护大小为k的最小堆
     * 处理元素1，频率3：堆=[(1,3)]
     * 处理元素2，频率2：堆=[(2,2), (1,3)]
     * 处理元素3，频率1：堆=[(3,1), (2,2), (1,3)]，大小超过k，弹出(3,1)
     * 最终堆=[(2,2), (1,3)]
     *
     * 第三步：提取结果
     * 结果=[1,2]（频率分别为3和2）
     *
     * 时间复杂度分析：
     * - 统计频率：O(n)
     * - 维护堆：O(n log k)
     * - 提取结果：O(k log k)
     * - 总时间复杂度：O(n log k)
     *
     * 空间复杂度分析：
     * - frequencyMap：O(n)
     * - minHeap：O(k)
     * - result：O(k)
     * - 总空间复杂度：O(n + k)
     *
     * @param nums 整数数组
     * @param k 前k个高频元素
     * @return 前k个高频元素数组
     */
    public int[] topKFrequentHeap(int[] nums, int k) {
        // 统计每个元素的频率
        // Map<Integer, Integer> frequencyMap = new HashMap<>() 创建频率映射
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        // for (int num : nums) 遍历数组统计频率
        for (int num : nums) {
            // frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1) 更新元素频率
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // 创建最小堆，按照频率排序
        // 堆中存储<integer, frequency>对
        // PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue()) 创建最小堆
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
            new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        // 遍历频率表，维护大小为k的堆
        // for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) 遍历频率映射
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            // minHeap.offer(entry) 将元素加入堆
            minHeap.offer(entry);

            // 如果堆大小超过k，弹出频率最小的元素
            // if (minHeap.size() > k) 检查堆大小是否超过k
            if (minHeap.size() > k) {
                // minHeap.poll() 弹出堆顶元素
                minHeap.poll();
            }
        }

        // 从堆中提取结果
        // int[] result = new int[k] 创建结果数组
        int[] result = new int[k];
        // for (int i = 0; i < k; i++) 提取前k个元素
        for (int i = 0; i < k; i++) {
            // result[i] = minHeap.poll().getKey() 从堆中取出元素
            result[i] = minHeap.poll().getKey();
        }

        // return result 返回结果
        return result;
    }

    /**
     * 方法2：桶排序解法（更优解）
     *
     * 算法思路：
     * 1. 使用哈希表统计每个元素的频率
     * 2. 创建桶数组，索引表示频率，值为具有该频率的元素列表
     * 3. 从高频率到低频率遍历桶，收集前k个元素
     *
     * 执行过程分析（以`nums=[1,1,1,2,2,3]`, `k=2`为例）：
     *
     * 第一步：统计频率
     * `frequencyMap` = {1:3, 2:2, 3:1}
     *
     * 第二步：创建桶数组（大小为`nums.length+1=7`）
     * buckets[0] = []
     * buckets[1] = [3]
     * buckets[2] = [2]
     * buckets[3] = [1]
     * buckets[4] = []
     * buckets[5] = []
     * buckets[6] = []
     *
     * 第三步：从高频率到低频率收集元素
     * 从`buckets[6]`到`buckets[1]`遍历：
     * buckets[3] = [1]，收集1
     * buckets[2] = [2]，收集2
     * 已收集2个元素，满足`k=2`
     *
     * 最终结果：[1,2]
     *
     * 时间复杂度分析：
     * - 统计频率：O(n)
     * - 初始化桶：O(n)
     * - 放入桶：O(n)
     * - 收集结果：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - frequencyMap：O(n)
     * - buckets：O(n)
     * - result：O(k)
     * - 总空间复杂度：O(n + k)
     *
     * @param nums 整数数组
     * @param k 前k个高频元素
     * @return 前k个高频元素数组
     */
    public int[] topKFrequentBucketSort(int[] nums, int k) {
        // 统计每个元素的频率
        // Map<Integer, Integer> frequencyMap = new HashMap<>() 创建频率映射
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        // for (int num : nums) 遍历数组统计频率
        for (int num : nums) {
            // frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1) 更新元素频率
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // 创建桶数组，索引表示频率
        // 桶的数量为`nums.length+1`，因为频率最大为`nums.length`
        // List<Integer>[] buckets = new List[nums.length + 1] 创建桶数组
        List<Integer>[] buckets = new List[nums.length + 1];

        // 初始化桶
        // for (int i = 0; i <= nums.length; i++) 初始化每个桶
        for (int i = 0; i <= nums.length; i++) {
            // buckets[i] = new ArrayList<>() 创建空列表
            buckets[i] = new ArrayList<>();
        }

        // 将元素放入对应频率的桶中
        // for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) 遍历频率映射
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            // int frequency = entry.getValue() 获取频率
            int frequency = entry.getValue();
            // int num = entry.getKey() 获取元素
            int num = entry.getKey();
            // buckets[frequency].add(num) 将元素加入对应频率的桶
            buckets[frequency].add(num);
        }

        // 从高频率到低频率收集前k个元素
        // List<Integer> result = new ArrayList<>() 创建结果列表
        List<Integer> result = new ArrayList<>();
        // for (int i = buckets.length - 1; i >= 0 && result.size() < k; i--) 从高频率到低频率遍历桶
        for (int i = buckets.length - 1; i >= 0 && result.size() < k; i--) {
            // result.addAll(buckets[i]) 将桶中所有元素加入结果
            result.addAll(buckets[i]);
        }

        // 转换为数组并返回前k个元素
        // return result.stream().mapToInt(i -> i).toArray() 转换为int数组
        return result.stream().mapToInt(i -> i).toArray();
    }

    /**
     * 方法3：快速选择解法
     *
     * 算法思路：
     * 1. 使用哈希表统计频率
     * 2. 将频率对转换为数组
     * 3. 使用快速选择算法找到第k大的频率
     * 4. 收集频率大于等于第k大频率的所有元素
     *
     * 时间复杂度分析：
     * - 统计频率：O(n)
     * - 转换数组：O(n)
     * - 快速选择：平均O(n)，最坏O(n²)
     * - 收集结果：O(n)
     * - 总时间复杂度：平均O(n)，最坏O(n²)
     *
     * 空间复杂度分析：
     * - frequencyMap：O(n)
     * - entries：O(n)
     * - result：O(k)
     * - 总空间复杂度：O(n + k)
     *
     * @param nums 整数数组
     * @param k 前k个高频元素
     * @return 前k个高频元素数组
     */
    public int[] topKFrequentQuickSelect(int[] nums, int k) {
        // 统计频率
        // Map<Integer, Integer> frequencyMap = new HashMap<>() 创建频率映射
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        // for (int num : nums) 遍历数组统计频率
        for (int num : nums) {
            // frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1) 更新元素频率
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // 转换为数组
        // List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(frequencyMap.entrySet()) 转换为列表
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());

        // 使用快速选择找到第k大的频率
        // int threshold = quickSelect(entries, 0, entries.size() - 1, k) 找到第k大的频率
        int threshold = quickSelect(entries, 0, entries.size() - 1, k);

        // 收集频率大于等于阈值的元素
        // List<Integer> result = new ArrayList<>() 创建结果列表
        List<Integer> result = new ArrayList<>();
        // for (Map.Entry<Integer, Integer> entry : entries) 遍历所有元素
        for (Map.Entry<Integer, Integer> entry : entries) {
            // if (entry.getValue() >= threshold) 检查频率是否大于等于阈值
            if (entry.getValue() >= threshold) {
                // result.add(entry.getKey()) 将元素加入结果
                result.add(entry.getKey());
            }
        }

        // return result.stream().mapToInt(i -> i).toArray() 转换为int数组
        return result.stream().mapToInt(i -> i).toArray();
    }

    /**
     * 快速选择辅助方法
     *
     * 时间复杂度分析：
     * - 平均情况：O(n)
     * - 最坏情况：O(n²)
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(log n)
     */
    private int quickSelect(List<Map.Entry<Integer, Integer>> entries, int left, int right, int k) {
        // if (left == right) 递归终止条件
        if (left == right) return entries.get(left).getValue();

        // java.util.Random random = new java.util.Random() 创建随机数生成器
        java.util.Random random = new java.util.Random();
        // int pivotIndex = left + random.nextInt(right - left + 1) 随机选择基准点
        int pivotIndex = left + random.nextInt(right - left + 1);
        // pivotIndex = partition(entries, left, right, pivotIndex) 分区操作
        pivotIndex = partition(entries, left, right, pivotIndex);

        // if (pivotIndex == k - 1) 检查是否找到第k大元素
        if (pivotIndex == k - 1) {
            // return entries.get(pivotIndex).getValue() 返回第k大频率
            return entries.get(pivotIndex).getValue();
        } else if (pivotIndex < k - 1) {
            // return quickSelect(entries, pivotIndex + 1, right, k) 在右半部分查找
            return quickSelect(entries, pivotIndex + 1, right, k);
        } else {
            // return quickSelect(entries, left, pivotIndex - 1, k) 在左半部分查找
            return quickSelect(entries, left, pivotIndex - 1, k);
        }
    }

    /**
     * 分区辅助方法
     *
     * 时间复杂度分析：
     * - O(n)
     */
    private int partition(List<Map.Entry<Integer, Integer>> entries, int left, int right, int pivotIndex) {
        // int pivotValue = entries.get(pivotIndex).getValue() 获取基准值
        int pivotValue = entries.get(pivotIndex).getValue();
        // Collections.swap(entries, pivotIndex, right) 将基准元素移到末尾
        Collections.swap(entries, pivotIndex, right);

        // int storeIndex = left 初始化存储索引
        int storeIndex = left;
        // for (int i = left; i < right; i++) 遍历元素
        for (int i = left; i < right; i++) {
            // if (entries.get(i).getValue() > pivotValue) 比较元素与基准值
            if (entries.get(i).getValue() > pivotValue) {
                // Collections.swap(entries, storeIndex, i) 交换元素
                Collections.swap(entries, storeIndex, i);
                // storeIndex++ 更新存储索引
                storeIndex++;
            }
        }

        // Collections.swap(entries, storeIndex, right) 将基准元素放到正确位置
        Collections.swap(entries, storeIndex, right);
        // return storeIndex 返回基准元素最终位置
        return storeIndex;
    }

    /**
     * 辅助方法：读取用户输入的数组
     *
     * 时间复杂度分析：
     * - O(n)
     *
     * 空间复杂度分析：
     * - O(n)
     *
     * @return 用户输入的整数数组
     */
    public static int[] readArray() {
        // Scanner scanner = new Scanner(System.in) 创建Scanner对象
        Scanner scanner = new Scanner(System.in);
        // System.out.println("请输入整数数组（用空格分隔）：") 打印提示信息
        System.out.println("请输入整数数组（用空格分隔）：");
        // String input = scanner.nextLine() 读取输入
        String input = scanner.nextLine();
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
     * 主函数：处理用户输入并找出前K个高频元素
     */
    public static void main(String[] args) {
        // System.out.println("前K个高频元素") 打印标题
        System.out.println("前K个高频元素");

        // 读取用户输入的数组
        // int[] nums = readArray() 读取数组
        int[] nums = readArray();
        // System.out.println("输入数组: " + java.util.Arrays.toString(nums)) 打印数组
        System.out.println("输入数组: " + java.util.Arrays.toString(nums));

        // 读取k值
        // Scanner scanner = new Scanner(System.in) 创建Scanner对象
        Scanner scanner = new Scanner(System.in);
        // System.out.print("请输入k值: ") 打印提示信息
        System.out.print("请输入k值: ");
        // int k = scanner.nextInt() 读取k值
        int k = scanner.nextInt();

        // 计算前k个高频元素
        // TopKFrequent347 solution = new TopKFrequent347() 创建解决方案对象
        TopKFrequent347 solution = new TopKFrequent347();
        // int[] result1 = solution.topKFrequentHeap(nums, k) 调用堆方法
        int[] result1 = solution.topKFrequentHeap(nums, k);
        // int[] result2 = solution.topKFrequentBucketSort(nums, k) 调用桶排序方法
        int[] result2 = solution.topKFrequentBucketSort(nums, k);

        // 输出结果
        // System.out.println("堆方法结果: " + java.util.Arrays.toString(result1)) 打印堆方法结果
        System.out.println("堆方法结果: " + java.util.Arrays.toString(result1));
        // System.out.println("桶排序方法结果: " + java.util.Arrays.toString(result2)) 打印桶排序方法结果
        System.out.println("桶排序方法结果: " + java.util.Arrays.toString(result2));
    }
}
