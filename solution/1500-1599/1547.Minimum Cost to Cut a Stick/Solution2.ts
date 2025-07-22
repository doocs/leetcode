function minCost(n: number, cuts: number[]): number {
    cuts.push(0);
    cuts.push(n);
    cuts.sort((a, b) => a - b);
    const m = cuts.length;
    const f: number[][] = Array.from({ length: m }, () => Array(m).fill(0));
    for (let i = m - 2; i >= 0; --i) {
        for (let j = i + 2; j < m; ++j) {
            f[i][j] = 1 << 30;
            for (let k = i + 1; k < j; ++k) {
                f[i][j] = Math.min(f[i][j], f[i][k] + f[k][j] + cuts[j] - cuts[i]);
            }
        }
    }
    return f[0][m - 1];
}
