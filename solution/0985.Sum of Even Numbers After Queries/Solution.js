/**
 * @param {number[]} A
 * @param {number[][]} queries
 * @return {number[]}
 */

/**
 * Author: Mcnwork2018
 */

var sumEvenAfterQueries = function (A, queries) {
  const len = A.length;        // A数组的长度
  const qlen = queries.length; // queries数组的长度
  let answer = [];
  let S = 0;
  for ( let i = 0; i < len; i++ ) {
    if (A[i] % 2 == 0) {
      S += A[i];
    }
  }
  for ( let j = 0; j < qlen; j++ ) {
    let val = queries[j][0];
    let index = queries[j][1];
    if ( A[index] % 2 == 0 ) {
      S -= A[index];
    }
    A[index] += val;
    if ( A[index] % 2 == 0 ) {
      S += A[index]
    }
    answer.push(S);
  }
  return answer;
};