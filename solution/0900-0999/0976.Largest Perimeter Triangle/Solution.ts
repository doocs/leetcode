function largestPerimeter(nums: number[]): number {
    nums.sort((a, b) => a - b);
    for (let i = nums.length - 1; i > 1; --i) {
        const [a, b, c] = nums.slice(i - 2, i + 1);
        if (a + b > c) {
            return a + b + c;
        }
    }
    return 0;
}
