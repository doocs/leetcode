package com.funian.algorithm.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 找到字符串中所有字母异位词（LeetCode 438）
 *
 * 时间复杂度：O(n)
 * - n 是字符串 s 的长度
 * - 初始化 pCount 数组需要 O(m) 时间，m 是字符串 p 的长度
 * - 遍历字符串 s 需要 O(n) 时间
 * - 每次比较两个数组需要 O(26) = O(1) 时间
 * - 总时间复杂度：O(m) + O(n) = O(n)（假设 m <= n）
 *
 * 空间复杂度：O(1)
 * - 使用了两个固定大小的数组 pCount 和 sCount，大小均为 26
 * - 结果列表的空间不计入，因为它是输出的一部分
 */
public class FindAnagrams438 {
    public static void main(String[] args) {
        // 创建 Scanner 对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入主字符串 s
        System.out.println("输入字符串：");

        // 读取主字符串 s
        String s = scanner.nextLine();

        // 提示用户输入模式字符串 p
        System.out.println("输入字符串：");

        // 读取模式字符串 p
        String p = scanner.nextLine();

        // 调用 findAnagrams 方法查找所有字母异位词的起始位置
        List<Integer> result = findAnagrams(s, p);

        // 输出结果
        System.out.println("结果：" + result);
    }

    /**
     * 找出字符串 s 中所有与字符串 p 互为字母异位词的子串的起始索引
     * 字母异位词指字母相同，但排列不同的字符串
     *
     * 算法思路：
     * 1. 使用滑动窗口技术，窗口大小固定为字符串 p 的长度
     * 2. 统计字符串 p 中每个字符的出现次数
     * 3. 使用滑动窗口遍历字符串 s，维护窗口内字符的出现次数
     * 4. 比较窗口内字符统计与 p 的字符统计是否相同
     * 5. 如果相同，则窗口起始位置是一个有效的字母异位词
     *
     * 示例过程（以 s="cbaebabacd", p="abc" 为例）：
     * p的字符统计: a:1, b:1, c:1
     *
     * 窗口位置          窗口内容  字符统计         是否匹配  结果
     * [0,2]  cba      a:1,b:1,c:1  是         [0]
     * [1,3]  bae      a:1,b:1,e:1  否         [0]
     * [2,4]  aeb      a:1,b:1,e:1  否         [0]
     * [3,5]  eba      a:1,b:1,e:1  否         [0]
     * [4,6]  bab      a:1,b:2      否         [0]
     * [5,7]  aba      a:2,b:1      否         [0]
     * [6,8]  bac      a:1,b:1,c:1  是         [0,6]
     * [7,9]  acd      a:1,c:1,d:1  否         [0,6]
     *
     * 最终结果：[0,6]
     *
     * 时间复杂度分析：
     * - 统计字符串p的字符：O(m)，m为p的长度
     * - 滑动窗口遍历字符串s：O(n)，n为s的长度
     * - 比较字符统计数组：O(26) = O(1)
     * - 总时间复杂度：O(n)，其中n为输入字符串`s`的长度
     *
     * 空间复杂度分析：
     * - 两个字符统计数组：O(26) = O(1)
     * - 结果列表：O(k)，k为结果数量
     * - 总空间复杂度：O(1)
     *
     * @param s 主字符串
     * @param p 模式字符串
     * @return 所有字母异位词的起始索引列表
     */
    public static List<Integer> findAnagrams(String s, String p) {
        // 存储结果的列表
        List<Integer> result = new ArrayList<>();

        // 边界条件检查：如果 s 为空或长度小于 p，直接返回空列表
        if (s == null || s.length() < p.length()) {
            return result;
        }

        // 使用数组统计字符出现次数（假设只包含小写字母 a-z）
        int[] pCount = new int[26];
        int[] sCount = new int[26];

        // 获取字符串 p 和 s 的长度
        int pp = p.length();
        int ss = s.length();

        // 统计字符串 p 中每个字符的出现次数
        for (int i = 0; i < pp; i++) {
            pCount[p.charAt(i) - 'a']++;
        }

        // 使用滑动窗口遍历字符串 s
        for (int i = 0; i < ss; i++) {
            // 将当前字符加入窗口统计
            sCount[s.charAt(i) - 'a']++;

            // 如果窗口大小超过 p 的长度，需要移除窗口左侧的字符
            if (i >= pp) {
                // 移除窗口左侧的字符（i - pp 位置的字符）
                sCount[s.charAt(i - pp) - 'a']--;
            }

            // 检查当前窗口是否与字符串 p 构成字母异位词
            if (isEqual(pCount, sCount)) {
                // 如果是字母异位词，将起始位置添加到结果列表中
                result.add(i - pp + 1);
            }
        }

        // 返回所有字母异位词的起始索引
        return result;
    }


    /**
     * 判断两个字符统计数组是否相等
     * 比较两个数组中每个字符的出现次数是否完全相同
     *
     * 时间复杂度分析：
     * - 遍历26个字符：O(26) = O(1)
     * - 总时间复杂度：O(1)
     *
     * 空间复杂度分析：
     * - 只使用了循环变量：O(1)
     * - 总空间复杂度：O(1)
     *
     * @param pCount 字符串 p 的字符统计数组
     * @param sCount 滑动窗口的字符统计数组
     * @return 如果两个数组相等返回 true，否则返回 false
     */
    public static boolean isEqual(int[] pCount, int[] sCount) {
        // 遍历数组比较每个字符的出现次数
        for (int i = 0; i < 26; i++) {
            // 如果有任何字符的出现次数不相等，则两个数组不相等
            if (pCount[i] != sCount[i]) {
                return false;
            }
        }
        // 所有字符的出现次数都相等，两个数组相等
        return true;
    }

