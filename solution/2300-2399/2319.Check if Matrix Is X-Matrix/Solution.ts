function checkXMatrix(grid: number[][]): boolean {
    const n = grid.length;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            if (i == j || i + j == n - 1) {
                if (!grid[i][j]) {
                    return false;
                }
            } else if (grid[i][j]) {
                return false;
            }
        }
    }
    return true;
}
