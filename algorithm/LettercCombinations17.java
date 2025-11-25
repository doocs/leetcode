package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/**
 * 电话号码的字母组合（LeetCode 17）
 *
 * 时间复杂度：O(3^m × 4^n)
 * - m是对应3个字母的数字个数，n是对应4个字母的数字个数
 * - 每个数字对应多个字母选择，总共有3^m × 4^n种组合
 *
 * 空间复杂度：O(m + n)
 * - 递归调用栈深度为数字串长度
 * - 需要额外的列表存储当前路径和数字字母映射
 */
public class LettercCombinations17 {

    // 数字到字母的映射
    private static final Map<Character, String> PHONE_MAP = new HashMap<>();
    static {
        PHONE_MAP.put('2', "abc");
        PHONE_MAP.put('3', "def");
        PHONE_MAP.put('4', "ghi");
        PHONE_MAP.put('5', "jkl");
        PHONE_MAP.put('6', "mno");
        PHONE_MAP.put('7', "pqrs");
        PHONE_MAP.put('8', "tuv");
        PHONE_MAP.put('9', "wxyz");
    }

    /**
     * 生成电话号码的所有字母组合
     *
     * 算法思路：
     * 使用回溯算法，通过递归和状态重置生成所有可能的字母组合
     * 1. 建立数字到字母的映射关系
     * 2. 递归处理每个数字，尝试对应的每个字母
     * 3. 构建完整组合后添加到结果中
     * 4. 回溯时重置状态，尝试其他可能
     *
     * 执行过程分析（以digits="23"为例）：
     *
     * 数字映射：
     * '2' -> "abc"
     * '3' -> "def"
     *
     * 递归树：
     *                    ""
     *              /      |      \
     *            "a"     "b"     "c"
     *           / | \   / | \   / | \
     *        "ad""ae""af""bd""be""bf""cd""ce""cf"
     *
     * 详细执行过程：
     *
     * 1. index=0, digit='2': 对应字母"abc"
     *    - choose('a'): combination="a", index=1
     *      - index=1, digit='3': 对应字母"def"
     *        - choose('d'): combination="ad", index=2
     *          - index=2 == digits.length()，添加"ad"到结果
     *        - choose('e'): combination="ae", index=2
     *          - index=2 == digits.length()，添加"ae"到结果
     *        - choose('f'): combination="af", index=2
     *          - index=2 == digits.length()，添加"af"到结果
     *    - choose('b'): combination="b", index=1
     *      - 同样处理'd','e','f'，得到"bd","be","bf"
     *    - choose('c'): combination="c", index=1
     *      - 同样处理'd','e','f'，得到"cd","ce","cf"
     *
     * 最终结果：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     *
     * 时间复杂度分析：
     * - 设输入数字串长度为L，每个数字对应的字母数为K(3或4)
     * - 最坏情况下需要遍历所有可能组合：O(K^L)
     * - 具体为O(3^m × 4^n)，其中：
     *   - m: 输入字符串中对应3个字母的数字个数（数字2,3,4,5,6,8的个数）
     *   - n: 输入字符串中对应4个字母的数字个数（数字7,9的个数）
     *   - 例如：digits="237"，则m=2（数字2,3），n=1（数字7），复杂度为O(3^2 × 4^1) = O(36)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度等于数字串长度：O(L)
     *   - L: 输入数字字符串的长度，即递归的最大深度
     * - 存储单个组合的空间：O(L)
     * - 总空间复杂度：O(L)，其中L为输入数字字符串的长度
     *
     * @param digits 输入的数字字符串
     * @return 所有可能的字母组合
     */
    public List<String> letterCombinations(String digits) {
        // 创建结果列表用于存储所有可能的字母组合
        List<String> result = new ArrayList<>();

        // 边界情况：空字符串
        if (digits == null || digits.length() == 0) {
            return result;
        }

        // 使用StringBuilder构建当前字母组合，提高字符串操作效率
        StringBuilder combination = new StringBuilder();

        // 开始回溯算法生成所有组合
        backtrack(digits, 0, combination, result);

        return result;
    }

