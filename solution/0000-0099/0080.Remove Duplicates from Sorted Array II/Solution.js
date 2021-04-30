/**
 * @param {number[]} nums
 * @return {number}
 */
var removeDuplicates = function (nums) {
    if (nums.length == 0) return 0;
    let len = nums.length;
    let j = 0;
    for (let i = 0; i < nums.length - 1; i++) {
        if (nums[i] != nums[i - 1] || nums[i] != nums[i + 1]) {
            nums[j++] = nums[i];
        }
    }
    nums[j] = nums[len - 1];
    return j + 1;
};