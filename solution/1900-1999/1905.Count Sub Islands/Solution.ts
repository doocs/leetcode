function countSubIslands(grid1: number[][], grid2: number[][]): number {
    let m = grid1.length,
        n = grid1[0].length;
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid2[i][j] == 1 && dfs(grid1, grid2, i, j)) {
                ++ans;
            }
        }
    }
    return ans;
}

function dfs(
    grid1: number[][],
    grid2: number[][],
    i: number,
    j: number,
): boolean {
    let m = grid1.length,
        n = grid1[0].length;
    let ans = true;
    if (grid1[i][j] == 0) {
        ans = false;
    }
    grid2[i][j] = 0;
    for (let [dx, dy] of [
        [0, 1],
        [0, -1],
        [1, 0],
        [-1, 0],
    ]) {
        let x = i + dx,
            y = j + dy;
        if (x < 0 || x > m - 1 || y < 0 || y > n - 1 || grid2[x][y] == 0) {
            continue;
        }
        if (!dfs(grid1, grid2, x, y)) {
            ans = false;
        }
    }
    return ans;
}
