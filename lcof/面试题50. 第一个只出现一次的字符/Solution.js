/**
 * @param {string} s
 * @return {character}
 */
var firstUniqChar = function (s) {
    if (s.length == 0) return ' ';
    let counter = new Array(26).fill(0);
    for (let i = 0; i < s.length; ++i) {
        const index = s[i].charCodeAt() - 'a'.charCodeAt();
        ++counter[index];
    }
    for (let i = 0; i < s.length; ++i) {
        const index = s[i].charCodeAt() - 'a'.charCodeAt();
        if (counter[index] == 1) return s[i];
    }
    return ' ';
};
