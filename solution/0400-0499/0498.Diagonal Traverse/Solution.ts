function findDiagonalOrder(mat: number[][]): number[] {
    const res = [];
    const m = mat.length;
    const n = mat[0].length;
    let i = 0;
    let j = 0;
    let mark = true;
    while (res.length !== n * m) {
        if (mark) {
            while (i >= 0 && j < n) {
                res.push(mat[i][j]);
                i--;
                j++;
            }
            if (j === n) {
                j--;
                i++;
            }
            i++;
        } else {
            while (i < m && j >= 0) {
                res.push(mat[i][j]);
                i++;
                j--;
            }
            if (i === m) {
                i--;
                j++;
            }
            j++;
        }
        mark = !mark;
    }
    return res;
}
