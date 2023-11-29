function tourOfKnight(m: number, n: number, r: number, c: number): number[][] {
    const g: number[][] = Array.from({ length: m }, () => Array(n).fill(-1));
    const dirs = [-2, -1, 2, 1, -2, 1, 2, -1, -2];
    let ok = false;
    const dfs = (i: number, j: number) => {
        if (g[i][j] === m * n - 1) {
            ok = true;
            return;
        }
        for (let k = 0; k < 8; ++k) {
            const [x, y] = [i + dirs[k], j + dirs[k + 1]];
            if (x >= 0 && x < m && y >= 0 && y < n && g[x][y] === -1) {
                g[x][y] = g[i][j] + 1;
                dfs(x, y);
                if (ok) {
                    return;
                }
                g[x][y] = -1;
            }
        }
    };
    g[r][c] = 0;
    dfs(r, c);
    return g;
}
