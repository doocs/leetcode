function minimumTotal(triangle: number[][]): number {
    for (let i = triangle.length - 2; ~i; --i) {
        for (let j = 0; j <= i; ++j) {
            triangle[i][j] += Math.min(triangle[i + 1][j], triangle[i + 1][j + 1]);
        }
    }
    return triangle[0][0];
}
