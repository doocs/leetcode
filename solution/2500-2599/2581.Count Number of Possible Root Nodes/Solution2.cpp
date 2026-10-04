class Solution {
public:
    int rootCount(vector<vector<int>>& edges, vector<vector<int>>& guesses, int k) {
        int n = edges.size() + 1;
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        unordered_map<long long, int> gs;
        for (auto& e : guesses) {
            gs[1LL * e[0] * n + e[1]]++;
        }
        auto get = [&](int i, int j) {
            auto it = gs.find(1LL * i * n + j);
            return it == gs.end() ? 0 : it->second;
        };
        int cnt = 0;
        vector<pair<int, int>> stk{{0, -1}};
        while (!stk.empty()) {
            auto [i, fa] = stk.back();
            stk.pop_back();
            for (int j : g[i]) {
                if (j != fa) {
                    cnt += get(i, j);
                    stk.push_back({j, i});
                }
            }
        }
        int ans = 0;
        vector<array<int, 3>> walk{{0, -1, cnt}};
        while (!walk.empty()) {
            auto [i, fa, c] = walk.back();
            walk.pop_back();
            ans += c >= k;
            for (int j : g[i]) {
                if (j != fa) {
                    walk.push_back({j, i, c - get(i, j) + get(j, i)});
                }
            }
        }
        return ans;
    }
};
