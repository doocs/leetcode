function minCost(n: number, cuts: number[]): number {
    cuts.push(0);
    cuts.push(n);
    cuts.sort((a, b) => a - b);
    const m = cuts.length;
    const f: number[][] = new Array(m).fill(0).map(() => new Array(m).fill(0));
    for (let l = 2; l < m; ++l) {
        for (let i = 0; i + l < m; ++i) {
            const j = i + l;
            f[i][j] = 1 << 30;
            for (let k = i + 1; k < j; ++k) {
                f[i][j] = Math.min(f[i][j], f[i][k] + f[k][j] + cuts[j] - cuts[i]);
            }
        }
    }
    return f[0][m - 1];
}
