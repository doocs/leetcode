function productExceptSelf(nums: number[]): number[] {
    return nums.map((_, i) => nums.reduce((pre, val, j) => pre * (i === j ? 1 : val), 1));
}
