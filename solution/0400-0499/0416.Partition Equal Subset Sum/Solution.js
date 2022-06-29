/**
 * @param {number[]} nums
 * @return {boolean}
 */
var canPartition = function (nums) {
    let s = 0;
    for (let v of nums) {
        s += v;
    }
    if (s % 2 != 0) {
        return false;
    }
    const m = nums.length;
    const n = s >> 1;
    const dp = new Array(n + 1).fill(false);
    dp[0] = true;
    for (let i = 1; i <= m; ++i) {
        for (let j = n; j >= nums[i - 1]; --j) {
            dp[j] = dp[j] || dp[j - nums[i - 1]];
        }
    }
    return dp[n];
};
