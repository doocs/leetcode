function findFinalValue(nums: number[], original: number): number {
    const s: Set<number> = new Set([...nums]);
    while (s.has(original)) {
        original <<= 1;
    }
    return original;
}
