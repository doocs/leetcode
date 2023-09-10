function uniqueOccurrences(arr: number[]): boolean {
    const cnt: Map<number, number> = new Map();
    for (const x of arr) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    return cnt.size === new Set(cnt.values()).size;
}
