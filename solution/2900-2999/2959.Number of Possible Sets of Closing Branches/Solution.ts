function numberOfSets(n: number, maxDistance: number, roads: number[][]): number {
    let ans = 0;
    for (let mask = 0; mask < 1 << n; ++mask) {
        const g: number[][] = Array.from({ length: n }, () => Array(n).fill(Infinity));
        for (const [u, v, w] of roads) {
            if ((mask >> u) & 1 && (mask >> v) & 1) {
                g[u][v] = Math.min(g[u][v], w);
                g[v][u] = Math.min(g[v][u], w);
            }
        }
        for (let k = 0; k < n; ++k) {
            if ((mask >> k) & 1) {
                g[k][k] = 0;
                for (let i = 0; i < n; ++i) {
                    for (let j = 0; j < n; ++j) {
                        g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
                    }
                }
            }
        }
        let ok = 1;
        for (let i = 0; i < n && ok; ++i) {
            for (let j = 0; j < n && ok; ++j) {
                if ((mask >> i) & 1 && (mask >> j) & 1 && g[i][j] > maxDistance) {
                    ok = 0;
                }
            }
        }
        ans += ok;
    }
    return ans;
}
