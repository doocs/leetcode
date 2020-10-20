/**
 * @param {string} s
 * @return {number}
 */
var lengthOfLongestSubstring = function (s) {
  let left = 0;
  let right = 0;
  let res = 0;
  let len = s.length;
  let rec = {};
  while (right < len) {
    let tmp = "*";
    while (right < len) {
      tmp = s[right];
      if (!rec[tmp]) rec[tmp] = 0;
      rec[tmp]++;
      if (rec[tmp] > 1) break;
      right++;
    }
    res = Math.max(res, right - left);
    while (rec[tmp] > 1) rec[s[left++]]--;
    right++;
  }
  return res;
};
