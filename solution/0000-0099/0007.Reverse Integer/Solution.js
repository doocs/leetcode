/**
 * @param {number} x
 * @return {number}
 */
var reverse = function (x) {
  let res = 0;
  while (x) {
    res = res * 10 + (x % 10);
    x = ~~(x / 10);
  }
  return res < Math.pow(-2, 31) || res > Math.pow(2, 31) - 1 ? 0 : res;
};
