class Solution {
public:
    int maxSelectedElements(vector<int>& nums) {
        sort(nums.begin(), nums.end());

        unordered_map<int, int> dp;
        int ans = 0;

        for (int x : nums) {
            int a = dp[x] + 1;
            int b = dp[x - 1] + 1;

            dp[x + 1] = max(dp[x + 1], a);
            dp[x] = max(dp[x], b);

            ans = max(ans, max(dp[x], dp[x + 1]));
        }

        return ans;
    }
};
