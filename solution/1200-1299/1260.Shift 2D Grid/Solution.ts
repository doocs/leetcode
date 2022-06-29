function shiftGrid(grid: number[][], k: number): number[][] {
    const m = grid.length,
        n = grid[0].length;
    const size = m * n;
    k = k % size;
    if (k == 0 || size <= 1) return grid;
    let arr = grid.flat();
    if (size <= 1) return grid;
    let ans = Array.from({ length: m }, v => new Array(n));
    for (let i = 0, j = size - k; i < size; i++) {
        ans[Math.floor(i / n)][i % n] = arr[j];
        j = j == size - 1 ? 0 : j + 1;
    }
    return ans;
}
