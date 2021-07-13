function countNegatives(grid: number[][]): number {
    let m = grid.length, n = grid[0].length;
    let i = 0, j = n - 1;
    let ans = 0;
    while (i < m && j > -1) {
        let cur = grid[i][j];
        if (cur < 0) {
            j--;
            ans += (m - i);
        } else {
            i++;
        }
    }
    return ans;
};