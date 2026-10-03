class Solution {
public:
    int countHighestScoreNodes(vector<int>& parents) {
        int n = parents.size();
        vector<vector<int>> g(n);
        for (int i = 1; i < n; ++i) {
            g[parents[i]].push_back(i);
        }
        int ans = 0;
        long long mx = 0;
        vector<int> sz(n);
        vector<array<int, 3>> stk{{0, -1, 0}};
        while (!stk.empty()) {
            auto cur = stk.back();
            stk.pop_back();
            int i = cur[0], fa = cur[1], state = cur[2];
            if (state == 0) {
                stk.push_back({i, fa, 1});
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push_back({j, i, 0});
                    }
                }
            } else {
                int cnt = 1;
                long long score = 1;
                for (int j : g[i]) {
                    if (j != fa) {
                        int t = sz[j];
                        cnt += t;
                        score *= t;
                    }
                }
                if (n - cnt) {
                    score *= n - cnt;
                }
                if (mx < score) {
                    mx = score;
                    ans = 1;
                } else if (mx == score) {
                    ++ans;
                }
                sz[i] = cnt;
            }
        }
        return ans;
    }
};
