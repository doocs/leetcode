class Solution {
public:
    int minimumTime(vector<int>& nums1, vector<int>& nums2, int x) {
        int n = nums1.size();
        vector<pair<int, int>> nums;
        for (int i = 0; i < n; ++i) {
            nums.emplace_back(nums2[i], nums1[i]);
        }
        sort(nums.begin(), nums.end());
        int f[n + 1];
        memset(f, 0, sizeof(f));
        for (auto [b, a] : nums) {
            for (int j = n; j; --j) {
                f[j] = max(f[j], f[j - 1] + a + b * j);
            }
        }
        int s1 = accumulate(nums1.begin(), nums1.end(), 0);
        int s2 = accumulate(nums2.begin(), nums2.end(), 0);
        for (int j = 0; j <= n; ++j) {
            if (s1 + s2 * j - f[j] <= x) {
                return j;
            }
        }
        return -1;
    }
};