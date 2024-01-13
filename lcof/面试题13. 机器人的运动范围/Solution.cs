public class Solution {
    public int MovingCount(int m, int n, int k) {
        bool[,] arr = new bool[m, n];
        return dfs(0, 0, m, n, k, arr);
    }

    public int dfs(int i, int j, int m, int n, int k, bool[,] arr) {
        if (i >= m || j >= n || arr[i,j] || (i % 10 + j % 10 + i / 10 + j / 10) > k) {
            return 0;
        }
        arr[i,j] = true;
        return 1 + dfs(i+1, j, m, n, k, arr) + dfs(i, j+1, m, n, k, arr);
    }
}
