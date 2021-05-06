/**
 *  Author: limbowandering
 */

const twoSum = function (nums, target) {
  const map = {};
  for (let i = 0; i < nums.length; i++) {
    if (map[nums[i]] !== undefined) {
      return [map[nums[i]], i];
    } else {
      map[target - nums[i]] = i;
    }
  }
};

/**
 *  Author: Mcnwork2018
 */

var twoSum = function (nums, target) {
  let len = nums.length;
  let n = {};
  for (let i = 0; i < len; i++) {
    if (n[target - nums[i]] !== undefined) {
      return [n[target - nums[i]], i];
    }
    n[nums[i]] = i;
  }
};

/**
 * Author: rookie
 */

var twoSum = function (nums, target) {
  const map = new Map();
  for (let i = 0; i < nums.length; i++) {
    if (map.has(target - nums[i])) {
      return [map.get(target - nums[i]), i];
    }
    map.set(nums[i], i);
  }
  return [];
};
