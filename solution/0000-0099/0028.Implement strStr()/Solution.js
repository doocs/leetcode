/**
 * @param {string} haystack
 * @param {string} needle
 * @return {number}
 */
var strStr = function (haystack, needle) {
  const slen = haystack.length;
  const plen = needle.length;
  if (slen == plen) {
    return haystack == needle ? 0 : -1;
  }
  for (let i = 0; i <= slen - plen; i++) {
    let j;
    for (j = 0; j < plen; j++) {
      if (haystack[i + j] != needle[j]) {
        break;
      }
    }
    if (j == plen) return i;
  }
  return -1;
};
