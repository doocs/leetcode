/**
 * @param {number[]} nums
 * @param {number} val
 * @return {number}
 */
var removeElement = function (nums, val) {
    let cnt = 0;
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        if (nums[i] == val) ++cnt;
        else nums[i - cnt] = nums[i];
    }
    return n - cnt;
};
