function minimumSeconds(nums: number[]): number {
    const d: Map<number, number[]> = new Map();
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        if (!d.has(nums[i])) {
            d.set(nums[i], []);
        }
        d.get(nums[i])!.push(i);
    }
    let ans = 1 << 30;
    for (const [_, idx] of d) {
        const m = idx.length;
        let t = idx[0] + n - idx[m - 1];
        for (let i = 1; i < m; ++i) {
            t = Math.max(t, idx[i] - idx[i - 1]);
        }
        ans = Math.min(ans, t >> 1);
    }
    return ans;
}
