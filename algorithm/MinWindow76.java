package com.funian.algorithm.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 最小覆盖子串（LeetCode 76）
 *
 * 时间复杂度：O(|s| + |t|)
 * - |s| 是字符串 s 的长度，|t| 是字符串 t 的长度
 * - 需要遍历字符串 s 一次：O(|s|)
 * - 需要遍历字符串 t 一次：O(|t|)
 * - 每个字符最多被访问两次（一次被右指针，一次被左指针）
 *
 * 空间复杂度：O(|s| + |t|)
 * - HashMap 存储字符串 t 中字符的频率：O(|t|)
 * - HashMap 存储滑动窗口中字符的频率：最坏情况 O(|s|)
 */
public class MinWindow76 {
    public static void main(String[] args) {
        // 创建 Scanner 对象读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入字符串 s
        System.out.print("请输入字符串 s：");

        // 读取字符串 s
        String s = scanner.nextLine();

        // 提示用户输入字符串 t
        System.out.print("请输入字符串 t：");

        // 读取字符串 t
        String t = scanner.nextLine();

        // 调用 minWindow 方法返回结果
        String result = minWindow(s, t);

        // 输出结果
        System.out.println("最小覆盖子串为：" + result);
    }

    /**
     * 找到字符串 s 中涵盖字符串 t 所有字符的最小子串
     *
     * 算法思路：
     * 使用滑动窗口技术，通过双指针维护一个窗口
     * 1. 右指针不断扩展窗口，直到窗口包含 t 中的所有字符
     * 2. 当窗口满足条件时，左指针尝试收缩窗口以找到最小解
     * 3. 维护一个有效字符计数器，记录窗口中满足字符数量要求的字符种类数
     *
     * 示例过程（以 s="ADOBECODEBANC", t="ABC" 为例）：
     *
     * 步骤  窗口           包含字符  有效字符数  是否满足  最小窗口
     * 1    [A)            A:1       1          否       ""
     * 2    [AD)           A:1,D:1   1          否       ""
     * 3    [ADO)          A:1,D:1,O:1 1        否       ""
     * 4    [ADOB)         A:1,B:1,D:1,O:1 2    否       ""
     * 5    [ADOBE)        A:1,B:1,D:1,E:1,O:1 2 否      ""
     * 6    [ADOBEC)       A:1,B:1,C:1,...     3 是      "ADOBEC"
     * 7    A[D OBEC)      A:0,B:1,C:1,...     2 否      "ADOBEC"
     * ...继续滑动直到找到更优解...
     * 最终结果: "BANC"
     *
     * 时间复杂度分析：
     * - 遍历字符串 t 统计字符频率：O(|t|)，其中|t|为字符串`t`的长度
     * - 滑动窗口遍历字符串 s：O(|s|)，其中|s|为字符串`s`的长度
     * - 每个字符最多被访问两次（右指针一次，左指针一次）：O(|s|)
     * - HashMap操作：O(1)
     * - 总时间复杂度：O(|s| + |t|)
     *
     * 空间复杂度分析：
     * - targetMap存储t中字符频率：O(|t|)
     * - windowMap存储窗口中字符频率：最坏情况O(|s|)
     * - 其他变量：O(1)
     * - 总空间复杂度：O(|s| + |t|)
     *
     * @param s 主字符串
     * @param t 需要覆盖的字符集合
     * @return 最小覆盖子串，如果不存在则返回空字符串
     */
    public static String minWindow(String s, String t) {
        // 边界条件检查：如果任一字符串为空，返回空字符串
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }

        // 记录 t 中每个字符的需求数量
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        // 滑动窗口所需的变量
        Map<Character, Integer> windowMap = new HashMap<>(); // 记录窗口中每个字符的实际数量
        int left = 0, right = 0; // 左右指针，表示窗口的范围 [left, right)
        int valid = 0; // 符合条件的字符种类数（窗口中数量满足需求的字符种类）
        int minLen = Integer.MAX_VALUE; // 最小窗口长度，初始化为最大整数值
        int start = 0; // 最小窗口的起始位置

        // 右指针遍历字符串 s
        while (right < s.length()) {
            // 获取当前右边界字符
            char c = s.charAt(right);
            // 扩展右边界
            right++;

            // 如果该字符在 t 中，则加入窗口进行处理
            if (targetMap.containsKey(c)) {
                // 更新窗口中该字符的数量
                windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
                // 如果窗口中的该字符数量和 t 中需求一致，则有效字符数 +1
                if (windowMap.get(c).equals(targetMap.get(c))) {
                    valid++; // 有效字符种类数增加
                }
            }

            // 当窗口内的字符已经满足 t 中所有字符时，尝试收缩左边界
            while (valid == targetMap.size()) {
                // 更新最小窗口长度和起始位置
                if (right - left < minLen) {
                    minLen = right - left; // 更新最小窗口长度
                    start = left; // 更新最小窗口起始位置
                }

                // 获取左边界字符
                char d = s.charAt(left);
                // 收缩左边界
                left++;

                // 如果该字符在 t 中，收缩窗口时需要进行处理
                if (targetMap.containsKey(d)) {
                    // 如果该字符数量减少到不再满足需求，则有效字符数 -1
                    if (windowMap.get(d).equals(targetMap.get(d))) {
                        valid--; // 有效字符种类数减少
                    }
                    // 更新窗口中的字符数量
                    windowMap.put(d, windowMap.get(d) - 1);
                }
            }
        }

