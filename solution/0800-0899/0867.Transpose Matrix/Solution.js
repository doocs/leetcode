/**
 * @param {number[][]} matrix
 * @return {number[][]}
 */
var transpose = function (matrix) {
  const m = matrix.length,
    n = matrix[0].length;
  let res = [];
  for (let i = 0; i < n; ++i) {
    res[i] = [];
    for (let j = 0; j < m; ++j) {
      res[i][j] = matrix[j][i];
    }
  }
  return res;
};
