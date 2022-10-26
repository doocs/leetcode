/**
 * @param {number[]} nums
 * @return {number}
 */
var arraySign = function (nums) {
    let ans = 1;
    for (const v of nums) {
        if (!v) {
            return 0;
        }
        if (v < 0) {
            ans *= -1;
        }
    }
    return ans;
};
