function countSubIslands(grid1, grid2) {
    const [m, n] = [grid1.length, grid1[0].length];
    let ans = 0;
    const dirs = [-1, 0, 1, 0, -1];
    const flood = (i, j) => {
        let ok = 1;
        grid2[i][j] = 0;
        const stk = [[i, j]];
        while (stk.length) {
            const [a, b] = stk.pop();
            ok &= grid1[a][b];
            for (let k = 0; k < 4; ++k) {
                const x = a + dirs[k];
                const y = b + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y]) {
                    grid2[x][y] = 0;
                    stk.push([x, y]);
                }
            }
        }
        return ok;
    };
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; j++) {
            if (grid2[i][j]) {
                ans += flood(i, j);
            }
        }
    }
    return ans;
}
