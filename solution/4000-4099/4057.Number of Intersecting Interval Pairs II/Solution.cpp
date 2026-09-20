class Solution {
public:
    long long countIntersectingIntervals(vector<vector<int>>& intervals) {
        int n = intervals.size();
        vector<int> starts(n), ends(n);
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        sort(starts.begin(), starts.end());
        sort(ends.begin(), ends.end());
        long long ans = 1LL * n * (n - 1) / 2;
        int i = 0;
        for (int start : starts) {
            while (i < n && ends[i] < start) {
                i++;
            }
            ans -= i;
        }
        return ans;
    }
};