class Solution {
public:
    vector<vector<int>> insert(vector<vector<int>> &intervals, vector<int> &newInterval) {
        intervals.push_back(newInterval);
        return merge(intervals);
    }

    vector<vector<int>> merge(vector<vector<int>> &intervals) {
        sort(intervals.begin(), intervals.end());
        vector<vector<int>> res;
        int st = -1, ed = -1;
        for (auto e : intervals)
        {
            if (ed < e[0])
            {
                if (st != -1)
                {
                    res.push_back({st, ed});
                }
                st = e[0];
                ed = e[1];
            }
            else
            {
                ed = max(ed, e[1]);
            }
        }
        if (st != -1)
        {
            res.push_back({st, ed});
        }
        return res;
    }
};