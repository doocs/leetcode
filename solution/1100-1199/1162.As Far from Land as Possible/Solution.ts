function maxDistance(grid: number[][]): number {
    const n = grid.length;
    const q: [number, number][] = [];
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] === 1) {
                q.push([i, j]);
            }
        }
    }
    let ans = -1;
    if (q.length === 0 || q.length === n * n) {
        return ans;
    }
    const dirs: number[] = [-1, 0, 1, 0, -1];
    while (q.length > 0) {
        for (let m = q.length; m; --m) {
            const [i, j] = q.shift()!;
            for (let k = 0; k < 4; ++k) {
                const x = i + dirs[k];
                const y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] === 0) {
                    grid[x][y] = 1;
                    q.push([x, y]);
                }
            }
        }
        ++ans;
    }
    return ans;
}
