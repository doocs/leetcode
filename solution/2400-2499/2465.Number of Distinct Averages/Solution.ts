function distinctAverages(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const s: Set<number> = new Set();
    const n = nums.length;
    for (let i = 0; i < n >> 1; ++i) {
        s.add(nums[i] + nums[n - i - 1]);
    }
    return s.size;
}
