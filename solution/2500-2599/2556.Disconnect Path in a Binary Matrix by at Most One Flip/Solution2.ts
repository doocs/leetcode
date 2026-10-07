function isPossibleToCutPath(grid: number[][]): boolean {
    const m = grid.length;
    const n = grid[0].length;
    const dfs = (): boolean => {
        const stk: number[][] = [[0, 0]];
        while (stk.length) {
            const [i, j] = stk.pop()!;
            if (i >= m || j >= n || grid[i][j] !== 1) {
                continue;
            }
            grid[i][j] = 0;
            if (i === m - 1 && j === n - 1) {
                return true;
            }
            stk.push([i, j + 1], [i + 1, j]);
        }
        return false;
    };
    const a = dfs();
    grid[0][0] = 1;
    grid[m - 1][n - 1] = 1;
    const b = dfs();
    return !(a && b);
}
