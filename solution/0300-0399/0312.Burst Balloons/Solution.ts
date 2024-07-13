function maxCoins(nums: number[]): number {
    const n = nums.length;
    const arr = Array(n + 2).fill(1);
    for (let i = 0; i < n; i++) {
        arr[i + 1] = nums[i];
    }

    const f: number[][] = Array.from({ length: n + 2 }, () => Array(n + 2).fill(0));
    for (let i = n - 1; i >= 0; i--) {
        for (let j = i + 2; j <= n + 1; j++) {
            for (let k = i + 1; k < j; k++) {
                f[i][j] = Math.max(f[i][j], f[i][k] + f[k][j] + arr[i] * arr[k] * arr[j]);
            }
        }
    }
    return f[0][n + 1];
}
