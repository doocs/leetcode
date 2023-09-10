function maxSizeSlices(slices: number[]): number {
    const n = Math.floor(slices.length / 3);
    const g = (nums: number[]): number => {
        const m = nums.length;
        const f: number[][] = Array(m + 1)
            .fill(0)
            .map(() => Array(n + 1).fill(0));
        for (let i = 1; i <= m; ++i) {
            for (let j = 1; j <= n; ++j) {
                f[i][j] = Math.max(f[i - 1][j], (i > 1 ? f[i - 2][j - 1] : 0) + nums[i - 1]);
            }
        }
        return f[m][n];
    };
    const a = g(slices.slice(0, -1));
    const b = g(slices.slice(1));
    return Math.max(a, b);
}
