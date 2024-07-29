function findTheCity(n, edges, distanceThreshold) {
    const g = Array.from({ length: n }, () => Array(n).fill(Infinity));
    const dist = Array(n).fill(Infinity);
    const vis = Array(n).fill(false);
    for (const [f, t, w] of edges) {
        g[f][t] = g[t][f] = w;
    }

    const dijkstra = u => {
        dist.fill(Infinity);
        vis.fill(false);
        dist[u] = 0;
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
        return dist.filter(d => d <= distanceThreshold).length;
    };

    let ans = n;
    let cnt = Infinity;
    for (let i = n - 1; i >= 0; --i) {
        const t = dijkstra(i);
        if (t < cnt) {
            cnt = t;
            ans = i;
        }
    }

    return ans;
}
