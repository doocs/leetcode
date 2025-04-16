function minOperations(nums: number[], k: number): number {
    return nums.reduce((acc, x) => acc + x, 0) % k;
}
