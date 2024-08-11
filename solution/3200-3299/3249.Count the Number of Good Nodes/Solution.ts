function countGoodNodes(edges: number[][]): number {
    const n = edges.length + 1;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    let ans = 0;
    const dfs = (a: number, fa: number): number => {
        let [pre, cnt, ok] = [-1, 1, 1];
        for (const b of g[a]) {
            if (b !== fa) {
                const cur = dfs(b, a);
                cnt += cur;
                if (pre < 0) {
                    pre = cur;
                } else if (pre !== cur) {
                    ok = 0;
                }
            }
        }
        ans += ok;
        return cnt;
    };
    dfs(0, -1);
    return ans;
}
