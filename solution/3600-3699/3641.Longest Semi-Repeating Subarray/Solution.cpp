class Solution {
public:
    int longestSubarray(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        int ans = 0, cur = 0, l = 0;
        for (int r = 0; r < nums.size(); ++r) {
            if (++cnt[nums[r]] == 2) {
                ++cur;
            }
            while (cur > k) {
                if (--cnt[nums[l++]] == 1) {
                    --cur;
                }
            }
            ans = max(ans, r - l + 1);
        }
        return ans;
    }
};