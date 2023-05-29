function maximumSubarraySum(nums: number[], k: number): number {
    const n = nums.length;
    const cnt: Map<number, number> = new Map();
    let s = 0;
    for (let i = 0; i < k; ++i) {
        cnt.set(nums[i], (cnt.get(nums[i]) ?? 0) + 1);
        s += nums[i];
    }
    let ans = cnt.size === k ? s : 0;
    for (let i = k; i < n; ++i) {
        cnt.set(nums[i], (cnt.get(nums[i]) ?? 0) + 1);
        s += nums[i];
        cnt.set(nums[i - k], cnt.get(nums[i - k])! - 1);
        s -= nums[i - k];
        if (cnt.get(nums[i - k]) === 0) {
            cnt.delete(nums[i - k]);
        }
        if (cnt.size === k) {
            ans = Math.max(ans, s);
        }
    }
    return ans;
}
