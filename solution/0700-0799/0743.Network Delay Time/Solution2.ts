function networkDelayTime(times: number[][], n: number, k: number): number {
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [u, v, w] of times) {
        g[u - 1].push([v - 1, w]);
    }
    const dist: number[] = Array(n).fill(Infinity);
    dist[k - 1] = 0;
    const pq = new MinPriorityQueue({ priority: v => v[0] });
    pq.enqueue([0, k - 1]);
    while (!pq.isEmpty()) {
        const [d, u] = pq.dequeue().element;
        if (d > dist[u]) {
            continue;
        }
        for (const [v, w] of g[u]) {
            if (dist[v] > dist[u] + w) {
                dist[v] = dist[u] + w;
                pq.enqueue([dist[v], v]);
            }
        }
    }
    const ans = Math.max(...dist);
    return ans === Infinity ? -1 : ans;
}
