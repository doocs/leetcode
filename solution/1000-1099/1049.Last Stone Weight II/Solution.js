/**
 * @param {number[]} stones
 * @return {number}
 */
var lastStoneWeightII = function (stones) {
    let s = 0;
    for (const v of stones) {
        s += v;
    }
    const m = stones.length;
    const n = s >> 1;
    const dp = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = 0; j <= n; ++j) {
            dp[i][j] = dp[i - 1][j];
            if (stones[i - 1] <= j) {
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - stones[i - 1]] + stones[i - 1]);
            }
        }
    }
    return s - dp[m][n] * 2;
};
