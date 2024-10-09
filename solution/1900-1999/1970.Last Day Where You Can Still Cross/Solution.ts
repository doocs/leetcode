function latestDayToCross(row: number, col: number, cells: number[][]): number {
    let [l, r] = [1, cells.length];
    const check = (k: number): boolean => {
        const g: number[][] = Array.from({ length: row }, () => Array(col).fill(0));
        for (let i = 0; i < k; ++i) {
            const [x, y] = cells[i];
            g[x - 1][y - 1] = 1;
        }
        const q: number[][] = [];
        for (let j = 0; j < col; ++j) {
            if (g[0][j] === 0) {
                q.push([0, j]);
                g[0][j] = 1;
            }
        }
        const dirs: number[] = [-1, 0, 1, 0, -1];
        for (const [x, y] of q) {
            if (x === row - 1) {
                return true;
            }
            for (let i = 0; i < 4; ++i) {
                const nx = x + dirs[i];
                const ny = y + dirs[i + 1];
                if (nx >= 0 && nx < row && ny >= 0 && ny < col && g[nx][ny] === 0) {
                    q.push([nx, ny]);
                    g[nx][ny] = 1;
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
    return l;
}
