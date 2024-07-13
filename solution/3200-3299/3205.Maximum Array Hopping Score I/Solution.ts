function maxScore(nums: number[]): number {
    const n = nums.length;
    const f: number[] = Array(n).fill(0);
    const dfs = (i: number): number => {
        if (f[i]) {
            return f[i];
        }
        for (let j = i + 1; j < n; ++j) {
            f[i] = Math.max(f[i], (j - i) * nums[j] + dfs(j));
        }
        return f[i];
    };
    return dfs(0);
}
