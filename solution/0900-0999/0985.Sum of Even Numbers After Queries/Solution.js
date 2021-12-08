/**
 * @param {number[]} nums
 * @param {number[][]} queries
 * @return {number[]}
 */
 var sumEvenAfterQueries = function(nums, queries) {
  let s = 0;
  for (let num of nums) {
      if (num % 2 == 0) {
          s += num;
      }
  }
  let ans = [];
  for (let [v, i] of queries) {
      const old = nums[i];
      nums[i] += v;
      if (nums[i] % 2 == 0 && old % 2 == 0) {
          s += v;
      } else if (nums[i] % 2 == 0 && old % 2 != 0) {
          s += nums[i];
      } else if (old % 2 == 0) {
          s -= old;
      }
      ans.push(s);
  }
  return ans;
};