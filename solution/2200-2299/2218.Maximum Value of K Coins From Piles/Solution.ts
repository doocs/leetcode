function maxValueOfCoins(piles: number[][], k: number): number {
    const n = piles.length;
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(0));
    for (let i = 1; i <= n; i++) {
        const nums = piles[i - 1];
        const s = Array(nums.length + 1).fill(0);
        for (let j = 1; j <= nums.length; j++) {
            s[j] = s[j - 1] + nums[j - 1];
        }
        for (let j = 0; j <= k; j++) {
            for (let h = 0; h < s.length && h <= j; h++) {
                f[i][j] = Math.max(f[i][j], f[i - 1][j - h] + s[h]);
            }
        }
    }
    return f[n][k];
}
