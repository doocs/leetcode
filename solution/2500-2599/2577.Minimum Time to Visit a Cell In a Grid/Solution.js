function minimumTime(grid) {
    if (grid[0][1] > 1 && grid[1][0] > 1) return -1;

    const [m, n] = [grid.length, grid[0].length];
    const DIRS = [-1, 0, 1, 0, -1];
    const q = new MinPriorityQueue({ priority: ([x]) => x });
    const dist = Array.from({ length: m }, () => new Array(n).fill(Number.POSITIVE_INFINITY));
    dist[0][0] = 0;
    q.enqueue([0, 0, 0]);

    while (true) {
        const [t, i, j] = q.dequeue().element;
        if (i === m - 1 && j === n - 1) return t;

        for (let k = 0; k < 4; k++) {
            const [x, y] = [i + DIRS[k], j + DIRS[k + 1]];
            if (x < 0 || x >= m || y < 0 || y >= n) continue;

            let nt = t + 1;
            if (nt < grid[x][y]) {
                nt = grid[x][y] + ((grid[x][y] - nt) % 2);
            }
            if (nt < dist[x][y]) {
                dist[x][y] = nt;
                q.enqueue([nt, x, y]);
            }
        }
    }
}
