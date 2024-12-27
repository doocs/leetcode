/**
 * @param {string} s
 * @return {number}
 */
var lengthOfLongestSubstring = function (s) {
    let ans = 0;
    const n = s.length;
    const cnt = new Map();
    for (let l = 0, r = 0; r < n; ++r) {
        cnt.set(s[r], (cnt.get(s[r]) || 0) + 1);
        while (cnt.get(s[r]) > 1) {
            cnt.set(s[l], cnt.get(s[l]) - 1);
            ++l;
        }
        ans = Math.max(ans, r - l + 1);
    }
    return ans;
};
