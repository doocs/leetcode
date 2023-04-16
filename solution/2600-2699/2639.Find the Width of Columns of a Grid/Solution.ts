function findColumnWidth(grid: number[][]): number[] {
    const n = grid[0].length;
    const ans: number[] = new Array(n).fill(0);
    for (const row of grid) {
        for (let j = 0; j < n; ++j) {
            const w: number = String(row[j]).length;
            ans[j] = Math.max(ans[j], w);
        }
    }
    return ans;
}
