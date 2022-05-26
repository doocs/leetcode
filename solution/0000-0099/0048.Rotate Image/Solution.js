/**
 * @param {number[][]} matrix
 * @return {void} Do not return anything, modify matrix in-place instead.
 */
const rotate1 = function (matrix) {
  // function swap(x,y){
  //   console.log(x,y);
  //   let z = x;
  //   x = y;
  //   y = z;
  // }
  for (let i = 0; i < matrix.length; i++) {
    for (let j = 0; j <= i; j++) {
      // swap(matrix[i][j],matrix[j][i]);
      // let t = matrix[i][j];
      // matrix[i][j] = matrix[j][i];
      // matrix[j][i] = t;
      [matrix[i][j], matrix[j][i]] = [matrix[j][i], matrix[i][j]];
    }
  }
  for (let i = 0, j = matrix.length - 1; i < j; i++, j--) {
    for (let k = 0; k < matrix.length; k++) {
      // swap(matrix[k][i], matrix[k][j]);
      // let t = matrix[k][i];
      // matrix[k][i] = matrix[k][j];
      // matrix[k][j] = t;
      [matrix[k][i], matrix[k][j]] = [matrix[k][j], matrix[k][i]];
    }
  }
};

const rotate = function (matrix) {
  matrix = matrix.reverse();
  for (let i = 0; i < matrix.length; i++) {
    for (let j = 0; j < i; j++) {
      [matrix[i][j], matrix[j][i]] = [matrix[j][i], matrix[i][j]];
    }
  }
};
