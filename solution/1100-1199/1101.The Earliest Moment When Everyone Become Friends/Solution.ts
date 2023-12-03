function earliestAcq(logs: number[][], n: number): number {
    const p: number[] = Array(n)
        .fill(0)
        .map((_, i) => i);
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    logs.sort((a, b) => a[0] - b[0]);
    for (const [t, x, y] of logs) {
        const rx = find(x);
        const ry = find(y);
        if (rx !== ry) {
            p[rx] = ry;
            if (--n === 1) {
                return t;
            }
        }
    }
    return -1;
}
