class Solution {
public:
    vector<int> getCoprimes(vector<int>& nums, vector<vector<int>>& edges) {
        int n = nums.size();
        vector<vector<int>> g(n);
        vector<vector<int>> f(51);
        vector<stack<pair<int, int>>> stks(51);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].emplace_back(v);
            g[v].emplace_back(u);
        }
        for (int i = 1; i < 51; ++i) {
            for (int j = 1; j < 51; ++j) {
                if (__gcd(i, j) == 1) {
                    f[i].emplace_back(j);
                }
            }
        }
        vector<int> ans(n);
        function<void(int, int, int)> dfs = [&](int i, int fa, int depth) {
            int t = -1, k = -1;
            for (int v : f[nums[i]]) {
                auto& stk = stks[v];
                if (!stk.empty() && stk.top().second > k) {
                    t = stk.top().first;
                    k = stk.top().second;
                }
            }
            ans[i] = t;
            for (int j : g[i]) {
                if (j != fa) {
                    stks[nums[i]].push({i, depth});
                    dfs(j, i, depth + 1);
                    stks[nums[i]].pop();
                }
            }
        };
        dfs(0, -1, 0);
        return ans;
    }
};