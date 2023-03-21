function sumOfDistancesInTree(n: number, edges: number[][]): number[] {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const ans: number[] = new Array(n).fill(0);
    const size: number[] = new Array(n).fill(0);
    const dfs1 = (i: number, fa: number, d: number) => {
        ans[0] += d;
        size[i] = 1;
        for (const j of g[i]) {
            if (j !== fa) {
                dfs1(j, i, d + 1);
                size[i] += size[j];
            }
        }
    };
    const dfs2 = (i: number, fa: number, t: number) => {
        ans[i] = t;
        for (const j of g[i]) {
            if (j != fa) {
                dfs2(j, i, t - size[j] + n - size[j]);
            }
        }
    };
    dfs1(0, -1, 0);
    dfs2(0, -1, ans[0]);
    return ans;
}
