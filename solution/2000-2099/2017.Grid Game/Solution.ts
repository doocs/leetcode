function gridGame(grid: number[][]): number {
    let ans = Number.MAX_SAFE_INTEGER;
    let s1 = grid[0].reduce((a, b) => a + b, 0);
    let s2 = 0;
    for (let j = 0; j < grid[0].length; ++j) {
        s1 -= grid[0][j];
        ans = Math.min(ans, Math.max(s1, s2));
        s2 += grid[1][j];
    }
    return ans;
}
