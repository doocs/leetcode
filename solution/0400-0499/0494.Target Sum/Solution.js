/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var findTargetSumWays = function (nums, target) {
    let s = 0;
    for (let v of nums) {
        s += v;
    }
    if (s < target || (s - target) % 2 != 0) {
        return 0;
    }
    const m = nums.length;
    const n = (s - target) / 2;
    let dp = new Array(n + 1).fill(0);
    dp[0] = 1;
    for (let i = 1; i <= m; ++i) {
        for (let j = n; j >= nums[i - 1]; --j) {
            dp[j] += dp[j - nums[i - 1]];
        }
    }
    return dp[n];
};
