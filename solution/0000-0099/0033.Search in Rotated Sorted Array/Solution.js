/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var search = function (nums, target) {
  for (let i = 0; i < nums.length; i++) {
    if (nums[i] == target) {
      return i;
    }
  }
  return -1;
};

//binary search

var search = function (nums, target) {
  let l = 0, r = nums.length - 1;
  if (l > r) return -1;
  while (l <= r) {
    let mid = l + Math.floor((r - l) / 2);
    if (nums[mid] === target) return mid;
    else if (nums[mid] <= nums[r] && target <= nums[r] && target >= nums[mid])
      l = mid + 1;
    else if (nums[mid] >= nums[l] && target <= nums[mid] && target >= nums[l])
      r = mid - 1;
    else if (nums[mid] >= nums[r])
      l = mid + 1;
    else if (nums[mid] <= nums[l])
      r = mid - 1;
    else return -1;
  }
  return -1;
};