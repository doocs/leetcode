function minimumOperations(nums: number[]): number {
    return nums.reduce((acc, x) => acc + (x % 3 !== 0 ? 1 : 0), 0);
}
