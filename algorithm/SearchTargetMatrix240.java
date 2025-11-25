package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 搜索二维矩阵 II（LeetCode 240）
 *
 * 时间复杂度：O(m + n)
 * - 最多遍历 m 行和 n 列
 * - 每次比较后要么行增加，要么列减少
 * - 总的移动步数最多为 m + n
 *
 * 空间复杂度：O(1)
 * - 只使用了常数级别的额外空间
 * - 没有使用与输入矩阵大小相关的额外存储空间
 */
public class SearchTargetMatrix240 {
    public static void main(String[] args) {
        // 创建 Scanner 对象读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 输入矩阵的大小
        System.out.print("请输入矩阵的行数 m：");

        // 读取行数
        int m = scanner.nextInt();

        System.out.print("请输入矩阵的列数 n：");

        // 读取列数
        int n = scanner.nextInt();

        // 初始化矩阵
        int[][] matrix = new int[m][n];

        // 提示用户输入矩阵元素
        System.out.println("请输入矩阵的元素：");

        // 读取矩阵元素
        // 外层循环遍历行
        for (int i = 0; i < m; i++) {
            // 内层循环遍历列
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // 输入目标值
        System.out.print("请输入目标值 target：");

        // 读取目标值
        int target = scanner.nextInt();

        // 调用 searchMatrix 方法查找目标值
        boolean result = searchMatrix(matrix, target);

        // 输出结果
        if (result) {
            System.out.println("目标值存在于矩阵中。");
        } else {
            System.out.println("目标值不存在于矩阵中。");
        }
    }

    /**
     * 在有序二维矩阵中搜索目标值
     * 矩阵满足以下条件：
     * 1. 每行的元素从左到右升序排列
     * 2. 每列的元素从上到下升序排列
     *
     * 算法思路：
     * 从矩阵的右上角开始搜索，利用矩阵的有序性质进行剪枝
     * 1. 从右上角开始，该位置是当前行的最大值，当前列的最小值
     * 2. 如果当前元素等于目标值，找到目标
     * 3. 如果当前元素大于目标值，说明目标值不可能在当前列，向左移动
     * 4. 如果当前元素小于目标值，说明目标值不可能在当前行，向下移动
     *
     * 示例过程（以矩阵 [[1,4,7,11],[2,5,8,12],[3,6,9,16],[10,13,14,17]]，target=5 为例）：
     *
     * 矩阵:
     * [ 1  4  7 11]
     * [ 2  5  8 12]
     * [ 3  6  9 16]
     * [10 13 14 17]
     *
     * 初始位置: row=0, col=3, matrix[0][3]=11
     *
     * 步骤1: matrix[0][3]=11 > target=5，向左移动
     *        row=0, col=2, matrix[0][2]=7
     *
     * 步骤2: matrix[0][2]=7 > target=5，向左移动
     *        row=0, col=1, matrix[0][1]=4
     *
     * 步骤3: matrix[0][1]=4 < target=5，向下移动
     *        row=1, col=1, matrix[1][1]=5
     *
     * 步骤4: matrix[1][1]=5 = target=5，找到目标
     *
     * 最终结果: true
     *
     * 时间复杂度分析：
     * - 最多遍历 m 行和 n 列：O(m + n)，其中m为行数，n为列数
     * - 每次比较后要么行增加，要么列减少
     * - 总的移动步数最多为 m + n
     *
     * 空间复杂度分析：
     * - 只使用了常数级别的额外空间：O(1)
     * - 没有使用与输入矩阵大小相关的额外存储空间
     *
     * @param matrix 输入的二维整数矩阵
     * @param target 要搜索的目标值
     * @return 如果目标值存在于矩阵中返回true，否则返回false
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        // 获取矩阵的行数和列数
        int m = matrix.length;
        int n = matrix[0].length;

        // 从矩阵的右上角开始搜索
        int row = 0;
        int col = n - 1;

        // 当行和列都在合理范围内时，进行搜索
        while (row < m && col >= 0) {
            if (matrix[row][col] == target) {
                // 找到目标值
                return true;
            } else if (matrix[row][col] > target) {
                // 当前元素大于目标值，向左移动一列
                col--;
            } else {
                // 当前元素小于目标值，向下移动一行
                row++;
            }
        }

        // 如果遍历完整个矩阵仍未找到目标值，返回 false
        return false;
    }
    /**
     * 方法2：从左下角开始搜索
     *
     * 算法思路：
     * 从矩阵的左下角开始搜索，利用矩阵的有序性质进行剪枝
     * 1. 从左下角开始，该位置是当前列的最大值，当前行的最小值
     * 2. 如果当前元素等于目标值，找到目标
     * 3. 如果当前元素大于目标值，说明目标值不可能在当前行，向上移动
     * 4. 如果当前元素小于目标值，说明目标值不可能在当前列，向右移动
     *
     * 示例过程（以矩阵 [[1,4,7,11],[2,5,8,12],[3,6,9,16],[10,13,14,17]]，target=5 为例）：
     *
     * 1. 初始化: row=3, col=0, matrix[3][0]=10
     * 2. matrix[3][0]=10 > target=5, 向上移动, row=2
     * 3. matrix[2][0]=3 < target=5, 向右移动, col=1
     * 4. matrix[2][1]=6 > target=5, 向上移动, row=1
     * 5. matrix[1][1]=5 = target=5, 找到目标, 返回true
     *
     * 时间复杂度分析：
     * - 最多遍历 m 行和 n 列：O(m + n)，其中m为行数，n为列数
     * - 每次比较后要么行减少，要么列增加
     * - 总的移动步数最多为 m + n
     *
     * 空间复杂度分析：
     * - 只使用了常数级别的额外空间：O(1)
     * - 没有使用与输入矩阵大小相关的额外存储空间
     *
     * @param matrix 输入的二维整数矩阵
     * @param target 要搜索的目标值
     * @return 如果目标值存在于矩阵中返回true，否则返回false
     */
    public static boolean searchMatrixFromBottomLeft(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 从矩阵的左下角开始搜索
        int row = m - 1;  // 初始行索引为最下行
        int col = 0;      // 初始列索引为0

        // 当行和列都在合理范围内时，进行搜索
        while (row >= 0 && col < n) {
            if (matrix[row][col] == target) {
                // 找到目标值
                return true;
            } else if (matrix[row][col] > target) {
                // 当前元素大于目标值，向上移动一行
                row--;
            } else {
                // 当前元素小于目标值，向右移动一列
                col++;
            }
        }

        // 如果遍历完整个矩阵仍未找到目标值，返回 false
        return false;
    }

    /**
     * 方法3：二分搜索（对每行进行二分搜索）
     *
     * 算法思路：
     * 对矩阵的每一行进行二分搜索
     *
     * 示例过程（以矩阵 [[1,4,7,11],[2,5,8,12],[3,6,9,16],[10,13,14,17]]，target=5 为例）：
     *
     * 1. 处理第0行[1,4,7,11]: binarySearch([1,4,7,11], 5) = false
     * 2. 处理第1行[2,5,8,12]: binarySearch([2,5,8,12], 5) = true, 返回true
     *
     * 时间复杂度分析：
     * - 遍历m行：O(m)，其中m为行数
     * - 每行二分搜索：O(log n)，其中n为列数
     * - 总时间复杂度：O(m * log n)
     *
     * 空间复杂度分析：
     * - 只使用了常数级别的额外空间：O(1)
     *
     * @param matrix 输入的二维整数矩阵
     * @param target 要搜索的目标值
     * @return 如果目标值存在于矩阵中返回true，否则返回false
     */
    public static boolean searchMatrixBinarySearch(int[][] matrix, int target) {
        // 遍历每一行
        for (int[] row : matrix) {
            // 对当前行进行二分搜索
            if (binarySearch(row, target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 二分搜索辅助方法
     *
     * 时间复杂度分析：
     * - 二分搜索：O(log n)，其中n为数组长度
     *
     * 空间复杂度分析：
     * - 只使用了常数级别的额外空间：O(1)
     *
     * @param arr 有序数组
     * @param target 目标值
     * @return 如果目标值存在于数组中返回true，否则返回false
     */
    private static boolean binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
}
