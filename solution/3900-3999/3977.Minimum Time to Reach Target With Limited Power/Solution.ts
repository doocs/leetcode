function minTimeMaxPower(
    n: number,
    edges: number[][],
    power: number,
    cost: number[],
    source: number,
    target: number,
): number[] {
    const inf = 1e18;

    const g: [number, number][][] = Array.from({ length: n }, () => []);

    for (const [u, v, t] of edges) {
        g[u].push([v, t]);
    }

    const dist: number[][] = Array.from({ length: n }, () => Array(power + 1).fill(inf));

    const pq = new PriorityQueue<number[]>((a, b) => {
        if (a[0] !== b[0]) return a[0] - b[0];
        return a[1] - b[1];
    });

    dist[source][power] = 0;
    pq.enqueue([0, -power, source]);

    while (!pq.isEmpty()) {
        const [d, negp, u] = pq.dequeue();
        let p = -negp;

        if (u === target) return [d, p];
        if (d > dist[u][p] || p < cost[u]) continue;

        p -= cost[u];

        for (const [v, t] of g[u]) {
            const nd = d + t;

            if (nd < dist[v][p]) {
                dist[v][p] = nd;
                pq.enqueue([nd, -p, v]);
            }
        }
    }

    return [-1, -1];
}
