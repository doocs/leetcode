function sortMatrix(grid: number[][]): number[][] {
    const n = grid.length;
    for (let k = n - 2; k >= 0; --k) {
        let [i, j] = [k, 0];
        const t: number[] = [];
        while (i < n && j < n) {
            t.push(grid[i++][j++]);
        }
        t.sort((a, b) => a - b);
        for (const x of t) {
            grid[--i][--j] = x;
        }
    }
    for (let k = n - 2; k > 0; --k) {
        let [i, j] = [k, n - 1];
        const t: number[] = [];
        while (i >= 0 && j >= 0) {
            t.push(grid[i--][j--]);
        }
        t.sort((a, b) => a - b);
        for (const x of t) {
            grid[++i][++j] = x;
        }
    }
    return grid;
}
