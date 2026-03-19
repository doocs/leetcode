function findRotation(mat: number[][], target: number[][]): boolean {
    const n = mat.length;
    let ok = 0b1111;

    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            if (mat[i][j] !== target[i][j]) {
                ok &= ~0b0001;
            }
            if (mat[j][n - 1 - i] !== target[i][j]) {
                ok &= ~0b0010;
            }
            if (mat[n - 1 - i][n - 1 - j] !== target[i][j]) {
                ok &= ~0b0100;
            }
            if (mat[n - 1 - j][i] !== target[i][j]) {
                ok &= ~0b1000;
            }
            if (ok === 0) {
                return false;
            }
        }
    }

    return ok !== 0;
}
