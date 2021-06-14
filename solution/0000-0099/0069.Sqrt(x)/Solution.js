/**
 * @param {number} x
 * @return {number}
 */
 var mySqrt = function(x) {
  if (x == 0) {
      return 0;
  }
  let low = 1;
  let high = x;
  while (low < high) {
      const mid = low + ((high - low + 1) >> 1);
      if (x / mid >= mid) {
          low = mid;
      } else {
          high = mid - 1;
      }
  }
  return low;
};