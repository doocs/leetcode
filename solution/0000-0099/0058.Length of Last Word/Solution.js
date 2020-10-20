var lengthOfLastWord = function (s) {
  s = s.trim();
  return s.length - s.lastIndexOf(" ") - 1;
};

var lengthOfLastWord2 = function (s) {
  let res = 0;
  for (let i = 0; i < s.length; i++) {
    if (s[i] !== " " && (i === 0 || s[i - 1] === " ")) {
      res = 1;
    } else if (s[i] !== " ") {
      res++;
    }
  }
  return res;
};
