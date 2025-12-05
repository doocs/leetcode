package com.funian.algorithm.algorithm;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 无重复字符的最长子串（LeetCode 3）
 *
 * 时间复杂度：O(n)
 * - 虽然有嵌套结构，但每个字符最多被访问两次（一次被右指针访问，一次被左指针访问）
 * - 左右指针都只向前移动，不会回退
 *
 * 空间复杂度：O(min(m,n))
 * - m 是字符集大小（如ASCII字符集大小为128）
 * - HashSet 最多存储 min(m,n) 个字符
 * - n 是字符串长度
 */
public class LongestSubstring3 {
    public static void main(String[] args) {
        // 创建 Scanner 对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入字符串
        System.out.println("输入字符串：");

        // 读取用户输入的字符串
        String s = scanner.nextLine();

        // 调用 longestSubstring 方法计算最长无重复子串长度
        int result = longestSubstring(s);

        // 输出结果
        System.out.println("不重复的字符串长度为：" + result);
    }

    /**
     * 计算字符串中无重复字符的最长子串长度
     * 使用滑动窗口 + HashSet 的方法
     *
     * 算法思路：
     * 1. 使用双指针维护一个滑动窗口 [left, right)
     * 2. 使用 HashSet 记录当前窗口中包含的字符
     * 3. 右指针不断向右扩展窗口
     * 4. 当遇到重复字符时，左指针向右收缩窗口直到没有重复字符
     * 5. 记录过程中窗口的最大长度
     *
     * 示例过程（以字符串 "abcabcbb" 为例）：
     * 步骤  窗口       HashSet          maxLen
     * 1    [a)        {a}              1
     * 2    [ab)       {a,b}            2
     * 3    [abc)      {a,b,c}          3
     * 4    [abca)     发现重复a        -
     * 5    [bca)      {b,c,a}          3
     * 6    [bcab)     发现重复b        -
     * 7    [cab)      {c,a,b}          3
     * 8    [cabc)     发现重复c        -
     * 9    [abc)      {a,b,c}          3
     * 10   [abcb)     发现重复b        -
     * 11   [bcb)      发现重复b        -
     * 12   [cb)       {c,b}            3
     * 13   [cbb)      发现重复b        -
     * 14   [bb)       发现重复b        -
     * 15   [b)        {b}              3
     * 16   [)         {}               3
     * 结果：最长无重复子串长度为3
     *
     * 时间复杂度分析：
     * - 右指针遍历字符串：O(n)，其中n为输入字符串`s`的长度
     * - 左指针最多移动n次：O(n)
     * - HashSet操作：O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - HashSet存储字符：O(min(m,n))，m为字符集大小，n为字符串长度
     * - 其他变量：O(1)
     * - 总空间复杂度：O(min(m,n))
     *
     * @param s 输入的字符串
     * @return 无重复字符的最长子串长度
     */
    public static int longestSubstring(String s) {
        // 使用 HashSet 存储当前窗口中的字符，用于快速判断是否有重复
        Set<Character> set = new HashSet<>();

        // 滑动窗口的左边界（包含）
        int left = 0;

        // 滑动窗口的右边界（不包含）
        int right = 0;

        // 获取字符串长度
        int n = s.length();

        // 记录最长子串长度
        int maxLength = 0;

        // 右指针遍历整个字符串
        while (right < n) {
            // 如果右指针指向的字符不在当前窗口中
            if (!set.contains(s.charAt(right))) {
                // 将该字符加入 HashSet
                set.add(s.charAt(right));

                // 右指针右移，扩展窗口
                right++;

                // 更新最大长度（窗口大小为 right - left）
                maxLength = Math.max(maxLength, right - left);
            } else {
                // 如果右指针指向的字符在当前窗口中（出现重复）
                // 从 HashSet 中移除左指针指向的字符
                set.remove(s.charAt(left));

                // 左指针右移，收缩窗口
                left++;
            }
        }

        // 返回最长无重复子串的长度
        return maxLength;
    }

