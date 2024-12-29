function minimumOperations(grid: number[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    let ans: number = 0;
    for (let j = 0; j < n; ++j) {
        let pre: number = -1;
        for (let i = 0; i < m; ++i) {
            const cur = grid[i][j];
            if (pre < cur) {
                pre = cur;
            } else {
                ++pre;
                ans += pre - cur;
            }
        }
    }
    return ans;
}
