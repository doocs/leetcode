function minCost(grid: number[][], k: number): number {
    const [m, n] = [grid.length, grid[0].length];
    const INF = 1e18;

    const f: number[][][] = Array.from({ length: k + 1 }, () =>
        Array.from({ length: m }, () => Array(n).fill(INF)),
    );

    f[0][0][0] = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (i > 0) f[0][i][j] = Math.min(f[0][i][j], f[0][i - 1][j] + grid[i][j]);
            if (j > 0) f[0][i][j] = Math.min(f[0][i][j], f[0][i][j - 1] + grid[i][j]);
        }
    }

    const g = new Map<number, Array<[number, number]>>();
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const x = grid[i][j];
            if (!g.has(x)) {
                g.set(x, []);
            }
            g.get(x)!.push([i, j]);
        }
    }

    const keys = Array.from(g.keys()).sort((a, b) => b - a);

    for (let t = 1; t <= k; t++) {
        let mn = INF;

        for (const key of keys) {
            const pos = g.get(key)!;
            for (const [x, y] of pos) {
                mn = Math.min(mn, f[t - 1][x][y]);
            }
            for (const [x, y] of pos) {
                f[t][x][y] = mn;
            }
        }

        for (let i = 0; i < m; i++) {
            for (let j = 0; j < n; j++) {
                if (i > 0) {
                    f[t][i][j] = Math.min(f[t][i][j], f[t][i - 1][j] + grid[i][j]);
                }
                if (j > 0) {
                    f[t][i][j] = Math.min(f[t][i][j], f[t][i][j - 1] + grid[i][j]);
                }
            }
        }
    }

    let ans = INF;
    for (let t = 0; t <= k; t++) {
        ans = Math.min(ans, f[t][m - 1][n - 1]);
    }
    return ans;
}
