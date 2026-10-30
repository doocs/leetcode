function shortestPath(n: number, edges: number[][], labels: string, k: number): number {
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [u, v, w] of edges) {
        g[u].push([v, w]);
    }

    const dist = Array.from({ length: n }, () => new Array(k + 1).fill(Infinity));
    dist[0][1] = 0;

    const pq = new PriorityQueue<number[]>((a, b) => a[0] - b[0]);
    pq.enqueue([0, 0, 1]);

    while (!pq.isEmpty()) {
        const [d, u, cur] = pq.dequeue();

        if (u === n - 1) {
            return d;
        }
        if (d > dist[u][cur]) {
            continue;
        }

        for (const [v, w] of g[u]) {
            const nxt = labels[v] !== labels[u] ? 1 : cur + 1;
            const nd = d + w;
            if (nxt <= k && nd < dist[v][nxt]) {
                dist[v][nxt] = nd;
                pq.enqueue([nd, v, nxt]);
            }
        }
    }
    return -1;
}
