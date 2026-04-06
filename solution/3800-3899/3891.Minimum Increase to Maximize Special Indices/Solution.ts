function minIncrease(nums: number[]): number {
    const n = nums.length;

    const f: number[][] = Array.from({ length: n }, () => Array(2).fill(-1));

    const dfs = (i: number, j: number): number => {
        if (i >= n - 1) {
            return 0;
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }

        const cost = Math.max(0, Math.max(nums[i - 1], nums[i + 1]) + 1 - nums[i]);
        let ans = cost + dfs(i + 2, j);

        if (j > 0) {
            ans = Math.min(ans, dfs(i + 1, 0));
        }

        f[i][j] = ans;
        return ans;
    };

    return dfs(1, (n & 1) ^ 1);
}
