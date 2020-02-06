const isPowerOfFour = function(num) {
  return (Math.log(num) / Math.log(4)) % 1.0 == 0.0;
};
