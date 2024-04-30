function sortArrayByParityII(nums: number[]): number[] {
    for (let i = 0, j = 1; i < nums.length; i += 2) {
        if (nums[i] % 2) {
            while (nums[j] % 2) {
                j += 2;
            }
            [nums[i], nums[j]] = [nums[j], nums[i]];
        }
    }
    return nums;
}
