function minimumDeleteSum(s1: string, s2: string): number {
    const m = s1.length;
    const n = s2.length;
    const f = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        f[i][0] = f[i - 1][0] + s1[i - 1].charCodeAt(0);
    }
    for (let j = 1; j <= n; ++j) {
        f[0][j] = f[0][j - 1] + s2[j - 1].charCodeAt(0);
    }
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            if (s1[i - 1] === s2[j - 1]) {
                f[i][j] = f[i - 1][j - 1];
            } else {
                f[i][j] = Math.min(
                    f[i - 1][j] + s1[i - 1].charCodeAt(0),
                    f[i][j - 1] + s2[j - 1].charCodeAt(0),
                );
            }
        }
    }
    return f[m][n];
}
