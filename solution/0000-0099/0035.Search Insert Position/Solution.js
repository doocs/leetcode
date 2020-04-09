var searchInsert2 = function (nums, target) {
  for (let i = 0; i < nums.length; i++) {
    if (nums[i] >= target) {
      return i;
    }
  }
  return nums.length;
}

var searchInsert = function (nums, target) {
  let left = 0;
  let right = nums.length - 1;

  if (target < nums[left]) {
    return 0;
  } else if (target > nums[right]) {
    return right + 1;
  }

  let count = 0;

  while (left < right) {
    if (left + 1 == right) {
      if (nums[left] < target) {
        return left + 1;
      } else {
        return left;
      }
    }

    let mid = Math.floor((left + right) / 2);
    if (nums[mid] < target) {
      left = mid;
    } else if (nums[mid] > target) {
      right = mid;
    } else {
      return mid;
    }
  }

  return left;
}