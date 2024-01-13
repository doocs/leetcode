class Solution {
public:
    int minAbsoluteSumDiff(vector<int>& nums1, vector<int>& nums2) {
        const int mod = 1e9 + 7;
        vector<int> nums(nums1);
        sort(nums.begin(), nums.end());
        int s = 0, n = nums.size();
        for (int i = 0; i < n; ++i) {
            s = (s + abs(nums1[i] - nums2[i])) % mod;
        }
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            int d1 = abs(nums1[i] - nums2[i]);
            int d2 = 1 << 30;
            int j = lower_bound(nums.begin(), nums.end(), nums2[i]) - nums.begin();
            if (j < n) {
                d2 = min(d2, abs(nums[j] - nums2[i]));
            }
            if (j) {
                d2 = min(d2, abs(nums[j - 1] - nums2[i]));
            }
            mx = max(mx, d1 - d2);
        }
        return (s - mx + mod) % mod;
    }
};