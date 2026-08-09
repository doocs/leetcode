function maxArea(mat: number[][]): number {
    return Math.max(calc(mat), calc(transpose(mat)));
}

function calc(mat: number[][]): number {
    const m = mat.length;
    const n = mat[0].length;

    let f = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    let g = Array(m + 1).fill(0);
    let suf = Array(m + 1).fill(0);

    for (let i = m - 1; i > 0; i--) {
        for (let j = n - 1; j >= 0; j--) {
            if (mat[i][j]) {
                f[i][j] = Math.min(f[i + 1][j], f[i][j + 1], f[i + 1][j + 1]) + 1;
                g[i] = Math.max(g[i], f[i][j]);
            }
        }
        suf[i] = Math.max(suf[i + 1], g[i]);
    }

    f = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    g = Array(m + 1).fill(0);
    const pre = Array(m + 1).fill(0);

    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            if (mat[i - 1][j - 1]) {
                f[i][j] = Math.min(f[i - 1][j], f[i][j - 1], f[i - 1][j - 1]) + 1;
                g[i] = Math.max(g[i], f[i][j]);
            }
        }
        pre[i] = Math.max(pre[i - 1], g[i]);
    }

    let ans = 0;
    for (let i = 1; i < m; i++) {
        const t = Math.min(pre[i], suf[i]);
        ans = Math.max(ans, t * t);
    }
    return ans;
}

function transpose(mat: number[][]): number[][] {
    const m = mat.length;
    const n = mat[0].length;

    const ans = Array.from({ length: n }, () => Array(m).fill(0));

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            ans[j][i] = mat[i][j];
        }
    }
    return ans;
}
