public class Solution {
    
    public static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    public int robot(int x, int y, int[][] m, int[][] cache) {
        if(cache[x][y] != 0) return cache[x][y];
        int max = 1;
        for(int[] dir : dirs) {
            int dx = dir[0], dy = dir[1];
            if(x + dx < 0 || x + dx >= m.length || y + dy < 0 || y + dy >= m[0].length || m[x][y] <= m[x + dx][y + dy]) {
                continue;
            }
            max = Math.max(max, robot(x + dx, y + dy, m, cache) + 1);
        }
        cache[x][y] = max;
        return max;
    }
    
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int ans = 1;
        // 枚举每一个点，计算每个点的最大升序
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                ans = Math.max(ans, robot(i, j, matrix, cache));
            }
        }
        return ans;
    }
}