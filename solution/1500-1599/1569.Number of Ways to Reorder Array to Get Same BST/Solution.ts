function numOfWays(nums: number[]): number {
    const n = nums.length;
    const mod = 1e9 + 7;
    const c = new Array(n).fill(0).map(() => new Array(n).fill(0));
    c[0][0] = 1;
    for (let i = 1; i < n; ++i) {
        c[i][0] = 1;
        for (let j = 1; j <= i; ++j) {
            c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % mod;
        }
    }
    const dfs = (nums: number[]): number => {
        if (nums.length < 2) {
            return 1;
        }
        const left: number[] = [];
        const right: number[] = [];
        for (let i = 1; i < nums.length; ++i) {
            if (nums[i] < nums[0]) {
                left.push(nums[i]);
            } else {
                right.push(nums[i]);
            }
        }
        const m = left.length;
        const n = right.length;
        const a = dfs(left);
        const b = dfs(right);
        return Number((BigInt(c[m + n][m]) * BigInt(a) * BigInt(b)) % BigInt(mod));
    };
    return (dfs(nums) - 1 + mod) % mod;
}
