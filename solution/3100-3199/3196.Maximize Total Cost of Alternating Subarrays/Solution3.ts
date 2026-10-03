function maximumTotalCost(nums: number[]): number {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(2).fill(0));
    for (let i = n - 1; i >= 0; --i) {
        for (let j = 0; j < 2; ++j) {
            f[i][j] = nums[i] + f[i + 1][1];
            if (j === 1) {
                f[i][j] = Math.max(f[i][j], -nums[i] + f[i + 1][0]);
            }
        }
    }
    return f[0][0];
}
