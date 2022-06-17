class Solution {
    private int[] cnt;
    private int ans;
    private int[] jobs;
    private int k;

    public int minimumTimeRequired(int[] jobs, int k) {
        this.k = k;
        Arrays.sort(jobs);
        for (int i = 0, j = jobs.length - 1; i < j; ++i, --j) {
            int t = jobs[i];
            jobs[i] = jobs[j];
            jobs[j] = t;
        }
        this.jobs = jobs;
        cnt = new int[k];
        ans = 0x3f3f3f3f;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == jobs.length) {
            int mx = 0;
            for (int v : cnt) {
                mx = Math.max(mx, v);
            }
            ans = Math.min(ans, mx);
            return;
        }
        for (int j = 0; j < k; ++j) {
            if (cnt[j] + jobs[i] >= ans) {
                continue;
            }
            cnt[j] += jobs[i];
            dfs(i + 1);
            cnt[j] -= jobs[i];
            if (cnt[j] == 0) {
                break;
            }
        }
    }
}