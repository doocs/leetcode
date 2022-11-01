class Solution {
public:
    int minimumEffort(vector<vector<int>>& tasks) {
        sort(tasks.begin(), tasks.end(), [&](auto& a, auto& b) -> bool { return a[0] - a[1] < b[0] - b[1]; });
        int ans = 0, t = 0;
        for (auto& e : tasks) {
            if (t < e[1]) {
                ans += e[1] - t;
                t = e[1];
            }
            t -= e[0];
        }
        return ans;
    }
};