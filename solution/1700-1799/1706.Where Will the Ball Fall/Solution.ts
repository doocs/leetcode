function findBall(grid: number[][]): number[] {
    const m = grid.length;
    const n = grid[0].length;
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
    const ans: number[] = [];
    for (let j = 0; j < n; ++j) {
        ans.push(dfs(0, j));
    }
    return ans;
}
