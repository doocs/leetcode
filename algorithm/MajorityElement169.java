package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数元素（LeetCode 169）
 *
 * 时间复杂度：
 * - 方法1（哈希表）：O(n)
 *   需要遍历数组一次
 * - 方法2（排序）：O(n log n)
 *   排序需要O(n log n)时间
 * - 方法3（Boyer-Moore投票算法）：O(n)
 *   需要遍历数组一次
 *
 * 空间复杂度：
 * - 方法1（哈希表）：O(n)
 *   需要哈希表存储元素计数
 * - 方法2（排序）：O(1)
 *   如果允许修改原数组，则为O(1)；否则需要O(n)空间复制数组
 * - 方法3（Boyer-Moore投票算法）：O(1)
 *   只使用常数个额外变量
 */
public class MajorityElement169 {

    /**
     * 方法1：哈希表解法
     *
     * 算法思路：
     * 使用哈希表统计每个元素出现的次数，然后找出出现次数超过n/2的元素
     *
     * 执行过程分析（以数组 [2,2,1,1,1,2,2] 为例）：
     *
     * 遍历过程：
     * 1. 处理2：map={2:1}
     * 2. 处理2：map={2:2}
     * 3. 处理1：map={2:2, 1:1}
     * 4. 处理1：map={2:2, 1:2}
     * 5. 处理1：map={2:2, 1:3}
     * 6. 处理2：map={2:3, 1:3}
     * 7. 处理2：map={2:4, 1:3}
     *
     * 检查计数：
     * n=7, n/2=3
     * 2的计数为4 > 3，返回2
     *
     * 时间复杂度分析：
     * - 遍历数组一次：O(n)
     * - 哈希表操作：O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 哈希表存储元素计数：O(n)
     * - 最坏情况下需要存储所有不同元素：O(n)
     *
     * @param nums 整数数组
     * @return 多数元素
     */
    public int majorityElementHashMap(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int n = nums.length;
        int majorityThreshold = n / 2;

        // 统计每个元素的出现次数
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);

            // 如果某个元素的计数超过n/2，直接返回
            if (countMap.get(num) > majorityThreshold) {
                return num;
            }
        }

        // 根据题目保证，一定存在多数元素，所以这里不会执行到
        return -1;
    }

    /**
     * 方法2：排序解法
     *
     * 算法思路：
     * 对数组进行排序，多数元素一定会出现在数组的中间位置
     * 因为多数元素出现次数超过n/2，所以无论在中间位置的左边还是右边，
     * 中间位置都一定是多数元素
     *
     * 执行过程分析（以数组 [2,2,1,1,1,2,2] 为例）：
     *
     * 排序前：[2,2,1,1,1,2,2]
     * 排序后：[1,1,1,2,2,2,2]
     * 中间位置：7/2 = 3
     * nums[3] = 2，即为多数元素
     *
     * 时间复杂度分析：
     * - 排序操作：O(n log n)
     * - 访问中间元素：O(1)
     * - 总时间复杂度：O(n log n)
     *
     * 空间复杂度分析：
     * - 如果允许修改原数组，则为O(1)
     * - 否则需要O(n)空间复制数组
     * - 排序算法本身可能需要额外空间，取决于具体实现
     *
     * @param nums 整数数组
     * @return 多数元素
     */
    public int majorityElementSort(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 方法3：Boyer-Moore投票算法（最优解）
     *
     * 算法思路：
     * 将多数元素记为+1，其他元素记为-1，将它们加起来，和一定大于0
     * 使用两个变量：
     * 1. candidate：候选的多数元素
     * 2. count：投票计数器
     *
     * 执行过程分析（以数组 [2,2,1,1,1,2,2] 为例）：
     *
     * 初始状态：candidate=0, count=0
     *
     * 遍历过程：
     * 1. 处理2：count=0，candidate=2, count=1
     * 2. 处理2：count=1，candidate=2, count=2
     * 3. 处理1：count=2，1≠2, count=1
     * 4. 处理1：count=1，1≠2, count=0
     * 5. 处理1：count=0，candidate=1, count=1
     * 6. 处理2：count=1，1≠2, count=0
     * 7. 处理2：count=0，candidate=2, count=1
     *
     * 最终candidate=2，即为多数元素
     *
     * 时间复杂度分析：
     * - 遍历数组一次：O(n)
     * - 每次操作：O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数个额外变量：O(1)
     * - candidate和count两个变量
     *
     * @param nums 整数数组
     * @return 多数元素
     */
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;

        // 投票过程
        for (int num : nums) {
            if (count == 0) {
                // 如果计数为0，更新候选元素
                candidate = num;
            }

            // 根据当前元素与候选元素是否相同更新计数
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        // 返回候选元素（根据题目保证，一定是多数元素）
        return candidate;
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
     * 主函数：处理用户输入并找出多数元素
     */
    public static void main(String[] args) {
        System.out.println("多数元素查找");

        // 读取用户输入的数组
        int[] nums = readArray();
        System.out.println("输入的数组: " + Arrays.toString(nums));

        // 计算多数元素
        MajorityElement169 solution = new MajorityElement169();
        int result1 = solution.majorityElementHashMap(nums);
        int result2 = solution.majorityElementSort(nums);
        int result3 = solution.majorityElement(nums);

        // 输出结果
        System.out.println("哈希表方法结果: " + result1);
        System.out.println("排序方法结果: " + result2);
        System.out.println("Boyer-Moore投票算法结果: " + result3);
    }

}
