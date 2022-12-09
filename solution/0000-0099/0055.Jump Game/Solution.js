/**
 * @param {number[]} nums
 * @return {boolean}
 */
var canJump = function (nums) {
    let mx = 0;
    for (let i = 0; i < nums.length; ++i) {
        if (i > mx) {
            return false;
        }
        mx = Math.max(mx, i + nums[i]);
    }
    return true;
};
