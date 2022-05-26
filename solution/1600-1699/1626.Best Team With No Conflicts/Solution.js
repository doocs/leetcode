/**
 * @param {number[]} scores
 * @param {number[]} ages
 * @return {number}
 */
var bestTeamScore = function (scores, ages) {
    const nums = ages.map((age, i) => [age, scores[i]]);
    nums.sort((a, b) => (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
    const n = nums.length;
    let dp = new Array(n);
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        dp[i] = nums[i][1];
        for (let j = 0; j < i; ++j) {
            if (nums[i][1] >= nums[j][1]) {
                dp[i] = Math.max(dp[i], dp[j] + nums[i][1]);
            }
        }
        ans = Math.max(ans, dp[i]);
    }
    return ans;
};
