function probabilityOfHeads(prob: number[], target: number): number {
    const n = prob.length;
    const f = new Array(n + 1).fill(0).map(() => new Array(target + 1).fill(0));
    f[0][0] = 1;
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j <= target; ++j) {
            f[i][j] = f[i - 1][j] * (1 - prob[i - 1]);
            if (j) {
                f[i][j] += f[i - 1][j - 1] * prob[i - 1];
            }
        }
    }
    return f[n][target];
}
