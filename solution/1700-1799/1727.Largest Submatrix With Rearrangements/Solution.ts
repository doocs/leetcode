function largestSubmatrix(matrix: number[][]): number {
    const m: number = matrix.length;
    const n: number = matrix[0].length;

    for (let i: number = 1; i < m; ++i) {
        for (let j: number = 0; j < n; ++j) {
            if (matrix[i][j] !== 0) {
                matrix[i][j] = matrix[i - 1][j] + 1;
            }
        }
    }

    let ans: number = 0;

    for (const row of matrix) {
        row.sort((a, b) => b - a);
        for (let j: number = 0; j < n; ++j) {
            ans = Math.max(ans, (j + 1) * row[j]);
        }
    }

    return ans;
}
