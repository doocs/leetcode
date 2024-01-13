class Solution {
public:
    int eraseOverlapIntervals(vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end(), [](const auto& a, const auto& b) { return a[1] < b[1]; });
        int ans = 0, t = intervals[0][1];
        for (int i = 1; i < intervals.size(); ++i) {
            if (t <= intervals[i][0])
                t = intervals[i][1];
            else
                ++ans;
        }
        return ans;
    }
};