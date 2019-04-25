var removeElement3 = function (nums, val) {
  let len = nums.length
  if (len < 1) {
    return 0
  }

  let i = 0
  while (i < len) {
    if (nums[i] === val) {
      nums[i] = nums[len - 1]
      len--
    } else {
      i++
    }
  }
  return len
};

var removeElement2 = function (nums, val) {
  let i = 0
  for (let j = 0; j < nums.length; j++) {
    if (nums[j] !== val) {
      nums[i] = nums[j]
      i++
    }
  }
  return i
}

var removeElement = function (nums, val) {
  let len = nums.length;
  for (let i = 0; i < len; i++) {
    if (nums[i] === val) {
      nums.splice(i, 1);
      i--;
    }
  }
  return len;
}