package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 乘积最大子数组（LeetCode 152）- 动态规划
 *
 * 时间复杂度：O(n)
 * - n是数组长度
 * - 只需要遍历数组一次
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量
 */
public class MaxProduct152 {

    /**
     * 主函数：处理用户输入并计算乘积最大的连续子数组的乘积
     *
     * 算法流程：
     * 1. 读取用户输入的整数数组
     * 2. 调用 [maxProduct](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/MaxProduct152.java#L125-L162)方法计算最大乘积
     * 3. 输出结果
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入数组
        System.out.print("请输入整数数组（用空格分隔）：");
        String[] input = scanner.nextLine().split(" ");
        int[] nums = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }

        int result = maxProduct(nums);
        System.out.println("乘积最大的连续子数组的乘积是：" + result);
    }

    /**
     * 计算乘积最大的连续子数组的乘积
     *
     * 算法思路：
     * 由于负数的存在，最大值和最小值可能会相互转换
     * 因此需要同时维护以当前位置结尾的最大乘积和最小乘积
     *
     * 状态定义：
     * maxProduct：以当前位置结尾的最大乘积
     * minProduct：以当前位置结尾的最小乘积
     * result：全局最大乘积
     *
     * 状态转移：
     * 当遇到正数时：maxProduct可能变为maxProduct*nums[i]或nums[i]
     * 当遇到负数时：maxProduct和minProduct会互换角色
     * 当遇到0时：重置乘积为0
     *
     * 执行过程分析（以nums=[2,3,-2,4]为例）：
     *
     * 初始状态：
     * maxProduct = 2
     * minProduct = 2
     * result = 2
     *
     * 遍历过程：
     * i=1, nums[1]=3:
     *   nums[1]=3 > 0
     *   maxProduct = max(3, 2*3) = max(3, 6) = 6
     *   minProduct = min(3, 2*3) = min(3, 6) = 3
     *   result = max(2, 6) = 6
     *
     * i=2, nums[2]=-2:
     *   nums[2]=-2 < 0，交换maxProduct和minProduct
     *   temp = maxProduct = 6
     *   maxProduct = minProduct = 3
     *   minProduct = temp = 6
     *   maxProduct = max(-2, 3*(-2)) = max(-2, -6) = -2
     *   minProduct = min(-2, 6*(-2)) = min(-2, -12) = -12
     *   result = max(6, -2) = 6
     *
     * i=3, nums[3]=4:
     *   nums[3]=4 > 0
     *   maxProduct = max(4, -2*4) = max(4, -8) = 4
     *   minProduct = min(4, -12*4) = min(4, -48) = -48
     *   result = max(6, 4) = 6
     *
     * 最终结果：result = 6
     * 最大乘积子数组：[2,3]，乘积=2*3=6
     *
     * 执行过程分析（以nums=[-2,0,-1]为例）：
     *
     * 初始状态：
     * maxProduct = -2
     * minProduct = -2
     * result = -2
     *
     * 遍历过程：
     * i=1, nums[1]=0:
     *   nums[1]=0
     *   maxProduct = max(0, -2*0) = max(0, 0) = 0
     *   minProduct = min(0, -2*0) = min(0, 0) = 0
     *   result = max(-2, 0) = 0
     *
     * i=2, nums[2]=-1:
     *   nums[2]=-1 < 0，交换maxProduct和minProduct
     *   temp = maxProduct = 0
     *   maxProduct = minProduct = 0
     *   minProduct = temp = 0
     *   maxProduct = max(-1, 0*(-1)) = max(-1, 0) = 0
     *   minProduct = min(-1, 0*(-1)) = min(-1, 0) = -1
     *   result = max(0, 0) = 0
     *
     * 最终结果：result = 0
     * 最大乘积子数组：[0]，乘积=0
     *
     * 时间复杂度分析：
     * - 遍历数组一次：O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param nums 输入的整数数组
     * @return 乘积最大的连续子数组的乘积
     */
    public static int maxProduct(int[] nums) {
        // 边界情况：空数组
        if (nums.length == 0) return 0;

        // 以当前位置结尾的最大乘积
        int maxProduct = nums[0];
        // 以当前位置结尾的最小乘积
        int minProduct = nums[0];
        // 全局最大乘积
        int result = nums[0];

        // 从第二个元素开始遍历
        for (int i = 1; i < nums.length; i++) {
            // 如果当前元素是负数，最大值和最小值会互换角色
            // 因为负数乘以最大值会变成最小值，乘以最小值会变成最大值
            if (nums[i] < 0) {
                // 负数会使得最大值和最小值互换
                int temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
            }

            // 更新最大值和最小值
            // maxProduct = max(当前元素单独作为子数组, 前一个最大乘积*当前元素)
            maxProduct = Math.max(nums[i], maxProduct * nums[i]);
            // minProduct = min(当前元素单独作为子数组, 前一个最小乘积*当前元素)
            minProduct = Math.min(nums[i], minProduct * nums[i]);

            // 更新全局最大乘积
            result = Math.max(result, maxProduct);
        }

        return result;
    }

    /**
     * 方法2：标准动态规划解法（更清晰的思路）
     *
     * 算法思路：
     * 同时维护当前位置的最大乘积和最小乘积
     * 因为负数可能导致最大值和最小值互换
     *
     * 时间复杂度分析：
     * - 遍历数组一次：O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param nums 输入的整数数组
     * @return 乘积最大的连续子数组的乘积
     */
    public int maxProductStandard(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int maxProduct = nums[0];
        int minProduct = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 计算三种可能的乘积
            int currentMax = maxProduct * nums[i];
            int currentMin = minProduct * nums[i];

            // 更新最大乘积和最小乘积
            maxProduct = Math.max(nums[i], Math.max(currentMax, currentMin));
            minProduct = Math.min(nums[i], Math.min(currentMax, currentMin));

            // 更新全局最大值
            result = Math.max(result, maxProduct);
        }

        return result;
    }

    /**
     * 扩展方法：返回乘积最大的连续子数组
     *
     * 时间复杂度分析：
     * - 遍历数组一次：O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     * - 返回子数组：O(k)，k为子数组长度
     *
     * @param nums 输入的整数数组
     * @return 乘积最大的连续子数组
     */
    public int[] getMaxProductSubarray(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];

        int maxProduct = nums[0];
        int minProduct = nums[0];
        int result = nums[0];
        int start = 0, end = 0, tempStart = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
            }

            int newMax = Math.max(nums[i], maxProduct * nums[i]);
            int newMin = Math.min(nums[i], minProduct * nums[i]);

            if (newMax > result) {
                result = newMax;
                end = i;
                start = tempStart;
            }

            if (newMax == nums[i]) {
                tempStart = i;
            }

            maxProduct = newMax;
            minProduct = newMin;
        }

        return Arrays.copyOfRange(nums, start, end + 1);
    }
}
