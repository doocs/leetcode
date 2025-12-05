package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 旋转数组（LeetCode 189）
 *
 * 时间复杂度：O(n)
 * - 需要翻转整个数组：O(n)
 * - 需要翻转前k个元素：O(k)
 * - 需要翻转后n-k个元素：O(n-k)
 * - 总时间复杂度：O(n) + O(k) + O(n-k) = O(n)
 *
 * 空间复杂度：O(1)
 * - 只使用了常数级别的额外空间
 * - 翻转操作是原地进行的，不需要额外的存储空间
 */
public class RotateArray189 {

    /**
     * 主函数测试
     * 测试数组 [1,2,3,4,5,6,7] 向右旋转 3 位的结果
     */
    public static void main(String[] args) {
        // 创建 Scanner 对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入数组
        System.out.println("请输入数组元素（用空格分隔）：");
        String line = scanner.nextLine();
        String[] strArray = line.split(" ");
        int[] nums = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            nums[i] = Integer.parseInt(strArray[i]);
        }

        // 提示用户输入旋转步数
        System.out.print("请输入旋转步数 k：");
        int k = scanner.nextInt();

        // 调用 rotate 方法将数组向右旋转 k 位
        rotate(nums, k);

        // 输出旋转后的数组
        System.out.print("旋转后的数组：");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    /**
     * 旋转数组
     * 将数组中的元素向右移动 k 个位置
     *
     * 算法思路：
     * 1. 先将整个数组翻转
     * 2. 再将前 k 个元素翻转
     * 3. 最后将后 n-k 个元素翻转
     *
     * 示例过程（以数组 [1,2,3,4,5,6,7]，k=3 为例）：
     * 原数组:     [1,2,3,4,5,6,7]
     * 步骤1 - 翻转整个数组: [7,6,5,4,3,2,1]
     * 步骤2 - 翻转前k个元素: [5,6,7,4,3,2,1]
     * 步骤3 - 翻转后n-k个元素: [5,6,7,1,2,3,4]
     * 结果: [5,6,7,1,2,3,4] （相当于原数组向右移动3位）
     *
     * 时间复杂度分析：
     * - 翻转整个数组：O(n)，其中n为输入数组`nums`的长度
     * - 翻转前k个元素：O(k)
     * - 翻转后n-k个元素：O(n-k)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用了常数级别的额外变量：O(1)
     * - 翻转操作是原地进行的：O(1)
     * - 总空间复杂度：O(1)
     *
     * @param nums 输入的整数数组
     * @param k    向右旋转的步数
     */
    public static void rotate(int[] nums, int k) {
        // 获取数组长度
        int n = nums.length;

        // 处理 k 大于数组长度的情况，取模运算
        k = k % n;

        // 步骤1：翻转整个数组
        reverse(nums, 0, n - 1);

        // 步骤2：翻转前 k 个元素
        reverse(nums, 0, k - 1);

        // 步骤3：翻转后 n-k 个元素
        reverse(nums, k, n - 1);
    }


    /**
     * 翻转数组指定范围内的元素
     * 使用双指针技术，从两端向中间交换元素
     *
     * 时间复杂度分析：
     * - 双指针遍历范围：O((end-start+1)/2) = O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用了常数级别的额外变量：O(1)
     * - 总空间复杂度：O(1)
     *
     * @param nums  输入的整数数组
     * @param start 翻转范围的起始索引（包含）
     * @param end   翻转范围的结束索引（包含）
     */
    public static void reverse(int[] nums, int start, int end) {
        // 双指针从两端向中间移动
        while (start < end) {
            // 交换 start 和 end 位置的元素
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            // 移动指针
            start++;
            end--;
        }
    }

    /**
     * 方法2：使用额外数组
     *
     * 算法思路：
     * 创建新数组，将原数组元素按旋转规则放置到新位置，再复制回原数组
     *
     * 示例过程（以数组 [1,2,3,4,5,6,7]，k=3 为例）：
     *
     * 1. 初始化: nums=[1,2,3,4,5,6,7], k=3
     * 2. 创建新数组: rotated=[0,0,0,0,0,0,0]
     * 3. 按规则放置元素:
     *    i=0: rotated[(0+3)%7] = rotated[3] = nums[0] = 1
     *    i=1: rotated[(1+3)%7] = rotated[4] = nums[1] = 2
     *    i=2: rotated[(2+3)%7] = rotated[5] = nums[2] = 3
     *    i=3: rotated[(3+3)%7] = rotated[6] = nums[3] = 4
     *    i=4: rotated[(4+3)%7] = rotated[0] = nums[4] = 5
     *    i=5: rotated[(5+3)%7] = rotated[1] = nums[5] = 6
     *    i=6: rotated[(6+3)%7] = rotated[2] = nums[6] = 7
     * 4. 复制回原数组: nums=[5,6,7,1,2,3,4]
     *
     * 时间复杂度分析：
     * - 遍历数组两次：O(n)，其中n为输入数组`nums`的长度
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 需要额外数组存储结果：O(n)
     * - 总空间复杂度：O(n)
     *
     * @param nums 输入的整数数组
     * @param k    向右旋转的步数
     */
    public static void rotateExtraArray(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        // 创建新数组存储旋转后的结果
        int[] rotated = new int[n];

        // 将原数组元素放到新位置
        for (int i = 0; i < n; i++) {
            rotated[(i + k) % n] = nums[i];
        }

        // 将结果复制回原数组
        for (int i = 0; i < n; i++) {
            nums[i] = rotated[i];
        }
    }

    /**
     * 方法3：循环替换
     *
     * 算法思路：
     * 通过循环替换的方式，将每个元素直接放到最终位置
     *
     * 示例过程（以数组 [1,2,3,4,5,6]，k=2 为例）：
     *
     * 1. 初始化: nums=[1,2,3,4,5,6], k=2, n=6
     * 2. 循环替换过程:
     *    start=0:
     *      current=0, prev=1
     *      next=(0+2)%6=2, temp=nums[2]=3, nums[2]=1, prev=3, current=2
     *      next=(2+2)%6=4, temp=nums[4]=5, nums[4]=3, prev=5, current=4
     *      next=(4+2)%6=0, temp=nums[0]=1, nums[0]=5, prev=1, current=0
     *      回到起始位置，结束本轮循环
     *    count=3 < n=6, 继续
     *    start=1:
     *      current=1, prev=2
     *      next=(1+2)%6=3, temp=nums[3]=4, nums[3]=2, prev=4, current=3
     *      next=(3+2)%6=5, temp=nums[5]=6, nums[5]=4, prev=6, current=5
     *      next=(5+2)%6=1, temp=nums[1]=2, nums[1]=6, prev=2, current=1
     *      回到起始位置，结束本轮循环
     *    count=6 = n=6, 结束
     * 3. 最终结果: nums=[5,6,1,2,3,4]
     *
     * 时间复杂度分析：
     * - 每个元素只被移动一次：O(n)，其中n为输入数组`nums`的长度
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用了常数级别的额外变量：O(1)
     * - 总空间复杂度：O(1)
     *
     * @param nums 输入的整数数组
     * @param k    向右旋转的步数
     */
    public static void rotateCyclic(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        int count = 0;
        for (int start = 0; count < n; start++) {
            int current = start;
            int prev = nums[start];

            // do-while循环进行元素替换
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current); // 当回到起始位置时结束循环
        }
    }
}
