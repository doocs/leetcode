class Solution {
    private int[] path;
    private int[] cnt;
    private int n;

    public int[] constructDistancedSequence(int n) {
        this.n = n;
        path = new int[n * 2];
        cnt = new int[n * 2];
        Arrays.fill(cnt, 2);
        cnt[1] = 1;
        dfs(1);
        int[] ans = new int[n * 2 - 1];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = path[i + 1];
        }
        return ans;
    }

    private boolean dfs(int u) {
        if (u == n * 2) {
            return true;
        }
        if (path[u] > 0) {
            return dfs(u + 1);
        }
        for (int i = n; i > 1; --i) {
            if (cnt[i] > 0 && u + i < n * 2 && path[u + i] == 0) {
                cnt[i] = 0;
                path[u] = i;
                path[u + i] = i;
                if (dfs(u + 1)) {
                    return true;
                }
                cnt[i] = 2;
                path[u] = 0;
                path[u + i] = 0;
            }
        }
        if (cnt[1] > 0) {
            path[u] = 1;
            cnt[1] = 0;
            if (dfs(u + 1)) {
                return true;
            }
            cnt[1] = 1;
            path[u] = 0;
        }
        return false;
    }
}