        // 如果 minLen 没有被更新过，说明没有找到符合条件的子串，返回空字符串
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    /**
     * 方法2：优化版本（使用数组代替HashMap）
     *
     * 算法思路：
     * 使用数组代替HashMap提高访问速度，适用于ASCII字符
     *
     * 示例过程（以 s="ADOBECODEBANC", t="ABC" 为例）：
     *
     * 1. 初始化:
     *    targetCount数组统计t中字符频率: targetCount['A']=1, targetCount['B']=1, targetCount['C']=1
     *    required=3 (t中不同字符数)
     *
     * 2. 滑动窗口过程:
     *    right=0, c='A': windowCount['A']++, formed=1
     *    right=1, c='D': targetCount['D']=0, 跳过
     *    right=2, c='O': targetCount['O']=0, 跳过
     *    right=3, c='B': windowCount['B']++, formed=2
     *    right=4, c='E': targetCount['E']=0, 跳过
     *    right=5, c='C': windowCount['C']++, formed=3 == required
     *
     *    进入收缩阶段: window="ADOBEC", len=6, start=0
     *    left=0, d='A': windowCount['A']--, formed=2
     *    left=1, d='D': targetCount['D']=0, 跳过
     *    left=2, d='O': targetCount['O']=0, 跳过
     *    left=3, d='B': windowCount['B']--, formed=1
     *    ...
     *
     * 时间复杂度分析：
     * - 遍历字符串 t 统计字符频率：O(|t|)
     * - 滑动窗口遍历字符串 s：O(|s|)
     * - 数组访问：O(1)
     * - 总时间复杂度：O(|s| + |t|)
     *
     * 空间复杂度分析：
     * - 两个字符计数数组：O(128) = O(1)
     * - 其他变量：O(1)
     * - 总空间复杂度：O(1)
     *
     * @param s 主字符串
     * @param t 需要覆盖的字符集合
     * @return 最小覆盖子串，如果不存在则返回空字符串
     */
    public static String minWindowOptimized(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }

        // 使用数组记录字符频率（假设为ASCII字符）
        int[] targetCount = new int[128];
        int[] windowCount = new int[128];

        // 统计t中字符频率
        for (char c : t.toCharArray()) {
            targetCount[c]++;
        }

        // 统计t中不同字符的数量
        int required = 0;
        for (int count : targetCount) {
            if (count > 0) {
                required++;
            }
        }

        int left = 0, right = 0;
        int formed = 0; // 窗口中满足频率要求的字符种类数
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;

            if (targetCount[c] > 0) {
                windowCount[c]++;
                if (windowCount[c] == targetCount[c]) {
                    formed++;
                }
            }

            while (formed == required) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }

                char d = s.charAt(left);
                left++;

                if (targetCount[d] > 0) {
                    if (windowCount[d] == targetCount[d]) {
                        formed--;
                    }
                    windowCount[d]--;
                }
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    /**
     * 方法3：暴力解法（仅供学习对比，效率较低）
     *
     * 算法思路：
     * 遍历所有可能的子串，检查是否包含t的所有字符
     *
     * 示例过程（以 s="ADOBECODEBANC", t="ABC" 为例）：
     *
     * i=0: 子串"A"不包含ABC; "AD"不包含ABC; ...; "ADOBEC"包含ABC，记录
     * i=1: 子串"D"不包含ABC; "DO"不包含ABC; ...继续检查
     * ...
     * 需要检查所有O(|s|²)个子串，每个子串需要O(|s|)时间统计字符
     *
     * 时间复杂度分析：
     * - 外层循环遍历起始位置：O(|s|)
     * - 内层循环遍历结束位置：O(|s|)
     * - containsAll方法检查字符包含：O(|t|)
     * - substring方法创建子串：O(|s|)
     * - 总时间复杂度：O(|s|³)
     *
     * 空间复杂度分析：
     * - targetMap存储t中字符频率：O(|t|)
     * - windowMap存储窗口中字符频率：O(|s|)
     * - minWindow存储结果子串：O(|s|)
     * - 总空间复杂度：O(|s| + |t|)
     *
     * @param s 主字符串
     * @param t 需要覆盖的字符集合
     * @return 最小覆盖子串，如果不存在则返回空字符串
     */
    public static String minWindowBruteForce(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }

        // 统计t中字符频率
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        String minWindow = "";
        int minLen = Integer.MAX_VALUE;

        // 遍历所有可能的子串
        for (int i = 0; i < s.length(); i++) {
            Map<Character, Integer> windowMap = new HashMap<>();
            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);

                // 检查当前窗口是否包含t的所有字符
                if (containsAll(windowMap, targetMap)) {
                    int currentLen = j - i + 1;
                    if (currentLen < minLen) {
                        minLen = currentLen;
                        minWindow = s.substring(i, j + 1);
                    }
                    break; // 找到满足条件的窗口后可以跳出内层循环
                }
            }
        }

        return minWindow;
    }

    /**
     * 辅助方法：检查窗口是否包含目标字符串的所有字符
     *
     * @param windowMap 窗口字符频率
     * @param targetMap 目标字符频率
     * @return 如果窗口包含所有目标字符返回true，否则返回false
     */
    private static boolean containsAll(Map<Character, Integer> windowMap, Map<Character, Integer> targetMap) {
        for (Map.Entry<Character, Integer> entry : targetMap.entrySet()) {
            char c = entry.getKey();
            int count = entry.getValue();
            if (windowMap.getOrDefault(c, 0) < count) {
                return false;
            }
        }
        return true;
    }
}
