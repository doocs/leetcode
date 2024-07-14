function minOperations(nums: number[], x: number): number {
    const s = nums.reduce((acc, cur) => acc + cur, -x);
    const vis: Map<number, number> = new Map([[0, -1]]);
    let [mx, t] = [-1, 0];
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        t += nums[i];
        if (!vis.has(t)) {
            vis.set(t, i);
        }
        if (vis.has(t - s)) {
            mx = Math.max(mx, i - vis.get(t - s)!);
        }
    }
    return ~mx ? n - mx : -1;
}
