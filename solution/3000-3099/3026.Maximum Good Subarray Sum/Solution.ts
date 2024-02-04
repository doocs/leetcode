function maximumSubarraySum(nums: number[], k: number): number {
    const p: Map<number, number> = new Map();
    p.set(nums[0], 0);
    let ans: number = -Infinity;
    let s: number = 0;
    const n: number = nums.length;
    for (let i = 0; i < n; ++i) {
        s += nums[i];
        if (p.has(nums[i] - k)) {
            ans = Math.max(ans, s - p.get(nums[i] - k)!);
        }
        if (p.has(nums[i] + k)) {
            ans = Math.max(ans, s - p.get(nums[i] + k)!);
        }
        if (i + 1 < n && (!p.has(nums[i + 1]) || p.get(nums[i + 1])! > s)) {
            p.set(nums[i + 1], s);
        }
    }
    return ans === -Infinity ? 0 : ans;
}
