function minimumHammingDistance(
    source: number[],
    target: number[],
    allowedSwaps: number[][],
): number {
    const n = source.length;
    const p: number[] = Array.from({ length: n }, (_, i) => i);
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    for (const [a, b] of allowedSwaps) {
        p[find(a)] = find(b);
    }
    const cnt: Map<number, Map<number, number>> = new Map();
    for (let i = 0; i < n; ++i) {
        const j = find(i);
        if (!cnt.has(j)) {
            cnt.set(j, new Map());
        }
        const m = cnt.get(j)!;
        m.set(source[i], (m.get(source[i]) ?? 0) + 1);
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        const j = find(i);
        const m = cnt.get(j)!;
        m.set(target[i], (m.get(target[i]) ?? 0) - 1);
        if (m.get(target[i])! < 0) {
            ++ans;
        }
    }
    return ans;
}
