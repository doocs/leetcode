function minMoves2(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const k = nums[nums.length >> 1];
    return nums.reduce((r, v) => r + Math.abs(v - k), 0);
}
