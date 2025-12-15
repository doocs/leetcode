function minCostExcludingMax(n: number, edges: number[][]): number {
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [u, v, w] of edges) {
        g[u].push([v, w]);
        g[v].push([u, w]);
    }

    const INF = Infinity;
    const dist: number[][] = Array.from({ length: n }, () => [INF, INF]);
    dist[0][0] = 0;

    const pq = new PriorityQueue<[number, number, number]>((a, b) =>
        a[0] === b[0] ? a[1] - b[1] : a[0] - b[0],
    );

    pq.enqueue([0, 0, 0]);

    while (pq.size() > 0) {
        const [cur, u, used] = pq.dequeue()!;
        if (cur > dist[u][used]) {
            continue;
        }
        if (u === n - 1 && used === 1) {
            return cur;
        }

        for (const [v, w] of g[u]) {
            const nxt1 = cur + w;
            if (nxt1 < dist[v][used]) {
                dist[v][used] = nxt1;
                pq.enqueue([nxt1, v, used]);
            }
            if (used === 0) {
                const nxt2 = cur;
                if (nxt2 < dist[v][1]) {
                    dist[v][1] = nxt2;
                    pq.enqueue([nxt2, v, 1]);
                }
            }
        }
    }

    return dist[n - 1][1];
}
