/**
 * @param {number[]} nums
 * @return {number[]}
 */
var singleNumbers = function (nums) {
  let xor = 0;
  let bit = 1;
  let res = [0, 0];
  for (let num of nums) {
    xor ^= num;
  }
  while ((xor & 1) === 0) {
    xor >>= 1;
    bit <<= 1;
  }
  for (let num of nums) {
    if ((num & bit) === 0) {
      res[0] ^= num;
    } else {
      res[1] ^= num;
    }
  }
  return res;
};
