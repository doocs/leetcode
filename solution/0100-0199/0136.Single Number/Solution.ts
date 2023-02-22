function singleNumber(nums: number[]): number {
    return nums.reduce((r, v) => r ^ v);
}
