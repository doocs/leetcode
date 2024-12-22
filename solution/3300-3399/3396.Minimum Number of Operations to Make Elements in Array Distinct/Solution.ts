function minimumOperations(nums: number[]): number {
    const s = new Set<number>();
    for (let i = nums.length - 1; ~i; --i) {
        if (s.has(nums[i])) {
            return Math.ceil((i + 1) / 3);
        }
        s.add(nums[i]);
    }
    return 0;
}
