function maxSubarraySum(nums: number[], k: number): number {
    const f: number[] = Array(k).fill(Infinity);
    f[k - 1] = 0;
    let ans = -Infinity;
    let s = 0;
    for (let i = 0; i < nums.length; ++i) {
        s += nums[i];
        ans = Math.max(ans, s - f[i % k]);
        f[i % k] = Math.min(f[i % k], s);
    }
    return ans;
}
