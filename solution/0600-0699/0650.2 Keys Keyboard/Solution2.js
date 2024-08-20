/**
 * @param {number} n
 * @return {number}
 */
var minSteps = function (n) {
    const dp = Array(n + 1).fill(1000);
    dp[1] = 0;

    for (let i = 2; i <= n; i++) {
        for (let j = 1, half = i / 2; j <= half; j++) {
            if (i % j === 0) {
                dp[i] = Math.min(dp[i], dp[j] + i / j);
            }
        }
    }

    return dp[n];
};
