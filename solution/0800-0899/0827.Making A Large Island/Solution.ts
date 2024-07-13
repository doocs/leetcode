function largestIsland(grid: number[][]): number {
    const n = grid.length;
    const p = Array.from({ length: n }, () => Array(n).fill(0));
    const cnt = Array(n * n + 1).fill(0);
    const dirs = [-1, 0, 1, 0, -1];
    let root = 0;
    let ans = 0;

    const dfs = (i: number, j: number): number => {
        p[i][j] = root;
        cnt[root]++;
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] === 1 && p[x][y] === 0) {
                dfs(x, y);
            }
        }
        return cnt[root];
    };

    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] === 1 && p[i][j] === 0) {
                root++;
                ans = Math.max(ans, dfs(i, j));
            }
        }
    }

    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] === 0) {
                const s = new Set<number>();
                for (let k = 0; k < 4; ++k) {
                    const x = i + dirs[k];
                    const y = j + dirs[k + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n) {
                        s.add(p[x][y]);
                    }
                }
                let t = 1;
                for (const x of s) {
                    t += cnt[x];
                }
                ans = Math.max(ans, t);
            }
        }
    }
    return ans;
}
