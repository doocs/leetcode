/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function (nums) {
    let dp = [-Infinity];
    for (let i = 0; i < nums.length; i++) {
        let cur = nums[i];
        dp[i + 1] = Math.max(dp[i] + cur, cur);
    }
    return Math.max(...dp);
};
