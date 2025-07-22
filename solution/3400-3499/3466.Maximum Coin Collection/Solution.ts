function maxCoins(lane1: number[], lane2: number[]): number {
    const n = lane1.length;
    const NEG_INF = -1e18;
    const f: number[][][] = Array.from({ length: n }, () =>
        Array.from({ length: 2 }, () => Array(3).fill(NEG_INF)),
    );
    const dfs = (dfs: Function, i: number, j: number, k: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i][j][k] !== NEG_INF) {
            return f[i][j][k];
        }
        const x = j === 0 ? lane1[i] : lane2[i];
        let ans = Math.max(x, dfs(dfs, i + 1, j, k) + x);
        if (k > 0) {
            ans = Math.max(ans, dfs(dfs, i + 1, j ^ 1, k - 1) + x);
            ans = Math.max(ans, dfs(dfs, i, j ^ 1, k - 1));
        }
        f[i][j][k] = ans;
        return ans;
    };
    let ans = NEG_INF;
    for (let i = 0; i < n; ++i) {
        ans = Math.max(ans, dfs(dfs, i, 0, 2));
    }
    return ans;
}
