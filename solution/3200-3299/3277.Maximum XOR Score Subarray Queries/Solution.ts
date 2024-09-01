function maximumSubarrayXor(nums: number[], queries: number[][]): number[] {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    const g: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    for (let i = n - 1; i >= 0; i--) {
        f[i][i] = nums[i];
        g[i][i] = nums[i];
        for (let j = i + 1; j < n; j++) {
            f[i][j] = f[i][j - 1] ^ f[i + 1][j];
            g[i][j] = Math.max(f[i][j], Math.max(g[i][j - 1], g[i + 1][j]));
        }
    }
    return queries.map(([l, r]) => g[l][r]);
}
