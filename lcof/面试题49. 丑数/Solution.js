/**
 * @param {number} n
 * @return {number}
 */
var nthUglyNumber = function (n) {
  let res = [1];
  //三指针
  let a = 0; //2
  let b = 0; //3
  let c = 0; //5
  let min = 0;
  for (let i = 1; i < n; i++) {
    min = Math.min(res[a] * 2, res[b] * 3, res[c] * 5);
    if (min === res[a] * 2) a++;
    if (min === res[b] * 3) b++;
    if (min === res[c] * 5) c++;
    res.push(min);
  }
  return res[n - 1];
};
