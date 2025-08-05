class Solution {
public:
    int minRemoval(vector<int>& nums, int k) {
        ranges::sort(nums);
        int cnt = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            int j = n;
            if (1LL * nums[i] * k <= nums[n - 1]) {
                j = upper_bound(nums.begin(), nums.end(), 1LL * nums[i] * k) - nums.begin();
            }
            cnt = max(cnt, j - i);
        }
        return n - cnt;
    }
};
