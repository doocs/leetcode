/**
 * @param {number[]} nums
 * @return {number}
 */
var arrayPairSum = function (nums) {
  nums.sort((a, b) => a - b);
  let res = 0;
  for (let i = 0, n = nums.length; i < n; i += 2) {
    res += nums[i];
  }
  return res;
};
