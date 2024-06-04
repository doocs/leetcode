class Solution {
public:
    vector<int> findRightInterval(vector<vector<int>>& intervals) {
        int n = intervals.size();
        vector<pair<int, int>> arr;
        for (int i = 0; i < n; ++i) {
            arr.emplace_back(intervals[i][0], i);
        }
        sort(arr.begin(), arr.end());
        vector<int> ans;
        for (auto& e : intervals) {
            int j = lower_bound(arr.begin(), arr.end(), make_pair(e[1], -1)) - arr.begin();
            ans.push_back(j == n ? -1 : arr[j].second);
        }
        return ans;
    }
};