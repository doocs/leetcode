class Solution {
public:
    int numOfMinutes(int n, int headID, vector<int>& manager, vector<int>& informTime) {
        vector<vector<int>> g(n);
        for (int i = 0; i < n; ++i) {
            if (manager[i] >= 0) {
                g[manager[i]].push_back(i);
            }
        }
        vector<int> time(n);
        vector<array<int, 2>> stk{{headID, 0}};
        while (!stk.empty()) {
            auto [i, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.push_back({i, 1});
                for (int j : g[i]) {
                    stk.push_back({j, 0});
                }
            } else {
                int ans = 0;
                for (int j : g[i]) {
                    ans = max(ans, time[j] + informTime[i]);
                }
                time[i] = ans;
            }
        }
        return time[headID];
    }
};
