package com.funian.algorithm.algorithm;

/**
 * 划分字母区间（LeetCode 763）
 *
 * 时间复杂度：O(n)
 * - n是字符串长度
 * - 需要遍历字符串两次
 *
 * 空间复杂度：O(1)
 * - 只使用了固定大小的数组（26个字母）
 * - 结果列表的空间复杂度为O(k)，k为片段数量
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Partition763 {

    /**
     * 主函数：处理用户输入并计算划分字母区间的长度
     *
     * 算法流程：
     * 1. 读取用户输入的字符串
     * 2. 调用 [partitionLabels](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/Partition763.java#L93-L129)方法计算每个片段的长度
     * 3. 输出结果
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入字符串：");
        String s = scanner.nextLine();

        List<Integer> result = partitionLabels(s);
        System.out.println("每个字符串片段的长度：" + result);
    }

    /**
     * 将字符串划分为尽可能多的片段，同一字母最多出现在一个片段中
     *
     * 算法思路：
     * 1. 首先遍历字符串，记录每个字符最后出现的位置
     * 2. 再次遍历字符串，维护当前片段的结束位置
     * 3. 对于每个字符，更新当前片段的结束位置为max(当前结束位置, 该字符最后出现位置)
     * 4. 当遍历到当前片段结束位置时，说明找到了一个完整的片段
     *
     * 执行过程分析（以`s="ababcbacadefegdehijhklij"`为例）：
     *
     * 第一步：记录每个字符最后出现的位置
     * a:8, b:5, c:7, d:14, e:15, f:11, g:13, h:19, i:22, j:23, k:20, l:21
     *
     * 第二步：确定片段边界
     *
     * 片段1的确定过程：
     * i=0, char='a': currentEnd = max(0, 8) = 8
     * i=1, char='b': currentEnd = max(8, 5) = 8
     * i=2, char='a': currentEnd = max(8, 8) = 8
     * i=3, char='b': currentEnd = max(8, 5) = 8
     * i=4, char='c': currentEnd = max(8, 7) = 8
     * i=5, char='b': currentEnd = max(8, 5) = 8
     * i=6, char='a': currentEnd = max(8, 8) = 8
     * i=7, char='c': currentEnd = max(8, 7) = 8
     * i=8, char='a': currentEnd = max(8, 8) = 8
     * i=8 == currentEnd=8，找到第一个片段[0,8]，长度=9
     *
     * 片段2的确定过程：
     * partitionStart = 9
     * i=9, char='d': currentEnd = max(8, 14) = 14
     * i=10, char='e': currentEnd = max(14, 15) = 15
     * i=11, char='f': currentEnd = max(15, 11) = 15
     * i=12, char='e': currentEnd = max(15, 15) = 15
     * i=13, char='g': currentEnd = max(15, 13) = 15
     * i=14, char='d': currentEnd = max(15, 14) = 15
     * i=15, char='e': currentEnd = max(15, 15) = 15
     * i=15 == currentEnd=15，找到第二个片段[9,15]，长度=7
     *
     * 片段3的确定过程：
     * partitionStart = 16
     * i=16, char='h': currentEnd = max(15, 19) = 19
     * i=17, char='i': currentEnd = max(19, 22) = 22
     * i=18, char='j': currentEnd = max(22, 23) = 23
     * i=19, char='h': currentEnd = max(23, 19) = 23
     * i=20, char='k': currentEnd = max(23, 20) = 23
     * i=21, char='l': currentEnd = max(23, 21) = 23
     * i=22, char='i': currentEnd = max(23, 22) = 23
     * i=23, char='j': currentEnd = max(23, 23) = 23
     * i=23 == currentEnd=23，找到第三个片段[16,23]，长度=8
     *
     * 最终结果：[9, 7, 8]
     * 对应片段："ababcbaca" | "defegde" | "hijhklij"
     *
     * 时间复杂度分析：
     * - 记录字符最后位置：O(n)
     * - 确定片段边界：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - lastIndex数组：O(1)（固定26个字母）
     * - result列表：O(k)（k为片段数量）
     * - 总空间复杂度：O(1)
     *
     * @param s 输入字符串
     * @return 每个字符串片段的长度列表
     */
    public static List<Integer> partitionLabels(String s) {
        // 存储每个字符最后出现的位置
        // 数组大小为26，对应26个小写字母
        int[] lastIndex = new int[26];
        List<Integer> result = new ArrayList<>();
        int n = s.length();

        // 记录每个字符最后出现的位置
        // 遍历字符串，更新每个字符的最后位置
        for (int i = 0; i < n; i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        // 当前片段的结束位置
        int currentEnd = 0;
        // 当前片段的开始位置
        int partitionStart = 0;

        // 遍历字符串，确定片段的边界
        for (int i = 0; i < n; i++) {
            // 更新当前片段的结束位置为当前字符最后出现位置和当前结束位置的最大值
            // 这确保了当前片段包含了所有需要的字符
            currentEnd = Math.max(currentEnd, lastIndex[s.charAt(i) - 'a']);

            // 如果当前索引到达片段的结束位置
            // 说明当前片段已经包含了所有需要的字符，可以划分
            if (i == currentEnd) {
                // 计算片段长度并添加到结果列表
                result.add(currentEnd - partitionStart + 1);
                // 更新下一个片段的开始位置
                partitionStart = i + 1;
            }
        }

        return result;
    }

    /**
     * 扩展方法：返回实际的字符串片段而不仅仅是长度
     *
     * 算法思路：
     * 与 [partitionLabels](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/Partition763.java#L93-L129)方法类似，但返回实际的字符串片段而不是长度
     *
     * 时间复杂度分析：
     * - 记录字符最后位置：O(n)
     * - 确定片段边界：O(n)
     * - substring操作：O(k*m)（k为片段数，m为平均片段长度）
     * - 总时间复杂度：O(n + k*m)
     *
     * 空间复杂度分析：
     * - lastIndex数组：O(1)（固定26个字母）
     * - result列表：O(n)（存储所有片段）
     * - 总空间复杂度：O(n)
     *
     * @param s 输入字符串
     * @return 字符串片段列表
     */
    public List<String> partitionLabelsStrings(String s) {
        int[] lastIndex = new int[26];
        List<String> result = new ArrayList<>();

        // 记录每个字符最后出现的位置
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        int currentEnd = 0;
        int partitionStart = 0;

        for (int i = 0; i < s.length(); i++) {
            currentEnd = Math.max(currentEnd, lastIndex[s.charAt(i) - 'a']);

            if (i == currentEnd) {
                // 添加实际的字符串片段
                result.add(s.substring(partitionStart, currentEnd + 1));
                partitionStart = i + 1;
            }
        }

        return result;
    }

    /**
     * 扩展方法：验证划分结果的正确性
     *
     * 算法思路：
     * 1. 检查重新拼接的片段是否等于原字符串
     * 2. 检查每个片段中的字符是否不会出现在其他片段中
     *
     * 时间复杂度分析：
     * - 重新拼接字符串：O(n)
     * - 验证字符分布：O(k²*m)（k为片段数，m为平均片段长度）
     * - 总时间复杂度：O(n + k²*m)
     *
     * 空间复杂度分析：
     * - StringBuilder：O(n)
     * - 总空间复杂度：O(n)
     *
     * @param s 原始字符串
     * @param partitions 字符串片段列表
     * @return 如果划分正确返回true，否则返回false
     */
    public boolean validatePartitions(String s, List<String> partitions) {
        // 重新拼接片段，应该等于原字符串
        StringBuilder sb = new StringBuilder();
        for (String partition : partitions) {
            sb.append(partition);
        }

        if (!sb.toString().equals(s)) {
            return false;
        }

        // 检查每个片段中的字符是否不会出现在其他片段中
        for (int i = 0; i < partitions.size(); i++) {
            String currentPartition = partitions.get(i);
            for (char c : currentPartition.toCharArray()) {
                // 检查该字符是否出现在其他片段中
                for (int j = 0; j < partitions.size(); j++) {
                    if (i != j && partitions.get(j).indexOf(c) != -1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
