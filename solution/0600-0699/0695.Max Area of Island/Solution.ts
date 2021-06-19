function maxAreaOfIsland(grid: number[][]): number {
    let m = grid.length, n = grid[0].length;
    let visited = Array.from({ length: m }, v => new Array(n).fill(false));
    let res = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (!visited[i][j]) {
                res = Math.max(dfs(grid, i, j, visited), res);
            }
        }
    }
    return res;
};

function dfs(grid: number[][], i: number, j: number, visited: boolean[][]): number {
    let m = grid.length, n = grid[0].length;
    if (i < 0 || i > m - 1 || j < 0 || j > n - 1 || visited[i][j]) {
        return 0;
    }
    visited[i][j] = true;
    if (grid[i][j] == 0) {
        return 0;
    }
    let res = 1;
    for (let [dx, dy] of [[0, 1], [0, -1], [1, 0], [-1, 0]]) {
        res += dfs(grid, i + dx, j + dy, visited);
    }
    return res;
}