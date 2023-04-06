function transportationHub(path: number[][]): number {
    const ind: number[] = new Array(1001).fill(0);
    const outd: number[] = new Array(1001).fill(0);
    const s: Set<number> = new Set();
    const vis: Set<number> = new Set();
    for (const [a, b] of path) {
        if (vis.has(a * 1000 + b)) {
            continue;
        }
        vis.add(a * 1000 + b);
        s.add(a);
        s.add(b);
        ind[b]++;
        outd[a]++;
    }
    for (const c of s) {
        if (ind[c] === s.size - 1 && outd[c] === 0) {
            return c;
        }
    }
    return -1;
}
