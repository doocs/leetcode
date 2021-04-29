/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var moveZeroes = function (nums) {
    if (!nums.length) return nums;
    let j = 0;
    for (i = 0; i < nums.length; i++) {
        if (nums[i]) {
            if (i > j) [nums[i], nums[j]] = [nums[j], nums[i]];
            j++;
        }
    }
    return nums;
};