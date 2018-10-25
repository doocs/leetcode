/**
 * @param {number} x
 * @return {number}
 */

/**
 *  First Way：将数字转化为字符串的处理
 */
var reverse = function(x) {
  const min = -Math.pow(2,31),max = Math.pow(2,31) - 1;
  let sign = 1;
  if(x < 0){
      sign = -sign;
      x = sign * x;
  }
  let a = x.toString();
  let len = a.length,b='';
  for(let i = len - 1;i >= 0;i--)b+=a[i];
  b = sign * Number(b);
  if(b > max || b < min) return 0;
  return b;
};
/** 
 *  Second Way: 弹出和推入数字
 */
let reverse = function(x) {
  let res = 0;
  while (x !== 0) {
      res = res * 10 + x % 10;
      x = x < 0 ? Math.ceil(x / 10) : Math.floor(x / 10);
  }
  return res < -(2**31) || res > 2**31 - 1 ? 0 : res;
};