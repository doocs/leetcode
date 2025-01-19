function maxAdjacentDistance(nums: number[]): number {
    const n = nums.length;
    let ans = Math.abs(nums[0] - nums[n - 1]);
    for (let i = 1; i < n; ++i) {
        ans = Math.max(ans, Math.abs(nums[i] - nums[i - 1]));
    }
    return ans;
}
