/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function (nums) {
  let f = nums[0],
    res = nums[0];
  for (let i = 1; i < nums.length; ++i) {
    f = nums[i] + Math.max(f, 0);
    res = Math.max(res, f);
  }
  return res;
};
