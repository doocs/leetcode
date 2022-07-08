/**
 * @param {number[]} arr
 * @param {number} difference
 * @return {number}
 */
var longestSubsequence = function (arr, difference) {
    let ans = 1;
    const dp = new Map();
    for (const v of arr) {
        dp.set(v, (dp.get(v - difference) || 0) + 1);
        ans = Math.max(ans, dp.get(v));
    }
    return ans;
};
