function lastMarkedNodes(edges: number[][]): number[] {
    const n = edges.length + 1;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }
    const dfs = (start: number, dist: number[]) => {
        const stk: [number, number][] = [[start, -1]];
        while (stk.length) {
            const [i, fa] = stk.pop()!;
            for (const j of g[i]) {
                if (j !== fa) {
                    dist[j] = dist[i] + 1;
                    stk.push([j, i]);
                }
            }
        }
    };

    const dist1: number[] = Array(n).fill(0);
    dfs(0, dist1);
    const a = dist1.indexOf(Math.max(...dist1));

    const dist2: number[] = Array(n).fill(0);
    dfs(a, dist2);
    const b = dist2.indexOf(Math.max(...dist2));

    const dist3: number[] = Array(n).fill(0);
    dfs(b, dist3);

    const ans: number[] = [];
    for (let i = 0; i < n; ++i) {
        ans.push(dist2[i] > dist3[i] ? a : b);
    }
    return ans;
}
