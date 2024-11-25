function networkDelayTime(times: number[][], n: number, k: number): number {
    const g: number[][] = Array.from({ length: n }, () => Array(n).fill(Infinity));
    for (const [u, v, w] of times) {
        g[u - 1][v - 1] = w;
    }
    const dist: number[] = Array(n).fill(Infinity);
    dist[k - 1] = 0;
    const vis: boolean[] = Array(n).fill(false);
    for (let i = 0; i < n; ++i) {
        let t = -1;
        for (let j = 0; j < n; ++j) {
            if (!vis[j] && (t === -1 || dist[j] < dist[t])) {
                t = j;
            }
        }
        vis[t] = true;
        for (let j = 0; j < n; ++j) {
            dist[j] = Math.min(dist[j], dist[t] + g[t][j]);
        }
    }
    const ans = Math.max(...dist);
    return ans === Infinity ? -1 : ans;
}
