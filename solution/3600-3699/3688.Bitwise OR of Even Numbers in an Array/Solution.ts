function evenNumberBitwiseORs(nums: number[]): number {
    return nums.reduce((ans, x) => (x % 2 === 0 ? ans | x : ans), 0);
}
