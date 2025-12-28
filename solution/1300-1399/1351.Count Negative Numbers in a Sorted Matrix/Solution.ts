function countNegatives(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let i = m - 1;
    let j = 0;
    let ans = 0;
    while (i >= 0 && j < n) {
        if (grid[i][j] >= 0) {
            j++;
        } else {
            ans += n - j;
            i--;
        }
    }
    return ans;
}
