function sumOfPower(nums: number[], k: number): number {
    const mod = 10 ** 9 + 7;
    const n = nums.length;
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(0));
    f[0][0] = 1;
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j <= k; ++j) {
            f[i][j] = (f[i - 1][j] * 2) % mod;
            if (j >= nums[i - 1]) {
                f[i][j] = (f[i][j] + f[i - 1][j - nums[i - 1]]) % mod;
            }
        }
    }
    return f[n][k];
}
