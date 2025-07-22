/**
 * @param {number[]} nums
 * @return {number}
 */
var maximumSumScore = function (nums) {
    let l = 0;
    let r = nums.reduce((a, b) => a + b, 0);
    let ans = -Infinity;
    for (const x of nums) {
        l += x;
        ans = Math.max(ans, l, r);
        r -= x;
    }
    return ans;
};
