function minOperations(nums: number[], x: number): number {
    x = nums.reduce((a, b) => a + b, 0) - x;
    const vis = new Map();
    vis.set(0, -1);
    const n = nums.length;
    let ans = 1 << 30;
    for (let i = 0, s = 0; i < n; ++i) {
        s += nums[i];
        if (!vis.has(s)) {
            vis.set(s, i);
        }
        if (vis.has(s - x)) {
            const j = vis.get(s - x);
            ans = Math.min(ans, n - (i - j));
        }
    }
    return ans == 1 << 30 ? -1 : ans;
}
