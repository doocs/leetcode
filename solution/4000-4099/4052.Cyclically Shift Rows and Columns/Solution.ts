function cyclicShift(
    n: number,
    grid: number[][],
    rowShift: number[],
    colShift: number[],
): number[][] {
    const t = Array.from({ length: n }, () => Array(n).fill(0));
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            t[i][(j - rowShift[i] + n) % n] = grid[i][j];
        }
    }
    const ans = Array.from({ length: n }, () => Array(n).fill(0));
    for (let j = 0; j < n; j++) {
        for (let i = 0; i < n; i++) {
            ans[(i - colShift[j] + n) % n][j] = t[i][j];
        }
    }
    return ans;
}
