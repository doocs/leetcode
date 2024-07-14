function maximumStrength(nums: number[], k: number): number {
    const n: number = nums.length;
    const f: number[][][] = Array.from({ length: n + 1 }, () =>
        Array.from({ length: k + 1 }, () => [-Infinity, -Infinity]),
    );
    f[0][0][0] = 0;
    for (let i = 1; i <= n; i++) {
        const x: number = nums[i - 1];
        for (let j = 0; j <= k; j++) {
            const sign: number = (j & 1) === 1 ? 1 : -1;
            const val: number = sign * x * (k - j + 1);
            f[i][j][0] = Math.max(f[i - 1][j][0], f[i - 1][j][1]);
            f[i][j][1] = Math.max(f[i][j][1], f[i - 1][j][1] + val);
            if (j > 0) {
                f[i][j][1] = Math.max(f[i][j][1], Math.max(...f[i - 1][j - 1]) + val);
            }
        }
    }
    return Math.max(...f[n][k]);
}
