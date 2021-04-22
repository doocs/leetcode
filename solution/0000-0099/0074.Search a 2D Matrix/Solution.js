/**
 * @param {number[][]} matrix
 * @param {number} target
 * @return {boolean}
 */
var searchMatrix = function (matrix, target) {
  const m = matrix.length;
  const n = matrix[0].length;
  let l = 0;
  let h = m * n - 1;
  while (l <= h) {
    const mid = (l + h) >>> 1;
    const x = Math.floor(mid / n);
    const y = mid % n;
    if (matrix[x][y] == target) {
      return true;
    }
    if (matrix[x][y] < target) {
      l = mid + 1;
    } else {
      h = mid - 1;
    }
  }
  return false;
};