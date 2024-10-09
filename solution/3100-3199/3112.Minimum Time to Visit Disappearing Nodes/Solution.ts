function minimumTime(n: number, edges: number[][], disappear: number[]): number[] {
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [u, v, w] of edges) {
        g[u].push([v, w]);
        g[v].push([u, w]);
    }
    const dist = Array.from({ length: n }, () => Infinity);
    dist[0] = 0;
    const pq = new PriorityQueue({
        compare: (a, b) => (a[0] === b[0] ? a[1] - b[1] : a[0] - b[0]),
    });
    pq.enqueue([0, 0]);
    while (pq.size() > 0) {
        const [du, u] = pq.dequeue()!;
        if (du > dist[u]) {
            continue;
        }
        for (const [v, w] of g[u]) {
            if (dist[v] > dist[u] + w && dist[u] + w < disappear[v]) {
                dist[v] = dist[u] + w;
                pq.enqueue([dist[v], v]);
            }
        }
    }
    return dist.map((a, i) => (a < disappear[i] ? a : -1));
}
