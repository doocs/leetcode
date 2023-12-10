function maxSubarrayLength(nums: number[], k: number): number {
    const cnt: Map<number, number> = new Map();
    let ans = 0;
    for (let i = 0, j = 0; i < nums.length; ++i) {
        cnt.set(nums[i], (cnt.get(nums[i]) ?? 0) + 1);
        for (; cnt.get(nums[i])! > k; ++j) {
            cnt.set(nums[j], cnt.get(nums[j])! - 1);
        }
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
}
