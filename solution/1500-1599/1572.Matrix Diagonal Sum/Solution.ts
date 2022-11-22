function diagonalSum(mat: number[][]): number {
    const n = mat.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        ans += mat[i][i] + mat[i][n - 1 - i];
    }
    if (n & 1) {
        ans -= mat[n >> 1][n >> 1];
    }
    return ans;
}
