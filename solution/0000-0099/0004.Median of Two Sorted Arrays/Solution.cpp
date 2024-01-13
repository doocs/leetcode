class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        int m = nums1.size(), n = nums2.size();
        function<int(int, int, int)> f = [&](int i, int j, int k) {
            if (i >= m) {
                return nums2[j + k - 1];
            }
            if (j >= n) {
                return nums1[i + k - 1];
            }
            if (k == 1) {
                return min(nums1[i], nums2[j]);
            }
            int p = k / 2;
            int x = i + p - 1 < m ? nums1[i + p - 1] : 1 << 30;
            int y = j + p - 1 < n ? nums2[j + p - 1] : 1 << 30;
            return x < y ? f(i + p, j, k - p) : f(i, j + p, k - p);
        };
        int a = f(0, 0, (m + n + 1) / 2);
        int b = f(0, 0, (m + n + 2) / 2);
        return (a + b) / 2.0;
    }
};