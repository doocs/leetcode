function checkXMatrix(grid: number[][]): boolean {
    const m = grid.length,
        n = grid[0].length;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (j == i || j == n - 1 - i) {
                if (!grid[i][j]) return false;
            } else {
                if (grid[i][j]) return false;
            }
        }
    }
    return true;
}
