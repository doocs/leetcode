function waysToChange(n: number): number {
    const MOD = 10 ** 9 + 7;
    let coins = [1, 5, 10, 25];
    let dp = new Array(n + 1).fill(0);
    dp[0] = 1;
    for (let coin of coins) {
        for (let i = coin; i <= n; ++i) {
            dp[i] += dp[i - coin];
        }
    }
    return dp.pop() % MOD;
}
