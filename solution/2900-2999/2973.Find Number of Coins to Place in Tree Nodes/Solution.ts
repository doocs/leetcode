function placedCoins(edges: number[][], cost: number[]): number[] {
    const n = cost.length;
    const ans: number[] = Array(n).fill(1);
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const dfs = (a: number, fa: number): number[] => {
        const res: number[] = [cost[a]];
        for (const b of g[a]) {
            if (b !== fa) {
                res.push(...dfs(b, a));
            }
        }
        res.sort((a, b) => a - b);
        const m = res.length;
        if (m >= 3) {
            const x = res[m - 1] * res[m - 2] * res[m - 3];
            const y = res[0] * res[1] * res[m - 1];
            ans[a] = Math.max(0, x, y);
        }
        if (m > 5) {
            res.splice(2, m - 5);
        }
        return res;
    };
    dfs(0, -1);
    return ans;
}
