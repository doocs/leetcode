function minOperations(nums: number[]): number {
    let ans = 0;
    for (let i = 1; i < nums.length; ++i) {
        ans += nums[i] !== nums[i - 1] ? 1 : 0;
    }
    return ans;
}
