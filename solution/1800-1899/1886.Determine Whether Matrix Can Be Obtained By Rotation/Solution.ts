function findRotation(mat: number[][], target: number[][]): boolean {
    for (let k = 0; k < 4; k++) {
        rotate(mat);
        if (isEqual(mat, target)) {
            return true;
        }
    }
    return false;
}

function isEqual(A: number[][], B: number[][]) {
    const n = A.length;
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            if (A[i][j] !== B[i][j]) {
                return false;
            }
        }
    }
    return true;
}

function rotate(matrix: number[][]): void {
    const n = matrix.length;
    for (let i = 0; i < n >> 1; i++) {
        for (let j = 0; j < (n + 1) >> 1; j++) {
            [
                matrix[i][j],
                matrix[n - 1 - j][i],
                matrix[n - 1 - i][n - 1 - j],
                matrix[j][n - 1 - i],
            ] = [
                matrix[n - 1 - j][i],
                matrix[n - 1 - i][n - 1 - j],
                matrix[j][n - 1 - i],
                matrix[i][j],
            ];
        }
    }
}
