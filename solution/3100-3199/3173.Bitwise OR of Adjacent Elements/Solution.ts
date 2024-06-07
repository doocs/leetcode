function orArray(nums: number[]): number[] {
    return nums.slice(0, -1).map((v, i) => v | nums[i + 1]);
}
