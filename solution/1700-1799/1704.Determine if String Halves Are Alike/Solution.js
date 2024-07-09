/**
 * @param {string} s
 * @return {boolean}
 */
var halvesAreAlike = function (s) {
    const vowels = new Set('aeiouAEIOU'.split(''));
    let cnt = 0;
    const n = s.length >> 1;
    for (let i = 0; i < n; ++i) {
        cnt += vowels.has(s[i]);
        cnt -= vowels.has(s[n + i]);
    }
    return cnt === 0;
};
