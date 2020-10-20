/**
 * @param {number} a
 * @param {number} b
 * @return {number}
 */
var add = function (a, b) {
  if (a === 0) return b;
  return add((a & b) << 1, a ^ b);
};
// (a & b) << 1 是 进位和
// a ^ b 是不进位和
// 两者相加得结果，由于本题禁止 + 号，所以递归
