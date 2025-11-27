package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 搜索插入位置（LeetCode 35）
 *
 * 时间复杂度：O(log n)
 * - 使用二分查找，每次将搜索范围减半
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量
 */
public class SearchInsert35 {

    /**
     * 主函数：处理用户输入并计算搜索插入位置
     *
     * 算法流程：
     * 1. 读取用户输入的排序数组和目标值
     * 2. 调用[searchInsert](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/SearchInsert35.java#L107-L137)方法查找目标值或插入位置
     * 3. 输出结果
     */
    public static void main(String[] args) {
        // 创建 Scanner 对象用于获取用户输入
        // Scanner scanner = new Scanner(System.in) 创建Scanner对象用于读取输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入排序数组
        // System.out.print("请输入排序数组（用空格分隔）：") 打印输入提示
        System.out.print("请输入排序数组（用空格分隔）：");
        // String line = scanner.nextLine() 读取用户输入的一行
        String line = scanner.nextLine();
        // String[] strArray = line.split(" ") 按空格分割字符串
        String[] strArray = line.split(" ");

        // 将字符串数组转换为整数数组
        // int[] nums = new int[strArray.length] 创建整数数组
        int[] nums = new int[strArray.length];
        // for (int i = 0; i < strArray.length; i++) 遍历字符串数组
        for (int i = 0; i < strArray.length; i++) {
            // nums[i] = Integer.parseInt(strArray[i]) 转换字符串为整数
            nums[i] = Integer.parseInt(strArray[i]); // 转换字符串为整数
        }

        // 提示用户输入目标值
        // System.out.print("请输入目标值：") 打印目标值输入提示
        System.out.print("请输入目标值：");
        // int target = scanner.nextInt() 读取目标值
        int target = scanner.nextInt();

        // 调用 searchInsert 方法查找目标值或插入位置
        // int result = searchInsert(nums, target) 调用searchInsert方法计算结果
        int result = searchInsert(nums, target);

        // 输出结果
        // System.out.println("目标值 " + target + " 的索引为：" + result) 打印结果
        System.out.println("目标值 " + target + " 的索引为：" + result);
    }

    /**
     * 在排序数组中查找目标值或插入位置
     *
     * 算法思路：
     * 使用二分查找，在有序数组中查找目标值
     * 如果找到目标值，返回其索引
     * 如果未找到，返回其应该插入的位置以保持数组有序
     *
     * 执行过程分析（以`nums=[1,3,5,6]`, `target=5`为例）：
     *
     * 初始状态：
     * left = 0, right = 3
     *
     * 第一次查找：
     * mid = 0 + (3-0)/2 = 1
     * nums[1] = 3 < target=5
     * left = mid + 1 = 2
     *
     * 第二次查找：
     * mid = 2 + (3-2)/2 = 2
     * nums[2] = 5 == target=5
     * 返回索引2
     *
     * 执行过程分析（以`nums=[1,3,5,6]`, `target=2`为例）：
     *
     * 初始状态：
     * left = 0, right = 3
     *
     * 第一次查找：
     * mid = 0 + (3-0)/2 = 1
     * nums[1] = 3 > target=2
     * right = mid - 1 = 0
     *
     * 第二次查找：
     * mid = 0 + (0-0)/2 = 0
     * nums[0] = 1 < target=2
     * left = mid + 1 = 1
     *
     * 循环结束：left=1 > right=0
     * 返回left=1（插入位置）
     *
     * 执行过程分析（以`nums=[1,3,5,6]`, `target=7`为例）：
     *
     * 初始状态：
     * left = 0, right = 3
     *
     * 第一次查找：
     * mid = 0 + (3-0)/2 = 1
     * nums[1] = 3 < target=7
     * left = mid + 1 = 2
     *
     * 第二次查找：
     * mid = 2 + (3-2)/2 = 2
     * nums[2] = 5 < target=7
     * left = mid + 1 = 3
     *
     * 第三次查找：
     * mid = 3 + (3-3)/2 = 3
     * nums[3] = 6 < target=7
     * left = mid + 1 = 4
     *
     * 循环结束：left=4 > right=3
     * 返回left=4（插入位置，数组末尾）
     *
     * 时间复杂度分析：
     * - 二分查找：O(log n)
     * - 每次迭代操作：O(1)
     * - 总时间复杂度：O(log n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param nums 升序排序的无重复元素数组
     * @param target 目标值
     * @return 目标值的索引或应该插入的位置
     */
    public static int searchInsert(int[] nums, int target) {
        // 左指针，指向搜索范围的左边界
        // int left = 0 初始化左指针
        int left = 0;
        // 右指针，指向搜索范围的右边界
        // int right = nums.length - 1 初始化右指针
        int right = nums.length - 1;

        // 二分查找
        // 当left <= right时继续查找
        // while (left <= right) 当左指针小于等于右指针时循环
        while (left <= right) {
            // 计算中间索引，避免整数溢出
            // int mid = left + (right - left) / 2 计算中间索引
            int mid = left + (right - left) / 2;

            // 找到目标值，直接返回索引
            // if (nums[mid] == target) 检查中间元素是否等于目标值
            if (nums[mid] == target) {
                // return mid 返回目标值索引
                return mid;
            }
            // 中间值小于目标值，向右半部分查找
            // else if (nums[mid] < target) 检查中间元素是否小于目标值
            else if (nums[mid] < target) {
                // left = mid + 1 更新左指针
                left = mid + 1;
            }
            // 中间值大于目标值，向左半部分查找
            else {
                // right = mid - 1 更新右指针
                right = mid - 1;
            }
        }

        // 如果未找到目标值，返回插入位置
        // 此时left即为插入位置，可以保持数组有序
        // return left 返回插入位置
        return left;
    }



    /**
     * 方法2：线性搜索解法（仅供对比）
     *
     * 算法思路：
     * 从左到右遍历数组，找到第一个大于等于目标值的元素位置
     *
     * 时间复杂度分析：
     * - 遍历数组：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param nums 升序排序的无重复元素数组
     * @param target 目标值
     * @return 目标值的索引或应该插入的位置
     */
    public int searchInsertLinear(int[] nums, int target) {
        // for (int i = 0; i < nums.length; i++) 遍历数组
        for (int i = 0; i < nums.length; i++) {
            // if (nums[i] >= target) 检查当前元素是否大于等于目标值
            if (nums[i] >= target) {
                // return i 返回索引
                return i;
            }
        }
        // return nums.length 返回数组长度作为插入位置
        return nums.length;
    }

    /**
     * 方法3：使用Java内置二分查找
     *
     * 算法思路：
     * 使用Java标准库的二分查找方法
     *
     * 时间复杂度分析：
     * - 二分查找：O(log n)
     * - 总时间复杂度：O(log n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param nums 升序排序的无重复元素数组
     * @param target 目标值
     * @return 目标值的索引或应该插入的位置
     */
    public int searchInsertBuiltIn(int[] nums, int target) {
        // int index = Arrays.binarySearch(nums, target) 调用Java内置二分查找
        int index = Arrays.binarySearch(nums, target);
        // return index >= 0 ? index : -index - 1 根据返回值确定索引或插入位置
        return index >= 0 ? index : -index - 1;
    }
}
