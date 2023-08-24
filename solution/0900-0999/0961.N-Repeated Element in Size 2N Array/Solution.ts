function repeatedNTimes(nums: number[]): number {
    const s: Set<number> = new Set();
    for (let i = 0; ; ++i) {
        if (s.has(nums[i])) {
            return nums[i];
        }
        s.add(nums[i]);
    }
}
