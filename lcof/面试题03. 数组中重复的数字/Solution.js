/**
 * @param {number[]} nums
 * @return {number}
 */
var findRepeatNumber = function (nums) {
    for (let i = 0; ; ++i) {
        while (nums[i] != i) {
            const j = nums[i];
            if (nums[j] == j) {
                return j;
            }
            [nums[i], nums[j]] = [nums[j], nums[i]];
        }
    }
};
