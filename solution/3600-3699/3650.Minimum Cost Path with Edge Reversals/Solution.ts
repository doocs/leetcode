function minCost(n: number, edges: number[][]): number {
    const g: number[][][] = Array.from({ length: n }, () => []);
    for (const [u, v, w] of edges) {
        g[u].push([v, w]);
        g[v].push([u, w * 2]);
    }
    const dist: number[] = Array(n).fill(Infinity);
    dist[0] = 0;
    const pq = new PriorityQueue<number[]>((a, b) => a[0] - b[0]);
    pq.enqueue([0, 0]);
    while (!pq.isEmpty()) {
        const [d, u] = pq.dequeue();
        if (d > dist[u]) {
            continue;
        }
        if (u === n - 1) {
            return d;
        }
        for (const [v, w] of g[u]) {
            const nd = d + w;
            if (nd < dist[v]) {
                dist[v] = nd;
                pq.enqueue([nd, v]);
            }
        }
    }
    return -1;
}
