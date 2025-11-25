package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 缺失的第一个正数（LeetCode 41）
 *
 * 时间复杂度：O(n)
 * - 第一个循环最多执行 n 次交换操作（每个元素最多被放置到正确位置一次）
 * - 第二个循环遍历数组：O(n)
 * - 总时间复杂度：O(n)
 *
 * 空间复杂度：O(1)
 * - 原地操作，只使用了常数级别的额外空间
 */
public class FirstMissingPositive41 {
    public static void main(String[] args) {
        // 创建 Scanner 对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 读取输入的数组
        System.out.print("请输入数组元素，以空格分隔：");

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

        // 调用 firstMissingPositive 方法计算缺失的第一个正数
        int result = firstMissingPositive(nums);

        // 输出结果
        System.out.println("数组中缺失的最小正整数是：" + result);
    }

    /**
     * 找到数组中缺失的第一个正整数
     *
     * 算法思路：
     * 使用原地哈希的思想，将数组本身作为哈希表
     * 1. 对于长度为 n 的数组，缺失的第一个正整数一定在 [1, n+1] 范围内
     * 2. 将每个数字放到它应该在的位置上（数字 i 放到索引 i-1 的位置）
     * 3. 遍历数组找到第一个不在正确位置的数字
     *
     * 示例过程（以数组 [3,4,-1,1] 为例）：
     * 数组: [3, 4, -1, 1]
     * 索引:  0  1   2  3
     *
     * 步骤1 - 将数字放到正确位置:
     * i=0: nums[0]=3, 应该放在索引2, 交换nums[0]和nums[2]
     *      [-1, 4, 3, 1]
     * i=0: nums[0]=-1, 不在[1,4]范围内, 跳过
     * i=1: nums[1]=4, 应该放在索引3, 交换nums[1]和nums[3]
     *      [-1, 1, 3, 4]
     * i=1: nums[1]=1, 应该放在索引0, 交换nums[1]和nums[0]
     *      [1, -1, 3, 4]
     * i=1: nums[1]=-1, 不在[1,4]范围内, 跳过
     * i=2: nums[2]=3, 已经在正确位置, 跳过
     * i=3: nums[3]=4, 已经在正确位置, 跳过
     *
     * 最终数组: [1, -1, 3, 4]
     *
     * 步骤2 - 找到第一个不匹配的位置:
     * i=0: nums[0]=1, 匹配 i+1=1
     * i=1: nums[1]=-1, 不匹配 i+1=2, 返回 2
     *
     * 结果: 2
     *
     * 时间复杂度分析：
     * - 放置数字到正确位置：O(n)，其中n为输入数组`nums`的长度，每个元素最多被交换一次
     * - 查找第一个不匹配位置：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 原地操作，只使用常数额外空间：O(1)
     *
     * @param nums 输入的整数数组
     * @return 缺失的第一个正整数
     */
    public static int firstMissingPositive(int[] nums) {
        // 获取数组长度
        int n = nums.length;

        // 1. 将数字放到正确的位置
        // 对于每个位置，如果该位置的数字在[1,n]范围内且不在正确位置，则交换到正确位置
        for (int i = 0; i < n; i++) {
            // 当前数字在有效范围内(1到n) 且 不在正确位置 且 正确位置上的数字不等于当前数字时进行交换
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // 交换 nums[i] 和 nums[nums[i] - 1]
                // 将数字 nums[i] 放到它应该在的位置 nums[i]-1
                swap(nums, i, nums[i] - 1);
            }
        }

        // 2. 找到第一个不匹配的数字
        // 正确情况下，索引i位置应该存放数字i+1
        for (int i = 0; i < n; i++) {
            // 如果索引i位置存放的不是i+1，则i+1就是缺失的第一个正整数
            if (nums[i] != i + 1) {
                // 返回缺失的最小正整数
                return i + 1;
            }
        }

