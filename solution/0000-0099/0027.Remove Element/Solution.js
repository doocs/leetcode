/**
 * @param {number[]} nums
 * @param {number} val
 * @return {number}
 */
var removeElement = function (nums, val) {
    let k = 0;
    for (const x of nums) {
        if (x !== val) {
            nums[k++] = x;
        }
    }
    return k;
};
