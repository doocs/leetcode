package com.funian.algorithm.algorithm;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 最长连续序列（LeetCode 128）
 *
 * 时间复杂度：O(n)
 * - 虽然看起来有嵌套循环，但每个元素最多被访问两次
 * - 外层循环遍历所有唯一元素：O(n)
 * - 内层while循环只对序列的起始元素执行，总共O(n)
 * - 总体时间复杂度为O(n)
 *
 * 空间复杂度：O(n)
 * - 使用HashSet存储所有数组元素：O(n)
 */
public class LongestConsecutive128 {
    public static void main(String[] args) {
        // 创建 Scanner 对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入数组
        System.out.println("输入数组：");

        // 读取一行输入
        String line = scanner.nextLine();

        // 按空格分割字符串得到字符串数组
        String[] strs = line.split(" ");

        // 创建整型数组
        int[] nums = new int[strs.length];

        // 将字符串数组转换为整型数组
        for (int i = 0; i < strs.length; i++) {
            nums[i] = Integer.parseInt(strs[i]);
        }

        // 调用 longestConsect 方法查找最长连续序列长度
        int result = longestConsect(nums);

        // 输出结果
        System.out.println("最长连续序列长度为：" + result);
    }

    /**
     * 最长连续子序列
     * 在给定整数数组中找到数字连续的最长序列的长度
     *
     * 算法思路：
     * 1. 使用HashSet存储所有数字，实现O(1)查找和去重
     * 2. 对于每个数字，只有当它是序列起点时（num-1不存在）才开始计算序列长度
     * 3. 从起点开始向上查找连续数字，计算序列长度
     * 4. 记录并更新最长序列长度
     *
     * 示例过程（以数组 [100,4,200,1,3,2] 为例）：
     *
     * numSet = {100, 4, 200, 1, 3, 2}
     *
     * 遍历过程：
     * num=100: num-1=99不存在，是起点
     *          序列: 100，长度: 1
     * num=4:   num-1=3存在，不是起点，跳过
     * num=200: num-1=199不存在，是起点
     *          序列: 200，长度: 1
     * num=1:   num-1=0不存在，是起点
     *          序列: 1->2->3->4，长度: 4
     * num=3:   num-1=2存在，不是起点，跳过
     * num=2:   num-1=1存在，不是起点，跳过
     *
     * 最长序列: [1,2,3,4]，长度: 4
     *
     * 时间复杂度分析：
     * - 创建HashSet：O(n)，其中n为输入数组`nums`的长度
     * - 外层for循环：O(m)，其中m为HashSet中唯一元素的个数
     * - 内层while循环：总共O(m)（每个元素最多被访问两次）
     *   - 虽然有嵌套循环，但只有当元素是连续序列起点时才会执行while循环
     *   - 每个元素最多作为起点访问一次，作为序列中元素访问一次
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - HashSet存储元素：O(m)，存储输入数组中的所有唯一元素，最坏情况下m=n
     * - 其他变量：O(1)，使用常数个额外变量
     * - 总空间复杂度：O(n)
     *
     * @param nums 输入的整数数组
     * @return 最长连续序列的长度
     */
    public static int longestConsect(int[] nums) {
        // 使用HashSet存储所有数字，用于O(1)时间复杂度的查找，并去重
        Set<Integer> numSet = new HashSet<>();

        // 遍历输入数组，将所有数字添加到HashSet中
        for (int num : nums) {
            numSet.add(num);
        }

        // 记录最长连续序列的长度
        int longestCount = 0;

        // 遍历HashSet中的每个数字
        for (int num : numSet) {
            // 只有当num-1不存在时，num才是一个序列的起点
            // 这样避免了重复计算序列的一部分
            if (!numSet.contains(num - 1)) {
                // 当前序列的起始数字
                int currentNum = num;

                // 当前序列的长度，初始为1（包含起始数字）
                int currentCount = 1;

                // 向上查找连续的数字
                while (numSet.contains(currentNum + 1)) {
                    // 如果存在下一个连续数字，则更新currentNum
                    currentNum++;
                    // 增加当前序列长度
                    currentCount++;
                }

                // 更新最长序列长度
                longestCount = Math.max(longestCount, currentCount);
            }
        }

        // 返回最长连续序列的长度
        return longestCount;
    }

