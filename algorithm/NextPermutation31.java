package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 下一个排列（LeetCode 31）
 *
 * 时间复杂度：O(n)
 * - 需要遍历数组常数次
 * - 最坏情况下需要遍历整个数组
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量
 * - 原地修改数组
 */
public class NextPermutation31 {

    /**
     * 主函数：处理用户输入并计算下一个排列
     *
     * 算法流程：
     * 1. 读取用户输入的整数数组
     * 2. 调用[nextPermutation](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/NextPermutation31.java#L137-L163)方法计算下一个排列
     * 3. 输出结果
     */
    public static void main(String[] args) {
        // 创建 Scanner 对象用于获取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入整数数组
        System.out.print("请输入整数数组（用空格分隔）：");
        String line = scanner.nextLine();
        String[] strArray = line.split(" ");

        // 将字符串数组转换为整数数组
        int[] nums = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            nums[i] = Integer.parseInt(strArray[i]); // 转换字符串为整数
        }

        // 保存原始数组用于输出
        int[] original = Arrays.copyOf(nums, nums.length);

        // 调用 nextPermutation 方法找出下一个排列
        nextPermutation(nums);

        // 输出结果
        System.out.print("原排列：");
        for (int num : original) {
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.print("下一个排列为：");
        for (int num : nums) {
            System.out.print(num + " "); // 打印下一个排列
        }
        System.out.println();
    }

    /**
     * 计算下一个排列
     *
     * 算法思路：
     * 1. 从右往左找到第一个递减对 (i, i+1)，即 nums[i] < nums[i+1]
     * 2. 从右往左找到第一个大于 nums[i] 的元素 nums[j]
     * 3. 交换 nums[i] 和 nums[j]
     * 4. 反转 i+1 到末尾的部分，使其变为最小排列
     *
     * 执行过程分析（以数组 [1,2,3] 为例）：
     *
     * 初始数组: [1, 2, 3]
     *
     * 步骤1：找到逆序对
     * 从右往左检查：3>2，2>1，都满足递增
     * 找到第一对递减元素：无，i = -1
     *
     * 步骤2：i < 0，直接跳到步骤4
     *
     * 步骤4：反转整个数组 [3, 2, 1]
     *
     * 执行过程分析（以数组 [1,3,2] 为例）：
     *
     * 初始数组: [1, 3, 2]
     *
     * 步骤1：找到逆序对
     * 从右往左检查：2<3，找到递减对
     * i = 1 (对应元素3)
     *
     * 步骤2：寻找替换元素
     * 从右往左找到第一个大于 nums[1]=3 的元素
     * nums[2]=2 < 3，继续
     * nums[1]=3 = 3，不满足
     * nums[0]=1 < 3，不满足
     * 实际上应该找到从右往左第一个大于nums[i]的元素，这里是不存在的
     * 让我们重新分析 [1,3,2]:
     *
     * 步骤1：找到递减对
     * 从右往左检查：2<3，找到递减对
     * i = 1 (对应元素3)
     *
     * 步骤2：寻找替换元素
     * 从右往左找到第一个大于 nums[1]=3 的元素
     * 但实际上2<3，没有找到
     *
     * 让我们用 [1,2,3,4] -> [1,2,4,3] 为例:
     * 步骤1：从右往左找到第一个 nums[i] < nums[i+1]，i=2 (元素3)
     * 步骤2：从右往左找到第一个大于3的元素，j=3 (元素4)
     * 步骤3：交换3和4，得到 [1,2,4,3]
     * 步骤4：反转i+1到末尾，即反转[3]，得到 [1,2,4,3]
     *
     * 再以 [1,3,2] 为例:
     * 步骤1：从右往左找到第一个 nums[i] < nums[i+1]，i=0 (元素1)
     * 步骤2：从右往左找到第一个大于1的元素，j=2 (元素2)
     * 步骤3：交换1和2，得到 [2,3,1]
     * 步骤4：反转i+1到末尾，即反转[3,1]得到[1,3]，最终 [2,1,3]
     *
     * 时间复杂度分析：
     * - 查找递减对：O(n)
     * - 查找替换元素：O(n)
     * - 交换元素：O(1)
     * - 反转数组：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 原地修改数组：O(1)
     *
     * @param nums 整数数组
     */
    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;

        // 步骤1：从右往左找到第一个递减对 (i, i+1)，即 nums[i] < nums[i+1]
        // 如果整个数组都是递减的，则i最终会变为-1
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // 如果找到了递减对
        if (i >= 0) {
            // 步骤2：从右往左找到第一个大于 nums[i] 的元素 nums[j]
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            // 步骤3：交换 nums[i] 和 nums[j]
            swap(nums, i, j);
        }

        // 步骤4：反转从 i+1 到末尾的部分
        // 如果i=-1（整个数组递减），则反转整个数组
        reverse(nums, i + 1, n - 1);
    }

    /**
     * 交换数组中两个元素的辅助方法
     *
     * 时间复杂度分析：
     * - 交换操作：O(1)
     *
     * 空间复杂度分析：
     * - 只使用常数额外空间：O(1)
     *
     * @param nums 数组
     * @param i 第一个元素的索引
     * @param j 第二个元素的索引
     */
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 反转数组从 start 到 end 的辅助方法
     *
     * 时间复杂度分析：
     * - 反转操作：O(n)，其中n为(end-start+1)/2
     *
     * 空间复杂度分析：
     * - 原地操作：O(1)
     *
     * @param nums 数组
     * @param start 起始索引
     * @param end 结束索引
     */
    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
