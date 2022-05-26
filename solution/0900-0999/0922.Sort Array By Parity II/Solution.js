/**
 * @param {number[]} nums
 * @return {number[]}
 */
var sortArrayByParityII = function (nums) {
    for (let i = 0, j = 1; i < nums.length; i += 2) {
        if ((nums[i] & 1) == 1) {
            while ((nums[j] & 1) == 1) {
                j += 2;
            }
            [nums[i], nums[j]] = [nums[j], nums[i]];
        }
    }
    return nums;
};
