function minimumPrefixLength(nums: number[]): number {
    for (let i = nums.length - 1; i; --i) {
        if (nums[i - 1] >= nums[i]) {
            return i;
        }
    }
    return 0;
}
