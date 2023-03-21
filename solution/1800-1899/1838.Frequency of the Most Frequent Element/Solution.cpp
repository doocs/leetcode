class Solution {
public:
    int maxFrequency(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        int ans = 1;
        long long window = 0;
        for (int l = 0, r = 1; r < n; ++r) {
            window += 1LL * (nums[r] - nums[r - 1]) * (r - l);
            while (window > k) {
                window -= (nums[r] - nums[l++]);
            }
            ans = max(ans, r - l + 1);
        }
        return ans;
    }
};