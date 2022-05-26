/**
 * @param {number[]} nums
 * @return {number}
 */
var findMin = function (nums) {
    const n = nums.length;
    if (nums[0] <= nums[n - 1]) {
        return nums[0];
    }
    let left = 0,
        right = n - 1;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (nums[0] <= nums[mid]) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return nums[left];
};
