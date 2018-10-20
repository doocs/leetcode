const reverse1 = function(x){
  let s = String(x);
  let isNegative = false;
  if(s[0] === '-'){
    isNegative = true;
  }
  s = parseInt(s.split('').reverse().join(''));
  return isNegative ? (s > Math.pow(2,31) ? 0 : -s) : (s > Math.pow(2,31) - 1 ? 0 : s);
}

const reverse = function(x){
  let result = parseInt(x.toString().split('').reverse().join(''));
  if(result > Math.pow(2,31) - 1 || -result < Math.pow(-2,31)) return 0;
  return x > 0 ? result: -result;
}