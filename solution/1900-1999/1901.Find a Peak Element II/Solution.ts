function findPeakGrid(mat: number[][]): number[] {
    let [l, r] = [0, mat.length - 1];
    while (l < r) {
        const mid = (l + r) >> 1;
        const j = mat[mid].indexOf(Math.max(...mat[mid]));
        if (mat[mid][j] > mat[mid + 1][j]) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return [l, mat[l].indexOf(Math.max(...mat[l]))];
}
