/**
 * @param {number[]} stones
 * @return {number}
 */
var lastStoneWeightII = function (stones) {
    let s = 0;
    for (const v of stones) {
        s += v;
    }
    const n = s >> 1;
    const dp = Array(n + 1).fill(0);
    for (const v of stones) {
        for (let j = n; j >= v; --j) {
            dp[j] = Math.max(dp[j], dp[j - v] + v);
        }
    }
    return s - dp[n] * 2;
};
