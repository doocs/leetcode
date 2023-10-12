function maximumMinutes(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const fire = Array.from({ length: m }, () => Array.from({ length: n }, () => false));
    const vis = Array.from({ length: m }, () => Array.from({ length: n }, () => false));
    const dirs: number[] = [-1, 0, 1, 0, -1];
    let [l, r] = [-1, m * n];
    const spread = (q: number[][]): number[][] => {
        const nq: number[][] = [];
        while (q.length) {
            const [i, j] = q.shift()!;
            for (let k = 0; k < 4; ++k) {
                const [x, y] = [i + dirs[k], j + dirs[k + 1]];
                if (x >= 0 && x < m && y >= 0 && y < n && !fire[x][y] && grid[x][y] === 0) {
                    fire[x][y] = true;
                    nq.push([x, y]);
                }
            }
        }
        return nq;
    };
    const check = (t: number): boolean => {
        for (let i = 0; i < m; ++i) {
            fire[i].fill(false);
            vis[i].fill(false);
        }
        let q1: number[][] = [];
        for (let i = 0; i < m; ++i) {
            for (let j = 0; j < n; ++j) {
                if (grid[i][j] === 1) {
                    q1.push([i, j]);
                    fire[i][j] = true;
                }
            }
        }
        for (; t && q1.length; --t) {
            q1 = spread(q1);
        }
        if (fire[0][0]) {
            return false;
        }
        const q2: number[][] = [[0, 0]];
        vis[0][0] = true;
        for (; q2.length; q1 = spread(q1)) {
            for (let d = q2.length; d; --d) {
                const [i, j] = q2.shift()!;
                if (fire[i][j]) {
                    continue;
                }
                for (let k = 0; k < 4; ++k) {
                    const [x, y] = [i + dirs[k], j + dirs[k + 1]];
                    if (
                        x >= 0 &&
                        x < m &&
                        y >= 0 &&
                        y < n &&
                        !vis[x][y] &&
                        !fire[x][y] &&
                        grid[x][y] === 0
                    ) {
                        if (x === m - 1 && y === n - 1) {
                            return true;
                        }
                        vis[x][y] = true;
                        q2.push([x, y]);
                    }
                }
            }
        }
        return false;
    };
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l === m * n ? 1e9 : l;
}
