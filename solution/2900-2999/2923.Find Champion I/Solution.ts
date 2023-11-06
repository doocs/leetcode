function findChampion(grid: number[][]): number {
    for (let i = 0, n = grid.length; ; ++i) {
        let cnt = 0;
        for (let j = 0; j < n; ++j) {
            if (i !== j && grid[i][j] === 1) {
                ++cnt;
            }
        }
        if (cnt === n - 1) {
            return i;
        }
    }
}
