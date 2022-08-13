class Solution {
public:
    int ans;

    int minimumTimeRequired(vector<int>& jobs, int k) {
        vector<int> cnt(k);
        ans = 0x3f3f3f3f;
        sort(jobs.begin(), jobs.end(), greater<int>());
        dfs(0, k, jobs, cnt);
        return ans;
    }

    void dfs(int i, int k, vector<int>& jobs, vector<int>& cnt) {
        if (i == jobs.size()) {
            ans = min(ans, *max_element(cnt.begin(), cnt.end()));
            return;
        }
        for (int j = 0; j < k; ++j) {
            if (cnt[j] + jobs[i] >= ans) continue;
            cnt[j] += jobs[i];
            dfs(i + 1, k, jobs, cnt);
            cnt[j] -= jobs[i];
            if (cnt[j] == 0) break;
        }
    }
};