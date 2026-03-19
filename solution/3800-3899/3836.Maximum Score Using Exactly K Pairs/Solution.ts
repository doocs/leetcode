function maxScore(nums1: number[], nums2: number[], K: number): number {
    const n = nums1.length,
        m = nums2.length;
    const NEG = -1e18;
    const f = Array.from({ length: n + 1 }, () =>
        Array.from({ length: m + 1 }, () => Array(K + 1).fill(NEG)),
    );
    f[0][0][0] = 0;
    for (let i = 0; i <= n; i++) {
        for (let j = 0; j <= m; j++) {
            for (let k = 0; k <= K; k++) {
                if (i > 0) {
                    f[i][j][k] = Math.max(f[i][j][k], f[i - 1][j][k]);
                }
                if (j > 0) {
                    f[i][j][k] = Math.max(f[i][j][k], f[i][j - 1][k]);
                }
                if (i > 0 && j > 0 && k > 0) {
                    f[i][j][k] = Math.max(
                        f[i][j][k],
                        f[i - 1][j - 1][k - 1] + nums1[i - 1] * nums2[j - 1],
                    );
                }
            }
        }
    }
    return f[n][m][K];
}
