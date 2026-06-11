function assignEdgeWeights(edges: number[][]): number {
    const mod = 1_000_000_007;
    const n = edges.length + 1;
    const g: number[][] = Array.from({ length: n + 1 }, () => []);

    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }

    const dfs = (i: number, fa: number): number => {
        let res = 0;
        for (const j of g[i]) {
            if (j !== fa) {
                res = Math.max(res, dfs(j, i) + 1);
            }
        }
        return res;
    };

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

    return pow(2, dfs(1, 0) - 1, mod);
}
