function numWays(steps: number, arrLen: number): number {
    const f = Array.from({ length: steps }, () => Array(steps + 1).fill(-1));
    const mod = 10 ** 9 + 7;
    const dfs = (i: number, j: number) => {
        if (i > j || i >= arrLen || i < 0 || j < 0) {
            return 0;
        }
        if (i == 0 && j == 0) {
            return 1;
        }
        if (f[i][j] != -1) {
            return f[i][j];
        }
        let ans = 0;
        for (let k = -1; k <= 1; ++k) {
            ans = (ans + dfs(i + k, j - 1)) % mod;
        }
        return (f[i][j] = ans);
    };
    return dfs(0, steps);
}
