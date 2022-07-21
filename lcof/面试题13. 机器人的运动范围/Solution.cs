// public class Solution {
//     int res = 0;
//     public int MovingCount(int m, int n, int k) [
//         bool[,] arr = new bool[m, n];
//         dfs(0, 0, m, n, k, arr);
//         return res;
//     ]

//     public void dfs(int i, int j, int m, int n, int k, bool[,] arr) {
//         if (i >= m || i < 0 || j >= n || j < 0 || arr[i, j]) {
//             return;
//         }
//         arr[i, j] = true;
//         int sum = i % 10 + j % 10 + i / 10 + j / 10;
//         if (sum > k) {
//             return;
//         }
//         res += 1;
//         dfs(i+1, j, m, n, k, arr);
//         dfs(i-1, j, m, n, k, arr);
//         dfs(i, j+1, m, n, k, arr);
//         dfs(i, j-1, m, n, k, arr);
//     }
// }

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