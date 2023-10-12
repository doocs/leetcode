function strangePrinter(s: string): number {
    const n = s.length;
    const f: number[][] = new Array(n).fill(0).map(() => new Array(n).fill(1 << 30));
    for (let i = n - 1; i >= 0; --i) {
        f[i][i] = 1;
        for (let j = i + 1; j < n; ++j) {
            if (s[i] === s[j]) {
                f[i][j] = f[i][j - 1];
            } else {
                for (let k = i; k < j; ++k) {
                    f[i][j] = Math.min(f[i][j], f[i][k] + f[k + 1][j]);
                }
            }
        }
    }
    return f[0][n - 1];
}
