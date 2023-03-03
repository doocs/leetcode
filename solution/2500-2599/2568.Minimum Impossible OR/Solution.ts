function minImpossibleOR(nums: number[]): number {
    const s: Set<number> = new Set();
    for (const x of nums) {
        s.add(x);
    }
    for (let i = 0; ; ++i) {
        if (!s.has(1 << i)) {
            return 1 << i;
        }
    }
}
