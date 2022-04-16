/**
 * @param {number[]} nums
 * @return {boolean}
 */
var isMonotonic = function (nums) {
    let incr = true;
    let decr = true;
    for (let i = 1; i < nums.length; ++i) {
        if (!incr && !decr) {
            return false;
        }
        if (nums[i] < nums[i - 1]) {
            incr = false;
        }
        if (nums[i] > nums[i - 1]) {
            decr = false;
        }
    }
    return incr || decr;
};
