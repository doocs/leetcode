package com.funian.algorithm.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 螺旋矩阵（LeetCode 54）
 *
 * 时间复杂度：O(m × n)
 * - 需要遍历矩阵中的每个元素一次
 * - 总共m×n个元素，时间复杂度为O(m × n)
 *
 * 空间复杂度：O(1)
 * - 不考虑返回结果列表，只使用了常数级别的额外空间
 * - 使用四个边界变量控制遍历过程
 */
public class SpiralOrderMatrix54 {
    public static void main(String[] args) {
        // 创建 Scanner 对象读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 输入矩阵的行和列
        System.out.print("请输入矩阵的行数 m 和列数 n：");

        // 读取行数
        int m = scanner.nextInt();

        // 读取列数
        int n = scanner.nextInt();

        // 初始化矩阵
        int[][] matrix = new int[m][n];

        // 提示用户输入矩阵元素
        System.out.println("请输入矩阵的元素，每行用空格分隔：");

        // 读取矩阵元素
        // 外层循环遍历行
        for (int i = 0; i < m; i++) {
            // 内层循环遍历列
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // 调用 spiralOrder 方法按螺旋顺序遍历矩阵
        List<Integer> result = spiralOrder(matrix);

        // 输出结果
        System.out.println("螺旋顺序遍历的结果是：");

        // 遍历并打印结果
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    /**
     * 螺旋顺序遍历矩阵
     * 按照顺时针螺旋顺序返回矩阵中的所有元素
     *
     * 算法思路：
     * 使用四个边界变量(top, bottom, left, right)控制遍历过程
     * 1. 从左到右遍历上边界
     * 2. 从上到下遍历右边界
     * 3. 从右到左遍历下边界
     * 4. 从下到上遍历左边界
     * 5. 每遍历完一边，相应的边界向内收缩
     * 6. 重复直到所有元素都被遍历
     *
     * 示例过程（以矩阵 [[1,2,3],[4,5,6],[7,8,9]] 为例）：
     *
     * 原矩阵:
     * [1 2 3]
     * [4 5 6]
     * [7 8 9]
     *
     * 初始边界: top=0, bottom=2, left=0, right=2
     *
     * 第1轮:
     * 1. 从左到右遍历上边界(top=0): 1 2 3
     *    top++ → top=1
     * 2. 从上到下遍历右边界(right=2): 6 9
     *    right-- → right=1
     * 3. 从右到左遍历下边界(bottom=2): 8 7
     *    bottom-- → bottom=1
     * 4. 从下到上遍历左边界(left=0): 4
     *    left++ → left=1
     *
     * 第2轮:
     * 1. 从左到右遍历上边界(top=1): 5
     *    top++ → top=2
     *
     * 边界条件: top(2) > bottom(1)，结束遍历
     *
     * 最终结果: [1, 2, 3, 6, 9, 8, 7, 4, 5]
     *
     * 时间复杂度分析：
     * - 遍历矩阵中的每个元素一次：O(m × n)，其中m为行数，n为列数
     *
     * 空间复杂度分析：
     * - 结果列表：O(m × n)
     * - 边界变量：O(1)
     * - 总空间复杂度：O(1)（不考虑输出空间）
     *
     * @param matrix 输入的二维整数矩阵
     * @return 按螺旋顺序排列的元素列表
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        // 存储螺旋遍历结果的列表
        List<Integer> result = new ArrayList<>();

        // 边界条件检查
        if (matrix == null || matrix.length == 0) {
            return result;
        }

        // 获取矩阵的行数和列数
        int m = matrix.length;
        int n = matrix[0].length;

        // 定义四个边界：上、下、左、右
        int top = 0;
        int bottom = m - 1;
        int left = 0;
        int right = n - 1;

        // 模拟螺旋顺序遍历
        while (top <= bottom && left <= right) {
            // 从左到右遍历上边界
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            // 上边界下移
            top++;

            // 从上到下遍历右边界
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            // 右边界左移
            right--;

            // 从右到左遍历下边界（检查是否还有行需要遍历）
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                // 下边界上移
                bottom--;
            }

            // 从下到上遍历左边界（检查是否还有列需要遍历）
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                // 左边界右移
                left++;
            }
        }

        // 返回结果列表
        return result;
    }

    /**
     * 方法2：递归解法
     *
     * 算法思路：
     * 递归地处理每一圈的螺旋遍历
     *
     * 示例过程（以矩阵 [[1,2,3],[4,5,6],[7,8,9]] 为例）：
     *
     * 1. 初始调用: spiral(matrix, 0, 0, 2, 2, result)
     *    遍历外圈: [1,2,3,6,9,8,7,4]，更新边界为top=1, right=1, bottom=1, left=1
     *
     * 2. 递归调用: spiral(matrix, 1, 1, 1, 1, result)
     *    遍历内圈: [5]，更新边界为top=2, right=0, bottom=0, left=2
     *
     * 3. 递归调用: spiral(matrix, 2, 2, 0, 0, result)
     *    边界条件不满足(top>bottom)，返回
     *
     * 最终结果: [1, 2, 3, 6, 9, 8, 7, 4, 5]
     *
     * 时间复杂度分析：
     * - 遍历矩阵中的每个元素一次：O(m × n)，其中m为行数，n为列数
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(min(m, n))
     * - 结果列表：O(m × n)
     * - 总空间复杂度：O(min(m, n))
     *
     * @param matrix 输入的二维整数矩阵
     * @return 按螺旋顺序排列的元素列表
     */
    public static List<Integer> spiralOrderRecursive(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }

        spiral(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1, result);
        return result;
    }

    /**
     * 递归辅助方法
     *
     * 算法思路：
     * 递归处理当前圈的螺旋遍历，然后递归处理内圈
     *
     * @param matrix 矩阵
     * @param top 上边界
     * @param left 左边界
     * @param bottom 下边界
     * @param right 右边界
     * @param result 结果列表
     */
    private static void spiral(int[][] matrix, int top, int left, int bottom, int right, List<Integer> result) {
        // 边界条件
        if (top > bottom || left > right) {
            return;
        }

        // 从左到右遍历上边界
        for (int i = left; i <= right; i++) {
            result.add(matrix[top][i]);
        }
        top++;

        // 从上到下遍历右边界
        for (int i = top; i <= bottom; i++) {
            result.add(matrix[i][right]);
        }
        right--;

        // 从右到左遍历下边界
        if (top <= bottom) {
            for (int i = right; i >= left; i--) {
                result.add(matrix[bottom][i]);
            }
            bottom--;
        }

        // 从下到上遍历左边界
        if (left <= right) {
            for (int i = bottom; i >= top; i--) {
                result.add(matrix[i][left]);
            }
            left++;
        }

        // 递归处理内层
        spiral(matrix, top, left, bottom, right, result);
    }

    /**
     * 方法3：方向数组解法
     *
     * 算法思路：
     * 使用方向数组控制移动方向，遇到边界或已访问元素时转向
     *
     * 示例过程（以矩阵 [[1,2,3],[4,5,6],[7,8,9]] 为例）：
     *
     * 1. 初始化:
     *    dx=[0,1,0,-1], dy=[1,0,-1,0] (右,下,左,上)
     *    direction=0 (向右), x=0, y=0
     *    visited=[[false,false,false],[false,false,false],[false,false,false]]
     *
     * 2. 遍历过程:
     *    i=0: 添加matrix[0][0]=1, visited[0][0]=true
     *    i=1: 添加matrix[0][1]=2, visited[0][1]=true
     *    i=2: 添加matrix[0][2]=3, visited[0][2]=true
     *    i=3: 下一位置(0,3)越界，转向(direction=1)，添加matrix[1][2]=6
     *    i=4: 添加matrix[2][2]=9, visited[2][2]=true
     *    i=5: 下一位置(3,2)越界，转向(direction=2)，添加matrix[2][1]=8
     *    ...继续直到遍历完所有元素
     *
     * 时间复杂度分析：
     * - 遍历矩阵中的每个元素一次：O(m × n)，其中m为行数，n为列数
     *
     * 空间复杂度分析：
     * - 访问标记数组：O(m × n)
     * - 结果列表：O(m × n)
     * - 方向数组：O(1)
     * - 其他变量：O(1)
     * - 总空间复杂度：O(m × n)
     *
     * @param matrix 输入的二维整数矩阵
     * @return 按螺旋顺序排列的元素列表
     */
    public static List<Integer> spiralOrderDirection(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        // 方向数组：右、下、左、上
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int direction = 0; // 当前方向索引

        int x = 0, y = 0; // 当前位置

        // 访问标记数组
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m * n; i++) {
            result.add(matrix[x][y]);
            visited[x][y] = true;

            // 计算下一个位置
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            // 检查是否需要转向
            if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[nx][ny]) {
                // 转向
                direction = (direction + 1) % 4;
                nx = x + dx[direction];
                ny = y + dy[direction];
            }

            // 更新当前位置
            x = nx;
            y = ny;
        }

        return result;
    }
}
