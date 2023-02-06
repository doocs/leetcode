/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function (nums, target) {
    let l = 0;
    let r = nums.length - 1;
    while (1) {
        if (nums[l] + nums[r] == target) {
            return [nums[l], nums[r]];
        }
        if (nums[l] + nums[r] > target) {
            --r;
        } else {
            ++l;
        }
    }
};
