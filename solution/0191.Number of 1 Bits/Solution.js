const hammingWeight = function(n){
  let result = 0;
  while(n){
    result += n & 1;
    n = n >>> 1;
  }
  return result;
}