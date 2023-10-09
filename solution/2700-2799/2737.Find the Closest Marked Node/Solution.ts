function minimumDistance(n: number, edges: number[][], s: number, marked: number[]): number {
    const inf = 1 << 29;
    const g: number[][] = Array(n)
        .fill(0)
        .map(() => Array(n).fill(inf));
    const dist: number[] = Array(n).fill(inf);
    const vis: boolean[] = Array(n).fill(false);
    for (const [u, v, w] of edges) {
        g[u][v] = Math.min(g[u][v], w);
    }
    dist[s] = 0;
    for (let i = 0; i < n; ++i) {
        let t = -1;
        for (let j = 0; j < n; ++j) {
            if (!vis[j] && (t == -1 || dist[t] > dist[j])) {
                t = j;
            }
        }
        vis[t] = true;
        for (let j = 0; j < n; ++j) {
            dist[j] = Math.min(dist[j], dist[t] + g[t][j]);
        }
    }
    let ans = inf;
    for (const i of marked) {
        ans = Math.min(ans, dist[i]);
    }
    return ans >= inf ? -1 : ans;
}
