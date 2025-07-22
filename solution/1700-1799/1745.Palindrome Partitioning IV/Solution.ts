function checkPartitioning(s: string): boolean {
    const n = s.length;
    const f: boolean[][] = Array.from({ length: n }, () => Array(n).fill(true));
    for (let i = n - 1; i >= 0; --i) {
        for (let j = i + 1; j < n; ++j) {
            f[i][j] = s[i] === s[j] && f[i + 1][j - 1];
        }
    }
    for (let i = 0; i < n - 2; ++i) {
        for (let j = i + 1; j < n - 1; ++j) {
            if (f[0][i] && f[i + 1][j] && f[j + 1][n - 1]) {
                return true;
            }
        }
    }
    return false;
}
