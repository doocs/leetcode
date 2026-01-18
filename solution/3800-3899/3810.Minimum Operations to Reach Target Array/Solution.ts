function minOperations(nums: number[], target: number[]): number {
    const s = new Set<number>();
    for (let i = 0; i < nums.length; i++) {
        if (nums[i] !== target[i]) {
            s.add(nums[i]);
        }
    }
    return s.size;
}
