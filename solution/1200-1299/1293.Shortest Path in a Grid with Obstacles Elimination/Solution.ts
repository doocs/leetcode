function shortestPath(grid: number[][], k: number): number {
    const m = grid.length;
    const n = grid[0].length;
    if (k >= m + n - 3) {
        return m + n - 2;
    }

    let q: Point[] = [[0, 0, k]];
    const vis = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => Array.from({ length: k + 1 }, () => false)),
    );
    vis[0][0][k] = true;
    const dirs = [0, 1, 0, -1, 0];
    let ans = 0;

    while (q.length) {
        const nextQ: Point[] = [];
        ++ans;

        for (const [i, j, k] of q) {
            for (let d = 0; d < 4; ++d) {
                const [x, y] = [i + dirs[d], j + dirs[d + 1]];
                if (x === m - 1 && y === n - 1) {
                    return ans;
                }
                const v = grid[x]?.[y];
                if (v === 0 && !vis[x][y][k]) {
                    nextQ.push([x, y, k]);
                    vis[x][y][k] = true;
                } else if (v === 1 && k > 0 && !vis[x][y][k - 1]) {
                    nextQ.push([x, y, k - 1]);
                    vis[x][y][k - 1] = true;
                }
            }
        }
        q = nextQ;
    }
    return -1;
}

type Point = [number, number, number];
