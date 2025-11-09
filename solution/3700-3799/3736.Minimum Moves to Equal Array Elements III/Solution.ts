function minMoves(nums: number[]): number {
    const n = nums.length;
    const mx = Math.max(...nums);
    const s = nums.reduce((a, b) => a + b, 0);
    return mx * n - s;
}
