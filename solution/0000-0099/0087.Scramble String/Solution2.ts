function isScramble(s1: string, s2: string): boolean {
    const n = s1.length;
    const f = new Array(n)
        .fill(0)
        .map(() => new Array(n).fill(0).map(() => new Array(n + 1).fill(false)));
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            f[i][j][1] = s1[i] === s2[j];
        }
    }
    for (let k = 2; k <= n; ++k) {
        for (let i = 0; i <= n - k; ++i) {
            for (let j = 0; j <= n - k; ++j) {
                for (let h = 1; h < k; ++h) {
                    if (f[i][j][h] && f[i + h][j + h][k - h]) {
                        f[i][j][k] = true;
                        break;
                    }
                    if (f[i + h][j][k - h] && f[i][j + k - h][h]) {
                        f[i][j][k] = true;
                        break;
                    }
                }
            }
        }
    }
    return f[0][0][n];
}
