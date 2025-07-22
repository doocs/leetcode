function minCost(n: number, cuts: number[]): number {
    cuts.push(0, n);
    cuts.sort((a, b) => a - b);
    const m = cuts.length;
    const f: number[][] = Array.from({ length: m }, () => Array(m).fill(0));
    for (let l = 2; l < m; l++) {
        for (let i = 0; i < m - l; i++) {
            const j = i + l;
            f[i][j] = Infinity;
            for (let k = i + 1; k < j; k++) {
                f[i][j] = Math.min(f[i][j], f[i][k] + f[k][j] + cuts[j] - cuts[i]);
            }
        }
    }
    return f[0][m - 1];
}
