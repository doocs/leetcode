function orangesRotting(grid: number[][]): number {
    const m: number = grid.length;
    const n: number = grid[0].length;
    const q: number[][] = [];
    let cnt: number = 0;
    for (let i: number = 0; i < m; ++i) {
        for (let j: number = 0; j < n; ++j) {
            if (grid[i][j] === 1) {
                cnt++;
            } else if (grid[i][j] === 2) {
                q.push([i, j]);
            }
        }
    }
    const dirs: number[] = [-1, 0, 1, 0, -1];
    for (let ans = 1; q.length && cnt; ++ans) {
        const t: number[][] = [];
        for (const [i, j] of q) {
            for (let d = 0; d < 4; ++d) {
                const [x, y] = [i + dirs[d], j + dirs[d + 1]];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] === 1) {
                    grid[x][y] = 2;
                    t.push([x, y]);
                    if (--cnt === 0) {
                        return ans;
                    }
                }
            }
        }
        q.splice(0, q.length, ...t);
    }
    return cnt > 0 ? -1 : 0;
}
