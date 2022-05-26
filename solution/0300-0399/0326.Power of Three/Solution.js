const isPowerOfThree1 = function (n) {
  return n <= 2 ? n === 1 : isPowerOfThree(n / 3);
};
//44%;

const isPowerOfThree2 = function (n) {
  const Max3PowerInt = 1162261467;
  const MaxInt = 2147483647;
  return n <= 0 || n > Max3PowerInt ? false : Max3PowerInt % n === 0;
};
//96%;

const isPowerOfThree = function (n) {
  let t = Math.log10(n) / Math.log10(3);
  return Number.isInteger(t);
};
//22%