    /**
     * 方法2：优化版本（使用差异计数）
     *
     * 算法思路：
     * 使用一个变量记录两个数组之间的差异，避免每次都比较整个数组
     *
     * 示例过程（以 s="cbaebabacd", p="abc" 为例）：
     *
     * 初始化:
     * count数组统计初始窗口与p的差异:
     * s[0:2]="cba", p="abc"
     * count['c'] = 1-0 = 1, count['b'] = 1-0 = 1, count['a'] = 1-0 = 1
     * count['a'] = 1-1 = 0, count['b'] = 1-1 = 0, count['c'] = 1-1 = 0
     * 最终count数组全为0，diff=0，匹配，添加索引0
     *
     * 滑动过程:
     * i=3, 移入'e', 移出'c':
     * count['e']从0变为1，diff++；count['c']从0变为-1，diff++
     * diff=2≠0，不匹配
     *
     * i=4, 移入'a', 移出'b':
     * count['a']从1变为0，diff--；count['b']从0变为-1，diff++
     * diff=1≠0，不匹配
     *
     * ...继续滑动直到i=6时重新匹配
     *
     * 时间复杂度分析：
     * - 初始化：O(m)，m为p的长度
     * - 滑动窗口：O(n)，n为s的长度
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 字符计数数组：O(26) = O(1)
     * - 其他变量：O(1)
     * - 总空间复杂度：O(1)
     *
     * @param s 主字符串
     * @param p 模式字符串
     * @return 所有字母异位词的起始索引列表
     */
    public static List<Integer> findAnagramsOptimized(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s == null || s.length() < p.length()) {
            return result;
        }

        int[] count = new int[26];
        int pLen = p.length();

        // 初始化字符计数差异数组
        for (int i = 0; i < pLen; i++) {
            count[s.charAt(i) - 'a']++;
            count[p.charAt(i) - 'a']--;
        }

        // 计算初始差异
        int diff = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                diff++;
            }
        }

        if (diff == 0) {
            result.add(0);
        }

        // 滑动窗口
        for (int i = pLen; i < s.length(); i++) {
            // 添加新字符
            int rightChar = s.charAt(i) - 'a';
            if (count[rightChar] == 0) {
                diff++;
            }
            count[rightChar]++;
            if (count[rightChar] == 0) {
                diff--;
            }

            // 移除旧字符
            int leftChar = s.charAt(i - pLen) - 'a';
            if (count[leftChar] == 0) {
                diff++;
            }
            count[leftChar]--;
            if (count[leftChar] == 0) {
                diff--;
            }

            // 检查是否匹配
            if (diff == 0) {
                result.add(i - pLen + 1);
            }
        }

        return result;
    }

    /**
     * 方法3：使用HashMap实现
     *
     * 算法思路：
     * 使用HashMap统计字符出现次数，避免数组大小限制
     *
     * 示例过程（以 s="cbaebabacd", p="abc" 为例）：
     *
     * pMap = {'a':1, 'b':1, 'c':1}
     *
     * i=0: windowMap = {'c':1}
     * i=1: windowMap = {'c':1, 'b':1}
     * i=2: windowMap = {'c':1, 'b':1, 'a':1} == pMap，添加索引0
     * i=3: 移入'e'，移出'c'，windowMap = {'b':1, 'a':1, 'e':1} ≠ pMap
     * i=4: 移入'a'，移出'b'，windowMap = {'a':2, 'e':1} ≠ pMap
     * ...
     * i=6: windowMap = {'b':1, 'a':1, 'c':1} == pMap，添加索引6
     *
     * 时间复杂度分析：
     * - 滑动窗口遍历：O(n)，n为s的长度
     * - HashMap操作：O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - HashMap存储字符：O(k)，k为不同字符数
     * - 结果列表：O(m)
     * - 总空间复杂度：O(k+m)
     *
     * @param s 主字符串
     * @param p 模式字符串
     * @return 所有字母异位词的起始索引列表
     */
    public static List<Integer> findAnagramsHashMap(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s == null || s.length() < p.length()) {
            return result;
        }

        java.util.Map<Character, Integer> pMap = new java.util.HashMap<>();
        java.util.Map<Character, Integer> windowMap = new java.util.HashMap<>();

        // 统计p中字符出现次数
        for (char c : p.toCharArray()) {
            pMap.put(c, pMap.getOrDefault(c, 0) + 1);
        }

        int pLen = p.length();

        // 滑动窗口
        for (int i = 0; i < s.length(); i++) {
            char rightChar = s.charAt(i);
            windowMap.put(rightChar, windowMap.getOrDefault(rightChar, 0) + 1);

            // 如果窗口大小超过p的长度，移除左侧字符
            if (i >= pLen) {
                char leftChar = s.charAt(i - pLen);
                windowMap.put(leftChar, windowMap.get(leftChar) - 1);
                if (windowMap.get(leftChar) == 0) {
                    windowMap.remove(leftChar);
                }
            }

            // 检查是否匹配
            if (windowMap.equals(pMap)) {
                result.add(i - pLen + 1);
            }
        }

        return result;
    }
}
