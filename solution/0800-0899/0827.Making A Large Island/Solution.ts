function largestIsland(grid: number[][]): number {
    const n = grid.length;
    const vis = Array.from({ length: n }, () => new Array(n).fill(false));
    const group = Array.from({ length: n }, () => new Array(n).fill(0));
    const dfs = (i: number, j: number, paths: [number, number][]) => {
        if (
            i < 0 ||
            j < 0 ||
            i === n ||
            j === n ||
            vis[i][j] ||
            grid[i][j] !== 1
        ) {
            return;
        }
        vis[i][j] = true;
        paths.push([i, j]);
        dfs(i + 1, j, paths);
        dfs(i, j + 1, paths);
        dfs(i - 1, j, paths);
        dfs(i, j - 1, paths);
    };
    let count = 1;
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            const paths: [number, number][] = [];
            dfs(i, j, paths);
            if (paths.length !== 0) {
                for (const [x, y] of paths) {
                    group[x][y] = count;
                    grid[x][y] = paths.length;
                }
                count++;
            }
        }
    }

    let res = 0;
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            let sum = grid[i][j];
            if (grid[i][j] === 0) {
                sum++;
                const set = new Set();
                if (i !== 0) {
                    sum += grid[i - 1][j];
                    set.add(group[i - 1][j]);
                }
                if (i !== n - 1 && !set.has(group[i + 1][j])) {
                    sum += grid[i + 1][j];
                    set.add(group[i + 1][j]);
                }
                if (j !== 0 && !set.has(group[i][j - 1])) {
                    sum += grid[i][j - 1];
                    set.add(group[i][j - 1]);
                }
                if (j !== n - 1 && !set.has(group[i][j + 1])) {
                    sum += grid[i][j + 1];
                }
            }
            res = Math.max(res, sum);
        }
    }
    return res;
}
