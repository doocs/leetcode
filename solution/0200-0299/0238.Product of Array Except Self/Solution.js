/**
 * @param {number[]} nums
 * @return {number[]}
 */
var productExceptSelf = function (nums) {
  const n = nums.length;
  let output = new Array(n);
  for (let i = 0, left = 1; i < n; ++i) {
    output[i] = left;
    left *= nums[i];
  }
  for (let i = n - 1, right = 1; i >= 0; --i) {
    output[i] *= right;
    right *= nums[i];
  }
  return output;
};
