function sortByAbsoluteValue(nums: number[]): number[] {
    return nums.sort((a, b) => Math.abs(a) - Math.abs(b));
}
