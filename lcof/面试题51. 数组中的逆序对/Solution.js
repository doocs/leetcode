/**
 * @param {number[]} nums
 * @return {number}
 */
var reversePairs = function (nums) {
  if (!nums || nums.length < 2) return 0;
  let res = 0;
  function mergeSort(arr) {
    if (arr.length === 1) {
      return arr;
    }
    let mid = ~~(arr.length / 2);
    return merge(mergeSort(arr.slice(0, mid)), mergeSort(arr.slice(mid)));
  }
  function merge(a, b) {
    let r = [];
    let cnt = 0;
    while (a && b && a.length && b.length) {
      if (a[0] <= b[0]) {
        res += cnt;
        r.push(a.shift());
      } else {
        r.push(b.shift());
        cnt++;
      }
    }
    res += a.length * cnt;
    return r.concat(a, b);
  }
  mergeSort(nums);
  return res;
};
