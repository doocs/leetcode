function minCost(nums: number[], x: number): number {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    for (let i = 0; i < n; ++i) {
        f[i][0] = nums[i];
        for (let j = 1; j < n; ++j) {
            f[i][j] = Math.min(f[i][j - 1], nums[(i + j) % n]);
        }
    }
    let ans = Infinity;
    for (let j = 0; j < n; ++j) {
        let cost = x * j;
        for (let i = 0; i < n; ++i) {
            cost += f[i][j];
        }
        ans = Math.min(ans, cost);
    }
    return ans;
}
