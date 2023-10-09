function maxSideLength(mat: number[][], threshold: number): number {
    const m = mat.length;
    const n = mat[0].length;
    const s: number[][] = Array(m + 1)
        .fill(0)
        .map(() => Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + mat[i - 1][j - 1];
        }
    }
    const check = (k: number): boolean => {
        for (let i = 0; i < m - k + 1; ++i) {
            for (let j = 0; j < n - k + 1; ++j) {
                if (s[i + k][j + k] - s[i + k][j] - s[i][j + k] + s[i][j] <= threshold) {
                    return true;
                }
            }
        }
        return false;
    };

    let l = 0;
    let r = Math.min(m, n);
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
}
