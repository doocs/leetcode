function matrixScore(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    for (let i = 0; i < m; ++i) {
        if (grid[i][0] == 0) {
            for (let j = 0; j < n; ++j) {
                grid[i][j] ^= 1;
            }
        }
    }
    let ans = 0;
    for (let j = 0; j < n; ++j) {
        let cnt = 0;
        for (let i = 0; i < m; ++i) {
            cnt += grid[i][j];
        }
        ans += Math.max(cnt, m - cnt) * (1 << (n - j - 1));
    }
    return ans;
}
