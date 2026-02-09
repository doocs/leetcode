class Solution {
public:
    long long maxScore(vector<int>& nums1, vector<int>& nums2, int K) {
        int n = nums1.size(), m = nums2.size();
        long long NEG = LLONG_MIN / 4;
        vector f(n + 1, vector(m + 1, vector<long long>(K + 1, NEG)));
        f[0][0][0] = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= K; k++) {
                    if (i > 0) {
                        f[i][j][k] = max(f[i][j][k], f[i - 1][j][k]);
                    }
                    if (j > 0) {
                        f[i][j][k] = max(f[i][j][k], f[i][j - 1][k]);
                    }
                    if (i > 0 && j > 0 && k > 0) {
                        f[i][j][k] = max(
                            f[i][j][k],
                            f[i - 1][j - 1][k - 1] + 1LL * nums1[i - 1] * nums2[j - 1]);
                    }
                }
            }
        }
        return f[n][m][K];
    }
};
