public class Solution {
    public int TotalNQueens(int n) {
        bool[] cols = new bool[10];
        bool[] dg = new bool[20];
        bool[] udg = new bool[20];
        int ans = 0;

        void dfs(int i) {
            if (i == n) {
                ans++;
                return;
            }
            for (int j = 0; j < n; j++) {
                int a = i + j, b = i - j + n;
                if (cols[j] || dg[a] || udg[b]) {
                    continue;
                }
                cols[j] = dg[a] = udg[b] = true;
                dfs(i + 1);
                cols[j] = dg[a] = udg[b] = false;
            }
        }

        dfs(0);
        return ans;
    }
}