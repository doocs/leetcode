/**
 * @param {number[]} nums
 * @return {number[]}
 */
var sortArrayByParity = function (nums) {
    for (let i = 0, j = nums.length - 1; i < j; ) {
        if (nums[i] & 1) {
            [nums[i], nums[j]] = [nums[j], nums[i]];
            --j;
        } else {
            ++i;
        }
    }
    return nums;
};
