function maximumSafenessFactor(grid: number[][]): number {
    const n = grid.length;
    const g = Array.from({ length: n }, () => new Array(n).fill(-1));
    const vis = Array.from({ length: n }, () => new Array(n).fill(false));
    let q: [number, number][] = [];
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] === 1) {
                q.push([i, j]);
            }
        }
    }
    let level = 0;
    while (q.length) {
        const t: [number, number][] = [];
        for (const [x, y] of q) {
            if (x < 0 || y < 0 || x === n || y === n || g[x][y] !== -1) {
                continue;
            }
            g[x][y] = level;
            t.push([x + 1, y]);
            t.push([x - 1, y]);
            t.push([x, y + 1]);
            t.push([x, y - 1]);
        }
        q = t;
        level++;
    }
    const dfs = (i: number, j: number, v: number) => {
        if (i < 0 || j < 0 || i === n || j === n || vis[i][j] || g[i][j] <= v) {
            return false;
        }
        vis[i][j] = true;
        return (
            (i === n - 1 && j === n - 1) ||
            dfs(i + 1, j, v) ||
            dfs(i, j + 1, v) ||
            dfs(i - 1, j, v) ||
            dfs(i, j - 1, v)
        );
    };

    let left = 0;
    let right = level;
    while (left < right) {
        vis.forEach(v => v.fill(false));
        const mid = (left + right) >>> 1;
        if (dfs(0, 0, mid)) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return right;
}
