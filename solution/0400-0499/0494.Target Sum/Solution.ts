function findTargetSumWays(nums: number[], target: number): number {
    const s = nums.reduce((a, b) => a + b, 0);
    if (s < target || (s - target) % 2) {
        return 0;
    }
    const [m, n] = [nums.length, ((s - target) / 2) | 0];
    const f: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    f[0][0] = 1;
    for (let i = 1; i <= m; i++) {
        for (let j = 0; j <= n; j++) {
            f[i][j] = f[i - 1][j];
            if (j >= nums[i - 1]) {
                f[i][j] += f[i - 1][j - nums[i - 1]];
            }
        }
    }
    return f[m][n];
}