    /**
     * 回溯辅助方法
     *
     * 算法思路：
     * 递归地为每个数字位置选择一个字母，构建完整组合后回溯尝试其他可能
     *
     * @param digits 输入的数字字符串
     * @param index 当前处理的数字位置
     * @param combination 当前构建的字母组合
     * @param result 存储所有字母组合的结果列表
     */
    private void backtrack(String digits, int index, StringBuilder combination, List<String> result) {
        // 递归终止条件：已处理完所有数字
        if (index == digits.length()) {
            // 将当前组合添加到结果中
            result.add(combination.toString());
            return;
        }

        // 获取当前数字对应的字母
        char digit = digits.charAt(index);
        String letters = PHONE_MAP.get(digit);

        // 尝试每个字母作为当前数字的对应字符
        for (int i = 0; i < letters.length(); i++) {
            char letter = letters.charAt(i);

            // 做选择：将字母添加到当前组合
            combination.append(letter);

            // 递归：处理下一个数字
            backtrack(digits, index + 1, combination, result);

            // 撤销选择：回溯，移除字母，为尝试下一个字母做准备
            combination.deleteCharAt(combination.length() - 1);
        }

    }


    /**
     * 方法2：迭代解法
     *
     * 算法思路：
     * 逐个处理数字，将新数字对应的字母与已有组合进行组合
     *
     * 执行过程分析（以digits="23"为例）：
     *
     * 初始：result = [""]
     *
     * 处理'2'（对应"abc"）：
     * 对""分别添加'a','b','c'
     * result = ["a", "b", "c"]
     *
     * 处理'3'（对应"def"）：
     * 对"a"分别添加'd','e','f' -> "ad","ae","af"
     * 对"b"分别添加'd','e','f' -> "bd","be","bf"
     * 对"c"分别添加'd','e','f' -> "cd","ce","cf"
     * result = ["ad","ae","af","bd","be","bf","cd","ce","cf"]
     *
     * 时间复杂度分析：
     * - 外层循环遍历所有数字：O(L)，L为数字串长度
     * - 内层双重循环生成新组合：O(3^m × 4^n)
     * - 总时间复杂度：O(3^m × 4^n)
     *   - 其中m: 输入字符串中对应3个字母的数字个数
     *   - 其中n: 输入字符串中对应4个字母的数字个数
     *   - 例如：digits="27"，则m=1（数字2），n=1（数字7），复杂度为O(3^1 × 4^1) = O(12)
     *
     * 空间复杂度分析：
     * - 使用临时列表存储中间结果：O(3^m × 4^n)
     *   - 3^m × 4^n: 所有可能的字母组合总数
     * - 总空间复杂度：O(3^m × 4^n)
     *
     * @param digits 输入的数字字符串
     * @return 所有可能的字母组合
     */
    public List<String> letterCombinationsIterative(String digits) {
        // 创建结果列表用于存储所有可能的字母组合
        List<String> result = new ArrayList<>();

        // 边界情况：空字符串
        if (digits == null || digits.length() == 0) {
            return result;
        }

        // 初始化为空字符串，作为构建组合的起点
        result.add("");

        // 逐个处理每个数字字符
        for (char digit : digits.toCharArray()) {
            // 获取当前数字对应的字母字符串
            String letters = PHONE_MAP.get(digit);
            // 创建临时列表存储新的组合
            List<String> temp = new ArrayList<>();

            // 将当前数字对应的每个字母与已有组合进行拼接
            for (String combination : result) {
                for (char letter : letters.toCharArray()) {
                    // 生成新的组合并添加到临时列表中
                    temp.add(combination + letter);
                }
            }

            // 更新结果列表为新的组合列表
            result = temp;
        }

        return result;
    }

