function containsCycle(grid: string[][]): boolean {
    const [m, n] = [grid.length, grid[0].length];
    const vis: boolean[][] = Array.from({ length: m }, () => Array(n).fill(false));
    const dfs = (x: number, y: number, px: number, py: number): boolean => {
        vis[x][y] = true;
        const dirs = [-1, 0, 1, 0, -1];
        for (let k = 0; k < 4; k++) {
            const [nx, ny] = [x + dirs[k], y + dirs[k + 1]];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                if (grid[nx][ny] !== grid[x][y] || (nx === px && ny === py)) {
                    continue;
                }
                if (vis[nx][ny] || dfs(nx, ny, x, y)) {
                    return true;
                }
            }
        }
        return false;
    };
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (!vis[i][j] && dfs(i, j, -1, -1)) {
                return true;
            }
        }
    }
    return false;
}
