function multiply(mat1: number[][], mat2: number[][]): number[][] {
    const [m, n] = [mat1.length, mat2[0].length];
    const ans: number[][] = Array.from({ length: m }, () => Array.from({ length: n }, () => 0));
    const f = (mat: number[][]): number[][][] => {
        const [m, n] = [mat.length, mat[0].length];
        const ans: number[][][] = Array.from({ length: m }, () => []);
        for (let i = 0; i < m; ++i) {
            for (let j = 0; j < n; ++j) {
                if (mat[i][j] !== 0) {
                    ans[i].push([j, mat[i][j]]);
                }
            }
        }
        return ans;
    };
    const g1 = f(mat1);
    const g2 = f(mat2);
    for (let i = 0; i < m; ++i) {
        for (const [k, x] of g1[i]) {
            for (const [j, y] of g2[k]) {
                ans[i][j] += x * y;
            }
        }
    }
    return ans;
}