        // 3. 如果所有位置都匹配，说明数组包含[1,n]的所有正整数
        // 缺失的第一个正整数就是n+1
        return n + 1;
    }

    /**
     * 交换数组中两个位置的元素
     *
     * 时间复杂度分析：
     * - 交换操作：O(1)
     *
     * 空间复杂度分析：
     * - 只使用常数额外空间：O(1)
     *
     * @param nums 数组
     * @param i 第一个位置
     * @param j 第二个位置
     */
    private static void swap(int[] nums, int i, int j) {
        // 临时变量存储第一个位置的值
        int temp = nums[i];
        // 将第二个位置的值赋给第一个位置
        nums[i] = nums[j];
        // 将临时变量的值赋给第二个位置
        nums[j] = temp;
    }

    /**
     * 方法2：使用布尔数组标记
     *
     * 算法思路：
     * 1. 创建布尔数组标记[1,n]范围内的数字是否存在
     * 2. 遍历原数组，标记存在的正整数
     * 3. 找到第一个未被标记的位置
     *
     * 示例过程（以数组 [3,4,-1,1] 为例）：
     *
     * 1. 初始化: present = [false, false, false, false, false] (长度为n+1=5)
     * 2. 标记过程:
     *    num=3: 0<3≤4, present[3]=true → [false, false, false, true, false]
     *    num=4: 0<4≤4, present[4]=true → [false, false, false, true, true]
     *    num=-1: -1≤0, 跳过
     *    num=1: 0<1≤4, present[1]=true → [false, true, false, true, true]
     * 3. 查找过程:
     *    i=1: present[1]=true, 继续
     *    i=2: present[2]=false, 返回2
     *
     * 时间复杂度分析：
     * - 标记存在的正整数：O(n)，其中n为输入数组`nums`的长度
     * - 查找第一个不存在的正整数：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 布尔数组：O(n)
     * - 其他变量：O(1)
     * - 总空间复杂度：O(n)
     *
     * @param nums 输入的整数数组
     * @return 缺失的第一个正整数
     */
    public static int firstMissingPositiveBooleanArray(int[] nums) {
        int n = nums.length;
        boolean[] present = new boolean[n + 1];

        // 标记存在的正整数
        for (int num : nums) {
            if (num > 0 && num <= n) {
                present[num] = true;
            }
        }

        // 找到第一个不存在的正整数
        for (int i = 1; i <= n; i++) {
            if (!present[i]) {
                return i;
            }
        }

        return n + 1;
    }

    /**
     * 方法3：将负数和超出范围的数标记为n+1
     *
     * 算法思路：
     * 1. 将所有非正数和大于n的数替换为n+1
     * 2. 使用数组本身作为标记数组，将对应位置的数变为负数
     * 3. 找到第一个正数的位置
     *
     * 示例过程（以数组 [3,4,-1,1] 为例）：
     *
     * 1. 初始化: nums = [3, 4, -1, 1], n = 4
     * 2. 替换非正数:
     *    nums[0]=3: 3>0, 不变
     *    nums[1]=4: 4>0, 不变
     *    nums[2]=-1: -1≤0, nums[2]=5 → [3, 4, 5, 1]
     *    nums[3]=1: 1>0, 不变
     * 3. 标记数组:
     *    i=0, num=abs(3)=3: 3≤4, nums[2]=-abs(nums[2])=-5 → [3, 4, -5, 1]
     *    i=1, num=abs(4)=4: 4≤4, nums[3]=-abs(nums[3])=-1 → [3, 4, -5, -1]
     *    i=2, num=abs(-5)=5: 5>4, 跳过
     *    i=3, num=abs(-1)=1: 1≤4, nums[0]=-abs(nums[0])=-3 → [-3, 4, -5, -1]
     * 4. 查找正数:
     *    i=0: nums[0]=-3<0, 继续
     *    i=1: nums[1]=4>0, 返回1+1=2
     *
     * 时间复杂度分析：
     * - 替换非正数：O(n)，其中n为输入数组`nums`的长度
     * - 标记数组：O(n)
     * - 查找正数：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 原地操作：O(1)
     *
     * @param nums 输入的整数数组
     * @return 缺失的第一个正整数
     */
    public static int firstMissingPositiveMarking(int[] nums) {
        int n = nums.length;

        // 将所有非正数替换为n+1
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }

        // 使用数组本身作为标记数组
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        // 找到第一个正数的位置
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        return n + 1;
    }
}
