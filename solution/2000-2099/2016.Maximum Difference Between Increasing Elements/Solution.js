/**
 * @param {number[]} nums
 * @return {number}
 */
var maximumDifference = function (nums) {
    let [ans, mi] = [-1, Infinity];
    for (const x of nums) {
        if (x > mi) {
            ans = Math.max(ans, x - mi);
        } else {
            mi = x;
        }
    }
    return ans;
};
