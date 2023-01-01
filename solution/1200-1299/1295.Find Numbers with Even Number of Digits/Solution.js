/**
 * @param {number[]} nums
 * @return {number}
 */
var findNumbers = function (nums) {
    let ans = 0;
    for (const v of nums) {
        ans += String(v).length % 2 == 0;
    }
    return ans;
};
