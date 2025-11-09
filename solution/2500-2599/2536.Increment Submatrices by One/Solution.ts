function rangeAddQueries(n: number, queries: number[][]): number[][] {
    const mat: number[][] = Array.from({ length: n }, () => Array(n).fill(0));

    for (const [x1, y1, x2, y2] of queries) {
        mat[x1][y1] += 1;
        if (x2 + 1 < n) mat[x2 + 1][y1] -= 1;
        if (y2 + 1 < n) mat[x1][y2 + 1] -= 1;
        if (x2 + 1 < n && y2 + 1 < n) mat[x2 + 1][y2 + 1] += 1;
    }

    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            if (i > 0) mat[i][j] += mat[i - 1][j];
            if (j > 0) mat[i][j] += mat[i][j - 1];
            if (i > 0 && j > 0) mat[i][j] -= mat[i - 1][j - 1];
        }
    }

    return mat;
}
