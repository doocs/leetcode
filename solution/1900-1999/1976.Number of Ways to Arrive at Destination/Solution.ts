function countPaths(n: number, roads: number[][]): number {
    const mod: number = 1e9 + 7;
    const g: number[][] = Array.from({ length: n }, () => Array(n).fill(Infinity));
    for (const [u, v, t] of roads) {
        g[u][v] = t;
        g[v][u] = t;
    }
    g[0][0] = 0;

    const dist: number[] = Array(n).fill(Infinity);
    dist[0] = 0;

    const f: number[] = Array(n).fill(0);
    f[0] = 1;

    const vis: boolean[] = Array(n).fill(false);
    for (let i = 0; i < n; ++i) {
        let t: number = -1;
        for (let j = 0; j < n; ++j) {
            if (!vis[j] && (t === -1 || dist[j] < dist[t])) {
                t = j;
            }
        }
        vis[t] = true;
        for (let j = 0; j < n; ++j) {
            if (j === t) {
                continue;
            }
            const ne: number = dist[t] + g[t][j];
            if (dist[j] > ne) {
                dist[j] = ne;
                f[j] = f[t];
            } else if (dist[j] === ne) {
                f[j] = (f[j] + f[t]) % mod;
            }
        }
    }
    return f[n - 1];
}
