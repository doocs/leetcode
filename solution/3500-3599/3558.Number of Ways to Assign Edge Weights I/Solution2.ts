function assignEdgeWeights(edges: number[][]): number {
    const mod = 1_000_000_007;
    const n = edges.length + 1;
    const g: number[][] = Array.from({ length: n + 1 }, () => []);

    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }

    const stk: [number, number, number][] = [[1, 0, 0]];
    let d = 0;
    while (stk.length) {
        const [i, fa, dep] = stk.pop()!;
        d = Math.max(d, dep);
        for (const j of g[i]) {
            if (j !== fa) {
                stk.push([j, i, dep + 1]);
            }
        }
    }

    const pow = (a: number, n: number, mod: number): number => {
        let res = 1n;
        let x = BigInt(a);
        const m = BigInt(mod);

        while (n > 0) {
            if (n & 1) {
                res = (res * x) % m;
            }
            x = (x * x) % m;
            n >>= 1;
        }

        return Number(res);
    };

    return pow(2, d - 1, mod);
}
