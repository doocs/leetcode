/**
 * @param {string} s
 * @return {boolean}
 */
var halvesAreAlike = function (s) {
    const str = 'aeiouAEIOU';
    let cnt = 0;
    for (let i = 0; i < s.length / 2; i++) {
        if (str.indexOf(s[i]) > -1) cnt++;
        if (str.indexOf(s[s.length - 1 - i]) > -1) cnt--;
    }
    return cnt === 0;
};
