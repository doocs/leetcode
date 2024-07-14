function diagonalSort(mat: number[][]): number[][] {
    const [m, n] = [mat.length, mat[0].length];
    const g: number[][] = Array.from({ length: m + n }, () => []);
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            g[m - i + j].push(mat[i][j]);
        }
    }
    for (const e of g) {
        e.sort((a, b) => b - a);
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            mat[i][j] = g[m - i + j].pop()!;
        }
    }
    return mat;
}
