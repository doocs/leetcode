function hasValidPath(grid: string[][]): boolean {
    const m = grid.length,
        n = grid[0].length;

    if ((m + n - 1) % 2 || grid[0][0] === ')' || grid[m - 1][n - 1] === '(') {
        return false;
    }

    const vis: boolean[][][] = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => Array(m + n).fill(false)),
    );
    const dirs = [1, 0, 1];

    const dfs = (i: number, j: number, k: number): boolean => {
        if (vis[i][j][k]) {
            return false;
        }

        vis[i][j][k] = true;
        k += grid[i][j] === '(' ? 1 : -1;

        if (k < 0 || k > m - i + n - j) {
            return false;
        }

        if (i === m - 1 && j === n - 1) {
            return k === 0;
        }

        for (let d = 0; d < 2; ++d) {
            const x = i + dirs[d],
                y = j + dirs[d + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && dfs(x, y, k)) {
                return true;
            }
        }

        return false;
    };

    return dfs(0, 0, 0);
}
