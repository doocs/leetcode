function xorBeauty(nums: number[]): number {
    return nums.reduce((acc, cur) => acc ^ cur, 0);
}
