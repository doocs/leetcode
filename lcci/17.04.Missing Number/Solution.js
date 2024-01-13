/**
 * @param {number[]} nums
 * @return {number}
 */
var missingNumber = function (nums) {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        if (i != nums[i]) {
            return i;
        }
    }
    return n;
};