    /**
     * 方法3：队列解法
     *
     * 算法思路：
     * 使用队列存储中间组合结果，按层次逐个处理数字
     *
     * 执行过程分析（以digits="23"为例）：
     *
     * 初始：queue = [""]
     *
     * 处理'2'（对应"abc"）：
     * 取出""，分别添加'a','b','c'
     * queue = ["a", "b", "c"]
     *
     * 处理'3'（对应"def"）：
     * 取出"a"，分别添加'd','e','f' -> "ad","ae","af"
     * 取出"b"，分别添加'd','e','f' -> "bd","be","bf"
     * 取出"c"，分别添加'd','e','f' -> "cd","ce","cf"
     * queue = ["ad","ae","af","bd","be","bf","cd","ce","cf"]
     *
     * 时间复杂度分析：
     * - 外层循环遍历所有数字：O(L)，L为数字串长度
     * - 内层双重循环生成新组合：O(3^m × 4^n)
     * - 总时间复杂度：O(3^m × 4^n)
     *   - 其中m: 输入字符串中对应3个字母的数字个数
     *   - 其中n: 输入字符串中对应4个字母的数字个数
     *   - 例如：digits="234"，则m=3（数字2,3,4），n=0，复杂度为O(3^3 × 4^0) = O(27)
     *
     * 空间复杂度分析：
     * - 队列存储中间结果：O(3^m × 4^n)
     *   - 3^m × 4^n: 所有可能的字母组合总数，队列在处理过程中需要存储这些组合
     * - 总空间复杂度：O(3^m × 4^n)
     *
     * @param digits 输入的数字字符串
     * @return 所有可能的字母组合
     */
    public List<String> letterCombinationsQueue(String digits) {
        // 创建结果列表用于存储所有可能的字母组合
        List<String> result = new ArrayList<>();

        // 边界情况检查
        if (digits == null || digits.length() == 0) {
            return result;
        }

        // 使用队列存储中间组合结果
        java.util.Queue<String> queue = new java.util.LinkedList<>();
        // 初始化队列，添加空字符串作为起点
        queue.offer("");

        // 逐个处理每个数字字符
        for (char digit : digits.toCharArray()) {
            // 获取当前数字对应的字母字符串
            String letters = PHONE_MAP.get(digit);
            // 获取当前队列大小，即当前层的组合数量
            int size = queue.size();

            // 处理当前层的所有组合
            for (int i = 0; i < size; i++) {
                // 取出队列头部的当前组合
                String current = queue.poll();
                // 将当前数字对应的每个字母与当前组合拼接
                for (char letter : letters.toCharArray()) {
                    // 将新生成的组合重新加入队列
                    queue.offer(current + letter);
                }
            }
        }

        // 将队列中剩余的所有组合转移到结果列表中
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }

        return result;
    }

    /**
     * 辅助方法：读取用户输入的数字字符串
     *
     * @return 用户输入的数字字符串
     */
    public static String readDigits() {
        // 创建输入读取器
        Scanner scanner = new Scanner(System.in);
        // 提示用户输入
        System.out.print("请输入数字字符串 (2-9): ");
        // 返回用户输入的字符串
        return scanner.nextLine();
    }

    /**
     * 辅助方法：打印字母组合
     *
     * @param result 字母组合列表
     */
    public static void printCombinations(List<String> result) {
        // 打印标题
        System.out.println("所有字母组合：");
        // 遍历并打印每个组合
        for (int i = 0; i < result.size(); i++) {
            System.out.println((i + 1) + ": " + result.get(i));
        }
        // 打印总计数量
        System.out.println("总共 " + result.size() + " 个组合");
    }

    /**
     * 主函数：处理用户输入并生成所有字母组合
     */
    public static void main(String[] args) {
        // 打印程序标题
        System.out.println("电话号码的字母组合");

        // 读取用户输入的数字字符串
        String digits = readDigits();
        System.out.println("输入数字: \"" + digits + "\"");

        // 生成所有字母组合
        LettercCombinations17 solution = new LettercCombinations17();
        List<String> result1 = solution.letterCombinations(digits);
        List<String> result2 = solution.letterCombinationsIterative(digits);
        List<String> result3 = solution.letterCombinationsQueue(digits);

        // 输出结果
        System.out.println("回溯方法结果：");
        printCombinations(result1);

        System.out.println("\n迭代方法结果：");
        printCombinations(result2);

        System.out.println("\n队列方法结果：");
        printCombinations(result3);
    }
}
