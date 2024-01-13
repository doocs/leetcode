/**
 * @param {number[]} coins
 * @param {number} amount
 * @return {number}
 */
var coinChange = function (coins, amount) {
    const n = amount;
    const f = Array(n + 1).fill(1 << 30);
    f[0] = 0;
    for (const x of coins) {
        for (let j = x; j <= n; ++j) {
            f[j] = Math.min(f[j], f[j - x] + 1);
        }
    }
    return f[n] > n ? -1 : f[n];
};
