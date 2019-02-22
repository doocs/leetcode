/**
 * @param {number[]} A
 * @return {number[]}
 */
// 第一次的做法
var sortArrayByParity = function (A) {
  const len = A.length;
  if (len == 1) return A;
  let evenNumber = [];
  let oddNumber = [];
  for (let i = 0; i < len; i++) {
    if (A[i] % 2 == 0) {
      evenNumber.push(A[i]);
    }
    if (A[i] % 2 != 0) {
      oddNumber.push(A[i]);
    }
  }
  return evenNumber.concat(oddNumber);
};
// 修改第一次的代码,只使用一个数组，减少一次合并数组操作
var sortArrayByParity = function (A) {
  const len = A.length;
  if (len == 1) return A;
  let eoNum = [];
  for (let i = 0; i < len; i++) {
    if (A[i] % 2 == 1) {
      eoNum.push(A[i]);
    }
    if (A[i] % 2 == 0) {
      eoNum.unshift(A[i]);
    }
  }
  return eoNum;
};
// 双指针做法,无需新数组，push和unshift操作，利用第三个变量进行交换
var sortArrayByParity = function (A) {
  const len = A.length;
  if (len == 1) return A;
  let i = 0,
    j = len - 1;
  while (i < j) {
    if ((A[i] % 2 == 1) && (A[j] % 2 == 0)) {
      let temp = A[j];
      A[j] = A[i];
      A[i] = temp;
      i++;
      j--;
    }
    if (A[i] % 2 == 0) i++;
    if (A[j] % 2 == 1) j--;
  }
  return A;
};