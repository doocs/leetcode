function rotateGrid(grid: number[][], k: number): number[][] {
    const m = grid.length;
    const n = grid[0].length;
    const rotate = (p: number, k: number) => {
        const nums: number[] = [];
        for (let j = p; j < n - p - 1; ++j) {
            nums.push(grid[p][j]);
        }
        for (let i = p; i < m - p - 1; ++i) {
            nums.push(grid[i][n - p - 1]);
        }
        for (let j = n - p - 1; j > p; --j) {
            nums.push(grid[m - p - 1][j]);
        }
        for (let i = m - p - 1; i > p; --i) {
            nums.push(grid[i][p]);
        }
        const l = nums.length;
        k %= l;
        if (k === 0) {
            return;
        }
        for (let j = p; j < n - p - 1; ++j) {
            grid[p][j] = nums[k++ % l];
        }
        for (let i = p; i < m - p - 1; ++i) {
            grid[i][n - p - 1] = nums[k++ % l];
        }
        for (let j = n - p - 1; j > p; --j) {
            grid[m - p - 1][j] = nums[k++ % l];
        }
        for (let i = m - p - 1; i > p; --i) {
            grid[i][p] = nums[k++ % l];
        }
    };
    for (let p = 0; p < Math.min(m, n) >> 1; ++p) {
        rotate(p, k);
    }
    return grid;
}
