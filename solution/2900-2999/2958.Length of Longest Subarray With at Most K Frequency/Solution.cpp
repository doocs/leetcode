class Solution {
public:
    int maxSubarrayLength(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        int ans = 0;
        for (int i = 0, j = 0; i < nums.size(); ++i) {
            ++cnt[nums[i]];
            while (cnt[nums[i]] > k) {
                --cnt[nums[j++]];
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};