function maxTargetNodes(edges1: number[][], edges2: number[][]): number[] {
    const g1 = build(edges1);
    const g2 = build(edges2);
    const [n, m] = [g1.length, g2.length];
    const c1 = Array(n).fill(0);
    const c2 = Array(m).fill(0);
    const cnt1 = [0, 0];
    const cnt2 = [0, 0];

    dfs(g2, 0, -1, c2, 0, cnt2);
    dfs(g1, 0, -1, c1, 0, cnt1);

    const t = Math.max(...cnt2);
    const ans = Array(n);
    for (let i = 0; i < n; i++) {
        ans[i] = t + cnt1[c1[i]];
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

function dfs(g: number[][], a: number, fa: number, c: number[], d: number, cnt: number[]): void {
    c[a] = d;
    cnt[d]++;
    for (const b of g[a]) {
        if (b !== fa) {
            dfs(g, b, a, c, d ^ 1, cnt);
        }
    }
}
