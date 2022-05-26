/**
 * @param {string} s
 * @return {number}
 */
var lengthOfLongestSubstring = function (s) {
    let i = 0,
        j = 0,
        ans = 0;
    let chars = new Set();
    for (let c of s) {
        while (chars.has(c)) {
            chars.delete(s[i++]);
        }
        chars.add(c);
        ans = Math.max(ans, j - i + 1);
        ++j;
    }
    return ans;
};
