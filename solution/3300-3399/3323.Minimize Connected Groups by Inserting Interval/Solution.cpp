class Solution {
public:
    int minConnectedGroups(vector<vector<int>>& intervals, int k) {
        sort(intervals.begin(), intervals.end());
        vector<vector<int>> merged;
        for (const auto& interval : intervals) {
            int s = interval[0], e = interval[1];
            if (merged.empty() || merged.back()[1] < s) {
                merged.emplace_back(interval);
            } else {
                merged.back()[1] = max(merged.back()[1], e);
            }
        }
        int ans = merged.size();
        for (int i = 0; i < merged.size(); ++i) {
            auto& interval = merged[i];
            int j = lower_bound(merged.begin(), merged.end(), vector<int>{interval[1] + k + 1, 0}) - merged.begin();
            ans = min(ans, (int) merged.size() - (j - i - 1));
        }
        return ans;
    }
};
