function numberOfPairs(nums: number[]): number[] {
    const n = nums.length;
    const count = new Array(101).fill(0);
    for (const num of nums) {
        count[num]++;
    }
    const sum = count.reduce((r, v) => r + (v >> 1), 0);
    return [sum, n - sum * 2];
}
