function countSpecialSubsequences(nums: number[]): number {
    const mod = 1e9 + 7;
    const n = nums.length;
    const f: number[][] = Array(n)
        .fill(0)
        .map(() => Array(3).fill(0));
    f[0][0] = nums[0] === 0 ? 1 : 0;
    for (let i = 1; i < n; ++i) {
        if (nums[i] === 0) {
            f[i][0] = (((2 * f[i - 1][0]) % mod) + 1) % mod;
            f[i][1] = f[i - 1][1];
            f[i][2] = f[i - 1][2];
        } else if (nums[i] === 1) {
            f[i][0] = f[i - 1][0];
            f[i][1] = (f[i - 1][0] + ((2 * f[i - 1][1]) % mod)) % mod;
            f[i][2] = f[i - 1][2];
        } else {
            f[i][0] = f[i - 1][0];
            f[i][1] = f[i - 1][1];
            f[i][2] = (f[i - 1][1] + ((2 * f[i - 1][2]) % mod)) % mod;
        }
    }
    return f[n - 1][2];
}
