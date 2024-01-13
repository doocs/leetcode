function countSpecialSubsequences(nums: number[]): number {
    const mod = 1e9 + 7;
    const n = nums.length;
    const f: number[] = [0, 0, 0];
    f[0] = nums[0] === 0 ? 1 : 0;
    for (let i = 1; i < n; ++i) {
        if (nums[i] === 0) {
            f[0] = (((2 * f[0]) % mod) + 1) % mod;
            f[1] = f[1];
            f[2] = f[2];
        } else if (nums[i] === 1) {
            f[0] = f[0];
            f[1] = (f[0] + ((2 * f[1]) % mod)) % mod;
            f[2] = f[2];
        } else {
            f[0] = f[0];
            f[1] = f[1];
            f[2] = (f[1] + ((2 * f[2]) % mod)) % mod;
        }
    }
    return f[2];
}
