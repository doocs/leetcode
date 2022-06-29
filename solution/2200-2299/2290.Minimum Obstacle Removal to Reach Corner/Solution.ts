function minimumObstacles(grid: number[][]): number {
    const m = grid.length,
        n = grid[0].length;
    const dirs = [
        [0, 1],
        [0, -1],
        [1, 0],
        [-1, 0],
    ];
    let ans = Array.from({ length: m }, v => new Array(n).fill(Infinity));
    ans[0][0] = 0;
    let deque = [[0, 0]];
    while (deque.length) {
        let [x, y] = deque.shift();
        for (let [dx, dy] of dirs) {
            let [i, j] = [x + dx, y + dy];
            if (i < 0 || i > m - 1 || j < 0 || j > n - 1) continue;
            const cost = grid[i][j];
            if (ans[x][y] + cost >= ans[i][j]) continue;
            ans[i][j] = ans[x][y] + cost;
            deque.push([i, j]);
        }
    }
    return ans[m - 1][n - 1];
}
