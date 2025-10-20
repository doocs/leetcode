function missingMultiple(nums: number[], k: number): number {
    const s = new Set<number>(nums);
    for (let i = 1; ; ++i) {
        const x = k * i;
        if (!s.has(x)) {
            return x;
        }
    }
}
