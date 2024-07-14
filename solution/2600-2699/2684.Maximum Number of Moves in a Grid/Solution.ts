function maxMoves(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let q = new Set<number>(Array.from({ length: m }, (_, i) => i));
    for (let j = 0; j < n - 1; ++j) {
        const t = new Set<number>();
        for (const i of q) {
            for (let k = i - 1; k <= i + 1; ++k) {
                if (k >= 0 && k < m && grid[i][j] < grid[k][j + 1]) {
                    t.add(k);
                }
            }
        }
        if (t.size === 0) {
            return j;
        }
        q = t;
    }
    return n - 1;
}
