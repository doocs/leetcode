function maximumPoints(edges: number[][], coins: number[], k: number): number {
    const n = coins.length;
    const f: number[][] = Array.from({ length: n }, () => Array(15).fill(-1));
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const dfs = (i: number, fa: number, j: number): number => {
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        let a = (coins[i] >> j) - k;
        let b = coins[i] >> (j + 1);
        for (const c of g[i]) {
            if (c !== fa) {
                a += dfs(c, i, j);
                if (j < 14) {
                    b += dfs(c, i, j + 1);
                }
            }
        }
        return (f[i][j] = Math.max(a, b));
    };
    return dfs(0, -1, 0);
}
