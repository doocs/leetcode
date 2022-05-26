const missingNumber = function (nums) {
  return (
    ((1 + nums.length) * nums.length) / 2 -
    nums.reduce((prev, cur) => (prev += cur), 0)
  );
};
