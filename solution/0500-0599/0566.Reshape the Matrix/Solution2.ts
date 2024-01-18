function matrixReshape(mat: number[][], r: number, c: number): number[][] {
    const m = mat.length;
    const n = mat[0].length;
    if (m * n !== r * c) {
        return mat;
    }
    const ans = Array.from({ length: r }, () => new Array(c).fill(0));
    for (let i = 0; i < r * c; i++) {
        ans[Math.floor(i / c)][i % c] = mat[Math.floor(i / n)][i % n];
    }
    return ans;
}
