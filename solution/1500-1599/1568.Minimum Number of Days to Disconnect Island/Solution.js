/**
 * @param {number[][]} grid
 * @return {number}
 */
var minDays = function (grid) {
    const dirs = [-1, 0, 1, 0, -1];
    const [m, n] = [grid.length, grid[0].length];

    const dfs = (i, j, visited) => {
        if (i < 0 || m <= i || j < 0 || n <= j || grid[i][j] === 0 || visited[i][j]) {
            return;
        }

        visited[i][j] = true;
        for (let d = 0; d < 4; d++) {
            const [y, x] = [i + dirs[d], j + dirs[d + 1]];
            dfs(y, x, visited);
        }
    };

    const count = () => {
        const vis = Array.from({ length: m }, () => Array(n).fill(false));
        let c = 0;
        for (let i = 0; i < m; i++) {
            for (let j = 0; j < n; j++) {
                if (grid[i][j] === 1 && !vis[i][j]) {
                    c++;
                    dfs(i, j, vis);
                }
            }
        }
        return c;
    };

    if (count() !== 1) return 0;

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] === 1) {
                grid[i][j] = 0;
                if (count() !== 1) return 1;
                grid[i][j] = 1;
            }
        }
    }

    return 2;
};
