class Solution {
    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] g = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 1) {
                    g[i][j] = j == 0 ? 1 : 1 + g[i][j - 1];
                }
            }
        }
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            List<int[]> stk = new ArrayList<>();
            for (int i = 0; i < m; ++i) {
                int cur = g[i][j];
                while (!stk.isEmpty() && stk.get(stk.size() - 1)[0] >= cur) {
                    stk.remove(stk.size() - 1);
                }
                int cnt = cur * (i + 1);
                if (!stk.isEmpty()) {
                    int[] t = stk.get(stk.size() - 1);
                    cnt = t[2] + cur * (i - t[1]);
                }
                ans += cnt;
                stk.add(new int[] {cur, i, cnt});
            }
        }
        return ans;
    }
}
