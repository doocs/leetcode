/**
 * @param {number[]} nums
 * @return {number}
 */
var removeDuplicates = function (nums) {
  let cnt = 0;
  const n = nums.length;
  for (let i = 1; i < n; ++i) {
    if (nums[i] == nums[i - 1]) ++cnt;
    else nums[i - cnt] = nums[i];
  }
  return n - cnt;
};
