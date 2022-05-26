/**
 * @param {number[]} nums
 * @return {number}
 */
var findMagicIndex = function (nums) {
    return helper(nums, 0, nums.length - 1);
};

function helper(nums, left, right) {
    if (left > right) return -1;
    let mid = Math.floor((left + right) / 2);
    let leftIndex = helper(nums, left, mid - 1);
    if (leftIndex != -1) return leftIndex;
    if (nums[mid] == mid) return mid;
    return helper(nums, mid + 1, right);
}
