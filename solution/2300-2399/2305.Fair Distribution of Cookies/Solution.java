class Solution {
    private int[] cookies;
    private int[] cnt;
    private int k;
    private int n;
    private int ans = 1 << 30;

    public int distributeCookies(int[] cookies, int k) {
        n = cookies.length;
        cnt = new int[k];
        Arrays.sort(cookies);
        this.cookies = cookies;
        this.k = k;
        dfs(n - 1);
        return ans;
    }

    private void dfs(int i) {
        if (i < 0) {
            // ans = Arrays.stream(cnt).max().getAsInt();
            ans = 0;
            for (int v : cnt) {
                ans = Math.max(ans, v);
            }
            return;
        }
        for (int j = 0; j < k; ++j) {
            if (cnt[j] + cookies[i] >= ans || (j > 0 && cnt[j] == cnt[j - 1])) {
                continue;
            }
            cnt[j] += cookies[i];
            dfs(i - 1);
            cnt[j] -= cookies[i];
        }
    }
}