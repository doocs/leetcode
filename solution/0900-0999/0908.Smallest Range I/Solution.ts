function smallestRangeI(nums: number[], k: number): number {
    const max = nums.reduce((r, v) => Math.max(r, v));
    const min = nums.reduce((r, v) => Math.min(r, v));
    return Math.max(max - min - k * 2, 0);
}
