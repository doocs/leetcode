package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 颜色分类（LeetCode 75）- 荷兰国旗问题
 *
 * 时间复杂度：O(n)
 * - 需要遍历数组一次
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量
 * - 原地排序数组
 */
public class SortColor75 {

    /**
     * 对颜色数组进行排序（0表示红色，1表示白色，2表示蓝色）
     *
     * 算法思路：
     * 使用三指针技术（Dutch National Flag Algorithm）：
     * 1. zero指针：指向下一个0应该放置的位置
     * 2. two指针：指向下一个2应该放置的位置
     * 3. current指针：当前正在处理的元素
     *
     * 执行过程分析（以数组 [2,0,2,1,1,0] 为例）：
     *
     * 初始状态：
     * nums = [2,0,2,1,1,0]
     * zero = 0, current = 0, two = 5
     *
     * 第1步：current=0, nums[0]=2
     * 交换nums[0]和nums[5]，two--，nums=[0,0,2,1,1,2]
     * zero=0, current=0, two=4
     *
     * 第2步：current=0, nums[0]=0
     * 交换nums[0]和nums[0]，zero++，current++，nums=[0,0,2,1,1,2]
     * zero=1, current=1, two=4
     *
     * 第3步：current=1, nums[1]=0
     * 交换nums[1]和nums[1]，zero++，current++，nums=[0,0,2,1,1,2]
     * zero=2, current=2, two=4
     *
     * 第4步：current=2, nums[2]=2
     * 交换nums[2]和nums[4]，two--，nums=[0,0,1,1,2,2]
     * zero=2, current=2, two=3
     *
     * 第5步：current=2, nums[2]=1
     * current++，nums=[0,0,1,1,2,2]
     * zero=2, current=3, two=3
     *
     * 第6步：current=3, nums[3]=1
     * current++，nums=[0,0,1,1,2,2]
     * zero=2, current=4, two=3
     *
     * current > two，循环结束
     * 最终结果：[0,0,1,1,2,2]
     *
     * 时间复杂度分析：
     * - 遍历数组一次：O(n)
     * - 每个元素最多被访问常数次
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量（zero, two, current）：O(1)
     * - 原地排序，不需要额外存储空间
     *
     * @param nums 包含0、1、2的数组
     */
    public void sortColors(int[] nums) {
        int zero = 0;        // 指向下一个0应该放置的位置
        int two = nums.length - 1;  // 指向下一个2应该放置的位置
        int current = 0;     // 当前正在处理的元素索引

        // 当current指针不超过two指针时继续循环
        while (current <= two) {
            if (nums[current] == 0) {
                // 如果当前元素是0，将其交换到数组前端
                swap(nums, current, zero);
                zero++;      // 前移zero指针
                current++;   // 前移current指针
            } else if (nums[current] == 2) {
                // 如果当前元素是2，将其交换到数组后端
                swap(nums, current, two);
                two--;       // 后移two指针
                // 注意：这里不前移current指针，因为从后面交换来的元素还未处理
            } else {
                // 如果当前元素是1，保持在中间，直接前移current指针
                current++;
            }
        }
    }


    /**
     * 方法2：两次遍历计数法
     *
     * 算法思路：
     * 1. 第一次遍历统计0、1、2的个数
     * 2. 第二次遍历根据统计结果重写数组
     *
     * 时间复杂度分析：
     * - 第一次遍历统计：O(n)
     * - 第二次遍历重写：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量（count0, count1, count2, i）：O(1)
     *
     * @param nums 包含0、1、2的数组
     */
    public void sortColorsTwoPass(int[] nums) {
        // 统计0、1、2的个数
        int count0 = 0, count1 = 0, count2 = 0;

        for (int num : nums) {
            if (num == 0) count0++;
            else if (num == 1) count1++;
            else count2++;
        }

        // 重写数组
        int i = 0;
        while (count0-- > 0) nums[i++] = 0;
        while (count1-- > 0) nums[i++] = 1;
        while (count2-- > 0) nums[i++] = 2;
    }

    /**
     * 交换数组中两个元素的辅助方法
     *
     * 时间复杂度分析：
     * - 交换操作：O(1)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量（temp）：O(1)
     *
     * @param nums 数组
     * @param i 第一个元素的索引
     * @param j 第二个元素的索引
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
     * - 读取输入：O(m)，m为输入字符数
     * - 解析为整数：O(n)，n为数组长度
     * - 总时间复杂度：O(m+n)
     *
     * 空间复杂度分析：
     * - 存储字符串数组：O(m)
     * - 存储整数数组：O(n)
     * - 总空间复杂度：O(m+n)
     *
     * @return 用户输入的整数数组
     */
    public static int[] readArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入颜色数组（0表示红色，1表示白色，2表示蓝色，用空格分隔）：");
        String input = scanner.nextLine();
        String[] strArray = input.split(" ");

        int[] nums = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            nums[i] = Integer.parseInt(strArray[i]);
        }

        return nums;
    }

    /**
     * 主函数：处理用户输入并演示颜色分类
     */
    public static void main(String[] args) {
        System.out.println("颜色分类（荷兰国旗问题）");

        // 读取用户输入的数组
        int[] nums = readArray();
        System.out.println("输入的数组: " + Arrays.toString(nums));

        // 保存原始数组用于对比
        int[] original = Arrays.copyOf(nums, nums.length);

        // 方法1：三指针法排序
        SortColor75 solution = new SortColor75();
        solution.sortColors(nums);
        System.out.println("三指针法排序结果: " + Arrays.toString(nums));

        // 方法2：两次遍历法排序
        int[] nums2 = Arrays.copyOf(original, original.length);
        solution.sortColorsTwoPass(nums2);
        System.out.println("两次遍历法排序结果: " + Arrays.toString(nums2));
    }
}
