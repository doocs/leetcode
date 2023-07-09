class Solution {
public:
    int maxNonDecreasingLength(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        int f = 1, g = 1;
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            int ff = 1, gg = 1;
            if (nums1[i] >= nums1[i - 1]) {
                ff = max(ff, f + 1);
            }
            if (nums1[i] >= nums2[i - 1]) {
                ff = max(ff, g + 1);
            }
            if (nums2[i] >= nums1[i - 1]) {
                gg = max(gg, f + 1);
            }
            if (nums2[i] >= nums2[i - 1]) {
                gg = max(gg, g + 1);
            }
            f = ff;
            g = gg;
            ans = max(ans, max(f, g));
        }
        return ans;
    }
};