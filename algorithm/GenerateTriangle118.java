package com.funian.algorithm.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 生成杨辉三角（LeetCode 118）
 *
 * 时间复杂度：O(n²)
 * - n是行数
 * - 需要生成n行，第i行有i个元素，总共约n²/2个元素
 *
 * 空间复杂度：O(n²)
 * - 需要存储杨辉三角的所有元素
 */
public class GenerateTriangle118 {

    /**
     * 主函数：处理用户输入并生成杨辉三角
     *
     * 算法流程：
     * 1. 读取用户输入的行数
     * 2. 调用 [generate](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/GenerateTriangle118.java#L77-L107)方法生成杨辉三角
     * 3. 输出结果
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入杨辉三角的行数：");
        int numRows = scanner.nextInt();

        List<List<Integer>> triangle = generate(numRows);
        System.out.println("杨辉三角的前 " + numRows + " 行：");
        for (List<Integer> row : triangle) {
            System.out.println(row);
        }
    }

    /**
     * 生成杨辉三角的前numRows行
     *
     * 算法思路：
     * 杨辉三角的性质：
     * 1. 每行的第一个和最后一个元素都是1
     * 2. 中间的元素等于其上方两个元素之和
     *
     * 执行过程分析（以numRows=5为例）：
     *
     * 第1行：1
     * 第2行：1 1
     * 第3行：1 2 1
     * 第4行：1 3 3 1
     * 第5行：1 4 6 4 1
     *
     * 构造过程详解：
     * i=0: row=[1] （j=0，边界情况）
     * triangle=[[1]]
     *
     * i=1: row=[1,1] （j=0和j=1，都是边界情况）
     * triangle=[[1], [1,1]]
     *
     * i=2:
     *   j=0: row=[1] （边界情况）
     *   j=1: row=[1,2] （triangle[1][0]+triangle[1][1] = 1+1 = 2）
     *   j=2: row=[1,2,1] （边界情况）
     * triangle=[[1], [1,1], [1,2,1]]
     *
     * i=3:
     *   j=0: row=[1] （边界情况）
     *   j=1: row=[1,3] （triangle[2][0]+triangle[2][1] = 1+2 = 3）
     *   j=2: row=[1,3,3] （triangle[2][1]+triangle[2][2] = 2+1 = 3）
     *   j=3: row=[1,3,3,1] （边界情况）
     * triangle=[[1], [1,1], [1,2,1], [1,3,3,1]]
     *
     * i=4:
     *   j=0: row=[1] （边界情况）
     *   j=1: row=[1,4] （triangle[3][0]+triangle[3][1] = 1+3 = 4）
     *   j=2: row=[1,4,6] （triangle[3][1]+triangle[3][2] = 3+3 = 6）
     *   j=3: row=[1,4,6,4] （triangle[3][2]+triangle[3][3] = 3+1 = 4）
     *   j=4: row=[1,4,6,4,1] （边界情况）
     * triangle=[[1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1]]
     *
     * 时间复杂度分析：
     * - 外层循环：O(numRows)
     * - 内层循环：O(i)（第i行有i+1个元素）
     * - 总时间复杂度：O(numRows²)
     *
     * 空间复杂度分析：
     * - 存储杨辉三角：O(numRows²)
     *
     * @param numRows 杨辉三角的行数
     * @return 杨辉三角的前numRows行
     */
    public static List<List<Integer>> generate(int numRows) {
        // 存储整个杨辉三角
        List<List<Integer>> triangle = new ArrayList<>();

        // 逐行生成杨辉三角
        for (int i = 0; i < numRows; i++) {
            // 存储当前行的元素
            List<Integer> row = new ArrayList<>();

            // 生成当前行的元素
            for (int j = 0; j <= i; j++) {
                // 每行的第一个和最后一个元素都是 1
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    // 当前元素为上方两个元素之和
                    // triangle.get(i-1).get(j-1) 是左上方元素
                    // triangle.get(i-1).get(j) 是右上方元素
                    int value = triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j);
                    row.add(value);
                }
            }

            // 添加当前行到三角形中
            triangle.add(row);
        }

        return triangle;
    }

    /**
     * 扩展方法：生成杨辉三角的第rowIndex行（从0开始）
     *
     * 算法思路：
     * 利用组合数公式：第n行第k个元素是C(n,k) = n!/(k!(n-k)!)
     *
     * 时间复杂度分析：
     * - 计算每行元素：O(rowIndex)
     * - 总时间复杂度：O(rowIndex²)
     *
     * 空间复杂度分析：
     * - 存储一行元素：O(rowIndex)
     *
     * @param rowIndex 行索引（从0开始）
     * @return 杨辉三角的第rowIndex行
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();

        // 第rowIndex行有rowIndex+1个元素
        for (int i = 0; i <= rowIndex; i++) {
            // 计算组合数C(rowIndex, i)
            long value = 1;
            for (int j = 0; j < i; j++) {
                value = value * (rowIndex - j) / (j + 1);
            }
            row.add((int) value);
        }

        return row;
    }

    /**
     * 扩展方法：优化空间复杂度的生成方法
     *
     * 算法思路：
     * 每次只保留前一行的数据，而不是存储所有行的数据
     *
     * 时间复杂度分析：
     * - 生成每行：O(i)（第i行有i+1个元素）
     * - 总时间复杂度：O(numRows²)
     *
     * 空间复杂度分析：
     * - 存储杨辉三角：O(numRows²)
     *
     * @param numRows 杨辉三角的行数
     * @return 杨辉三角的前numRows行
     */
    public List<List<Integer>> generateOptimized(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        if (numRows == 0) return triangle;

        // 第一行
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        triangle.add(firstRow);

        // 生成其余行
        for (int i = 1; i < numRows; i++) {
            List<Integer> prevRow = triangle.get(i - 1);
            List<Integer> newRow = new ArrayList<>();
            newRow.add(1); // 每行第一个元素

            // 计算中间元素
            for (int j = 1; j < i; j++) {
                newRow.add(prevRow.get(j - 1) + prevRow.get(j));
            }

            newRow.add(1); // 每行最后一个元素
            triangle.add(newRow);
        }

        return triangle;
    }
}
