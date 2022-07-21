/**
 * @param {number} n
 * @return {number}
 */
var countVowelPermutation = function (n) {
    const mod = 1000000007;
    const dp = new Array(5).fill(1);
    const t = new Array(5).fill(0);
    for (let i = 0; i < n - 1; ++i) {
        t[0] = (dp[1] + dp[2] + dp[4]) % mod;
        t[1] = (dp[0] + dp[2]) % mod;
        t[2] = (dp[1] + dp[3]) % mod;
        t[3] = dp[2];
        t[4] = (dp[2] + dp[3]) % mod;
        dp.splice(0, 5, ...t);
    }
    let ans = 0;
    for (let i = 0; i < 5; ++i) {
        ans = (ans + dp[i]) % mod;
    }
    return ans;
};
