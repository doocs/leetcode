class Solution {
public:
    int maximumTeamSize(vector<int>& startTime, vector<int>& endTime) {
        int n = startTime.size();
        vector<pair<int, int>> intervals(n);
        for (int i = 0; i < n; i++) {
            intervals[i] = {startTime[i], endTime[i]};
        }

        sort(startTime.begin(), startTime.end());
        sort(endTime.begin(), endTime.end());

        int ans = 0;
        for (const auto& [l, r] : intervals) {
            int i = upper_bound(endTime.begin(), endTime.end(), l - 1) - endTime.begin();
            int j = upper_bound(startTime.begin(), startTime.end(), r) - startTime.begin();
            ans = max(ans, j - i);
        }

        return ans;
    }
};
