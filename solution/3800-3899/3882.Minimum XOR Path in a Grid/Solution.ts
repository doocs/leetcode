function minCost(grid: number[][]): number {
    const m = grid.length,
        n = grid[0].length;
    let mx = 0;
    for (const row of grid) {
        for (const x of row) {
            mx = Math.max(mx, x);
        }
    }
    mx = 1 << (32 - Math.clz32(mx));
    const f: boolean[][][] = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => new Array<boolean>(mx).fill(false)),
    );
    f[0][0][grid[0][0]] = true;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const x = grid[i][j];
            if (i > 0 || j > 0) {
                for (let k = 0; k < mx; k++) {
                    if (i > 0 && f[i - 1][j][k ^ x]) {
                        f[i][j][k] = true;
                    }
                    if (j > 0 && f[i][j - 1][k ^ x]) {
                        f[i][j][k] = true;
                    }
                }
            }
        }
    }
    for (let k = 0; ; k++) {
        if (f[m - 1][n - 1][k]) {
            return k;
        }
    }
}
