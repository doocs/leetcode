function findSquare(matrix: number[][]): number[] {
    const n = matrix.length;
    const down: number[][] = new Array(n).fill(0).map(() => new Array(n).fill(0));
    const right: number[][] = new Array(n).fill(0).map(() => new Array(n).fill(0));
    for (let i = n - 1; i >= 0; --i) {
        for (let j = n - 1; j >= 0; --j) {
            if (matrix[i][j] === 0) {
                down[i][j] = i + 1 < n ? down[i + 1][j] + 1 : 1;
                right[i][j] = j + 1 < n ? right[i][j + 1] + 1 : 1;
            }
        }
    }
    for (let k = n; k > 0; --k) {
        for (let i = 0; i <= n - k; ++i) {
            for (let j = 0; j <= n - k; ++j) {
                if (
                    down[i][j] >= k &&
                    right[i][j] >= k &&
                    right[i + k - 1][j] >= k &&
                    down[i][j + k - 1] >= k
                ) {
                    return [i, j, k];
                }
            }
        }
    }
    return [];
}
