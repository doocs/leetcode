function shortestPathLength(graph: number[][]): number {
    const n = graph.length;
    const q: number[][] = [];
    const vis: boolean[][] = new Array(n).fill(false).map(() => new Array(1 << n).fill(false));
    for (let i = 0; i < n; ++i) {
        q.push([i, 1 << i]);
        vis[i][1 << i] = true;
    }
    for (let ans = 0; ; ++ans) {
        for (let k = q.length; k; --k) {
            const [i, st] = q.shift()!;
            if (st === (1 << n) - 1) {
                return ans;
            }
            for (const j of graph[i]) {
                const nst = st | (1 << j);
                if (!vis[j][nst]) {
                    vis[j][nst] = true;
                    q.push([j, nst]);
                }
            }
        }
    }
}
