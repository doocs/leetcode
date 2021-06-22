function numIslands(grid: string[][]): number {
    let m = grid.length, n = grid[0].length;
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] == '1') {
                bfs(grid, i, j)
                ++ans;
            }
        }
    }
    return ans;
};

function bfs(grid: string[][], r: number, c: number): void {
    let m = grid.length, n = grid[0].length;
    let queue = new Array();
    queue.push([r, c]);
    while (queue.length > 0) {
        let [i, j] = queue.shift();
        for (let [dx, dy] of [[0, 1], [0, -1], [1, 0], [-1, 0]]) {
            let x = i + dx, y = j + dy;
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                grid[x][y] = '0';
                queue.push([x, y]);
            }
        }
    }
}