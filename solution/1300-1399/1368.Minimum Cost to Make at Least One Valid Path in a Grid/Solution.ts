function minCost(grid: number[][]): number {
    const m = grid.length,
        n = grid[0].length;
    let ans = Array.from({ length: m }, v => new Array(n).fill(Infinity));
    ans[0][0] = 0;
    let queue = [[0, 0]];
    const dirs = [
        [0, 1],
        [0, -1],
        [1, 0],
        [-1, 0],
    ];
    while (queue.length) {
        let [x, y] = queue.shift();
        for (let step = 1; step < 5; step++) {
            let [dx, dy] = dirs[step - 1];
            let [i, j] = [x + dx, y + dy];
            if (i < 0 || i >= m || j < 0 || j >= n) continue;
            let cost = ~~(grid[x][y] != step) + ans[x][y];
            if (cost >= ans[i][j]) continue;
            ans[i][j] = cost;
            if (grid[x][y] == step) {
                queue.unshift([i, j]);
            } else {
                queue.push([i, j]);
            }
        }
    }
    return ans[m - 1][n - 1];
}
