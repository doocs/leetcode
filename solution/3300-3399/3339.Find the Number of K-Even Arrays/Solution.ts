function countOfArrays(n: number, m: number, k: number): number {
    const f = Array.from({ length: n }, () =>
        Array.from({ length: k + 1 }, () => Array(2).fill(-1)),
    );
    const mod = 1e9 + 7;
    const cnt0 = Math.floor(m / 2);
    const cnt1 = m - cnt0;
    const dfs = (i: number, j: number, k: number): number => {
        if (j < 0) {
            return 0;
        }
        if (i >= n) {
            return j === 0 ? 1 : 0;
        }
        if (f[i][j][k] !== -1) {
            return f[i][j][k];
        }
        const a = (cnt1 * dfs(i + 1, j, 1)) % mod;
        const b = (cnt0 * dfs(i + 1, j - ((k & 1) ^ 1), 0)) % mod;
        return (f[i][j][k] = (a + b) % mod);
    };
    return dfs(0, k, 1);
}
