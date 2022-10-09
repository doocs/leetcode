function numberOfPaths(grid: number[][], k: number): number {
    const MOD = 10 ** 9 + 7;
    const m = grid.length,
        n = grid[0].length;
    let ans = Array.from({ length: m + 1 }, () =>
        Array.from({ length: n + 1 }, () => new Array(k).fill(0)),
    );
    ans[0][1][0] = 1;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            for (let v = 0; v < k; v++) {
                let key = (grid[i][j] + v) % k;
                ans[i + 1][j + 1][key] =
                    (ans[i][j + 1][v] +
                        ans[i + 1][j][v] +
                        ans[i + 1][j + 1][key]) %
                    MOD;
            }
        }
    }
    return ans[m][n][0];
}
