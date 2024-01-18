function findShortestCycle(n: number, edges: number[][]): number {
    const g: number[][] = new Array(n).fill(0).map(() => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }
    const inf = 1 << 30;
    let ans = inf;
    const bfs = (u: number, v: number) => {
        const dist: number[] = new Array(n).fill(inf);
        dist[u] = 0;
        const q: number[] = [u];
        while (q.length) {
            const i = q.shift()!;
            for (const j of g[i]) {
                if ((i == u && j == v) || (i == v && j == u) || dist[j] != inf) {
                    continue;
                }
                dist[j] = dist[i] + 1;
                q.push(j);
            }
        }
        return 1 + dist[v];
    };
    for (const [u, v] of edges) {
        ans = Math.min(ans, bfs(u, v));
    }
    return ans < inf ? ans : -1;
}
