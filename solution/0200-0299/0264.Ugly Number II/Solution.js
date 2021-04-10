/**
 * @param {number} n
 * @return {number}
 */
var nthUglyNumber = function (n) {
  let dp = [1];
  let p2 = 0,
    p3 = 0,
    p5 = 0;
  for (let i = 1; i < n; ++i) {
    const next2 = dp[p2] * 2,
      next3 = dp[p3] * 3,
      next5 = dp[p5] * 5;
    dp[i] = Math.min(next2, Math.min(next3, next5));
    if (dp[i] == next2) ++p2;
    if (dp[i] == next3) ++p3;
    if (dp[i] == next5) ++p5;
    dp.push(dp[i]);
  }
  return dp[n - 1];
};
