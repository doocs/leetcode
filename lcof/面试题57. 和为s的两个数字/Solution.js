/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function (nums, target) {
    for (let p = 0, q = nums.length; p < q; ) {
        const s = nums[p] + nums[q];
        if (s == target) {
            return [nums[p], nums[q]];
        }
        if (s < target) {
            ++p;
        } else {
            --q;
        }
    }
};
