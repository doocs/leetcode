function countSubIslands(grid1: number[][], grid2: number[][]): number {
    const [m, n] = [grid1.length, grid1[0].length];
    let ans = 0;
    const dirs: number[] = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number): number => {
        let ok = grid1[i][j];
        grid2[i][j] = 0;
        for (let k = 0; k < 4; ++k) {
            const [x, y] = [i + dirs[k], j + dirs[k + 1]];
            if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y]) {
                ok &= dfs(x, y);
            }
        }
        return ok;
    };
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; j++) {
            if (grid2[i][j]) {
                ans += dfs(i, j);
            }
        }
    }
    return ans;
}
