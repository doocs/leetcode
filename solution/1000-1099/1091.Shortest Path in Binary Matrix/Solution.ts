function shortestPathBinaryMatrix(grid: number[][]): number {
    if (grid[0][0]) {
        return -1;
    }
    const max = grid.length - 1;
    grid[0][0] = 1;
    let q: number[][] = [[0, 0]];
    for (let ans = 1; q.length > 0; ++ans) {
        const nq: number[][] = [];
        for (const [i, j] of q) {
            if (i === max && j === max) {
                return ans;
            }
            for (let x = i - 1; x <= i + 1; ++x) {
                for (let y = j - 1; y <= j + 1; ++y) {
                    if (grid[x]?.[y] === 0) {
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
