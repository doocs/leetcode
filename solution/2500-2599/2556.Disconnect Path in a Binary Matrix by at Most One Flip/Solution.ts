function isPossibleToCutPath(grid: number[][]): boolean {
    const m = grid.length;
    const n = grid[0].length;

    const dfs = (i: number, j: number): boolean => {
        if (i >= m || j >= n || grid[i][j] !== 1) {
            return false;
        }
        grid[i][j] = 0;
        if (i === m - 1 && j === n - 1) {
            return true;
        }
        return dfs(i + 1, j) || dfs(i, j + 1);
    };
    const a = dfs(0, 0);
    grid[0][0] = 1;
    grid[m - 1][n - 1] = 1;
    const b = dfs(0, 0);
    return !(a && b);
}
