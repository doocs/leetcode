package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 岛屿数量（LeetCode 200）
 *
 * 时间复杂度：O(M×N)
 * - M和N是网格的行数和列数
 * - 最坏情况下需要访问每个单元格一次
 *
 * 空间复杂度：O(M×N)
 * - 最坏情况下递归调用栈深度为M×N（整个网格都是陆地）
 */
public class NumIsLands200 {

    /**
     * 计算二维网格中的岛屿数量
     * 岛屿由 '1' 表示，水由 '0' 表示
     * 岛屿在水平和垂直方向上相连
     *
     * 算法思路：
     * 使用深度优先搜索（DFS）遍历网格
     * 1. 遍历网格中的每个单元格
     * 2. 当发现未访问的陆地('1')时，岛屿计数加1
     * 3. 使用DFS将与当前陆地相连的所有陆地标记为已访问('0')
     * 4. 继续遍历直到处理完所有单元格
     *
     * @param grid 二维字符数组，表示地图
     * @return 岛屿的数量
     */
    public int numIslands(char[][] grid) {
        // 检查边界条件
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int islandCount = 0;

        // 遍历网格中的每个单元格
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 如果发现一个未访问的陆地单元格
                if (grid[i][j] == '1') {
                    islandCount++;
                    // 使用DFS将与当前陆地相连的所有陆地标记为已访问
                    dfs(grid, i, j);
                }
            }
        }

        return islandCount;
    }

    /**
     * 深度优先搜索，将与当前陆地相连的所有陆地标记为已访问（'0'）
     *
     * @param grid 二维字符数组
     * @param row 当前行索引
     * @param col 当前列索引
     */
    private void dfs(char[][] grid, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;

        // 检查边界条件和是否已访问
        // 剪枝：如果越界或者当前单元格是水('0')，直接返回
        if (row < 0 || col < 0 || row >= rows || col >= cols || grid[row][col] == '0') {
            return;
        }

        // 标记当前单元格为已访问（将陆地'1'改为水'0'）
        // 这样避免了使用额外的visited数组
        grid[row][col] = '0';

        // 递归访问四个相邻单元格（上下左右）
        dfs(grid, row + 1, col);
        dfs(grid, row - 1, col);
        dfs(grid, row, col + 1);
        dfs(grid, row, col - 1);
    }

    /**
     * 方法2：广度优先搜索（BFS）解法
     *
     * 算法思路：
     * 使用队列进行广度优先搜索
     * 1. 发现陆地时，将位置加入队列
     * 2. 从队列中取出位置，检查四个方向的相邻位置
     * 3. 将相邻的陆地加入队列并标记为已访问
     *
     * @param grid 二维字符数组
     * @return 岛屿的数量
     */
    public int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int islandCount = 0;

        // 四个方向的偏移量
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    islandCount++;

                    // 使用BFS标记相连的陆地
                    java.util.Queue<int[]> queue = new java.util.LinkedList<>();
                    queue.offer(new int[]{i, j});
                    grid[i][j] = '0';

                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        int row = current[0];
                        int col = current[1];

                        // 检查四个方向
                        for (int[] dir : directions) {
                            int newRow = row + dir[0];
                            int newCol = col + dir[1];

                            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                                grid[newRow][newCol] == '1') {
                                grid[newRow][newCol] = '0';
                                queue.offer(new int[]{newRow, newCol});
                            }
                        }
                    }
                }
            }
        }

        return islandCount;
    }

    /**
     * 方法3：并查集解法
     *
     * 算法思路：
     * 使用并查集数据结构，将相邻的陆地合并到同一个集合中
     * 最终岛屿数量等于并查集中不同集合的数量
     *
     * @param grid 二维字符数组
     * @return 岛屿的数量
     */
    public int numIslandsUnionFind(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        UnionFind uf = new UnionFind(grid);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    // 检查下方和右方的相邻陆地
                    if (i + 1 < rows && grid[i + 1][j] == '1') {
                        uf.union(i * cols + j, (i + 1) * cols + j);
                    }
                    if (j + 1 < cols && grid[i][j + 1] == '1') {
                        uf.union(i * cols + j, i * cols + j + 1);
                    }
                }
            }
        }

        return uf.getCount();
    }

    /**
     * 并查集辅助类
     */
    class UnionFind {
        private int count;
        private int[] parent;
        private int[] rank;

        public UnionFind(char[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;
            parent = new int[rows * cols];
            rank = new int[rows * cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '1') {
                        parent[i * cols + j] = i * cols + j;
                        ++count;
                    }
                    rank[i * cols + j] = 0;
                }
            }
        }

        public int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]); // 路径压缩
            }
            return parent[i];
        }

        public void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                // 按秩合并
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx;
                    rank[rootx] += 1;
                }
                --count;
            }
        }

        public int getCount() {
            return count;
        }
    }

    /**
     * 主函数：处理用户输入并计算岛屿数量
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取网格的行数和列数
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.nextLine();

        // 创建网格并读取输入
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            String line = scanner.nextLine();
            grid[i] = line.toCharArray();
        }

        NumIsLands200 solution = new NumIsLands200();
        int islandCount = solution.numIslands(grid);
        System.out.println(islandCount);
    }
}
