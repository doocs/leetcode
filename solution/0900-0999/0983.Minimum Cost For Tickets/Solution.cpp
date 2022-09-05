class Solution {
public:
    vector<int> t = {1, 7, 30};
    vector<int> days;
    vector<int> costs;
    vector<int> f;
    int n;

    int mincostTickets(vector<int>& days, vector<int>& costs) {
        n = days.size();
        this->days = days;
        this->costs = costs;
        f.assign(n, -1);
        return dfs(0);
    }

    int dfs(int i) {
        if (i >= n) return 0;
        if (f[i] != -1) return f[i];
        int res = INT_MAX;
        for (int k = 0; k < 3; ++k) {
            int j = lower_bound(days.begin(), days.end(), days[i] + t[k]) - days.begin();
            res = min(res, costs[k] + dfs(j));
        }
        f[i] = res;
        return res;
    }
};