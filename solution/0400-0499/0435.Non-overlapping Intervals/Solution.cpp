class Solution {
public:
    int eraseOverlapIntervals(vector<vector<int>> &intervals) {
        if (intervals.empty())
        {
            return 0;
        }
        sort(intervals.begin(), intervals.end(), [](const auto &a, const auto &b)
             { return a[1] < b[1]; });
        int ed = intervals[0][1], cnt = 0;
        for (int i = 1; i < intervals.size(); ++i)
        {
            if (ed <= intervals[i][0])
            {
                ed = intervals[i][1];
            }
            else
            {
                ++cnt;
            }
        }
        return cnt;
    }
};