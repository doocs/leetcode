function maxTargetNodes(edges1: number[][], edges2: number[][], k: number): number[] {
    const g2 = build(edges2);
    const m = edges2.length + 1;
    let t = 0;
    for (let i = 0; i < m; i++) {
        t = Math.max(t, dfs(g2, i, -1, k - 1));
    }

    const g1 = build(edges1);
    const n = edges1.length + 1;
    const ans = Array(n).fill(t);

    for (let i = 0; i < n; i++) {
        ans[i] += dfs(g1, i, -1, k);
    }

    return ans;
}

function build(edges: number[][]): number[][] {
    const n = edges.length + 1;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    return g;
}

function dfs(g: number[][], a: number, fa: number, d: number): number {
    if (d < 0) {
        return 0;
    }
    let cnt = 1;
    for (const b of g[a]) {
        if (b !== fa) {
            cnt += dfs(g, b, a, d - 1);
        }
    }
    return cnt;
}
