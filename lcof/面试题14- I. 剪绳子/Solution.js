/**
 * @param {number} n
 * @return {number}
 */
var cuttingRope = function (n) {
    // 数学方法
    if (n <= 3) return n - 1;
    let a = ~~(n / 3);
    let b = n % 3;
    if (b === 1) {
        return 3 ** (a - 1) * 2 * 2;
    }
    if (b === 0) return 3 ** a;
    return 3 ** a * b;
    // dp 方法
    // let dp = new Array(n+1).fill(0)
    // dp[0] = 1
    // for(let i=1;i<n;i++) {
    //     for(let j=i;j<=n;j++) {
    //         dp[j] = Math.max(dp[j],dp[j-i] * i)
    //     }
    // }
    // return dp[n]
};
