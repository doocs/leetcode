class Solution {
public:
    int maxSubarrayLength(vector<int>& nums, int k) {
        int ans = 0;
        unordered_map<int, int> cnt;
        for (int l = 0, r = 0; r < nums.size(); ++r) {
            ++cnt[nums[r]];
            while (cnt[nums[r]] > k) {
                --cnt[nums[l++]];
            }
            ans = max(ans, r - l + 1);
        }
        return ans;
    }
};