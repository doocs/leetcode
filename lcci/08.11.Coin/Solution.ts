function waysToChange(n: number): number {
    const mod = 10 ** 9 + 7;
    const coins: number[] = [25, 10, 5, 1];
    const f: number[][] = Array(5)
        .fill(0)
        .map(() => Array(n + 1).fill(0));
    f[0][0] = 1;
    for (let i = 1; i <= 4; ++i) {
        for (let j = 0; j <= n; ++j) {
            f[i][j] = f[i - 1][j];
            if (j >= coins[i - 1]) {
                f[i][j] = (f[i][j] + f[i][j - coins[i - 1]]) % mod;
            }
        }
    }
    return f[4][n];
}
