function minimumSeconds(land: string[][]): number {
    const m = land.length;
    const n = land[0].length;
    const g: number[][] = Array(m)
        .fill(0)
        .map(() => Array(n).fill(1 << 30));
    const vis: boolean[][] = Array(m)
        .fill(0)
        .map(() => Array(n).fill(false));
    const q: number[][] = [];
    let [si, sj] = [0, 0];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            const c = land[i][j];
            if (c === '*') {
                q.push([i, j]);
            } else if (c === 'S') {
                [si, sj] = [i, j];
            }
        }
    }
    const dirs = [-1, 0, 1, 0, -1];
    for (let t = 0; q.length; ++t) {
        for (let k = q.length; k; --k) {
            const [i, j] = q.shift()!;
            g[i][j] = t;
            for (let d = 0; d < 4; ++d) {
                const [x, y] = [i + dirs[d], j + dirs[d + 1]];
                if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] && 'S.'.includes(land[x][y])) {
                    vis[x][y] = true;
                    q.push([x, y]);
                }
            }
        }
    }
    q.push([si, sj]);
    for (let i = 0; i < m; ++i) {
        vis[i].fill(false);
    }
    vis[si][sj] = true;
    for (let t = 0; q.length; ++t) {
        for (let k = q.length; k; --k) {
            const [i, j] = q.shift()!;
            if (land[i][j] === 'D') {
                return t;
            }
            for (let d = 0; d < 4; ++d) {
                const [x, y] = [i + dirs[d], j + dirs[d + 1]];
                if (
                    x >= 0 &&
                    x < m &&
                    y >= 0 &&
                    y < n &&
                    !vis[x][y] &&
                    g[x][y] > t + 1 &&
                    'D.'.includes(land[x][y]) &&
                    t + 1 < g[x][y]
                ) {
                    vis[x][y] = true;
                    q.push([x, y]);
                }
            }
        }
    }
    return -1;
}
