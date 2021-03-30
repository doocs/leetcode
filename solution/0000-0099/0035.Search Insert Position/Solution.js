/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var searchInsert = function (nums, target) {
  let l = 0,
    h = nums.length;
  while (l <= h) {
    const m = (l + h) >>> 1;
    if (nums[m] == target) return m;
    if (nums[m] < target) l = m + 1;
    else h = m - 1;
  }
  return l;
};
