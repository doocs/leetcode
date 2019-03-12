/**
 * @param {number[][]} A
 * @return {number[][]}
 */

/**
 *  Author: Mcnwork2018
 */
 
var transpose = function (A) {
  if ( A.length === 1 && A[0].length === 1 ) return A;
  let tran_matrix = [];
  for ( let i = 0; i < A[0].length; ++i ) {
    tran_matrix[i] = [];
    for ( let j = 0; j < A.length; ++j ) {
      tran_matrix[i][j] = A[j][i];
    }
  }
  return tran_matrix;
};