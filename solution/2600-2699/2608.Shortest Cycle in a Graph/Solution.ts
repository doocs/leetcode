function findShortestCycle(n: number, edges: number[][]): number {
    const g: number[][] = new Array(n).fill(0).map(() => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }
    const inf = 1 << 30;
    let ans = inf;
    const bfs = (u: number) => {
        const dist: number[] = new Array(n).fill(-1);
        dist[u] = 0;
        const q: number[][] = [[u, -1]];
        let ans = inf;
        while (q.length) {
            const p = q.shift()!;
            u = p[0];
            const fa = p[1];
            for (const v of g[u]) {
                if (dist[v] < 0) {
                    dist[v] = dist[u] + 1;
                    q.push([v, u]);
                } else if (v !== fa) {
                    ans = Math.min(ans, dist[u] + dist[v] + 1);
                }
            }
        }
        return ans;
    };
    for (let i = 0; i < n; ++i) {
        ans = Math.min(ans, bfs(i));
    }
    return ans < inf ? ans : -1;
}
