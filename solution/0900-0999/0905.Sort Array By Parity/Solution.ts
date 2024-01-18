function sortArrayByParity(nums: number[]): number[] {
    for (let i = 0, j = nums.length - 1; i < j; ) {
        if (nums[i] % 2 === 0) {
            ++i;
        } else if (nums[j] % 2 === 1) {
            --j;
        } else {
            [nums[i], nums[j]] = [nums[j], nums[i]];
            ++i;
            --j;
        }
    }
    return nums;
}
