function findFinalValue(nums: number[], original: number): number {
    let set: Set<number> = new Set(nums);
    while (set.has(original)) {
        original *= 2;
    }
    return original;
}
