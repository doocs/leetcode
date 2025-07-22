function maxProbability(
    n: number,
    edges: number[][],
    succProb: number[],
    start_node: number,
    end_node: number,
): number {
    const pq = new MaxPriorityQueue({ priority: v => v[0] });
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (let i = 0; i < edges.length; ++i) {
        const [a, b] = edges[i];
        g[a].push([b, succProb[i]]);
        g[b].push([a, succProb[i]]);
    }
    const dist = Array.from({ length: n }, () => 0);
    dist[start_node] = 1;
    pq.enqueue([1, start_node]);
    while (!pq.isEmpty()) {
        const [w, a] = pq.dequeue().element;
        if (dist[a] > w) {
            continue;
        }
        for (const [b, p] of g[a]) {
            const nw = w * p;
            if (nw > dist[b]) {
                dist[b] = nw;
                pq.enqueue([nw, b]);
            }
        }
    }
    return dist[end_node];
}
