function highestRankedKItems(
    grid: number[][],
    pricing: number[],
    start: number[],
    k: number,
): number[][] {
    const [m, n] = [grid.length, grid[0].length];
    const [row, col] = start;
    const [low, high] = pricing;
    let q: [number, number][] = [[row, col]];
    const pq: [number, number, number, number][] = [];
    if (low <= grid[row][col] && grid[row][col] <= high) {
        pq.push([0, grid[row][col], row, col]);
    }
    grid[row][col] = 0;
    const dirs = [-1, 0, 1, 0, -1];
    for (let step = 1; q.length > 0; ++step) {
        const nq: [number, number][] = [];
        for (const [x, y] of q) {
            for (let j = 0; j < 4; j++) {
                const nx = x + dirs[j];
                const ny = y + dirs[j + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] > 0) {
                    if (low <= grid[nx][ny] && grid[nx][ny] <= high) {
                        pq.push([step, grid[nx][ny], nx, ny]);
                    }
                    grid[nx][ny] = 0;
                    nq.push([nx, ny]);
                }
            }
        }
        q = nq;
    }
    pq.sort((a, b) => {
        if (a[0] !== b[0]) return a[0] - b[0];
        if (a[1] !== b[1]) return a[1] - b[1];
        if (a[2] !== b[2]) return a[2] - b[2];
        return a[3] - b[3];
    });
    const ans: number[][] = [];
    for (let i = 0; i < Math.min(k, pq.length); i++) {
        ans.push([pq[i][2], pq[i][3]]);
    }
    return ans;
}
