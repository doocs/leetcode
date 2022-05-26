const longestCommonPrefix = function (strs) {
  if (strs.length === 0) return "";
  for (let j = 0; j < strs[0].length; j++) {
    for (let i = 0; i < strs.length; i++) {
      if (strs[0][j] !== strs[i][j]) {
        return strs[0].substring(0, j);
      }
    }
  }
  return strs[0];
};
