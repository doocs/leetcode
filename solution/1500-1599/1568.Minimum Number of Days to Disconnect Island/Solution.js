/**
 * @param {number[][]} grid
 * @return {number}
 */
var minDays = function(grid) {
        const directions = [[0, 1], [1, 0], [0, -1], [-1, 0]];
    const rows = grid.length;
    const cols = grid[0].length;

    function dfs(x, y, visited) {
        visited[x][y] = true;
        for (let [dx, dy] of directions) {
            const nx = x + dx, ny = y + dy;
            if (nx >= 0 && ny >= 0 && nx < rows && ny < cols && grid[nx][ny] === 1 && !visited[nx][ny]) {
                dfs(nx, ny, visited);
            }
        }
    }

    function countIslands() {
        let visited = Array.from({ length: rows }, () => Array(cols).fill(false));
        let count = 0;
        for (let i = 0; i < rows; i++) {
            for (let j = 0; j < cols; j++) {
                if (grid[i][j] === 1 && !visited[i][j]) {
                    count++;
                    dfs(i, j, visited);
                }
            }
        }
        return count;
    }

    if (countIslands() !== 1) return 0;

    for (let i = 0; i < rows; i++) {
        for (let j = 0; j < cols; j++) {
            if (grid[i][j] === 1) {
                grid[i][j] = 0;
                if (countIslands() !== 1) return 1;
                grid[i][j] = 1;
            }
        }
    }

    return 2;

};
