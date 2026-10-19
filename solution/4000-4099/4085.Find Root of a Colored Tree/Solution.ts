function findColoredRoot(edges: number[][], colors: string): number {
    const d: Record<string, number> = { W: 0, R: 1, B: 2 };
    const n = colors.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }
    let ans = -1;
    for (let u = 0; u < n; ++u) {
        const vs = g[u];
        if (vs.length > 3) {
            return -1;
        }
        const cnt = [0, 0, 0];
        for (const v of vs) {
            ++cnt[d[colors[v]]];
        }
        const c = d[colors[u]];
        if (cnt[c] > 0 || cnt[(c + 2) % 3] > 1) {
            return -1;
        }
        if (vs.length < 3 && cnt[(c + 2) % 3] === 0) {
            ans = u;
        }
    }
    return ans;
}
