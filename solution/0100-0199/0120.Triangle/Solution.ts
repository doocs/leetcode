function minimumTotal(triangle: number[][]): number {
    const n = triangle.length;
    for (let i = n - 2; i >= 0; i--) {
        for (let j = 0; j < i + 1; j++) {
            triangle[i][j] += Math.min(
                triangle[i + 1][j],
                triangle[i + 1][j + 1],
            );
        }
    }
    return triangle[0][0];
}
