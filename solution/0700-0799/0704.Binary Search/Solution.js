/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var search = function (nums, target) {
    let [l, r] = [0, nums.length - 1];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] >= target) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return nums[l] === target ? l : -1;
};
