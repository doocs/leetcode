function minimumTotal(triangle: number[][]): number {
    const n = triangle.length;
    const f: number[] = Array(n + 1).fill(0);
    for (let i = n - 1; i >= 0; --i) {
        for (let j = 0; j <= i; ++j) {
            f[j] = Math.min(f[j], f[j + 1]) + triangle[i][j];
        }
    }
    return f[0];
}
