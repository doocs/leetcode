function arrayChange(nums: number[], operations: number[][]): number[] {
    const d = new Map(nums.map((v, i) => [v, i]));
    for (const [a, b] of operations) {
        nums[d.get(a)] = b;
        d.set(b, d.get(a));
    }
    return nums;
}
