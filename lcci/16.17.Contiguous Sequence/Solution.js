/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function (nums) {
    let [ans, f] = [-Infinity, -Infinity];
    for (const x of nums) {
        f = Math.max(f, 0) + x;
        ans = Math.max(ans, f);
    }
    return ans;
};
