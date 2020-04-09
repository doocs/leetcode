/**
 * @param {number} x
 * @return {number}
 */

/** 
 *  Author: mcnwork2018
 */

var reverse = function(x) {
  let min = -Math.pow(2,31), max = Math.pow(2,31) - 1;
  let rev = 0;
  while (x != 0) {
    let pop = x % 10;
    x = (x - pop) / 10;
    if (rev > max / 10 || (rev == max / 10 && pop > 7)) return 0;
    if (rev < min / 10 || (rev == min / 10 && pop < -8)) return 0;
    rev = rev * 10 + pop;
  }
  return rev;
};

/** 
 *  Author: rookie
 */

var reverse = function (x) {
  const s = x + ""
  let i = 0
  let sign = 1
  if (s[i] == "-") {
    i++
    sign = -1
  }
  if (s[i] == "+") {
    i++
  }
  let num = 0
  for (let j = s.length - 1; j >= i; j--) {
    num = num * 10 + parseInt(s[j])
  }
  num *= sign
  let max = 2 
  for (let n = 0; n < 30; n++) {
    max *= 2
  }
  if (num > max || num < -max) {
    return 0
  }
  return num
};