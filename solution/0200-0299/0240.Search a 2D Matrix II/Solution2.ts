function searchMatrix(matrix: number[][], target: number): boolean {
    let m = matrix.length,
        n = matrix[0].length;
    let i = m - 1,
        j = 0;
    while (i >= 0 && j < n) {
        let cur = matrix[i][j];
        if (cur == target) return true;
        if (cur > target) {
            --i;
        } else {
            ++j;
        }
    }
    return false;
}
