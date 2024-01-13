class Solution {
public:
    int jobScheduling(vector<int>& startTime, vector<int>& endTime, vector<int>& profit) {
        int n = profit.size();
        vector<tuple<int, int, int>> jobs(n);
        for (int i = 0; i < n; ++i) jobs[i] = {startTime[i], endTime[i], profit[i]};
        sort(jobs.begin(), jobs.end());
        vector<int> f(n);
        function<int(int)> dfs = [&](int i) -> int {
            if (i >= n) return 0;
            if (f[i]) return f[i];
            auto [_, e, p] = jobs[i];
            tuple<int, int, int> t{e, 0, 0};
            int j = lower_bound(jobs.begin() + i + 1, jobs.end(), t, [&](auto& l, auto& r) -> bool { return get<0>(l) < get<0>(r); }) - jobs.begin();
            int ans = max(dfs(i + 1), p + dfs(j));
            f[i] = ans;
            return ans;
        };
        return dfs(0);
    }
};