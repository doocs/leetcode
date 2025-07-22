function numberOfStableArrays(zero: number, one: number, limit: number): number {
    const mod = 1e9 + 7;
    const f: number[][][] = Array.from({ length: zero + 1 }, () =>
        Array.from({ length: one + 1 }, () => [-1, -1]),
    );

    const dfs = (i: number, j: number, k: number): number => {
        if (i < 0 || j < 0) {
            return 0;
        }
        if (i === 0) {
            return k === 1 && j <= limit ? 1 : 0;
        }
        if (j === 0) {
            return k === 0 && i <= limit ? 1 : 0;
        }
        let res = f[i][j][k];
        if (res !== -1) {
            return res;
        }
        if (k === 0) {
            res = (dfs(i - 1, j, 0) + dfs(i - 1, j, 1) - dfs(i - limit - 1, j, 1) + mod) % mod;
        } else {
            res = (dfs(i, j - 1, 0) + dfs(i, j - 1, 1) - dfs(i, j - limit - 1, 0) + mod) % mod;
        }
        return (f[i][j][k] = res);
    };

    return (dfs(zero, one, 0) + dfs(zero, one, 1)) % mod;
}
