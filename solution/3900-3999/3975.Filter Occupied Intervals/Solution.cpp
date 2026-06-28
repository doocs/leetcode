class Solution {
public:
    vector<vector<int>> filterOccupiedIntervals(vector<vector<int>>& occupiedIntervals, int freeStart, int freeEnd) {
        sort(occupiedIntervals.begin(), occupiedIntervals.end());

        vector<vector<int>> busy;
        busy.push_back(occupiedIntervals[0]);

        for (int i = 1; i < occupiedIntervals.size(); i++) {
            auto& cur = occupiedIntervals[i];
            auto& last = busy.back();

            if (last[1] + 1 < cur[0]) {
                busy.push_back(cur);
            } else {
                last[1] = max(last[1], cur[1]);
            }
        }

        vector<vector<int>> ans;

        for (auto& it : busy) {
            int s = it[0], e = it[1];

            if (e < freeStart || s > freeEnd) {
                ans.push_back({s, e});
            } else {
                if (s < freeStart) {
                    ans.push_back({s, freeStart - 1});
                }
                if (e > freeEnd) {
                    ans.push_back({freeEnd + 1, e});
                }
            }
        }

        return ans;
    }
};