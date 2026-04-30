function findDegrees(matrix: number[][]): number[] {
    const n = matrix.length;
    const ans: number[] = Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        for (const x of matrix[i]) {
            ans[i] += x;
        }
    }
    return ans;
}
