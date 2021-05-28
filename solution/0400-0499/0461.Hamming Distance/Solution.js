/**
 * @param {number} x
 * @param {number} y
 * @return {number}
 */
 var hammingDistance = function(x, y) {
  let distance = x ^ y;
  let count = 0;
  while (distance != 0) {
      count++;
      distance &= (distance - 1);
  }
  return count;
};