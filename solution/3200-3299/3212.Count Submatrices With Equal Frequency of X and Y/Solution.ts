function numberOfSubmatrices(grid: string[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    const s = Array.from({ length: m + 1 }, () => Array.from({ length: n + 1 }, () => [0, 0]));
    let ans = 0;

    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            s[i][j][0] =
                s[i - 1][j][0] +
                s[i][j - 1][0] -
                s[i - 1][j - 1][0] +
                (grid[i - 1][j - 1] === 'X' ? 1 : 0);
            s[i][j][1] =
                s[i - 1][j][1] +
                s[i][j - 1][1] -
                s[i - 1][j - 1][1] +
                (grid[i - 1][j - 1] === 'Y' ? 1 : 0);
            if (s[i][j][0] > 0 && s[i][j][0] === s[i][j][1]) {
                ++ans;
            }
        }
    }

    return ans;
}
