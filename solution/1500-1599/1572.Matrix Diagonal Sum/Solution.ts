function diagonalSum(mat: number[][]): number {
    let n = mat.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        ans += mat[i][i];
        let j = n - 1 - i;
        if (i != j) {
            ans += mat[i][j];
        }
    }
    return ans;
}
