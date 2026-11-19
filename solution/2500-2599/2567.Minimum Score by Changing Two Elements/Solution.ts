function minimizeSum(nums: number[]): number {
    nums.sort((x, y) => x - y);
    const n = nums.length;
    const a = nums[n - 3] - nums[0];
    const b = nums[n - 2] - nums[1];
    const c = nums[n - 1] - nums[2];
    return Math.min(a, b, c);
}
