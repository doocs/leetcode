/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function (nums) {
    let res = nums[0];
    let f = nums[0];
    for (let i = 1; i < nums.length; ++i) {
        f = Math.max(f, 0) + nums[i];
        res = Math.max(res, f);
    }
    return res;
};
