class Solution {
public:
    long long minEnergy(int n, int brightness, vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end());
        vector<vector<int>> merged = {intervals[0]};
        for (int i = 1; i < intervals.size(); ++i) {
            auto& x = intervals[i];
            if (merged.back()[1] < x[0]) {
                merged.push_back(x);
            } else {
                merged.back()[1] = max(merged.back()[1], x[1]);
            }
        }
        long long ans = 0;
        for (const auto& interval : merged) {
            int start = interval[0];
            int end = interval[1];
            int m = end - start + 1;
            ans += (brightness + 2LL) / 3 * m;
        }
        return ans;
    }
};