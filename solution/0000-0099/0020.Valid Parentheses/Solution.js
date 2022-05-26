const isValid = function (s) {
  let arr = [];
  for (let i = 0; i < s.length; i++) {
    if (s[i] === "{" || s[i] === "[" || s[i] === "(") {
      arr.push(s[i]);
    } else {
      if (s[i] === ")" && arr[arr.length - 1] === "(") arr.pop();
      else if (s[i] === "]" && arr[arr.length - 1] === "[") arr.pop();
      else if (s[i] === "}" && arr[arr.length - 1] === "{") arr.pop();
      else return false;
    }
  }
  return arr.length === 0;
};
