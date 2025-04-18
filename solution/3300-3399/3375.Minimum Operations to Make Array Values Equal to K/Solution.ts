function minOperations(nums: number[], k: number): number {
    const s = new Set<number>([k]);
    for (const x of nums) {
        if (x < k) return -1;
        s.add(x);
    }
    return s.size - 1;
}
