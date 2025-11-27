package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 矩阵置零（LeetCode 73）
 *
 * 时间复杂度：O(m × n)
 * - 需要遍历矩阵多次，每次遍历O(m × n)
 * - 总时间复杂度为O(m × n)
 *
 * 空间复杂度：O(1)
 * - 只使用了常数级别的额外空间
 * - 利用矩阵的第一行和第一列作为标记空间
 */
public class SetZeroMatrix73 {
    public static void main(String[] args) {
        // 创建 Scanner 对象读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 读取矩阵的大小
        System.out.print("请输入矩阵的行数 m 和列数 n：");

        // 读取行数
        int m = scanner.nextInt();

        // 读取列数
        int n = scanner.nextInt();

        // 初始化矩阵
        int[][] matrix = new int[m][n];

        // 提示用户输入矩阵元素
        System.out.println("请输入矩阵元素，每行用空格分隔：");

        // 读取矩阵元素
        // 外层循环遍历行
        for (int i = 0; i < m; i++) {
            // 内层循环遍历列
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // 调用 setZeroes 方法将矩阵中0元素所在的行和列置零
        setZeroes(matrix);

        // 输出结果矩阵
        System.out.println("处理后的矩阵：");

        // 遍历并打印处理后的矩阵
        // 外层循环遍历行
        for (int i = 0; i < m; i++) {
            // 内层循环遍历列
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 矩阵置零
     * 如果矩阵中某个元素为0，则将其所在的行和列都置零
     *
     * 算法思路：
     * 使用矩阵的第一行和第一列作为标记空间，记录哪些行和列需要置零
     * 由于第一行和第一列本身可能包含0，需要额外变量记录它们是否需要置零
     *
     * 示例过程（以矩阵 [[1,1,1],[1,0,1],[1,1,1]] 为例）：
     *
     * 原矩阵:
     * [1 1 1]
     * [1 0 1]
     * [1 1 1]
     *
     * 步骤1 - 检查第一行第一列是否包含0:
     * firstRowZero = false, firstColZero = false
     *
     * 步骤2 - 利用第一行第一列标记需要置零的行列:
     * 发现matrix[1][1]=0，标记matrix[1][0]=0, matrix[0][1]=0
     * [1 0 1]
     * [0 0 1]
     * [1 1 1]
     *
     * 步骤3 - 根据标记置零:
     * matrix[1][2]=0 (因为matrix[1][0]=0)
     * matrix[2][1]=0 (因为matrix[0][1]=0)
     * [1 0 1]
     * [0 0 0]
     * [1 0 1]
     *
     * 步骤4-5 - 处理第一行第一列:
     * firstRowZero=false, firstColZero=false，不处理
     *
     * 最终结果:
     * [1 0 1]
     * [0 0 0]
     * [1 0 1]
     *
     * 时间复杂度分析：
     * - 检查第一行和第一列：O(m + n)，其中m为行数，n为列数
     * - 标记需要置零的行列：O(m × n)
     * - 根据标记置零：O(m × n)
     * - 处理第一行和第一列：O(m + n)
     * - 总时间复杂度：O(m × n)
     *
     * 空间复杂度分析：
     * - 只使用了两个布尔变量：O(1)
     * - 利用原矩阵的第一行和第一列作为标记空间：O(1)
     * - 总空间复杂度：O(1)
     *
     * @param matrix 输入的二维整数矩阵
     */
    public static void setZeroes(int[][] matrix) {
        // 获取矩阵的行数和列数
        int m = matrix.length;
        int n = matrix[0].length;

        // 记录第一行是否需要置零
        boolean firstRowZero = false;

        // 记录第一列是否需要置零
        boolean firstColZero = false;

        // 1. 判断第一行是否有 0
        // 遍历第一行的所有列
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // 1. 判断第一列是否有 0
        // 遍历第一列的所有行
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // 2. 利用第一行和第一列标记需要置零的行和列
        // 从第二行第二列开始遍历（索引从1开始）
        // 外层循环遍历行（从索引1开始）
        for (int i = 1; i < m; i++) {
            // 内层循环遍历列（从索引1开始）
            for (int j = 1; j < n; j++) {
                // 如果当前元素为0
                if (matrix[i][j] == 0) {
                    // 在第一行标记该列需要置零
                    matrix[i][0] = 0;
                    // 在第一列标记该行需要置零
                    matrix[0][j] = 0;
                }
            }
        }

        // 3. 根据标记置零（从第二行、第二列开始）
        // 外层循环遍历行（从索引1开始）
        for (int i = 1; i < m; i++) {
            // 内层循环遍历列（从索引1开始）
            for (int j = 1; j < n; j++) {
                // 如果该行或该列需要置零
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    // 将当前元素置零
                    matrix[i][j] = 0;
                }
            }
        }

        // 4. 处理第一列
        if (firstColZero) {
            // 遍历所有行，将第一列元素置零
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }

        // 5. 处理第一行
        if (firstRowZero) {
            // 遍历所有列，将第一行元素置零
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
    }

    /**
     * 方法2：使用额外空间的解法
     *
     * 算法思路：
     * 使用额外的布尔数组记录哪些行和列需要置零
     *
     * 示例过程（以矩阵 [[1,1,1],[1,0,1],[1,1,1]] 为例）：
     *
     * 1. 初始化: m=3, n=3
     *    rowZero = [false, false, false]
     *    colZero = [false, false, false]
     *
     * 2. 标记包含0的行列:
     *    matrix[1][1]=0: rowZero[1]=true, colZero[1]=true
     *    rowZero = [false, true, false]
     *    colZero = [false, true, false]
     *
     * 3. 根据标记置零:
     *    i=1: rowZero[1]=true, 第1行全部置零
     *    j=1: colZero[1]=true, 第1列全部置零
     *
     * 4. 最终结果:
     *    [1 0 1]
     *    [0 0 0]
     *    [1 0 1]
     *
     * 时间复杂度分析：
     * - 标记包含0的行和列：O(m × n)，其中m为行数，n为列数
     * - 根据标记置零：O(m × n)
     * - 总时间复杂度：O(m × n)
     *
     * 空间复杂度分析：
     * - 行标记数组：O(m)
     * - 列标记数组：O(n)
     * - 总空间复杂度：O(m + n)
     *
     * @param matrix 输入的二维整数矩阵
     */
    public static void setZeroesExtraSpace(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 创建行和列的标记数组
        boolean[] rowZero = new boolean[m];
        boolean[] colZero = new boolean[n];

        // 标记包含0的行和列
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rowZero[i] = true;
                    colZero[j] = true;
                }
            }
        }

        // 根据标记置零
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rowZero[i] || colZero[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 方法3：暴力解法（仅供对比）
     *
     * 算法思路：
     * 创建矩阵副本，遍历原矩阵找到0元素，然后在副本中置零对应行列
     *
     * 示例过程（以矩阵 [[1,1,1],[1,0,1],[1,1,1]] 为例）：
     *
     * 1. 创建副本: copy = [[1,1,1],[1,0,1],[1,1,1]]
     *
     * 2. 遍历原矩阵处理0元素:
     *    i=0,j=0: matrix[0][0]=1, 跳过
     *    i=0,j=1: matrix[0][1]=1, 跳过
     *    i=0,j=2: matrix[0][2]=1, 跳过
     *    i=1,j=0: matrix[1][0]=1, 跳过
     *    i=1,j=1: matrix[1][1]=0, 置零第1行和第1列
     *             copy[1][0]=0, copy[1][1]=0, copy[1][2]=0
     *             copy[0][1]=0, copy[1][1]=0, copy[2][1]=0
     *             copy = [[1,0,1],[0,0,0],[1,0,1]]
     *    i=1,j=2: matrix[1][2]=1, 跳过
     *    i=2,j=0: matrix[2][0]=1, 跳过
     *    i=2,j=1: matrix[2][1]=1, 跳过
     *    i=2,j=2: matrix[2][2]=1, 跳过
     *
     * 3. 复制回原矩阵: matrix = [[1,0,1],[0,0,0],[1,0,1]]
     *
     * 时间复杂度分析：
     * - 创建副本：O(m × n)，其中m为行数，n为列数
     * - 遍历原矩阵并置零：O(m × n × (m + n))
     * - 复制回原矩阵：O(m × n)
     * - 总时间复杂度：O(m × n × (m + n))
     *
     * 空间复杂度分析：
     * - 矩阵副本：O(m × n)
     * - 总空间复杂度：O(m × n)
     *
     * @param matrix 输入的二维整数矩阵
     */
    public static void setZeroesBruteForce(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 创建矩阵副本
        int[][] copy = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = matrix[i][j];
            }
        }

        // 遍历原矩阵
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    // 置零第i行
                    for (int k = 0; k < n; k++) {
                        copy[i][k] = 0;
                    }
                    // 置零第j列
                    for (int k = 0; k < m; k++) {
                        copy[k][j] = 0;
                    }
                }
            }
        }

        // 将结果复制回原矩阵
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = copy[i][j];
            }
        }
    }
}
