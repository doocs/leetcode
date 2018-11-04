const isAnagram = function(s,t){
  let a = {}, b = {};
  for(let i = 0 ; i < s.length; i++){
    if(a.hasOwnProperty(s[i])){
      a[s[i]]++;
    }else{
      a[s[i]] = 1;
    }
  }
  for(let i = 0 ; i < t.length; i++){
    if(b.hasOwnProperty(t[i])){
      b[t[i]]++;
    }else{
      b[t[i]] = 1;
    }
  }
  let keyA = Object.keys(a);
  let keyB = Object.keys(b);
  if(keyA.length !== keyB.length){
    return false;
  }
  for(let i = 0; i < keyA.length; i++){
    if(a[keyA[i]] !== b[keyA[i]]) return false;
  }
  return true;
}