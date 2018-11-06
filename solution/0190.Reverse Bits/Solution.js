const reverseBits = function(n){
  return parseInt(n.toString(2).split('').reverse().join('').padEnd(32,'0'),2);
};