    /**
     * 方法2：排序后遍历（时间复杂度较高但思路简单）
     *
     * 算法思路：
     * 1. 先对数组进行排序
     * 2. 遍历排序后的数组，统计连续序列长度
     *
     * 示例过程（以数组 [100,4,200,1,3,2] 为例）：
     *
     * 排序后数组: [1, 2, 3, 4, 100, 200]
     *
     * 遍历过程：
     * i=1: nums[1]=2, nums[0]=1, 连续，currentCount=2
     * i=2: nums[2]=3, nums[1]=2, 连续，currentCount=3
     * i=3: nums[3]=4, nums[2]=3, 连续，currentCount=4
     * i=4: nums[4]=100, nums[3]=4, 不连续，更新longestCount=4，重置currentCount=1
     * i=5: nums[5]=200, nums[4]=100, 不连续，longestCount仍为4，currentCount=1
     *
     * 最长序列长度: 4
     *
     * 时间复杂度分析：
     * - 排序操作：O(n log n)，其中n为输入数组`nums`的长度
     * - 遍历数组：O(n)，单次遍历排序后的数组
     * - 总时间复杂度：O(n log n)
     *
     * 空间复杂度分析：
     * - 排序算法可能需要额外空间：O(log n)到O(n)
     *   - 快速排序需要O(log n)栈空间
     *   - 归并排序需要O(n)额外数组空间
     * - 其他变量：O(1)，使用常数个额外变量
     * - 总空间复杂度：O(log n)到O(n)
     *
     * @param nums 输入的整数数组
     * @return 最长连续序列的长度
     */
    public static int longestConsecutiveSort(int[] nums) {
        // 边界条件：空数组
        if (nums.length == 0) {
            return 0;
        }

        // 对数组进行排序
        java.util.Arrays.sort(nums);

        // 记录最长连续序列长度
        int longestCount = 1;
        // 记录当前连续序列长度
        int currentCount = 1;

        // 从第二个元素开始遍历
        for (int i = 1; i < nums.length; i++) {
            // 如果当前元素等于前一个元素，跳过（去重）
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            // 如果当前元素等于前一个元素+1，说明连续
            else if (nums[i] == nums[i - 1] + 1) {
                currentCount++;
            }
            // 否则，连续序列中断
            else {
                // 更新最长序列长度
                longestCount = Math.max(longestCount, currentCount);
                // 重置当前序列长度
                currentCount = 1;
            }
        }

        // 最后一次更新最长序列长度
        return Math.max(longestCount, currentCount);
    }

    /**
     * 方法3：并查集解法
     *
     * 算法思路：
     * 1. 使用HashMap存储数字和对应的索引
     * 2. 使用并查集将相邻的数字合并到同一集合
     * 3. 返回最大集合的大小
     *
     * 示例过程（以数组 [100,4,200,1,3,2] 为例）：
     *
     * 初始化并查集，每个元素独立成集合:
     * 集合: {0}, {1}, {2}, {3}, {4}, {5}
     *
     * 处理过程:
     * i=0, nums[0]=100: 无相邻元素，集合不变
     * i=1, nums[1]=4: 有相邻元素3(nums[4])，合并{1}和{4} -> {1,4}
     * i=2, nums[2]=200: 无相邻元素，集合不变
     * i=3, nums[3]=1: 有相邻元素2(nums[5])，合并{3}和{5} -> {3,5}
     * i=4, nums[4]=3: 有相邻元素2(nums[5])和4(nums[1])，合并{4,1}和{3,5} -> {1,3,4,5}
     * i=5, nums[5]=2: 有相邻元素1(nums[3])和3(nums[4])，已在同一集合
     *
     * 最大集合{1,3,4,5}大小为4，对应序列[1,2,3,4]
     *
     * 时间复杂度分析：
     * - 遍历数组：O(n)，其中n为输入数组`nums`的长度
     * - 并查集操作：近似O(1)（考虑路径压缩和按秩合并）
     *   - [find](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/NumIsLands200.java#L311-L319)操作经过路径压缩后接近O(1)
     *   - [union](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/NumIsLands200.java#L321-L345)操作经过按秩合并后接近O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - HashMap存储元素：O(m)，存储数组中的唯一元素及其索引，m为唯一元素个数
     * - 并查集数组：O(n)，[parent](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/NumIsLands200.java#L281-L281)数组和[size](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/LRUCache146.java#L40-L40)数组各需要O(n)空间，n为输入数组长度
     * - 总空间复杂度：O(n)
     *
     * @param nums 输入的整数数组
     * @return 最长连续序列的长度
     */
    public static int longestConsecutiveUnionFind(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // 使用HashMap存储数字和对应的索引
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        UnionFind uf = new UnionFind(nums.length);

        // 遍历数组，建立并查集
        for (int i = 0; i < nums.length; i++) {
            // 如果数字已存在，跳过
            if (map.containsKey(nums[i])) {
                continue;
            }

            // 将数字和索引关联
            map.put(nums[i], i);

            // 如果前一个数字存在，合并两个集合
            if (map.containsKey(nums[i] - 1)) {
                uf.union(i, map.get(nums[i] - 1));
            }

            // 如果后一个数字存在，合并两个集合
            if (map.containsKey(nums[i] + 1)) {
                uf.union(i, map.get(nums[i] + 1));
            }
        }

        // 返回最大集合的大小
        return uf.getMaxSize();
    }

    /**
     * 并查集辅助类
     */
    static class UnionFind {
        private int[] parent;
        private int[] size;
        private int maxSize;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            maxSize = 1;

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                // 按秩合并
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
                maxSize = Math.max(maxSize, size[rootY]);
            }
        }

        public int getMaxSize() {
            return maxSize;
        }
    }
}
