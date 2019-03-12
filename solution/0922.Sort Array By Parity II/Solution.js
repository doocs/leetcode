/**
 * @param {number[]} A
 * @return {number[]}
 */

/** 
 * Author: Mcnwork2018
 */
 
var sortArrayByParityII = function(A) {
  let index = A.length - 1, i = 0, j = 1;   // index A的索引, i偶数位， j奇数位。 
  for ( ; i < index; i += 2 ) { 
    if ( (A[i] & 1) != 0 ) {                // 寻找A[i]是奇数的情况。
      while ( (A[j] & 1) != 0 ) {           // 寻找A[j]是偶数的情况。
        j += 2;
      }
      let temp = A[j];
      A[j] = A[i];
      A[i] = temp;
    }
  }
  return A;
};