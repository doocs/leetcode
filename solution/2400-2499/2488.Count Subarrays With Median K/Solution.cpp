class Solution {
public:
    int countSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        int i = find(nums.begin(), nums.end(), k) - nums.begin();
        int cnt[n << 1 | 1];
        memset(cnt, 0, sizeof(cnt));
        int ans = 1;
        int x = 0;
        for (int j = i + 1; j < n; ++j) {
            x += nums[j] > k ? 1 : -1;
            if (x >= 0 && x <= 1) {
                ++ans;
            }
            ++cnt[x + n];
        }
        x = 0;
        for (int j = i - 1; ~j; --j) {
            x += nums[j] > k ? 1 : -1;
            if (x >= 0 && x <= 1) {
                ++ans;
            }
            ans += cnt[-x + n] + cnt[-x + 1 + n];
        }
        return ans;
    }
};