function minCost(nums: number[], costs: number[]): number {
    const n = nums.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    const stk: number[] = [];
    for (let i = n - 1; i >= 0; --i) {
        while (stk.length && nums[stk[stk.length - 1]] < nums[i]) {
            stk.pop();
        }
        if (stk.length) {
            g[i].push(stk[stk.length - 1]);
        }
        stk.push(i);
    }
    stk.length = 0;
    for (let i = n - 1; i >= 0; --i) {
        while (stk.length && nums[stk[stk.length - 1]] >= nums[i]) {
            stk.pop();
        }
        if (stk.length) {
            g[i].push(stk[stk.length - 1]);
        }
        stk.push(i);
    }
    const f: number[] = Array.from({ length: n }, () => Infinity);
    f[0] = 0;
    for (let i = 0; i < n; ++i) {
        for (const j of g[i]) {
            f[j] = Math.min(f[j], f[i] + costs[j]);
        }
    }
    return f[n - 1];
}
