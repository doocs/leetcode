/**
 * @param {number[]} nums
 * @return {number}
 */
 var repeatedNTimes = function(nums) {
  const s = new Set();
  for (const num of nums) {
      if (s.has(num)) {
          return num;
      }
      s.add(num);
  }
  return -1;
};