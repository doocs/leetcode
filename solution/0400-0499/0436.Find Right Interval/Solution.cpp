class Solution {
public:
    vector<int> findRightInterval(vector<vector<int>>& intervals) {
        int n = intervals.size();
        vector<pair<int, int>> starts;
        for (int i = 0; i < n; ++i) {
            starts.emplace_back(make_pair(intervals[i][0], i));
        }
        sort(starts.begin(), starts.end());
        vector<int> res;
        for (auto interval : intervals) {
            int left = 0, right = n - 1;
            int end = interval[1];
            while (left < right) {
                int mid = left + right >> 1;
                if (starts[mid].first >= end)
                    right = mid;
                else
                    left = mid + 1;
            }
            res.push_back(starts[left].first < end ? -1 : starts[left].second);
        }
        return res;
    }
};