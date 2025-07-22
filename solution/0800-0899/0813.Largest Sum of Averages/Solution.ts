function largestSumOfAverages(nums: number[], k: number): number {
    const n = nums.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; i++) {
        s[i + 1] = s[i] + nums[i];
    }
    const f: number[][] = Array.from({ length: n }, () => Array(k + 1).fill(0));
    const dfs = (i: number, k: number): number => {
        if (i === n) {
            return 0;
        }
        if (f[i][k] > 0) {
            return f[i][k];
        }
        if (k === 1) {
            return (s[n] - s[i]) / (n - i);
        }
        for (let j = i + 1; j < n; j++) {
            f[i][k] = Math.max(f[i][k], dfs(j, k - 1) + (s[j] - s[i]) / (j - i));
        }
        return f[i][k];
    };
    return dfs(0, k);
}
