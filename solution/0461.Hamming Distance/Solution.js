const hammingDistance = function(x,y){
  let a = x ^ y;
  a = a.toString(2);
  let res = 0;
  for(let i = 0 ; i < a.length; i++){
    res += a[i] === '1';
  }
  return res;
}
