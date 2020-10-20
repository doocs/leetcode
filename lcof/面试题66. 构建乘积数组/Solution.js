/**
 * @param {number[]} a
 * @return {number[]}
 */
var constructArr = function (a) {
  let pre = new Array(a.length + 1).fill(1);
  pre[0] = 1;
  let res = new Array(a.length).fill(1);
  for (let i = 1; i <= a.length; i++) {
    pre[i] = a[i - 1] * pre[i - 1];
  }
  let cur = 1;
  for (let i = a.length - 1; i >= 0; i--) {
    res[i] = pre[i] * cur;
    cur *= a[i];
  }
  return res;
};
