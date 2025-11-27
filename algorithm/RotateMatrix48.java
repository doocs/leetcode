package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 旋转图像（LeetCode 48）
 *
 * 时间复杂度：O(n²)
 * - 矩阵转置需要遍历上三角矩阵：O(n²/2)
 * - 反转每一行需要遍历矩阵的一半：O(n²/2)
 * - 总时间复杂度为O(n²)
 *
 * 空间复杂度：O(1)
 * - 原地旋转，只使用了常数级别的额外空间
 * - 没有使用与输入矩阵大小相关的额外存储空间
 */
public class RotateMatrix48 {
    public static void main(String[] args) {
        // 创建 Scanner 对象读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 输入矩阵的大小 n
        System.out.print("请输入矩阵的大小 n：");

        // 读取矩阵大小
        int n = scanner.nextInt();

        // 初始化矩阵
        int[][] matrix = new int[n][n];

        // 提示用户输入矩阵元素
        System.out.println("请输入矩阵的元素：");

        // 读取矩阵元素
        // 外层循环遍历行
        for (int i = 0; i < n; i++) {
            // 内层循环遍历列
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // 调用 rotate 方法将矩阵顺时针旋转90度
        rotate(matrix);

        // 输出旋转后的矩阵
        System.out.println("旋转后的矩阵是：");

        // 遍历并打印旋转后的矩阵
        // 外层循环遍历行
        for (int i = 0; i < n; i++) {
            // 内层循环遍历列
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 将矩阵顺时针旋转90度
     *
     * 算法思路：
     * 通过两个步骤实现旋转：
     * 1. 矩阵转置（沿主对角线翻转）
     * 2. 反转每一行
     *
     * 数学原理：
     * 矩阵顺时针旋转90度的变换公式：
     * 原位置(i,j) → 新位置(j, n-1-i)
     *
     * 示例过程（以矩阵 [[1,2,3],[4,5,6],[7,8,9]] 为例）：
     *
     * 原矩阵:
     * [1 2 3]
     * [4 5 6]
     * [7 8 9]
     *
     * 步骤1 - 矩阵转置（沿主对角线翻转）:
     * [1 4 7]
     * [2 5 8]
     * [3 6 9]
     *
     * 步骤2 - 反转每一行:
     * [7 4 1]
     * [8 5 2]
     * [9 6 3]
     *
     * 最终结果（顺时针旋转90度）:
     * [7 4 1]
     * [8 5 2]
     * [9 6 3]
     *
     * 时间复杂度分析：
     * - 矩阵转置：O(n²)，其中n为矩阵的边长，需要遍历上三角矩阵
     * - 反转每一行：O(n²)，需要对每一行进行反转操作
     * - 总时间复杂度：O(n²)
     *
     * 空间复杂度分析：
     * - 原地操作，只使用常数额外空间：O(1)
     *
     * @param matrix 输入的n×n二维整数矩阵
     */
    public static void rotate(int[][] matrix) {
        // 获取矩阵大小
        int n = matrix.length;

        // 第一步：矩阵转置（沿主对角线翻转）
        // 只需要处理上三角矩阵（j >= i），避免重复交换
        // 外层循环遍历行
        for (int i = 0; i < n; i++) {
            // 内层循环遍历列（从对角线开始）
            for (int j = i; j < n; j++) {
                // 交换 matrix[i][j] 和 matrix[j][i]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 第二步：反转每一行
        // 外层循环遍历行
        for (int i = 0; i < n; i++) {
            // 只需要交换前半部分和后半部分的元素
            // 内层循环遍历列（只需要处理前半部分）
            for (int j = 0; j < n / 2; j++) {
                // 交换 matrix[i][j] 和 matrix[i][n-1-j]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }

    /**
     * 方法2：一次循环实现旋转
     *
     * 算法思路：
     * 直接按照旋转规律进行元素交换，一圈一圈地处理
     *
     * 示例过程（以矩阵 [[1,2,3],[4,5,6],[7,8,9]] 为例）：
     *
     * 1. 初始化: n=3, layer从0到1
     *
     * 2. layer=0时:
     *    first=0, last=2
     *    i=0: offset=0
     *         top=matrix[0][0]=1
     *         matrix[0][0]=matrix[2][0]=7
     *         matrix[2][0]=matrix[2][2]=9
     *         matrix[2][2]=matrix[0][2]=3
     *         matrix[0][2]=top=1
     *         矩阵变为: [[7,2,1],[4,5,6],[9,8,3]]
     *    i=1: offset=1
     *         top=matrix[0][1]=2
     *         matrix[0][1]=matrix[1][0]=4
     *         matrix[1][0]=matrix[2][1]=8
     *         matrix[2][1]=matrix[1][2]=6
     *         matrix[1][2]=top=2
     *         矩阵变为: [[7,4,1],[8,5,2],[9,6,3]]
     *
     * 3. layer=1时:
     *    first=1, last=1, first>=last，循环结束
     *
     * 最终结果: [[7,4,1],[8,5,2],[9,6,3]]
     *
     * 时间复杂度分析：
     * - 处理每一圈：O(n)
     * - 圈数：O(n/2)
     * - 总时间复杂度：O(n²)
     *
     * 空间复杂度分析：
     * - 原地操作，只使用常数额外空间：O(1)
     *
     * @param matrix 输入的n×n二维整数矩阵
     */
    public static void rotateInPlace(int[][] matrix) {
        int n = matrix.length;

        // 圈数为 n/2
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;

            // 处理当前圈的每条边
            for (int i = first; i < last; i++) {
                int offset = i - first;

                // 保存top元素
                int top = matrix[first][i];

                // left -> top
                matrix[first][i] = matrix[last - offset][first];

                // bottom -> left
                matrix[last - offset][first] = matrix[last][last - offset];

                // right -> bottom
                matrix[last][last - offset] = matrix[i][last];

                // top -> right
                matrix[i][last] = top;
            }
        }
    }

    /**
     * 方法3：使用额外空间
     *
     * 算法思路：
     * 创建新矩阵，按照旋转规律将原矩阵元素复制到新矩阵中
     *
     * 示例过程（以矩阵 [[1,2,3],[4,5,6],[7,8,9]] 为例）：
     *
     * 1. 初始化: n=3, rotated = [[0,0,0],[0,0,0],[0,0,0]]
     *
     * 2. 复制元素过程:
     *    i=0,j=0: rotated[0][2] = matrix[0][0] = 1
     *    i=0,j=1: rotated[1][2] = matrix[0][1] = 2
     *    i=0,j=2: rotated[2][2] = matrix[0][2] = 3
     *    i=1,j=0: rotated[0][1] = matrix[1][0] = 4
     *    i=1,j=1: rotated[1][1] = matrix[1][1] = 5
     *    i=1,j=2: rotated[2][1] = matrix[1][2] = 6
     *    i=2,j=0: rotated[0][0] = matrix[2][0] = 7
     *    i=2,j=1: rotated[1][0] = matrix[2][1] = 8
     *    i=2,j=2: rotated[2][0] = matrix[2][2] = 9
     *
     *    rotated = [[7,4,1],[8,5,2],[9,6,3]]
     *
     * 3. 复制回原矩阵: matrix = [[7,4,1],[8,5,2],[9,6,3]]
     *
     * 时间复杂度分析：
     * - 复制元素：O(n²)，需要遍历原矩阵的每个元素
     * - 复制回原矩阵：O(n²)，需要将新矩阵的每个元素复制回原矩阵
     * - 总时间复杂度：O(n²)
     *
     * 空间复杂度分析：
     * - 需要额外矩阵存储结果：O(n²)
     *
     * @param matrix 输入的n×n二维整数矩阵
     */
    public static void rotateWithExtraSpace(int[][] matrix) {
        int n = matrix.length;
        int[][] rotated = new int[n][n];

        // 按照旋转规律复制元素
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][n - 1 - i] = matrix[i][j];
            }
        }

        // 将结果复制回原矩阵
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rotated[i][j];
            }
        }
    }
}
