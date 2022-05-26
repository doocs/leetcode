const firstUniqChar2 = function (s) {
  let arr = {};
  for (let i = 0; i < s.length; i++) {
    // console.log(arr[s[i]]);
    if (arr[s[i]]) {
      arr[s[i]]++;
    } else {
      arr[s[i]] = 1;
    }
  }
  let keys = Object.keys(arr);
  for (let i = 0; i < keys.length; i++) {
    if (arr[keys[i]] !== 1) {
      keys.splice(i, 1);
      i--;
    }
  }
  for (let i = 0; i < s.length; i++) {
    for (let j = 0; j < keys.length; j++) {
      if (s[i] === keys[j]) {
        return i;
      }
    }
  }
  return -1;
};

const firstUniqChar = function (s) {
  let hashTable = {};
  let arr = [];

  for (let i = 0; i < s.length; i++) {
    let c = s.charAt(i);
    if (!hashTable.hasOwnProperty(c)) {
      hashTable[c] = i;
      arr.push(i);
    } else {
      if (hashTable[c] !== null) {
        let val = hashTable[c];
        let index = arr.indexOf(val);
        arr.splice(index, 1);
        hashTable[c] = null;
      }
    }
  }

  return arr.length ? arr[0] : -1;
};
