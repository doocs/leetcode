/**
 * @param {string} s
 * @return {character}
 */
var firstUniqChar = function (s) {
    const cnt = new Array(26).fill(0);
    for (const c of s) {
        cnt[c.charCodeAt(0) - 97]++;
    }
    for (const c of s) {
        if (cnt[c.charCodeAt(0) - 97] === 1) {
            return c;
        }
    }
    return ' ';
};
