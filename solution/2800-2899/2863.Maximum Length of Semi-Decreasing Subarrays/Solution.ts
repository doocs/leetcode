function maxSubarrayLength(nums: number[]): number {
    const d: Map<number, number[]> = new Map();
    for (let i = 0; i < nums.length; ++i) {
        if (!d.has(nums[i])) {
            d.set(nums[i], []);
        }
        d.get(nums[i])!.push(i);
    }
    const keys = Array.from(d.keys()).sort((a, b) => b - a);
    let ans = 0;
    let k = Infinity;
    for (const x of keys) {
        const idx = d.get(x)!;
        ans = Math.max(ans, idx.at(-1) - k + 1);
        k = Math.min(k, idx[0]);
    }
    return ans;
}
