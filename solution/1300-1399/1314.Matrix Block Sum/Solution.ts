function matrixBlockSum(mat: number[][], k: number): number[][] {
    const m: number = mat.length;
    const n: number = mat[0].length;

    const s: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            s[i + 1][j + 1] = s[i][j + 1] + s[i + 1][j] - s[i][j] + mat[i][j];
        }
    }

    const ans: number[][] = Array.from({ length: m }, () => Array(n).fill(0));
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const x1: number = Math.max(i - k, 0);
            const y1: number = Math.max(j - k, 0);
            const x2: number = Math.min(m - 1, i + k);
            const y2: number = Math.min(n - 1, j + k);
            ans[i][j] = s[x2 + 1][y2 + 1] - s[x1][y2 + 1] - s[x2 + 1][y1] + s[x1][y1];
        }
    }

    return ans;
}
