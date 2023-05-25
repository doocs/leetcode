function shortestPathBinaryMatrix(grid: number[][]): number {
    if (grid[0][0]) {
        return -1;
    }
    const n = grid.length;
    grid[0][0] = 1;
    let q: number[][] = [[0, 0]];
    for (let ans = 1; q.length > 0; ++ans) {
        const nq: number[][] = [];
        for (const [i, j] of q) {
            if (i === n - 1 && j === n - 1) {
                return ans;
            }
            for (let x = i - 1; x <= i + 1; ++x) {
                for (let y = j - 1; y <= j + 1; ++y) {
                    if (x >= 0 && x < n && y >= 0 && y < n && !grid[x][y]) {
                        grid[x][y] = 1;
                        nq.push([x, y]);
                    }
                }
            }
        }
        q = nq;
    }
    return -1;
}
