function largestLocal(grid: number[][]): number[][] {
    const n = grid.length;
    const res = Array.from({ length: n - 2 }, () => new Array(n - 2).fill(0));
    for (let i = 0; i < n - 2; i++) {
        for (let j = 0; j < n - 2; j++) {
            let max = 0;
            for (let k = i; k < i + 3; k++) {
                for (let z = j; z < j + 3; z++) {
                    max = Math.max(max, grid[k][z]);
                }
            }
            res[i][j] = max;
        }
    }
    return res;
}
