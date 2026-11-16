function maxDifference(nums: number[]): number {
    const n = nums.length;
    const memo: number[] = new Array(n).fill(NaN);
    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (!Number.isNaN(memo[i])) {
            return memo[i];
        }
        const x = nums[i];
        let res = x % 3 === 0 ? x - dfs(i + 1) : x + dfs(i + 1);
        if (i + 1 < n) {
            const s = x + nums[i + 1];
            const t = s % 3 === 0 ? s - dfs(i + 2) : s + dfs(i + 2);
            res = Math.max(res, t);
        }
        memo[i] = res;
        return res;
    };
    return dfs(0);
}
