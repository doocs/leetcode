function minScore(grid: number[][]): number[][] {
    const m = grid.length;
    const n = grid[0].length;
    const nums = [];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            nums.push([grid[i][j], i, j]);
        }
    }
    nums.sort((a, b) => a[0] - b[0]);
    const rowMax = new Array(m).fill(0);
    const colMax = new Array(n).fill(0);
    const ans = Array.from({ length: m }, _ => new Array(n));
    for (const [_, i, j] of nums) {
        ans[i][j] = Math.max(rowMax[i], colMax[j]) + 1;
        rowMax[i] = colMax[j] = ans[i][j];
    }
    return ans;
}
