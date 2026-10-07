class Solution {
public:
    vector<int> smallestMissingValueSubtree(vector<int>& parents, vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> g(n);
        int idx = -1;
        for (int i = 0; i < n; ++i) {
            if (i) {
                g[parents[i]].push_back(i);
            }
            if (nums[i] == 1) {
                idx = i;
            }
        }
        vector<int> ans(n, 1);
        if (idx == -1) {
            return ans;
        }
        vector<char> vis(n), has(n + 2);
        auto dfs = [&](int start) {
            vector<int> stk{start};
            while (!stk.empty()) {
                int i = stk.back();
                stk.pop_back();
                if (vis[i]) {
                    continue;
                }
                vis[i] = 1;
                if (nums[i] < n + 2) {
                    has[nums[i]] = 1;
                }
                for (int j : g[i]) {
                    stk.push_back(j);
                }
            }
        };
        for (int i = 2; ~idx; idx = parents[idx]) {
            dfs(idx);
            while (has[i]) {
                ++i;
            }
            ans[idx] = i;
        }
        return ans;
    }
};
