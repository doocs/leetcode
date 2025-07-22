class Solution {
public:
    int maxDotProduct(vector<int>& nums1, vector<int>& nums2) {
        int m = nums1.size(), n = nums2.size();
        int f[m + 1][n + 1];
        memset(f, 0xc0, sizeof f);
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                int v = nums1[i - 1] * nums2[j - 1];
                f[i][j] = max(f[i - 1][j], f[i][j - 1]);
                f[i][j] = max(f[i][j], max(0, f[i - 1][j - 1]) + v);
            }
        }
        return f[m][n];
    }
};
