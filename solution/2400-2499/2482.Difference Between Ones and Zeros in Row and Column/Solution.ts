function onesMinusZeros(grid: number[][]): number[][] {
    const m = grid.length;
    const n = grid[0].length;
    const rows = new Array(m).fill(0);
    const cols = new Array(n).fill(0);
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j]) {
                rows[i]++;
                cols[j]++;
            }
        }
    }
    const ans = Array.from({ length: m }, () => new Array(n).fill(0));
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            ans[i][j] = rows[i] + cols[j] - (m - rows[i]) - (n - cols[j]);
        }
    }
    return ans;
}
