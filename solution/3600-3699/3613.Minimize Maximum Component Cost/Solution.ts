function minCost(n: number, edges: number[][], k: number): number {
    const p: number[] = Array.from({ length: n }, (_, i) => i);
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };

    if (k === n) {
        return 0;
    }

    edges.sort((a, b) => a[2] - b[2]);
    let cnt = n;
    for (const [u, v, w] of edges) {
        const pu = find(u),
            pv = find(v);
        if (pu !== pv) {
            p[pu] = pv;
            if (--cnt <= k) {
                return w;
            }
        }
    }
    return 0;
}
