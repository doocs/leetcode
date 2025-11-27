package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 数组中的第K个最大元素（LeetCode 215）
 *
 * 时间复杂度：
 * - 方法1（排序）：O(n log n)
 * - 方法2（堆）：O(n log k)
 * - 方法3（快速选择）：O(n) 平均情况，O(n²) 最坏情况
 *
 * 空间复杂度：
 * - 方法1（排序）：O(1)
 * - 方法2（堆）：O(k)
 * - 方法3（快速选择）：O(log n) 平均情况，O(n) 最坏情况
 */
public class FindKLargest215 {

    /**
     * 方法1：排序解法
     *
     * 算法思路：
     * 对数组进行排序，然后直接返回第k个最大元素
     *
     * 时间复杂度分析：
     * - 排序操作：O(n log n)
     * - 访问元素：O(1)
     * - 总时间复杂度：O(n log n)
     *
     * 空间复杂度分析：
     * - 排序算法所需空间：O(1)（原地排序）
     *
     * @param nums 整数数组
     * @param k 第k个最大元素
     * @return 第k个最大元素
     */
    public int findKthLargestSort(int[] nums, int k) {
        java.util.Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 方法2：最小堆解法
     *
     * 算法思路：
     * 维护一个大小为k的最小堆
     * 1. 遍历数组，将元素加入堆中
     * 2. 如果堆大小超过k，弹出最小元素
     * 3. 最终堆顶就是第k个最大元素
     *
     * 执行过程分析（以`nums=[3,2,1,5,6,4]`, `k=2`为例）：
     *
     * 初始状态：
     * heap = []
     *
     * 遍历过程：
     * num=3: heap=[3]
     * num=2: heap=[2,3]
     * num=1: heap=[1,2,3]，大小超过k，弹出1，heap=[2,3]
     * num=5: heap=[2,3,5]，大小超过k，弹出2，heap=[3,5]
     * num=6: heap=[3,5,6]，大小超过k，弹出3，heap=[5,6]
     * num=4: heap=[4,5,6]，大小超过k，弹出4，heap=[5,6]
     *
     * 最终结果：堆顶元素5即为第2个最大元素
     *
     * 时间复杂度分析：
     * - 遍历数组：O(n)
     * - 堆操作：每次O(log k)
     * - 总时间复杂度：O(n log k)
     *
     * 空间复杂度分析：
     * - 最小堆存储空间：O(k)
     *
     * @param nums 整数数组
     * @param k 第k个最大元素
     * @return 第k个最大元素
     */
    public int findKthLargestHeap(int[] nums, int k) {
        // 创建最小堆，堆顶是最小元素
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // 遍历数组
        for (int num : nums) {
            // 将元素加入堆中
            minHeap.offer(num);

            // 如果堆大小超过k，弹出最小元素
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // 返回堆顶元素（第k个最大元素）
        return minHeap.peek();
    }

    /**
     * 方法3：快速选择算法（最优解）
     *
     * 算法思路：
     * 基于快速排序的分区思想
     * 1. 随机选择一个基准元素
     * 2. 进行分区操作，将数组分为大于基准和小于基准的两部分
     * 3. 根据基准元素的位置决定在哪个部分继续查找
     *
     * 执行过程分析（以`nums=[3,2,1,5,6,4]`, `k=2`为例）：
     *
     * 目标：找到第2个最大元素（即第5大的元素）
     * 转换：在0索引系统中，目标索引 = `nums.length` - k = 6 - 2 = 4
     *
     * 第一次分区：
     * 选择基准元素`pivot=4`（随机选择）
     * 分区后：[3,2,1,4,6,5]，基准元素4在索引3位置
     * 3 < 4，说明第4大的元素在右半部分[6,5]
     *
     * 第二次分区：
     * 在[6,5]中选择基准元素`pivot=5`
     * 分区后：[5,6]，基准元素5在索引4位置
     * 4 == 4，找到目标元素5
     *
     * 时间复杂度分析：
     * - 平均情况：O(n)
     * - 最坏情况：O(n²)
     * - 分区操作：O(n)
     *
     * 空间复杂度分析：
     * - 平均情况：O(log n)（递归调用栈）
     * - 最坏情况：O(n)（递归调用栈）
     *
     * @param nums 整数数组
     * @param k 第k个最大元素
     * @return 第k个最大元素
     */
    public int findKthLargest(int[] nums, int k) {
        // 目标索引（转换为0索引系统）
        int targetIndex = nums.length - k;

        // 使用快速选择算法
        return quickSelect(nums, 0, nums.length - 1, targetIndex);
    }

    /**
     * 快速选择辅助方法
     *
     * 算法思路：
     * 实现快速选择算法的核心逻辑，通过递归方式查找第k个元素
     *
     * @param nums 数组
     * @param left 左边界
     * @param right 右边界
     * @param k 目标索引
     * @return 第k个元素
     */
    private int quickSelect(int[] nums, int left, int right, int k) {
        // 随机选择基准元素以避免最坏情况
        Random random = new Random();
        int pivotIndex = left + random.nextInt(right - left + 1);

        // 进行分区操作
        pivotIndex = partition(nums, left, right, pivotIndex);

        // 根据基准元素位置决定下一步操作
        if (pivotIndex == k) {
            // 找到目标元素
            return nums[pivotIndex];
        } else if (pivotIndex < k) {
            // 目标在右半部分
            return quickSelect(nums, pivotIndex + 1, right, k);
        } else {
            // 目标在左半部分
            return quickSelect(nums, left, pivotIndex - 1, k);
        }
    }

    /**
     * 分区辅助方法
     *
     * 算法思路：
     * 实现快速排序中的分区操作，将数组分为小于基准和大于基准的两部分
     *
     * @param nums 数组
     * @param left 左边界
     * @param right 右边界
     * @param pivotIndex 基准元素索引
     * @return 分区后基准元素的最终位置
     */
    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];

        // 将基准元素移到末尾
        swap(nums, pivotIndex, right);

        int storeIndex = left;

        // 将小于基准的元素移到左边
        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }

        // 将基准元素放到正确位置
        swap(nums, storeIndex, right);

        return storeIndex;
    }

    /**
     * 交换数组中两个元素
     *
     * 算法思路：
     * 交换数组中指定位置的两个元素
     *
     * @param nums 数组
     * @param i 第一个元素索引
     * @param j 第二个元素索引
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
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
     * @return 用户输入的整数数组
     */
    public static int[] readArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入整数数组（用空格分隔）：");
        String input = scanner.nextLine();
        String[] strArray = input.split(" ");

        int[] nums = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            nums[i] = Integer.parseInt(strArray[i]);
        }

        return nums;
    }

    /**
     * 主函数：处理用户输入并找出第K个最大元素
     */
    public static void main(String[] args) {
        System.out.println("数组中的第K个最大元素");

        // 读取用户输入的数组
        int[] nums = readArray();
        System.out.println("输入数组: " + java.util.Arrays.toString(nums));

        // 读取k值
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入k值: ");
        int k = scanner.nextInt();

        // 计算第k个最大元素
        FindKLargest215 solution = new FindKLargest215();
        int result1 = solution.findKthLargestSort(nums, k);  // 修正：添加k参数
        int result2 = solution.findKthLargestHeap(nums, k);
        int result3 = solution.findKthLargest(nums, k);

        // 输出结果
        System.out.println("排序方法结果: " + result1);
        System.out.println("堆方法结果: " + result2);
        System.out.println("快速选择方法结果: " + result3);
    }
}
