class Solution {
public:
    int countNegatives(vector<vector<int>>& grid) {
        int ans = 0;
        for (auto& row : grid) {
            ans += lower_bound(row.rbegin(), row.rend(), 0) - row.rbegin();
        }
        return ans;
    }
};