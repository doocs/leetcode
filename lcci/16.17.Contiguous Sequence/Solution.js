/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function (nums) {
    const inf = -Infinity;
    let s = inf;
    let ans = inf;
    for (const v of nums) {
        s = Math.max(s, 0) + v;
        ans = Math.max(ans, s);
    }
    return ans;
};
