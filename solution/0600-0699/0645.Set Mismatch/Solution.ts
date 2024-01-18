function findErrorNums(nums: number[]): number[] {
    const n = nums.length;
    const s1 = (n * (n + 1)) >> 1;
    const s2 = [...new Set(nums)].reduce((a, b) => a + b);
    const s = nums.reduce((a, b) => a + b);
    return [s - s2, s1 - s2];
}
