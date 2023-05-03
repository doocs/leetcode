function PredictTheWinner(nums: number[]): boolean {
    const n = nums.length;
    const f: number[][] = new Array(n).fill(0).map(() => new Array(n).fill(0));
    for (let i = 0; i < n; ++i) {
        f[i][i] = nums[i];
    }
    for (let i = n - 2; i >= 0; --i) {
        for (let j = i + 1; j < n; ++j) {
            f[i][j] = Math.max(nums[i] - f[i + 1][j], nums[j] - f[i][j - 1]);
        }
    }
    return f[0][n - 1] >= 0;
}
