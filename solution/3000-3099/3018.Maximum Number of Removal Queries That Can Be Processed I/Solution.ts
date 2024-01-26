function maximumProcessableQueries(nums: number[], queries: number[]): number {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n }, () => Array.from({ length: n }, () => 0));
    const m = queries.length;
    for (let i = 0; i < n; ++i) {
        for (let j = n - 1; j >= i; --j) {
            if (i > 0) {
                f[i][j] = Math.max(
                    f[i][j],
                    f[i - 1][j] + (nums[i - 1] >= queries[f[i - 1][j]] ? 1 : 0),
                );
            }
            if (j + 1 < n) {
                f[i][j] = Math.max(
                    f[i][j],
                    f[i][j + 1] + (nums[j + 1] >= queries[f[i][j + 1]] ? 1 : 0),
                );
            }
            if (f[i][j] == m) {
                return m;
            }
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans = Math.max(ans, f[i][i] + (nums[i] >= queries[f[i][i]] ? 1 : 0));
    }
    return ans;
}
