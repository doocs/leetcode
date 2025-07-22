function makeConnected(n: number, connections: number[][]): number {
    const p: number[] = Array.from({ length: n }, (_, i) => i);
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    let cnt = 0;
    for (const [a, b] of connections) {
        const [pa, pb] = [find(a), find(b)];
        if (pa === pb) {
            ++cnt;
        } else {
            p[pa] = pb;
            --n;
        }
    }
    return cnt >= n - 1 ? n - 1 : -1;
}
