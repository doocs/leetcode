function maxProductPath(grid: number[][]): number {
    const m = grid.length,
        n = grid[0].length;
    const f = Array.from({ length: m }, () => Array.from({ length: n }, () => [0, 0]));

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const x = grid[i][j];

            if (i === 0 && j === 0) {
                f[i][j] = [x, x];
                continue;
            }

            let mn = Infinity,
                mx = -Infinity;

            if (i > 0) {
                const [a, b] = f[i - 1][j];
                mn = Math.min(mn, a * x, b * x);
                mx = Math.max(mx, a * x, b * x);
            }

            if (j > 0) {
                const [a, b] = f[i][j - 1];
                mn = Math.min(mn, a * x, b * x);
                mx = Math.max(mx, a * x, b * x);
            }

            f[i][j] = [mn, mx];
        }
    }

    const ans = f[m - 1][n - 1][1];
    const mod = 1e9 + 7;
    return ans < 0 ? -1 : ans % mod;
}
