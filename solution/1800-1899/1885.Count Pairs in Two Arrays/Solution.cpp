class Solution {
public:
    long long countPairs(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        vector<int> nums(n);
        for (int i = 0; i < n; ++i) {
            nums[i] = nums1[i] - nums2[i];
        }
        ranges::sort(nums);
        int l = 0, r = n - 1;
        long long ans = 0;
        while (l < r) {
            while (l < r && nums[l] + nums[r] <= 0) {
                ++l;
            }
            ans += r - l;
            --r;
        }
        return ans;
    }
};
