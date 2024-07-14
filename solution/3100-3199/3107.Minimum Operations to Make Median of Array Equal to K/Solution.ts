function minOperationsToMakeMedianK(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const m = n >> 1;
    let ans = Math.abs(nums[m] - k);
    if (nums[m] > k) {
        for (let i = m - 1; i >= 0 && nums[i] > k; --i) {
            ans += nums[i] - k;
        }
    } else {
        for (let i = m + 1; i < n && nums[i] < k; ++i) {
            ans += k - nums[i];
        }
    }
    return ans;
}
