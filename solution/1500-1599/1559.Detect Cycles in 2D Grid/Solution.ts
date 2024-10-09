function containsCycle(grid: string[][]): boolean {
    const [m, n] = [grid.length, grid[0].length];
    const vis: boolean[][] = Array.from({ length: m }, () => Array(n).fill(false));
    const dirs = [-1, 0, 1, 0, -1];
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (!vis[i][j]) {
                const q: [number, number, number, number][] = [[i, j, -1, -1]];
                vis[i][j] = true;
                for (const [x, y, px, py] of q) {
                    for (let k = 0; k < 4; k++) {
                        const [nx, ny] = [x + dirs[k], y + dirs[k + 1]];
                        if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                            if (grid[nx][ny] !== grid[x][y] || (nx === px && ny === py)) {
                                continue;
                            }
                            if (vis[nx][ny]) {
                                return true;
                            }
                            q.push([nx, ny, x, y]);
                            vis[nx][ny] = true;
                        }
                    }
                }
            }
        }
    }
    return false;
}
