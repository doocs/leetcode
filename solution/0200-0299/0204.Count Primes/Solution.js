// 600ms多
const countPrimes2 = function (n) {
  let arr = [];
  let res = 0;
  for (let i = 2; i < n; i++) {
    if (arr[i] === undefined) {
      arr[i] = 1;
      res++;
      for (let j = 2; i * j < n; j++) {
        arr[i * j] = 0;
      }
    }
  }
  return res;
};

//200ms多
const countPrimes = function (n) {
  let arr = [];
  let res = 0;
  for (let i = 2; i < n; i++) {
    if (arr[i] === undefined) {
      arr[i] = 1;
      res++;
      for (let j = i; i * j < n; j++) {
        arr[i * j] = 0;
      }
    }
  }
  return res;
};
