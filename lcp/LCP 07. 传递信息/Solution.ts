function numWays(n: number, relation: number[][], k: number): number {
    const f: number[][] = Array.from({ length: k + 1 }, () => Array(n).fill(0));
    f[0][0] = 1;
    for (let i = 1; i <= k; ++i) {
        for (const [a, b] of relation) {
            f[i][b] += f[i - 1][a];
        }
    }
    return f[k][n - 1];
}
