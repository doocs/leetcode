class Solution {
public:
    int minimumXORSum(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        int f[1 << n];
        memset(f, 0x3f, sizeof(f));
        f[0] = 0;
        for (int x : nums1) {
            for (int j = (1 << n) - 1; ~j; --j) {
                for (int k = 0; k < n; ++k) {
                    if (j >> k & 1) {
                        f[j] = min(f[j], f[j ^ (1 << k)] + (x ^ nums2[k]));
                    }
                }
            }
        }
        return f[(1 << n) - 1];
    }
};