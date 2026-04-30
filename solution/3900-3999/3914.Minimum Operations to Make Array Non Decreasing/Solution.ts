function minOperations(nums: number[]): number {
    let ans = 0;
    for (let i = 1; i < nums.length; ++i) {
        ans += Math.max(nums[i - 1] - nums[i], 0);
    }
    return ans;
}
