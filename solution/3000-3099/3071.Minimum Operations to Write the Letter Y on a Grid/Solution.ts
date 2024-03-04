function minimumOperationsToWriteY(grid: number[][]): number {
    const n = grid.length;
    const cnt1: number[] = Array(3).fill(0);
    const cnt2: number[] = Array(3).fill(0);
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            const a = i === j && i <= n >> 1;
            const b = i + j === n - 1 && i <= n >> 1;
            const c = j === n >> 1 && i >= n >> 1;
            if (a || b || c) {
                ++cnt1[grid[i][j]];
            } else {
                ++cnt2[grid[i][j]];
            }
        }
    }
    let ans = n * n;
    for (let i = 0; i < 3; ++i) {
        for (let j = 0; j < 3; ++j) {
            if (i !== j) {
                ans = Math.min(ans, n * n - cnt1[i] - cnt2[j]);
            }
        }
    }
    return ans;
}
