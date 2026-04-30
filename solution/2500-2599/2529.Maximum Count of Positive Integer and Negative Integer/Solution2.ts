function maximumCount(nums: number[]): number {
    const i = _.sortedLastIndex(nums, 0);
    const j = _.sortedIndex(nums, 0);
    const [a, b] = [nums.length - i, j];
    return Math.max(a, b);
}
