class Solution {
public:
    long long finishTime(int n, vector<vector<int>>& edges, vector<int>& baseTime) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            g[e[0]].push_back(e[1]);
        }
        vector<long long> fin(n);
        vector<array<int, 2>> stk{{0, 0}};
        while (!stk.empty()) {
            auto [i, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                if (g[i].empty()) {
                    fin[i] = baseTime[i];
                } else {
                    stk.push_back({i, 1});
                    for (int j : g[i]) {
                        stk.push_back({j, 0});
                    }
                }
            } else {
                long long earliest = LLONG_MAX;
                long long latest = LLONG_MIN;
                for (int j : g[i]) {
                    earliest = min(earliest, fin[j]);
                    latest = max(latest, fin[j]);
                }
                long long ownDuration = (latest - earliest) + baseTime[i];
                fin[i] = latest + ownDuration;
            }
        }
        return fin[0];
    }
};
