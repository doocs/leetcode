class Solution {
public:
    vector<int> delayedCount(vector<int>& nums, int k) {
        int n = nums.size();
        unordered_map<int, int> cnt;
        vector<int> ans(n);
        for (int i = n - k - 2; i >= 0; --i) {
            ++cnt[nums[i + k + 1]];
            ans[i] = cnt[nums[i]];
        }
        return ans;
    }
};
