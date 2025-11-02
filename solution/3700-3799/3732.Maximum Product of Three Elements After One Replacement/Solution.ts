function maxProduct(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const [a, b] = [nums[0], nums[1]];
    const [c, d] = [nums[n - 2], nums[n - 1]];
    const x = 100000;
    return Math.max(a * b * x, c * d * x, -a * d * x);
}
