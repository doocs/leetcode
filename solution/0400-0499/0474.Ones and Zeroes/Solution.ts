function findMaxForm(strs: string[], m: number, n: number): number {
    const sz = strs.length;
    const f = Array.from({ length: sz + 1 }, () =>
        Array.from({ length: m + 1 }, () => Array.from({ length: n + 1 }, () => 0)),
    );
    const count = (s: string): [number, number] => {
        let a = 0;
        for (const c of s) {
            a += c === '0' ? 1 : 0;
        }
        return [a, s.length - a];
    };
    for (let i = 1; i <= sz; ++i) {
        const [a, b] = count(strs[i - 1]);
        for (let j = 0; j <= m; ++j) {
            for (let k = 0; k <= n; ++k) {
                f[i][j][k] = f[i - 1][j][k];
                if (j >= a && k >= b) {
                    f[i][j][k] = Math.max(f[i][j][k], f[i - 1][j - a][k - b] + 1);
                }
            }
        }
    }
    return f[sz][m][n];
}
