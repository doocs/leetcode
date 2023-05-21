function modifiedGraphEdges(
    n: number,
    edges: number[][],
    source: number,
    destination: number,
    target: number,
): number[][] {
    const inf = 2e9;
    const dijkstra = (edges: number[][]): number => {
        const g: number[][] = Array(n)
            .fill(0)
            .map(() => Array(n).fill(inf));
        const dist: number[] = Array(n).fill(inf);
        const vis: boolean[] = Array(n).fill(false);
        for (const [a, b, w] of edges) {
            if (w === -1) {
                continue;
            }
            g[a][b] = w;
            g[b][a] = w;
        }
        dist[source] = 0;
        for (let i = 0; i < n; ++i) {
            let k = -1;
            for (let j = 0; j < n; ++j) {
                if (!vis[j] && (k === -1 || dist[j] < dist[k])) {
                    k = j;
                }
            }
            vis[k] = true;
            for (let j = 0; j < n; ++j) {
                dist[j] = Math.min(dist[j], dist[k] + g[k][j]);
            }
        }
        return dist[destination];
    };
    let d = dijkstra(edges);
    if (d < target) {
        return [];
    }
    let ok = d === target;
    for (const e of edges) {
        if (e[2] > 0) {
            continue;
        }
        if (ok) {
            e[2] = inf;
            continue;
        }
        e[2] = 1;
        d = dijkstra(edges);
        if (d <= target) {
            ok = true;
            e[2] += target - d;
        }
    }
    return ok ? edges : [];
}
