function numSubmat(mat: number[][]): number {
    const m = mat.length;
    const n = mat[0].length;
    const g: number[][] = Array.from({ length: m }, () => Array(n).fill(0));

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (mat[i][j]) {
                g[i][j] = j === 0 ? 1 : 1 + g[i][j - 1];
            }
        }
    }

    let ans = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            let col = Infinity;
            for (let k = i; k >= 0; k--) {
                col = Math.min(col, g[k][j]);
                ans += col;
            }
        }
    }

    return ans;
}
