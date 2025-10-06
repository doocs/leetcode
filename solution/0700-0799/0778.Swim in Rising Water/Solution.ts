function swimInWater(grid: number[][]): number {
    const n = grid.length;
    const m = n * n;
    const p = Array.from({ length: m }, (_, i) => i);
    const hi = new Array<number>(m);
    const find = (x: number): number => (p[x] === x ? x : (p[x] = find(p[x])));

    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            hi[grid[i][j]] = i * n + j;
        }
    }

    const dirs = [-1, 0, 1, 0, -1];

    for (let t = 0; t < m; ++t) {
        const id = hi[t];
        const x = Math.floor(id / n);
        const y = id % n;

        for (let k = 0; k < 4; ++k) {
            const nx = x + dirs[k];
            const ny = y + dirs[k + 1];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] <= t) {
                p[find(x * n + y)] = find(nx * n + ny);
            }
        }
        if (find(0) === find(m - 1)) {
            return t;
        }
    }

    return 0;
}
