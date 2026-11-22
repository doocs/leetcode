function minRange(a: number, b: number, c: number): number {
    const nums = [a, b, c].sort((a, b) => a - b);
    return Math.min(nums[2] - nums[0], nums[1]);
}
