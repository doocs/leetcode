function paintingPlan(n: number, k: number): number {
    if (k === n * n) {
        return 1;
    }
    const c: number[][] = Array(n + 1)
        .fill(0)
        .map(() => Array(n + 1).fill(0));
    for (let i = 0; i <= n; ++i) {
        c[i][0] = 1;
        for (let j = 1; j <= i; ++j) {
            c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
        }
    }
    let ans = 0;
    for (let i = 0; i <= n; ++i) {
        for (let j = 0; j <= n; ++j) {
            if (n * (i + j) - i * j === k) {
                ans += c[n][i] * c[n][j];
            }
        }
    }
    return ans;
}
