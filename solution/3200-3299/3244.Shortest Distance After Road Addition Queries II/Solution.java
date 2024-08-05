class Solution {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int[] nxt = new int[n - 1];
        for (int i = 1; i < n; ++i) {
            nxt[i - 1] = i;
        }
        int m = queries.length;
        int cnt = n - 1;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int u = queries[i][0], v = queries[i][1];
            if (nxt[u] > 0 && nxt[u] < v) {
                int j = nxt[u];
                while (j < v) {
                    --cnt;
                    int t = nxt[j];
                    nxt[j] = 0;
                    j = t;
                }
                nxt[u] = v;
            }
            ans[i] = cnt;
        }
        return ans;
    }
}
