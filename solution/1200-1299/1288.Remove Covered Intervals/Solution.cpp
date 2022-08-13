class Solution {
public:
    int removeCoveredIntervals(vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end(), [](const vector<int>& a, const vector<int>& b) { return a[0] == b[0] ? b[1] < a[1] : a[0] < b[0]; });
        int cnt = 1;
        vector<int> pre = intervals[0];
        for (int i = 1; i < intervals.size(); ++i) {
            if (pre[1] < intervals[i][1]) {
                ++cnt;
                pre = intervals[i];
            }
        }
        return cnt;
    }
};