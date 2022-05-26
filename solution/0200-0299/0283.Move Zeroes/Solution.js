/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var moveZeroes = function (nums) {
    let left = 0,
        n = nums.length;
    for (let right = 0; right < n; ++right) {
        if (nums[right]) {
            [nums[left], nums[right]] = [nums[right], nums[left]];
            ++left;
        }
    }
};
