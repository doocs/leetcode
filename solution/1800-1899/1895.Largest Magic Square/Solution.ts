function largestMagicSquare(grid: number[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    const rowsum: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    const colsum: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));

    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            rowsum[i][j] = rowsum[i][j - 1] + grid[i - 1][j - 1];
            colsum[i][j] = colsum[i - 1][j] + grid[i - 1][j - 1];
        }
    }

    const check = (x1: number, y1: number, x2: number, y2: number): boolean => {
        const val = rowsum[x1 + 1][y2 + 1] - rowsum[x1 + 1][y1];
        for (let i = x1 + 1; i <= x2; ++i) {
            if (rowsum[i + 1][y2 + 1] - rowsum[i + 1][y1] !== val) {
                return false;
            }
        }
        for (let j = y1; j <= y2; ++j) {
            if (colsum[x2 + 1][j + 1] - colsum[x1][j + 1] !== val) {
                return false;
            }
        }
        let s = 0;
        for (let i = x1, j = y1; i <= x2; ++i, ++j) {
            s += grid[i][j];
        }
        if (s !== val) {
            return false;
        }
        s = 0;
        for (let i = x1, j = y2; i <= x2; ++i, --j) {
            s += grid[i][j];
        }
        if (s !== val) {
            return false;
        }
        return true;
    };

    for (let k = Math.min(m, n); k > 1; --k) {
        for (let i = 0; i + k - 1 < m; ++i) {
            for (let j = 0; j + k - 1 < n; ++j) {
                const i2 = i + k - 1,
                    j2 = j + k - 1;
                if (check(i, j, i2, j2)) {
                    return k;
                }
            }
        }
    }
    return 1;
}
