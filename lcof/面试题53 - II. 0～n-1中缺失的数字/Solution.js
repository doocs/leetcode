/**
 * @param {number[]} nums
 * @return {number}
 */
var missingNumber = function (nums) {
    let l = 0;
    let r = nums.length;
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] > mid) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
};
