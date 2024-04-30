function rootCount(edges: number[][], guesses: number[][], k: number): number {
    const n = edges.length + 1;
    const g: number[][] = Array.from({ length: n }, () => []);
    const gs: Map<number, number> = new Map();
    const f = (i: number, j: number) => i * n + j;
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    for (const [a, b] of guesses) {
        const x = f(a, b);
        gs.set(x, gs.has(x) ? gs.get(x)! + 1 : 1);
    }
    let ans = 0;
    let cnt = 0;
    const dfs1 = (i: number, fa: number): void => {
        for (const j of g[i]) {
            if (j !== fa) {
                cnt += gs.get(f(i, j)) || 0;
                dfs1(j, i);
            }
        }
    };
    const dfs2 = (i: number, fa: number): void => {
        ans += cnt >= k ? 1 : 0;
        for (const j of g[i]) {
            if (j !== fa) {
                const a = gs.get(f(i, j)) || 0;
                const b = gs.get(f(j, i)) || 0;
                cnt -= a;
                cnt += b;
                dfs2(j, i);
                cnt -= b;
                cnt += a;
            }
        }
    };
    dfs1(0, -1);
    dfs2(0, -1);
    return ans;
}
