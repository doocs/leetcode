function minTrioDegree(n: number, edges: number[][]): number {
    const g = Array.from({ length: n }, () => Array(n).fill(false));
    const deg: number[] = Array(n).fill(0);
    for (let [u, v] of edges) {
        u--;
        v--;
        g[u][v] = g[v][u] = true;
        ++deg[u];
        ++deg[v];
    }
    let ans = Infinity;
    for (let i = 0; i < n; ++i) {
        for (let j = i + 1; j < n; ++j) {
            if (g[i][j]) {
                for (let k = j + 1; k < n; ++k) {
                    if (g[i][k] && g[j][k]) {
                        ans = Math.min(ans, deg[i] + deg[j] + deg[k] - 6);
                    }
                }
            }
        }
    }
    return ans === Infinity ? -1 : ans;
}
