function getFinalState(nums, k, multiplier) {
    while (k--) {
        const min = Math.min(...nums);
        const i = nums.indexOf(min);
        nums[i] *= multiplier;
    }

    return nums;
}
