function ways(pizza: string[], k: number): number {
    const mod = 1e9 + 7;
    const m = pizza.length;
    const n = pizza[0].length;
    const f = new Array(m).fill(0).map(() => new Array(n).fill(0).map(() => new Array(k).fill(-1)));
    const s = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            const x = pizza[i - 1][j - 1] === 'A' ? 1 : 0;
            s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + x;
        }
    }
    const dfs = (i: number, j: number, k: number): number => {
        if (f[i][j][k] !== -1) {
            return f[i][j][k];
        }
        if (k === 0) {
            return s[m][n] - s[i][n] - s[m][j] + s[i][j] > 0 ? 1 : 0;
        }
        let ans = 0;
        for (let x = i + 1; x < m; ++x) {
            if (s[x][n] - s[i][n] - s[x][j] + s[i][j] > 0) {
                ans = (ans + dfs(x, j, k - 1)) % mod;
            }
        }
        for (let y = j + 1; y < n; ++y) {
            if (s[m][y] - s[i][y] - s[m][j] + s[i][j] > 0) {
                ans = (ans + dfs(i, y, k - 1)) % mod;
            }
        }
        return (f[i][j][k] = ans);
    };
    return dfs(0, 0, k - 1);
}