    /**
     * 方法2：优化的滑动窗口（使用HashMap记录字符位置）
     *
     * 算法思路：
     * 使用HashMap记录每个字符最后出现的位置，当遇到重复字符时可以直接跳转
     *
     * 示例过程（以字符串 "abcabcbb" 为例）：
     *
     * 初始化: map = {}, left = 0, maxLength = 0
     *
     * right=0, c='a': map不包含'a'，更新map={'a':0}，maxLength=max(0,0-0+1)=1
     * right=1, c='b': map不包含'b'，更新map={'a':0,'b':1}，maxLength=max(1,1-0+1)=2
     * right=2, c='c': map不包含'c'，更新map={'a':0,'b':1,'c':2}，maxLength=max(2,2-0+1)=3
     * right=3, c='a': map包含'a'且位置0>=left(0)，left=0+1=1，更新map={'a':3,'b':1,'c':2}，maxLength=max(3,3-1+1)=3
     * right=4, c='b': map包含'b'且位置1>=left(1)，left=1+1=2，更新map={'a':3,'b':4,'c':2}，maxLength=max(3,4-2+1)=3
     * right=5, c='c': map包含'c'且位置2>=left(2)，left=2+1=3，更新map={'a':3,'b':4,'c':5}，maxLength=max(3,5-3+1)=3
     * right=6, c='b': map包含'b'且位置4>=left(3)，left=4+1=5，更新map={'a':3,'b':6,'c':5}，maxLength=max(3,6-5+1)=2
     * right=7, c='b': map包含'b'且位置6>=left(5)，left=6+1=7，更新map={'a':3,'b':7,'c':5}，maxLength=max(2,7-7+1)=2
     *
     * 最终结果：maxLength = 3
     *
     * 时间复杂度分析：
     * - 单次遍历字符串：O(n)，其中n为输入字符串`s`的长度
     * - HashMap操作：O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - HashMap存储字符位置：O(min(m,n))，m为字符集大小，n为字符串长度
     * - 其他变量：O(1)
     * - 总空间复杂度：O(min(m,n))
     *
     * @param s 输入的字符串
     * @return 无重复字符的最长子串长度
     */
    public static int longestSubstringOptimized(String s) {
        // 使用HashMap存储字符及其最后出现的位置
        java.util.Map<Character, Integer> map = new java.util.HashMap<>();
        int left = 0;
        int maxLength = 0;

        // 遍历字符串
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // 如果字符已存在且在当前窗口内
            if (map.containsKey(c) && map.get(c) >= left) {
                // 直接将左指针跳转到重复字符的下一个位置
                left = map.get(c) + 1;
            }

            // 更新字符的最新位置
            map.put(c, right);

            // 更新最大长度
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    /**
     * 方法3：使用数组代替HashSet（适用于ASCII字符）
     *
     * 算法思路：
     * 使用布尔数组代替HashSet，提高访问速度
     *
     * 示例过程（以字符串 "abcabcbb" 为例）：
     *
     * 初始化: visited = [128个false], left = 0, maxLength = 0
     *
     * right=0, c='a'(97): visited[97]=false，标记visited[97]=true，maxLength=max(0,0-0+1)=1
     * right=1, c='b'(98): visited[98]=false，标记visited[98]=true，maxLength=max(1,1-0+1)=2
     * right=2, c='c'(99): visited[99]=false，标记visited[99]=true，maxLength=max(2,2-0+1)=3
     * right=3, c='a'(97): visited[97]=true，进入while循环
     *                    s.charAt(0)='a'==c，跳出while，left=1，标记visited[97]=true，maxLength=max(3,3-1+1)=3
     * right=4, c='b'(98): visited[98]=true，进入while循环
     *                    s.charAt(1)='b'==c，跳出while，left=2，标记visited[98]=true，maxLength=max(3,4-2+1)=3
     * ...
     *
     * 时间复杂度分析：
     * - 单次遍历字符串：O(n)，其中n为输入字符串`s`的长度
     * - 数组访问：O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 布尔数组：O(128) = O(1)，固定大小的ASCII字符集
     * - 其他变量：O(1)
     * - 总空间复杂度：O(1)
     *
     * @param s 输入的字符串
     * @return 无重复字符的最长子串长度
     */
    public static int longestSubstringArray(String s) {
        // 使用布尔数组记录字符是否在当前窗口中（假设为ASCII字符）
        boolean[] visited = new boolean[128];
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // 如果字符已在当前窗口中
            if (visited[c]) {
                // 收缩窗口直到没有重复字符
                while (s.charAt(left) != c) {
                    visited[s.charAt(left)] = false;
                    left++;
                }
                // 跳过重复字符
                left++;
            }

            // 标记字符为已访问
            visited[c] = true;

            // 更新最大长度
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
