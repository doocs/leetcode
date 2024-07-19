function arrayChange(nums: number[], operations: number[][]): number[] {
    const d: Map<number, number> = new Map(nums.map((x, i) => [x, i]));
    for (const [x, y] of operations) {
        nums[d.get(x)!] = y;
        d.set(y, d.get(x)!);
    }
    return nums;
}
