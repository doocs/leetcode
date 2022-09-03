function maxUncrossedLines(nums1: number[], nums2: number[]): number {
    const m = nums1.length;
    const n = nums2.length;
    const dp = Array.from({ length: m + 1 }, () => new Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            if (nums1[i - 1] == nums2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            }
        }
    }
    return dp[m][n];
}
