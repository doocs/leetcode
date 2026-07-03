class Solution {
public:
    long long finishTime(int n, vector<vector<int>>& edges, vector<int>& baseTime) {
        vector<vector<int>> g(n);

        for (auto& e : edges) {
            g[e[0]].push_back(e[1]);
        }

        auto dfs = [&](this auto&& dfs, int i) -> long long {
            if (g[i].empty()) {
                return baseTime[i];
            }

            long long earliest = LLONG_MAX / 4;
            long long latest = -LLONG_MAX / 4;

            for (int j : g[i]) {
                long long a = dfs(j);
                earliest = min(earliest, a);
                latest = max(latest, a);
            }

            long long own_duration = (latest - earliest) + baseTime[i];
            return latest + own_duration;
        };

        return dfs(0);
    }
};