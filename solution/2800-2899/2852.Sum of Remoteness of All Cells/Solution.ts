function sumRemoteness(grid: number[][]): number {
    const n = grid.length;
    let cnt = 0;
    for (const row of grid) {
        for (const x of row) {
            if (x > 0) {
                cnt++;
            }
        }
    }
    const dirs = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number): [number, number] => {
        let s = grid[i][j];
        let t = 1;
        grid[i][j] = 0;
        for (let k = 0; k < 4; ++k) {
            const [x, y] = [i + dirs[k], j + dirs[k + 1]];
            if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] > 0) {
                const [ss, tt] = dfs(x, y);
                s += ss;
                t += tt;
            }
        }
        return [s, t];
    };
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] > 0) {
                const [s, t] = dfs(i, j);
                ans += (cnt - t) * s;
            }
        }
    }
    return ans;
}
