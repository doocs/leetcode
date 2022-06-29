function minPathCost(grid: number[][], moveCost: number[][]): number {
    const m = grid.length,
        n = grid[0].length;
    let pre = grid[0].slice();
    for (let i = 1; i < m; i++) {
        let next = new Array(n);
        for (let j = 0; j < n; j++) {
            const key = grid[i - 1][j];
            for (let k = 0; k < n; k++) {
                let sum = pre[j] + moveCost[key][k] + grid[i][k];
                if (j == 0 || next[k] > sum) {
                    next[k] = sum;
                }
            }
        }
        pre = next;
    }
    return Math.min(...pre);
}
