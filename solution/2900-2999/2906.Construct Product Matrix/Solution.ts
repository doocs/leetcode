function constructProductMatrix(grid: number[][]): number[][] {
    const mod = 12345;
    const [n, m] = [grid.length, grid[0].length];
    const p: number[][] = Array.from({ length: n }, () => Array.from({ length: m }, () => 0));
    let suf = 1;
    for (let i = n - 1; ~i; --i) {
        for (let j = m - 1; ~j; --j) {
            p[i][j] = suf;
            suf = (suf * grid[i][j]) % mod;
        }
    }
    let pre = 1;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < m; ++j) {
            p[i][j] = (p[i][j] * pre) % mod;
            pre = (pre * grid[i][j]) % mod;
        }
    }
    return p;
}
