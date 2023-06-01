class Solution {
public:
    int maxSum(vector<int>& nums1, vector<int>& nums2) {
        const int mod = 1e9 + 7;
        int m = nums1.size(), n = nums2.size();
        int i = 0, j = 0;
        long long f = 0, g = 0;
        while (i < m || j < n) {
            if (i == m) {
                g += nums2[j++];
            } else if (j == n) {
                f += nums1[i++];
            } else if (nums1[i] < nums2[j]) {
                f += nums1[i++];
            } else if (nums1[i] > nums2[j]) {
                g += nums2[j++];
            } else {
                f = g = max(f, g) + nums1[i];
                i++;
                j++;
            }
        }
        return max(f, g) % mod;
    }
};