function findMaxLength(nums: number[]): number {
    const d: Map<number, number> = new Map();
    d.set(0, -1);
    let ans = 0;
    let s = 0;
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        s += nums[i] === 0 ? -1 : 1;
        if (d.has(s)) {
            ans = Math.max(ans, i - d.get(s)!);
        } else {
            d.set(s, i);
        }
    }
    return ans;
}
