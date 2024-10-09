class Solution {
public:
    int countGoodNodes(vector<vector<int>>& edges) {
        int n = edges.size() + 1;
        vector<int> g[n];
        for (const auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        int ans = 0;
        auto dfs = [&](auto&& dfs, int a, int fa) -> int {
            int pre = -1, cnt = 1, ok = 1;
            for (int b : g[a]) {
                if (b != fa) {
                    int cur = dfs(dfs, b, a);
                    cnt += cur;
                    if (pre < 0) {
                        pre = cur;
                    } else if (pre != cur) {
                        ok = 0;
                    }
                }
            }
            ans += ok;
            return cnt;
        };
        dfs(dfs, 0, -1);
        return ans;
    }
};
