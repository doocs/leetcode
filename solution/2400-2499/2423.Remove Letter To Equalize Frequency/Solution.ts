function equalFrequency(word: string): boolean {
    const map = new Map();
    for (const c of word) {
        map.set(c, (map.get(c) ?? 0) + 1);
    }
    const count = new Map();
    for (const v of map.values()) {
        count.set(v, (count.get(v) ?? 0) + 1);
    }
    if (count.size === 1) {
        return map.size == 1 || [...count.keys()][0] === 1;
    }
    if (count.size === 2) {
        return [...count.entries()].some(
            (v, i, arr) =>
                (v[0] === 1 || v[0] - arr[i ^ 1][0] === 1) && v[1] === 1,
        );
    }
    return false;
}
