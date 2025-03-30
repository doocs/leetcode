class Solution {
public:
    int longestSquareStreak(vector<int>& nums) {
        unordered_set<long long> s(nums.begin(), nums.end());
        int ans = -1;
        for (long long x : nums) {
            int t = 0;
            for (; s.contains(x); x *= x) {
                ++t;
            }
            if (t > 1) {
                ans = max(ans, t);
            }
        }
        return ans;
    }
};