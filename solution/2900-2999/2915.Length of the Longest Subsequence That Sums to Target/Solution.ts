function lengthOfLongestSubsequence(nums: number[], target: number): number {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(target + 1).fill(-Infinity));
    f[0][0] = 0;
    for (let i = 1; i <= n; ++i) {
        const x = nums[i - 1];
        for (let j = 0; j <= target; ++j) {
            f[i][j] = f[i - 1][j];
            if (j >= x) {
                f[i][j] = Math.max(f[i][j], f[i - 1][j - x] + 1);
            }
        }
    }
    return f[n][target] <= 0 ? -1 : f[n][target];
}
