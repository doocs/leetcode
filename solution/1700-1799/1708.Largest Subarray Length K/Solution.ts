function largestSubarray(nums: number[], k: number): number[] {
    let j = 0;
    for (let i = 1; i < nums.length - k + 1; ++i) {
        if (nums[j] < nums[i]) {
            j = i;
        }
    }
    return nums.slice(j, j + k);
}
