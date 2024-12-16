function getFinalState(nums: number[], k: number, multiplier: number): number[] {
    while (k--) {
        const min = Math.min(...nums);
        const i = nums.indexOf(min);
        nums[i] *= multiplier;
    }

    return nums;
}
