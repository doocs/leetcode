/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var moveZeroes = function (nums) {
    let i = -1;
    for (let j = 0; j < nums.length; ++j) {
        if (nums[j]) {
            const t = nums[++i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
};
