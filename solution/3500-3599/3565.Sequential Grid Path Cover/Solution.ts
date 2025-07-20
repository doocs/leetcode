function findPath(grid: number[][], k: number): number[][] {
    const m = grid.length;
    const n = grid[0].length;

    const dirs = [-1, 0, 1, 0, -1];
    const path: number[][] = [];
    let st = 0;

    function f(i: number, j: number): number {
        return i * n + j;
    }

    function dfs(i: number, j: number, v: number): boolean {
        path.push([i, j]);
        if (path.length === m * n) {
            return true;
        }

        st |= 1 << f(i, j);
        if (grid[i][j] === v) {
            v += 1;
        }

        for (let d = 0; d < 4; d++) {
            const x = i + dirs[d];
            const y = j + dirs[d + 1];
            const pos = f(x, y);
            if (
                x >= 0 &&
                x < m &&
                y >= 0 &&
                y < n &&
                (st & (1 << pos)) === 0 &&
                (grid[x][y] === 0 || grid[x][y] === v)
            ) {
                if (dfs(x, y, v)) {
                    return true;
                }
            }
        }

        path.pop();
        st ^= 1 << f(i, j);
        return false;
    }

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] === 0 || grid[i][j] === 1) {
                st = 0;
                path.length = 0;
                if (dfs(i, j, 1)) {
                    return path;
                }
            }
        }
    }

    return [];
}
