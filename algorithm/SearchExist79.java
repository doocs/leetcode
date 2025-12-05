package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 单词搜索（LeetCode 79）
 *
 * 时间复杂度：O(M×N×4^L)
 * - M和N是网格的行数和列数
 * - L是单词长度
 * - 最坏情况下需要从每个单元格开始进行深度为L的搜索
 *
 * 空间复杂度：O(L)
 * - 递归调用栈深度最大为L（单词长度）
 * - 使用原地修改标记已访问单元格，不需要额外空间
 */
public class SearchExist79 {

    // 成员变量用于存储网格、单词和网格尺寸
    private char[][] board;
    private String word;
    private int rows, cols;

    /**
     * 程序入口点，处理用户输入并执行单词搜索
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 创建 Scanner 对象用于输入
        Scanner scanner = new Scanner(System.in);

        // 输入二维字符网格的行数和列数
        System.out.print("请输入网格的行数 m 和列数 n（用空格分隔）：");
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.nextLine(); // 处理换行符

        // 输入二维字符网格
        char[][] board = new char[m][n];
        System.out.println("请输入网格的字符（每行输入一个字符串）：");
        for (int i = 0; i < m; i++) {
            String line = scanner.nextLine();
            board[i] = line.toCharArray();
        }

        // 输入要查找的单词
        System.out.print("请输入要查找的单词：");
        String word = scanner.nextLine();

        // 创建 SearchExist79 对象并检查单词是否存在
        SearchExist79 solution = new SearchExist79();
        boolean exists = solution.exist(board, word);

        // 输出结果
        System.out.println("单词 " + word + (exists ? " 存在于网格中。" : " 不存在于网格中。"));
    }

    /**
     * 主方法，检查单词是否存在于网格中
     *
     * 算法思路：
     * 使用回溯算法（深度优先搜索）在网格中搜索单词
     * 1. 遍历网格中的每个单元格作为起始点
     * 2. 从每个起始点开始进行深度优先搜索
     * 3. 搜索过程中标记已访问的单元格避免重复使用
     * 4. 如果找到完整单词返回true，否则继续搜索
     *
     * @param board 二维字符网格
     * @param word 要查找的单词
     * @return 如果单词存在于网格中返回true，否则返回false
     */
    public boolean exist(char[][] board, String word) {
        // 初始化成员变量
        this.board = board;
        this.word = word;
        this.rows = board.length;
        this.cols = board[0].length;

        // 遍历每个单元格作为起始点
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(i, j, 0)) { // 从当前位置开始 DFS 搜索
                    return true; // 找到单词
                }
            }
        }
        return false; // 未找到单词
    }

    /**
     * 深度优先搜索方法
     *
     * 算法思路：
     * 从指定位置开始深度优先搜索，匹配单词的剩余部分
     *
     * @param i 当前行索引
     * @param j 当前列索引
     * @param index 当前匹配的单词字符索引
     * @return 如果从当前位置能匹配剩余单词返回true，否则返回false
     */
    private boolean dfs(int i, int j, int index) {
        // 递归终止条件：已匹配完整个单词
        if (index == word.length()) {
            return true;
        }

        // 边界检查和字符匹配检查
        if (i < 0 || i >= rows || j < 0 || j >= cols || board[i][j] != word.charAt(index)) {
            return false;
        }

        // 标记当前单元格为已访问
        char temp = board[i][j];
        board[i][j] = '#'; // 使用特殊字符标记已访问单元格

        // 向四个方向递归搜索
        boolean found = dfs(i + 1, j, index + 1) || // 向下搜索
                dfs(i - 1, j, index + 1) || // 向上搜索
                dfs(i, j + 1, index + 1) || // 向右搜索
                dfs(i, j - 1, index + 1);   // 向左搜索

        // 回溯：恢复当前单元格的字符
        board[i][j] = temp;
        return found;
    }

    /**
     * 方法2：使用额外的visited数组记录访问状态
     *
     * 算法思路：
     * 使用额外的布尔数组记录访问状态，避免修改原数组
     *
     * @param board 二维字符网格
     * @param word 要查找的单词
     * @return 如果单词存在于网格中返回true，否则返回false
     */
    public boolean existWithVisited(char[][] board, String word) {
        // 边界条件检查
        if (board == null || board.length == 0 || word == null) {
            return false;
        }

        // 初始化网格尺寸和访问状态数组
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];

        // 遍历每个单元格作为起始点
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfsWithVisited(board, word, 0, i, j, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 使用visited数组的DFS方法
     *
     * 算法思路：
     * 使用visited数组记录访问状态的深度优先搜索实现
     *
     * @param board 二维字符网格
     * @param word 要查找的单词
     * @param index 当前匹配的单词字符索引
     * @param i 当前行索引
     * @param j 当前列索引
     * @param visited 访问状态数组
     * @return 如果从当前位置能匹配剩余单词返回true，否则返回false
     */
    private boolean dfsWithVisited(char[][] board, String word, int index, int i, int j, boolean[][] visited) {
        // 递归终止条件：已匹配完整个单词
        if (index == word.length()) {
            return true;
        }

        // 边界检查、访问状态检查和字符匹配检查
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length ||
            visited[i][j] || board[i][j] != word.charAt(index)) {
            return false;
        }

        // 标记当前位置为已访问
        visited[i][j] = true;

        // 向四个方向递归搜索
        boolean found = dfsWithVisited(board, word, index + 1, i + 1, j, visited) ||
                       dfsWithVisited(board, word, index + 1, i - 1, j, visited) ||
                       dfsWithVisited(board, word, index + 1, i, j + 1, visited) ||
                       dfsWithVisited(board, word, index + 1, i, j - 1, visited);

        // 回溯：标记当前位置为未访问
        visited[i][j] = false;

        return found;
    }
}
