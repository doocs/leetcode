function closestMeetingNode(
    edges: number[],
    node1: number,
    node2: number,
): number {
    const n = edges.length;
    const g = Array.from({ length: n }, () => []);
    for (let i = 0; i < n; ++i) {
        if (edges[i] != -1) {
            g[i].push(edges[i]);
        }
    }
    const inf = 1 << 30;
    const f = (i: number) => {
        const dist = new Array(n).fill(inf);
        dist[i] = 0;
        const q: number[] = [i];
        while (q.length) {
            i = q.shift();
            for (const j of g[i]) {
                if (dist[j] == inf) {
                    dist[j] = dist[i] + 1;
                    q.push(j);
                }
            }
        }
        return dist;
    };
    const d1 = f(node1);
    const d2 = f(node2);
    let ans = -1;
    let d = inf;
    for (let i = 0; i < n; ++i) {
        const t = Math.max(d1[i], d2[i]);
        if (t < d) {
            d = t;
            ans = i;
        }
    }
    return ans;
}
