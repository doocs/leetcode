export function findTheCity(n, edges, distanceThreshold) {
    const MAX = Number.POSITIVE_INFINITY;
    const g = Array.from({ length: n }, () => new Map());
    const dist = Array(n).fill(MAX);
    const vis = Array(n).fill(false);
    for (const [f, t, w] of edges) {
        g[f].set(t, w);
        g[t].set(f, w);
    }

    const dijkstra = u => {
        dist.fill(MAX);
        vis.fill(false);
        dist[u] = 0;
        const pq = new MinPriorityQueue();
        pq.enqueue(u, 0);

        while (!pq.isEmpty()) {
            const u = pq.dequeue().element;
            if (vis[u]) continue;
            vis[u] = true;

            for (const [v, w] of g[u]) {
                if (vis[v]) continue;

                const wNext = dist[u] + w;
                if (wNext < dist[v]) {
                    dist[v] = wNext;
                    pq.enqueue(v, dist[v]);
                }
            }
        }

        return dist.filter(d => d <= distanceThreshold).length;
    };

    let ans = n;
    let cnt = MAX;
    for (let i = n - 1; i >= 0; --i) {
        const t = dijkstra(i);
        if (t < cnt) {
            cnt = t;
            ans = i;
        }
    }

    return ans;
}
