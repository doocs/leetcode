function maxSubArray(nums: number[]): number {
    let res = nums[0];
    for (let i = 1; i < nums.length; i++) {
        nums[i] = Math.max(nums[i], nums[i - 1] + nums[i]);
        res = Math.max(res, nums[i]);
    }
    return res;
}
