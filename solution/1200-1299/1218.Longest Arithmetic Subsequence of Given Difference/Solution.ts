function longestSubsequence(arr: number[], difference: number): number {
    const f: Map<number, number> = new Map();
    for (const x of arr) {
        f.set(x, (f.get(x - difference) ?? 0) + 1);
    }
    return Math.max(...f.values());
}
