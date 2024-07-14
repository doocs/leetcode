function findTargetSumWays(nums: number[], target: number): number {
    const s = nums.reduce((a, b) => a + b, 0);
    if (s < target || (s - target) % 2) {
        return 0;
    }
    const n = ((s - target) / 2) | 0;
    const f = Array(n + 1).fill(0);
    f[0] = 1;
    for (const x of nums) {
        for (let j = n; j >= x; j--) {
            f[j] += f[j - x];
        }
    }
    return f[n];
}
