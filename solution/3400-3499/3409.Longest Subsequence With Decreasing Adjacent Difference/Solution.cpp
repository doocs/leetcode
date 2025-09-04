#include <algorithm>
#include <ranges>
#include <vector>
using namespace std;
using namespace std::ranges;

class Solution {
public:
    int longestSubsequence(vector<int>& nums) {
        const int mx = max(nums);
        vector<vector<int>> dp(mx + 1, vector<int>(mx + 1));

        for (const int num : nums) {
            for (int prev = 1; prev <= mx; ++prev) {
                const int diff = abs(num - prev);
                dp[num][diff] = max(dp[num][diff], dp[prev][diff] + 1);
            }
            for (int j = mx - 1; j >= 0; --j)
                dp[num][j] = max(dp[num][j], dp[num][j + 1]);
        }

        return max_element(dp, less{}, [](const vector<int>& row) {
            return row[0];
        })->at(0);
    }
};
