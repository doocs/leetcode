function maximumAmount(coins: number[][]): number {
    const [m, n] = [coins.length, coins[0].length];
    const f = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => Array(3).fill(-Infinity)),
    );
    const dfs = (i: number, j: number, k: number): number => {
        if (i >= m || j >= n) {
            return -Infinity;
        }
        if (f[i][j][k] !== -Infinity) {
            return f[i][j][k];
        }
        if (i === m - 1 && j === n - 1) {
            return k > 0 ? Math.max(0, coins[i][j]) : coins[i][j];
        }
        let ans = coins[i][j] + Math.max(dfs(i + 1, j, k), dfs(i, j + 1, k));
        if (coins[i][j] < 0 && k > 0) {
            ans = Math.max(ans, dfs(i + 1, j, k - 1), dfs(i, j + 1, k - 1));
        }
        return (f[i][j][k] = ans);
    };
    return dfs(0, 0, 2);
}
