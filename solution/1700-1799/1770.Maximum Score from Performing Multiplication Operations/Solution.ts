function maximumScore(nums: number[], multipliers: number[]): number {
    const inf = 1 << 30;
    const n = nums.length;
    const m = multipliers.length;
    const f = new Array(m + 1).fill(0).map(() => new Array(m + 1).fill(-inf));
    f[0][0] = 0;
    let ans = -inf;
    for (let i = 0; i <= m; ++i) {
        for (let j = 0; j <= m - i; ++j) {
            const k = i + j - 1;
            if (i > 0) {
                f[i][j] = Math.max(f[i][j], f[i - 1][j] + nums[i - 1] * multipliers[k]);
            }
            if (j > 0) {
                f[i][j] = Math.max(f[i][j], f[i][j - 1] + nums[n - j] * multipliers[k]);
            }
            if (i + j === m) {
                ans = Math.max(ans, f[i][j]);
            }
        }
    }
    return ans;
}
