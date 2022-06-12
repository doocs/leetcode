class Solution {
    private int[] cookies;
    private int k;
    private int[] cnt;
    private int ans;

    public int distributeCookies(int[] cookies, int k) {
        ans = 0x3f3f3f3f;
        this.cookies = cookies;
        this.k = k;
        this.cnt = new int[k];
        dfs(0);
        return ans;
    }

    private void dfs(int u) {
        if (u == cookies.length) {
            int mx = cnt[0];
            for (int v : cnt) {
                mx = Math.max(mx, v);
            }
            ans = Math.min(ans, mx);
            return;
        }
        for (int i = 0; i < k; ++i) {
            if (cnt[i] + cookies[u] < ans) {
                cnt[i] += cookies[u];
                dfs(u + 1);
                cnt[i] -= cookies[u];
            }
        }
    }
}