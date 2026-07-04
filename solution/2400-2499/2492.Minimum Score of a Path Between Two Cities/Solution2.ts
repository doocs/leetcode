function minScore(n: number, roads: number[][]): number {
    const g: [number, number][][] = Array.from({ length: n + 1 }, () => []);
    for (const [a, b, w] of roads) {
        g[a].push([b, w]);
        g[b].push([a, w]);
    }

    const vis = new Array(n + 1).fill(false);
    let ans = Infinity;
    let q: number[] = [1];
    vis[1] = true;

    while (q.length > 0) {
        const nq: number[] = [];
        for (const a of q) {
            for (const [b, w] of g[a]) {
                ans = Math.min(ans, w);
                if (!vis[b]) {
                    vis[b] = true;
                    nq.push(b);
                }
            }
        }
        q = nq;
    }
    return ans;
}
