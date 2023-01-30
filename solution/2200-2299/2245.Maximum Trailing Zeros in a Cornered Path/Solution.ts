function maxTrailingZeros(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const r2 = Array.from({ length: m + 1 }, () => new Array(n + 1).fill(0));
    const c2 = Array.from({ length: m + 1 }, () => new Array(n + 1).fill(0));
    const r5 = Array.from({ length: m + 1 }, () => new Array(n + 1).fill(0));
    const c5 = Array.from({ length: m + 1 }, () => new Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            let x = grid[i - 1][j - 1];
            let s2 = 0;
            let s5 = 0;
            for (; x % 2 == 0; x = Math.floor(x / 2)) {
                ++s2;
            }
            for (; x % 5 == 0; x = Math.floor(x / 5)) {
                ++s5;
            }
            r2[i][j] = r2[i][j - 1] + s2;
            c2[i][j] = c2[i - 1][j] + s2;
            r5[i][j] = r5[i][j - 1] + s5;
            c5[i][j] = c5[i - 1][j] + s5;
        }
    }
    let ans = 0;
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            const a = Math.min(
                r2[i][j] + c2[i - 1][j],
                r5[i][j] + c5[i - 1][j],
            );
            const b = Math.min(
                r2[i][j] + c2[m][j] - c2[i][j],
                r5[i][j] + c5[m][j] - c5[i][j],
            );
            const c = Math.min(
                r2[i][n] - r2[i][j] + c2[i][j],
                r5[i][n] - r5[i][j] + c5[i][j],
            );
            const d = Math.min(
                r2[i][n] - r2[i][j - 1] + c2[m][j] - c2[i][j],
                r5[i][n] - r5[i][j - 1] + c5[m][j] - c5[i][j],
            );
            ans = Math.max(ans, a, b, c, d);
        }
    }
    return ans;
}
