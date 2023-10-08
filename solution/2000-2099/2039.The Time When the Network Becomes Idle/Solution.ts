function networkBecomesIdle(edges: number[][], patience: number[]): number {
    const n = patience.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }
    const vis: boolean[] = Array.from({ length: n }, () => false);
    vis[0] = true;
    let q: number[] = [0];
    let ans = 0;
    for (let d = 1; q.length > 0; ++d) {
        const t = d * 2;
        const nq: number[] = [];
        for (const u of q) {
            for (const v of g[u]) {
                if (!vis[v]) {
                    vis[v] = true;
                    nq.push(v);
                    ans = Math.max(ans, (((t - 1) / patience[v]) | 0) * patience[v] + t + 1);
                }
            }
        }
        q = nq;
    }
    return ans;
}
