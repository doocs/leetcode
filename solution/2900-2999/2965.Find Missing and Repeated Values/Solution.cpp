class Solution {
public:
    vector<int> findMissingAndRepeatedValues(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<int> cnt(n * n + 1);
        vector<int> ans(2);
        for (auto& row : grid) {
            for (int x : row) {
                if (++cnt[x] == 2) {
                    ans[0] = x;
                }
            }
        }
        for (int x = 1;; ++x) {
            if (cnt[x] == 0) {
                ans[1] = x;
                return ans;
            }
        }
    }
};