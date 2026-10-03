function minIncrease(nums: number[]): number {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n + 1 }, () => [0, 0]);
    for (let i = n - 2; i >= 1; --i) {
        const cost = Math.max(0, Math.max(nums[i - 1], nums[i + 1]) + 1 - nums[i]);
        f[i][0] = cost + f[i + 2][0];
        f[i][1] = Math.min(cost + f[i + 2][1], f[i + 1][0]);
    }
    return f[1][(n & 1) ^ 1];
}
