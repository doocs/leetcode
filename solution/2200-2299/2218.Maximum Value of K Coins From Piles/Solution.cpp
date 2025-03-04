class Solution {
public:
    int maxValueOfCoins(vector<vector<int>>& piles, int k) {
        int n = piles.size();
        vector<vector<int>> f(n + 1, vector<int>(k + 1));
        for (int i = 1; i <= n; i++) {
            vector<int> nums = piles[i - 1];
            vector<int> s(nums.size() + 1);
            for (int j = 1; j <= nums.size(); j++) {
                s[j] = s[j - 1] + nums[j - 1];
            }
            for (int j = 0; j <= k; j++) {
                for (int h = 0; h < s.size() && h <= j; h++) {
                    f[i][j] = max(f[i][j], f[i - 1][j - h] + s[h]);
                }
            }
        }
        return f[n][k];
    }
};
