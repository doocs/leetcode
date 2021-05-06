/**
 * @param {number[]} A
 * @return {number[]}
 */
var sortArrayByParity = function (A) {
  let i = 0;
  let j = A.length - 1;
  while (i < j) {
    if ((A[i] & 1) > (A[j] & 1)) {
      const t = A[i];
      A[i] = A[j];
      A[j] = t;
    }
    if ((A[i] & 1) == 0) {
      ++i;
    }
    if ((A[j] & 1) == 1) {
      --j;
    }
  }
  return A;
};
