/**
 * @param {number[]} nums
 * @return {number}
 */
var maximumDifference = function (nums) {
    let mi = 1 << 30;
    let ans = -1;
    for (const x of nums) {
        if (mi < x) {
            ans = Math.max(ans, x - mi);
        } else {
            mi = x;
        }
    }
    return ans;
};
