function triangularSum(nums: number[]): number {
    for (let k = nums.length - 1; k; --k) {
        for (let i = 0; i < k; ++i) {
            nums[i] = (nums[i] + nums[i + 1]) % 10;
        }
    }
    return nums[0];
}
