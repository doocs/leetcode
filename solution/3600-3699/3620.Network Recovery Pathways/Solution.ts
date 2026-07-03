function findMaxPathScore(edges: number[][], online: boolean[], k: number): number {
    const n = online.length;
    const g: [number, number][][] = Array.from({ length: n }, () => []);

    let l = Number.MAX_SAFE_INTEGER;
    let r = 0;

    for (const [u, v, w] of edges) {
        if (!online[u] || !online[v]) continue;
        g[u].push([v, w]);
        l = Math.min(l, w);
        r = Math.max(r, w);
    }

    const check = (mid: number): boolean => {
        const INF = Number.MAX_SAFE_INTEGER / 2;
        const dist = new Array<number>(n).fill(INF);
        dist[0] = 0;

        const pq = new PriorityQueue<[number, number]>((a, b) => a[0] - b[0]);
        pq.enqueue([0, 0]);

        while (!pq.isEmpty()) {
            const [d, u] = pq.dequeue();

            if (d > k) return false;
            if (u === n - 1) return true;
            if (dist[u] < d) continue;

            for (const [v, w] of g[u]) {
                if (w < mid) continue;

                const nd = d + w;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.enqueue([nd, v]);
                }
            }
        }

        return false;
    };

    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (check(mid)) l = mid;
        else r = mid - 1;
    }

    return check(l) ? l : -1;
}
