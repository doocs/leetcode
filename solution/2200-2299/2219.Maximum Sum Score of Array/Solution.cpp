class Solution {
public:
    long long maximumSumScore(vector<int>& nums) {
        long long l = 0, r = accumulate(nums.begin(), nums.end(), 0LL);
        long long ans = -1e18;
        for (int x : nums) {
            l += x;
            ans = max({ans, l, r});
            r -= x;
        }
        return ans;
    }
};
