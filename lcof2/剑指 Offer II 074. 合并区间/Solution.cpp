class Solution {
public:
    vector<vector<int>> merge(vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end());
        int st = intervals[0][0], ed = intervals[0][1];
        vector<vector<int>> ans;
        for (int i = 1; i < intervals.size(); ++i) {
            int s = intervals[i][0], e = intervals[i][1];
            if (ed < s) {
                ans.push_back({st, ed});
                st = s, ed = e;
            } else
                ed = max(ed, e);
        }
        ans.push_back({st, ed});
        return ans;
    }
};