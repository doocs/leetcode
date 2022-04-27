/**
 * @param {number[]} nums
 * @return {boolean}
 */
var isMonotonic = function (nums) {
    let isIncr = false;
    let isDecr = false;
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i] < nums[i - 1]) {
            isIncr = true;
        }
        if (nums[i] > nums[i - 1]) {
            isDecr = true;
        }
        if (isIncr && isDecr) {
            return false;
        }
    }
    return true;
};
