function findBall(grid: number[][]): number[] {
    const m = grid.length;
    const n = grid[0].length;
    const res = new Array(n).fill(0);
    const dfs = (i: number, j: number) => {
        if (i === m) {
            return j;
        }
        if (grid[i][j] === 1) {
            if (j === n - 1 || grid[i][j + 1] === -1) {
                return -1;
            }
            return dfs(i + 1, j + 1);
        } else {
            if (j === 0 || grid[i][j - 1] === 1) {
                return -1;
            }
            return dfs(i + 1, j - 1);
        }
    };
    for (let i = 0; i < n; i++) {
        res[i] = dfs(0, i);
    }
    return res;
}
