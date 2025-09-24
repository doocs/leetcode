function minimumTotal(triangle: number[][]): number {
    const n = triangle.length;
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(n + 1).fill(0));
    for (let i = n - 1; i >= 0; --i) {
        for (let j = 0; j <= i; ++j) {
            f[i][j] = Math.min(f[i + 1][j], f[i + 1][j + 1]) + triangle[i][j];
        }
    }
    return f[0][0];
}
