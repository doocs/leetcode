/**
 * @param {number[]} arr
 * @return {number}
 */
 var findSpecialInteger = function(arr) {
  const n = arr.length;
  for (let i = 0; i < n; ++i) {
      if (arr[i] == arr[i + (n >> 2)]) {
          return arr[i];
      }
  }
  return 0;
};