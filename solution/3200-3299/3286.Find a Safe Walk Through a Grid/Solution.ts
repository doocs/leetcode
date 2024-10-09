function findSafeWalk(grid: number[][], health: number): boolean {
    const m = grid.length;
    const n = grid[0].length;
    const dist: number[][] = Array.from({ length: m }, () => Array(n).fill(Infinity));
    dist[0][0] = grid[0][0];
    const q: [number, number][] = [[0, 0]];
    const dirs = [-1, 0, 1, 0, -1];
    while (q.length > 0) {
        const [x, y] = q.shift()!;
        for (let i = 0; i < 4; i++) {
            const nx = x + dirs[i];
            const ny = y + dirs[i + 1];
            if (
                nx >= 0 &&
                nx < m &&
                ny >= 0 &&
                ny < n &&
                dist[nx][ny] > dist[x][y] + grid[nx][ny]
            ) {
                dist[nx][ny] = dist[x][y] + grid[nx][ny];
                q.push([nx, ny]);
            }
        }
    }
    return dist[m - 1][n - 1] < health;
}
