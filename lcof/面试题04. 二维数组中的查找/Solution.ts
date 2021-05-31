function findNumberIn2DArray(matrix: number[][], target: number): boolean {
    let m: number = matrix.length, n: number;
    if (!matrix || !m || !matrix[0] || !(n = matrix[0].length)) return false;
    let i: number = 0, j: number = n - 1;
    while (i < m && j >= 0) {
        let cur: number = matrix[i][j];
        if (cur == target) return true;
        if (cur > target) {
            j--;
        } else {
            i++;
        }
    }
    return false;
};