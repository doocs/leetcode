function largestSumOfAverages(nums: number[], k: number): number {
    const n = nums.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; i++) {
        s[i + 1] = s[i] + nums[i];
    }
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(0));
    for (let i = 1; i <= n; ++i) {
        f[i][1] = s[i] / i;
        for (let j = 2; j <= Math.min(i, k); ++j) {
            for (let h = 0; h < i; ++h) {
                f[i][j] = Math.max(f[i][j], f[h][j - 1] + (s[i] - s[h]) / (i - h));
            }
        }
    }
    return f[n][k];
}
