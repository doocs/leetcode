/**
 * @param {number} n
 * @return {number[]}
 */
var dicesProbability = function (n) {
    const f = Array.from({ length: n + 1 }, () => Array(6 * n + 1).fill(0));
    for (let j = 1; j <= 6; ++j) {
        f[1][j] = 1;
    }
    for (let i = 2; i <= n; ++i) {
        for (let j = i; j <= 6 * i; ++j) {
            for (let k = 1; k <= 6; ++k) {
                if (j >= k) {
                    f[i][j] += f[i - 1][j - k];
                }
            }
        }
    }
    const ans = [];
    const m = Math.pow(6, n);
    for (let j = n; j <= 6 * n; ++j) {
        ans.push(f[n][j] / m);
    }
    return ans;
};
