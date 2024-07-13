class Solution {
public:
    int mincostTickets(vector<int>& days, vector<int>& costs) {
        int valid[3] = {1, 7, 30};
        int n = days.size();
        int f[n];
        memset(f, 0, sizeof(f));
        function<int(int)> dfs = [&](int i) {
            if (i >= n) {
                return 0;
            }
            if (f[i]) {
                return f[i];
            }
            f[i] = INT_MAX;
            for (int k = 0; k < 3; ++k) {
                int j = lower_bound(days.begin(), days.end(), days[i] + valid[k]) - days.begin();
                f[i] = min(f[i], dfs(j) + costs[k]);
            }
            return f[i];
        };
        return dfs(0);
    }
};