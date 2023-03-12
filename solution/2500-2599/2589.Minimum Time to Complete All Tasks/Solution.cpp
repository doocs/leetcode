class Solution {
public:
    int findMinimumTime(vector<vector<int>>& tasks) {
        sort(tasks.begin(), tasks.end(), [&](auto& a, auto& b) { return a[1] < b[1]; });
        bitset<2010> vis;
        int ans = 0;
        for (auto& task : tasks) {
            int start = task[0], end = task[1], duration = task[2];
            for (int i = start; i <= end; ++i) {
                duration -= vis[i];
            }
            for (int i = end; i >= start && duration > 0; --i) {
                if (!vis[i]) {
                    --duration;
                    ans += vis[i] = 1;
                }
            }
        }
        return ans;
    }
};