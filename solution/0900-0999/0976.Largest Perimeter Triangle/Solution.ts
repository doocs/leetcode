function largestPerimeter(nums: number[]): number {
    const n = nums.length;
    nums.sort((a, b) => b - a);
    for (let i = 2; i < n; i++) {
        const [a, b, c] = [nums[i - 2], nums[i - 1], nums[i]];
        if (a < b + c) {
            return a + b + c;
        }
    }
    return 0;
}
