function specialNodes(n: number, edges: number[][], x: number, y: number, z: number): number {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }

    const inf = 1e9;

    const bfs = (i: number): number[] => {
        const dist = Array(n).fill(inf);
        let q: number[] = [i];
        dist[i] = 0;
        while (q.length) {
            const nq = [];
            for (const u of q) {
                for (const v of g[u]) {
                    if (dist[v] > dist[u] + 1) {
                        dist[v] = dist[u] + 1;
                        nq.push(v);
                    }
                }
            }
            q = nq;
        }
        return dist;
    };

    const d1 = bfs(x);
    const d2 = bfs(y);
    const d3 = bfs(z);

    let ans = 0;
    for (let i = 0; i < n; i++) {
        const a = [d1[i], d2[i], d3[i]];
        a.sort((p, q) => p - q);
        if (a[0] * a[0] + a[1] * a[1] === a[2] * a[2]) {
            ans++;
        }
    }
    return ans;
}
