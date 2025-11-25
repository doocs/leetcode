package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 移动零（LeetCode 283）- 优化版本
 *
 * 时间复杂度：O(n)
 * - 只需要遍历数组一次
 * - 每个元素最多被访问和交换一次
 *
 * 空间复杂度：O(1)
 * - 只使用了常数级别的额外空间
 * - 通过原地交换完成操作，不需要额外的存储空间
 */
public class MoveZero283 {
    public static void main(String[] args) {
        // 创建 Scanner 对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入数组
        System.out.println("请输入数组元素（用空格分隔）：");

        // 读取一行输入
        String line = scanner.nextLine();

        // 按空格分割字符串得到字符串数组
        String[] str = line.split(" ");

        // 获取数组长度
        int n = str.length;

        // 创建整型数组
        int[] nums = new int[n];

        // 将字符串数组转换为整型数组
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(str[i]);
        }

        // 调用 moveZeroes 方法移动零元素
        moveZeroes(nums);

        // 输出结果
        System.out.println("处理后的数组:");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    /**
     * 将数组中的所有 0 移动到数组的末尾，同时保持非零元素的相对顺序
     * 使用双指针优化算法，只需要一次遍历
     *
     * 算法思路：
     * 使用双指针技术，left指针指向下一个非零元素应该放置的位置
     * right指针遍历数组，遇到非零元素就与left指针位置交换
     * 这样保证了非零元素的相对顺序，并将零元素移到末尾
     *
     * 示例过程（以数组 [0,1,0,3,12] 为例）：
     *
     * 初始状态:
     * nums = [0, 1, 0, 3, 12]
     *        L
     *        R
     *
     * right=0: nums[0]=0，跳过
     * right=1: nums[1]=1≠0，交换nums[0]和nums[1]，left++
     *          nums = [1, 0, 0, 3, 12]
     *                    L
     *                    R
     * right=2: nums[2]=0，跳过
     * right=3: nums[3]=3≠0，交换nums[1]和nums[3]，left++
     *          nums = [1, 3, 0, 0, 12]
     *                       L
     *                       R
     * right=4: nums[4]=12≠0，交换nums[2]和nums[4]，left++
     *          nums = [1, 3, 12, 0, 0]
     *                           L
     *                          R
     *
     * 最终结果: [1, 3, 12, 0, 0]
     *
     * 时间复杂度分析：
     * - 单次遍历数组：O(n)，其中n为输入数组`nums`的长度
     * - 每次交换操作：O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用了几个变量存储索引和临时值：O(1)
     * - 原地操作，不需要额外数组：O(1)
     * - 总空间复杂度：O(1)
     *
     * @param nums 输入的整数数组
     */
    public static void moveZeroes(int[] nums) {
        // 使用双指针技术
        // left 指针指向下一个非零元素应该放置的位置
        int left = 0;

        // 遍历数组
        for (int right = 0; right < nums.length; right++) {
            // 如果右指针指向的元素不为0
            if (nums[right] != 0) {
                // 将右指针指向的非零元素与左指针指向的位置交换
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;

                // 左指针向前移动一位
                left++;
            }
        }
    }

    /**
     * 方法2：两次遍历法
     *
     * 算法思路：
     * 1. 第一次遍历：将所有非零元素移动到数组前面
     * 2. 第二次遍历：将剩余位置填充为0
     *
     * 示例过程（以数组 [0,1,0,3,12] 为例）：
     *
     * 初始状态:
     * nums = [0, 1, 0, 3, 12]
     *
     * 第一次遍历:
     * i=0: nums[0]=0，跳过
     * i=1: nums[1]=1≠0，nums[0]=1，index=1
     * i=2: nums[2]=0，跳过
     * i=3: nums[3]=3≠0，nums[1]=3，index=2
     * i=4: nums[4]=12≠0，nums[2]=12，index=3
     * 结果: nums = [1, 3, 12, 3, 12]，index=3
     *
     * 第二次遍历:
     * index=3: nums[3]=0，index=4
     * index=4: nums[4]=0，index=5
     * 结果: nums = [1, 3, 12, 0, 0]
     *
     * 时间复杂度分析：
     * - 第一次遍历：O(n)，其中n为输入数组`nums`的长度
     * - 第二次遍历：O(n-k)，其中k为非零元素个数
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用了一个索引变量：O(1)
     * - 原地操作：O(1)
     * - 总空间复杂度：O(1)
     *
     * @param nums 输入的整数数组
     */
    public static void moveZeroesTwoPass(int[] nums) {
        // 第一次遍历：将所有非零元素移动到数组前面
        int index = 0;

        // 遍历数组，将非零元素依次放到前面
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }

        // 第二次遍历：将剩余位置填充为0
        while (index < nums.length) {
            nums[index++] = 0;
        }
    }

    /**
     * 方法3：优化的交换法（减少不必要的交换）
     *
     * 算法思路：
     * 只有当left和right不相等时才进行交换，避免自己与自己交换
     *
     * 示例过程（以数组 [0,1,0,3,12] 为例）：
     *
     * 初始状态:
     * nums = [0, 1, 0, 3, 12]
     *        L
     *        R
     *
     * right=0: nums[0]=0，跳过
     * right=1: nums[1]=1≠0，left≠right，交换nums[0]和nums[1]，left++
     *          nums = [1, 0, 0, 3, 12]
     *                    L
     *                    R
     * right=2: nums[2]=0，跳过
     * right=3: nums[3]=3≠0，left≠right，交换nums[1]和nums[3]，left++
     *          nums = [1, 3, 0, 0, 12]
     *                       L
     *                       R
     * right=4: nums[4]=12≠0，left≠right，交换nums[2]和nums[4]，left++
     *          nums = [1, 3, 12, 0, 0]
     *                           L
     *                          R
     *
     * 时间复杂度分析：
     * - 单次遍历数组：O(n)，其中n为输入数组`nums`的长度
     * - 交换操作减少，但仍为O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用了几个变量存储索引和临时值：O(1)
     * - 原地操作：O(1)
     * - 总空间复杂度：O(1)
     *
     * @param nums 输入的整数数组
     */
    public static void moveZeroesOptimized(int[] nums) {
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != 0) {
                // 只有当left和right不相等时才进行交换
                if (left != right) {
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                }
                left++;
            }
        }
    }
}
