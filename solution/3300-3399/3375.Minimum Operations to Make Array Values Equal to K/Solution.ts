function minOperations(nums: number[], k: number): number {
    const s = new Set<number>();
    let mi = Infinity;
    for (const x of nums) {
        if (x < k) {
            return -1;
        }
        s.add(x);
        mi = Math.min(mi, x);
    }
    return s.size - (mi === k ? 1 : 0);
}
