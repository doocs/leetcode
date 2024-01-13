function maxAlternatingSum(nums: number[]): number {
    const n = nums.length;
    const f: number[] = new Array(n + 1).fill(0);
    const g = f.slice();
    for (let i = 1; i <= n; ++i) {
        f[i] = Math.max(g[i - 1] + nums[i - 1], f[i - 1]);
        g[i] = Math.max(f[i - 1] - nums[i - 1], g[i - 1]);
    }
    return Math.max(f[n], g[n]);
}
