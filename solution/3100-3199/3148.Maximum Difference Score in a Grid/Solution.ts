function maxScore(grid: number[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    const f: number[][] = Array.from({ length: m }, () => Array.from({ length: n }, () => 0));
    let ans = -Infinity;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            let mi = Infinity;
            if (i) {
                mi = Math.min(mi, f[i - 1][j]);
            }
            if (j) {
                mi = Math.min(mi, f[i][j - 1]);
            }
            ans = Math.max(ans, grid[i][j] - mi);
            f[i][j] = Math.min(mi, grid[i][j]);
        }
    }
    return ans;
}
